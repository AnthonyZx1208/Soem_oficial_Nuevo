package Seguridad;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public final class SeguridadAplicacion {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int ITERACIONES = 210000;
    private static final int BITS = 256;

    private SeguridadAplicacion() { }

    public static String hashPassword(String password) {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        byte[] hash = pbkdf2(password.toCharArray(), salt, ITERACIONES);
        return "pbkdf2$" + ITERACIONES + "$" + Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(hash);
    }

    public static boolean verificarPassword(String password, String almacenado) {
        if (password == null || almacenado == null) return false;
        // Nunca aceptar contraseñas heredadas en texto plano. Obliga a migrarlas
        // mediante un restablecimiento de contraseña antes de permitir el acceso.
        if (!almacenado.startsWith("pbkdf2$")) return false;
        String[] partes = almacenado.split("\\$");
        if (partes.length != 4) return false;
        try {
            byte[] esperado = Base64.getDecoder().decode(partes[3]);
            byte[] actual = pbkdf2(password.toCharArray(), Base64.getDecoder().decode(partes[2]), Integer.parseInt(partes[1]));
            return MessageDigest.isEqual(esperado, actual);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public static String csrf(HttpSession session) {
        String token = (String) session.getAttribute("csrfToken");
        if (token == null) {
            byte[] bytes = new byte[32];
            RANDOM.nextBytes(bytes);
            token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
            session.setAttribute("csrfToken", token);
        }
        return token;
    }

    public static boolean csrfValido(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String esperado = session == null ? null : (String) session.getAttribute("csrfToken");
        String recibido = request.getParameter("csrf");
        return esperado != null && recibido != null && MessageDigest.isEqual(esperado.getBytes(StandardCharsets.UTF_8), recibido.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iteraciones) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iteraciones, BITS);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception ex) {
            throw new IllegalStateException("No fue posible proteger la contraseña", ex);
        }
    }
}
