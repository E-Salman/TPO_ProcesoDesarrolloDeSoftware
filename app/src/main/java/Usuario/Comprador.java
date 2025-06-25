package Usuario;

import java.util.List;
import Modelo.PedidoCompra;

public class Comprador extends Usuario {
    public Comprador(String nombre, String email) {
        super(nombre, email);
    }

    public List<PedidoCompra> verPedidos(List<PedidoCompra> todosLosPedidos) {
        return todosLosPedidos.stream()
                .filter(p -> p.getCliente().getEmail().equalsIgnoreCase(email))
                .toList();
    }
}