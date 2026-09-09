/*
 * SOEM Oficial - Utilidad de Seguridad con Tokens JWT
 * Gestiona la encriptación de datos sensibles en el tráfico
 */
package Controlador;

import java.util.*;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Clase para manejo de tokens y encriptación de datos sensibles
 * Implementa AES para encriptación de datos en tránsito
 * @author SOEM Development Team
 */
public class TokenSeguridad {

    private static final String ALGORITMO = "AES";
    private static final int TAMANIO_CLAVE = 256;
    private static final String CLAVE_SECRETA = "SoEmOficialClave256BitSegura2024"; // 32 caracteres = 256 bits

    /**
     * Encripta una cadena de texto usando AES
     * @param texto Texto a encriptar
     * @return Texto encriptado en Base64
     */
    public static String encriptar(String texto) {
        try {
            SecretKey clave = generarClave();
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            byte[] bytesEncriptados = cipher.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(bytesEncriptados);
        } catch (Exception e) {
            System.out.println("Error al encriptar: " + e.getMessage());
            return null;
        }
    }

    /**
     * Desencripta una cadena de texto
     * @param textoEncriptado Texto encriptado en Base64
     * @return Texto desencriptado
     */
    public static String desencriptar(String textoEncriptado) {
        try {
            SecretKey clave = generarClave();
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, clave);
            byte[] bytesDesencriptados = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));
            return new String(bytesDesencriptados);
        } catch (Exception e) {
            System.out.println("Error al desencriptar: " + e.getMessage());
            return null;
        }
    }

    /**
     * Genera un token único para sesiones
     * @return Token único de sesión
     */
    public static String generarTokenSesion() {
        return UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
    }

    /**
     * Genera un token con información encriptada
     * @param usuarioId ID del usuario
     * @param correo Correo del usuario
     * @return Token encriptado
     */
    public static String generarTokenUsuario(int usuarioId, String correo) {
        String datos = usuarioId + "|" + correo + "|" + System.currentTimeMillis();
        return encriptar(datos);
    }

    /**
     * Valida y extrae información del token
     * @param token Token a validar
     * @return Array con [usuarioId, correo, timestamp]
     */
    public static String[] validarTokenUsuario(String token) {
        try {
            String datos = desencriptar(token);
            String[] partes = datos.split("\\|");
            if (partes.length == 3) {
                long timestamp = Long.parseLong(partes[2]);
                long ahora = System.currentTimeMillis();
                // Validar que el token no tenga más de 24 horas
                if ((ahora - timestamp) < (24 * 60 * 60 * 1000)) {
                    return partes;
                }
            }
        } catch (Exception e) {
            System.out.println("Error validando token: " + e.getMessage());
        }
        return null;
    }

    /**
     * Encripta datos de pago
     * @param numeroCuenta Número de cuenta/tarjeta
     * @return Datos encriptados
     */
    public static String encriptarDatosPago(String numeroCuenta) {
        return encriptar(numeroCuenta);
    }

    /**
     * Desencripta datos de pago
     * @param datosPago Datos encriptados
     * @return Número de cuenta desencriptado
     */
    public static String desencriptarDatosPago(String datosPago) {
        return desencriptar(datosPago);
    }

    /**
     * Genera una clave secreta AES
     * @return SecretKey para AES
     */
    private static SecretKey generarClave() {
        try {
            byte[] bytesDecodificados = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(CLAVE_SECRETA.getBytes()));
            return new SecretKeySpec(bytesDecodificados, 0, bytesDecodificados.length, ALGORITMO);
        } catch (Exception e) {
            System.out.println("Error generando clave: " + e.getMessage());
            return null;
        }
    }

    /**
     * Hash para contraseñas (simple, se recomienda bcrypt en producción)
     * @param contrasena Contraseña a hashear
     * @return Hash de la contraseña
     */
    public static String hashearContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytesHash = md.digest(contrasena.getBytes());
            return Base64.getEncoder().encodeToString(bytesHash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hasheando contraseña: " + e.getMessage());
            return null;
        }
    }

    /**
     * Valida una contraseña contra su hash
     * @param contrasena Contraseña a validar
     * @param hash Hash almacenado
     * @return true si coincide, false en caso contrario
     */
    public static boolean validarContrasena(String contrasena, String hash) {
        String contraseniaHasheada = hashearContrasena(contrasena);
        return contraseniaHasheada != null && contraseniaHasheada.equals(hash);
    }

    /**
     * Genera un código QR de pago (representación)
     * @param monto Monto a pagar
     * @param referencia Referencia de pago
     * @return String con datos para el QR
     */
    public static String generarDatosQR(double monto, String referencia) {
        return "MONTO:" + monto + "|REF:" + referencia + "|CUENTA:NEqui|FECHA:" + new Date();
    }
}
