package Seguridad;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public final class Util {

    private Util() { }

    /**
     * Lee un parámetro directamente de la query string y lo decodifica como UTF-8,
     * sin pasar por request.getParameter(...): ese método usa la codificación por
     * defecto del contenedor para los parámetros de un GET (a veces ISO-8859-1),
     * lo que rompe tildes y ñ en búsquedas sin importar cómo se configure el filtro
     * de la aplicación (que solo afecta el body de un POST).
     */
    public static String parametroUtf8(HttpServletRequest request, String nombre) {
        String query = request.getQueryString();
        if (query == null) return null;
        for (String par : query.split("&")) {
            int igual = par.indexOf('=');
            String clave = URLDecoder.decode(igual < 0 ? par : par.substring(0, igual), StandardCharsets.UTF_8);
            if (nombre.equals(clave)) {
                return igual < 0 ? "" : URLDecoder.decode(par.substring(igual + 1), StandardCharsets.UTF_8);
            }
        }
        return null;
    }

    /** Pasa a minúsculas y quita tildes/diéresis, para comparar texto sin importar acentos ("pantalon" == "Pantalón"). */
    public static String normalizarBusqueda(String s) {
        if (s == null) return "";
        String sinTildes = Normalizer.normalize(s.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return sinTildes;
    }

    public static String escapeHtml(String s) {
        if (s == null) return "";
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '&': sb.append("&amp;"); break;
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                case '"': sb.append("&quot;"); break;
                case '\'': sb.append("&#39;"); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String escapeHtml(Object o) {
        return o == null ? "" : escapeHtml(String.valueOf(o));
    }
}
