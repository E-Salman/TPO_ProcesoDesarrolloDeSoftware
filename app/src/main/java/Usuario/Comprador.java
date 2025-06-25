package Usuario;

import java.util.List;

import Modelo.PedidoCompra;

public class Comprador extends Usuario {

    public Comprador(String nombre, String email) {
        super(nombre, email);
    }

    public List<PedidoCompra> verPedidos() {
        System.out.println("Comprador: Mostrando pedidos...");
        return List.of();
    }
}