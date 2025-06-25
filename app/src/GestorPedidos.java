import NotificadorCliente;
import NotificadorArea;

public class GestorPedidos {
    private final List<PedidoCompra> pedidos = new ArrayList<>();

    public void crearPedido(Cliente cliente, Vehiculo vehiculo, EstrategiaEntrega estrategiaEntrega,
            FormaPago formaPago) {
        PedidoCompra pedido = new PedidoCompra((pedidos.length()), cliente, vehiculo, estrategiaEntrega, formaPago);
        NotificadorCliente nc = new NotificadorCliente(cliente);
        pedido.agregarObservador(nc);
        NotificadorArea na = new NotificadorArea();
        pedido.agregarObservador(na);
    }

    public void cambiarEstado(PedidoCompra pedido, String estado) {
        pedido.getEstadoActual().cambiarEstado(estado);
    }
}