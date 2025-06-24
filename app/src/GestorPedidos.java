public class GestorPedidos {
    
    public PedidoCompra crearPedido(Cliente cliente, Vehiculo vehiculo, FormaPago formaPago){
        return new PedidoCompra(cliente, vehiculo, formaPago);
    }

    public void cambiarEstado(PedidoCompra, );
}