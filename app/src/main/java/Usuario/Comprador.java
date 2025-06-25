package Usuario;

import java.util.List;

import Modelo.Cliente;
import Modelo.PedidoCompra;

public class Comprador extends Usuario {
    private Cliente cliente;
    public Comprador(String nombre, String email, Cliente cliente) {
        super(nombre, email);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<PedidoCompra> verPedidos(List<PedidoCompra> todosLosPedidos) {
        return todosLosPedidos.stream()
                .filter(p -> p.getCliente().getEmail().equalsIgnoreCase(email))
                .toList();
    }
}