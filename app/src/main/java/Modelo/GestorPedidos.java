package Modelo;

import java.util.ArrayList;
import java.util.List;

import EstrategiaEntrega.EstrategiaEntrega;
import Observador.NotificadorArea;
import Observador.NotificadorCliente;

public class GestorPedidos {
    private final List<PedidoCompra> pedidos = new ArrayList<>();

    public void crearPedido(Cliente cliente, Vehiculo vehiculo, EstrategiaEntrega estrategiaEntrega,
            FormaPago formaPago) {
        PedidoCompra pedido = new PedidoCompra((pedidos.size()), cliente, vehiculo, estrategiaEntrega, formaPago);
        NotificadorCliente nc = new NotificadorCliente(cliente);
        pedido.agregarObservador(nc);
        NotificadorArea na = new NotificadorArea();
        pedido.agregarObservador(na);
    }

    public void cambiarEstado(PedidoCompra pedido, String estado) {
        pedido.cambiarEstado(estado);
    }
}