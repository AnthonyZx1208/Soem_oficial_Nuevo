package Seguridad;

import Controlador.TiendaDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Libera cada hora el stock de órdenes cuya reserva de 24 horas venció sin
 * que un administrador la haya aprobado o rechazado. Reutiliza
 * TiendaDAO.actualizarEstado(), que ya restaura el stock reservado al
 * marcar una orden como "Rechazado" (Controlador.TiendaDAO).
 */
@WebListener
public class LiberacionStockListener implements ServletContextListener {

    private static final String MOTIVO = "Pedido liberado automáticamente por vencimiento del plazo de pago";

    private ScheduledExecutorService executor;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "liberacion-stock");
            t.setDaemon(true);
            return t;
        });
        executor.scheduleAtFixedRate(this::liberarVencidas, 1, 1, TimeUnit.HOURS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (executor != null) executor.shutdownNow();
    }

    /**
     * Cualquier excepción no controlada aquí cancelaría silenciosamente
     * todas las ejecuciones futuras de scheduleAtFixedRate, así que se
     * captura Throwable a nivel de tarea completa, además del try/catch
     * por orden individual.
     */
    private void liberarVencidas() {
        try {
            TiendaDAO dao = new TiendaDAO();
            for (int ordenId : dao.ordenesConReservaVencida()) {
                try {
                    dao.actualizarEstado(ordenId, "Rechazado", MOTIVO);
                } catch (Exception ex) {
                    System.err.println("No fue posible liberar automáticamente la orden " + ordenId + ": " + ex.getMessage());
                }
            }
        } catch (Throwable ex) {
            System.err.println("Fallo inesperado en la liberación automática de stock: " + ex.getMessage());
        }
    }
}
