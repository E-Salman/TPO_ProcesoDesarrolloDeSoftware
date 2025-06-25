import java.util.Observer;

public class NotificadorArea implements Observer {
    @Override
    public void actualizar(PedidoCompra pedido) {
        String mensaje = String.format(
                "ATENCIÓN ÁREAS: el pedido %s cambió a estado %s",
                pedido.getNumero(),
                pedido.getEstadoActual().getNombreEstado());
        enviarCorreoInterno("ventas@autoplus.com", mensaje);
        enviarCorreoInterno("logistica@autoplus.com", mensaje);
    }

    private void enviarCorreoInterno(String emailInt, String cuerpo) {
        System.out.printf("[Mail interno a %s] %s%n", emailInt, cuerpo);
    }
}
