package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import EstrategiaEntrega.EstrategiaEntrega;
import Observador.NotificadorArea;
import Observador.NotificadorCliente;
import Usuario.Cliente;
import Usuario.Usuario;
import Vehiculos.Vehiculo;
import FormaPago.FormaPago;

public class GestorPedidos {
    private final List<PedidoCompra> pedidos = new ArrayList<>();

    public void crearPedido(Cliente cliente, Vehiculo vehiculo, EstrategiaEntrega estrategiaEntrega,
            FormaPago formaPago, DatosFacturacion datosFacturacion, Usuario vendedor, boolean equipamientoExtra,
            boolean garantiaExtendida, boolean accesorios) {

        PedidoCompra pedido = new PedidoCompra((pedidos.size()), cliente, vehiculo, estrategiaEntrega, formaPago,
                datosFacturacion, vendedor, equipamientoExtra, garantiaExtendida, accesorios);

        NotificadorCliente nc = new NotificadorCliente(cliente);
        pedido.agregarObservador(nc);
        NotificadorArea na = new NotificadorArea();
        pedido.agregarObservador(na);
        pedidos.add(pedido);
    }

    public void cambiarEstado(PedidoCompra pedido, String estado) {
        pedido.cambiarEstado(estado);
    }

    public List<PedidoCompra> listarPedidos() {
        return Collections.unmodifiableList(pedidos);
    }

    public List<PedidoCompra> listarPedidosCliente(Cliente cliente) {
        List<PedidoCompra> listaPedidoComprador = new ArrayList<PedidoCompra>();
        for (PedidoCompra p : pedidos) {
            if (p.getCliente().equals(cliente)) {
                listaPedidoComprador.add(p);
            }
        }
        return listaPedidoComprador;
    }

    public Optional<PedidoCompra> buscarPedidoPorVehiculo(Vehiculo vehiculoElBuscado) {
        return pedidos.stream()
                .filter(p -> p.getVehiculo().getNumeroChasis()
                        .equals(vehiculoElBuscado.getNumeroChasis()))
                .findFirst();
    }
}
