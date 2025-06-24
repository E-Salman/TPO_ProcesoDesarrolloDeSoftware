package tpo_procesodesarrollodesoftware;

public class EntregaADomicilio implements EstrategiaEntrega {

    @Override
    public void entregar() {
        System.out.println("El vehículo será entregado al domicilio del cliente.");
    }

}
