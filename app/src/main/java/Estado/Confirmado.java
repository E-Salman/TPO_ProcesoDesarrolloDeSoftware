package Estado;

public class Confirmado implements EstadoPedido {

    public String estado() {
        return "Confirmado";
    }

    public EstadoPedido cambiar(String nEstado) {
        switch (nEstado.toLowerCase()) {
            case "reservado":
                return new Reservado();

            case "cancelado":
                return new Cancelado();

            default:
                break;
        }
        return null;
    }

}
