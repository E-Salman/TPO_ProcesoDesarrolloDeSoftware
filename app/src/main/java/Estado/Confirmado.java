package Estado;

public class Confirmado implements EstadoPedido {

    public String estado() {
        return "Confirmado";
    }

    public EstadoPedido cambiar(String nEstado) {
        switch (nEstado.toLowerCase()) {
            case "reservado":
                return new Cancelado();

            case "cancelado":
                return new Confirmado();

            default:
                break;
        }
        return null;
    }

}
