package Estado;

public class Reservado implements EstadoPedido{

    public String estado(){
        return "Reservado";
    }

    public EstadoPedido cambiar(String nEstado){
        switch (nEstado.toLowerCase()){
            case "cancelado":
                return new Cancelado();

            case "confirmado":
                return new Confirmado();
                
            default:
                break;
        }
        return null;
    }
}