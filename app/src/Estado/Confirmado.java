public class Confirmado implements EstadoPedido {

    public String estado() {
        return "Confirmado";
    }

    public void cambiar(EstadoPedido nEstado) {
        switch (nEstado.toLowerCase()) {
            case "reservado":
                this.estadoActual = new Cancelado();
                break;

            case "cancelado":
                this.estadoActual = new Confirmado();
                break;

            default:
                break;
        }
    }

}
