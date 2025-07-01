package Observador;

import Modelo.PedidoCompra;
import Usuario.Cliente;

public class NotificadorCliente implements Observador {
    private final Cliente cliente;

    public NotificadorCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void actualizar(PedidoCompra pedido) {
        String mensaje = String.format(
                "Hola %s, tu pedido %d ahora está en estado: %s",
                cliente.getNombre(),
                pedido.getNumero(),
                pedido.getEstadoActual());
        enviarCorreo(cliente.getEmail(), mensaje);
    }

    private void enviarCorreo(String email, String cuerpo) {
        System.out.printf("[Mail a cliente %s] %s%n", email, cuerpo);
    }
}
