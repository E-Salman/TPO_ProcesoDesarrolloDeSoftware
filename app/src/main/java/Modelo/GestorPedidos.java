package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import EstrategiaEntrega.EstrategiaEntrega;
import Observador.NotificadorArea;
import Observador.NotificadorCliente;
import Usuario.Comprador;
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

    public List<PedidoCompra> listarPedidosComprador(Comprador comprador){
        List<PedidoCompra> listaPedidoComprador = new ArrayList<PedidoCompra>();
        for (PedidoCompra p : pedidos){
            if (p.getCliente().equals(comprador.getCliente())){
                listaPedidoComprador.add(p);
            }
        }
        return listaPedidoComprador;
    }
}
