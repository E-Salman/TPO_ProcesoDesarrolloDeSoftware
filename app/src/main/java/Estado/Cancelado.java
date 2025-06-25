package Estado;

public class Cancelado implements EstadoPedido {

    public String estado() {
        return "Cancelado";
    }

    public EstadoPedido cambiar(String nEstado) {
        switch (nEstado.toLowerCase()) {
            case "reservado":
                return new Reservado();

            case "confirmado":
                return new Confirmado();

            default:
                break;
        }
        return null;
    }
}