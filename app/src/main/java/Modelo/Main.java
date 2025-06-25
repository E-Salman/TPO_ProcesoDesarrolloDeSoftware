package Modelo;

import Usuario.Administrador;
import Usuario.Comprador;
import Usuario.Vendedor;
import Vehiculos.Auto;
import Vehiculos.Camion;
import Vehiculos.Camioneta;
import Vehiculos.Moto;
import Modelo.ReportesPedidos;

public class Main {
    public static void main(String[] args) {
       Concesionaria concesionaria = new Concesionaria();

       concesionaria.getUsuarios().add(new Administrador("Juan", "JuanPerez@gmal.com", new ReportesPedidos(concesionaria.getGestorPedidos()) ));
       concesionaria.getUsuarios().add(new Vendedor("Ivan", "ivantestacc@gmail.com"));
       concesionaria.getUsuarios().add(new Vendedor("Adrian", "Adrianortega@gmail.com"));
       concesionaria.getUsuarios().add(new Comprador("Stalin", "Ioseff@Stalingrado.com", new Cliente("Stalin", "Vissarionovioh", "20.354.345", "Ioseff@Stalingrado.com", "11234534")));
       concesionaria.getGestorVehiculos().registrarVehiculo(new Auto("bmw", "Serie 3", "blue", "123456", "220", 50000));
       concesionaria.getGestorVehiculos().registrarVehiculo(new Moto("Honda","CBR600RR","Rojo","CHASIS789XYZ","MOTOR456DEF",12000));
       concesionaria.getGestorVehiculos().registrarVehiculo(new Camion("Mercedes","Actros","Azul","CHASIS123XYZ","MOTOR987ABC",50000));
       concesionaria.getGestorVehiculos().registrarVehiculo(new Camioneta("Ford","Ranger","Rojo","CHASIS789XYZ","MOTOR456ABC",45000));
       concesionaria.getGestorPedidos().crearPedido(concesionaria.getClientes().get(1), concesionaria.getCatalogoVehiculos().getVehiculos().get(1), "EntregaADomicilio", "PagoContado", new DatosFacturacion("Papa", "Rammus 234", "2435325"), null, false, false, false);
    }


}
