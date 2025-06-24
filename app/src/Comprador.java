package tpo_procesodesarrollodesoftware;

import java.util.List;

public class Comprador extends Usuario {

    public Comprador(String nombre, String email) {
        super(nombre, email);
    }

    public List<PedidoCompra> verPedidos() {
        System.out.println("Comprador: Mostrando pedidos...");
        return List.of();
    }
}