public class Cancelado implements EstadoPedido {

    public String estado() {
        return "Cancelado";
    }

    public void cambiar(EstadoPedido nEstado) {
        switch (nEstado.toLowerCase()) {
            case "reservado":
                this.estadoActual = new Cancelado();
                break;

            case "confirmado":
                this.estadoActual = new Confirmado();
                break;

            default:
                break;
        }
    }

}
