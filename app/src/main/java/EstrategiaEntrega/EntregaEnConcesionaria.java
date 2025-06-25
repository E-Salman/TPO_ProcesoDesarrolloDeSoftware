package EstrategiaEntrega;

public class EntregaEnConcesionaria implements EstrategiaEntrega {

    @Override
    public void entregar() {
        System.out.println("El cliente debe retirar el vehículo en la concesionaria.");
    }
}
