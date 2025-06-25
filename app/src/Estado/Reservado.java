public class Reservado implements EstadoPedido{

    public String estado(){
        return "Reservado";
    }

    public void cambiar(String nEstado){
        switch (nEstado.toLowerCase()){
            case "cancelado":
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