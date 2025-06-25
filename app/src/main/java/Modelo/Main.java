package Modelo;

import Usuario.Administrador;
import Usuario.Comprador;
import Usuario.Vendedor;
import Vehiculos.Auto;
import Vehiculos.Camion;
import Vehiculos.Camioneta;
import Vehiculos.Moto;
import EstrategiaEntrega.EntregaADomicilio;
import EstrategiaEntrega.EntregaEnConcesionaria;
import EstrategiaEntrega.EstrategiaEntrega;
import FormaPago.FormaPago;
import FormaPago.PagoContado;
import FormaPago.PagoTarjeta;
import FormaPago.PagoTransferencia;

public class Main {
    public static void main(String[] args) {
        Concesionaria concesionaria = new Concesionaria();

        concesionaria.getUsuarios().add(
                new Administrador("Juan", "JuanPerez@gmal.com", new ReportesPedidos(concesionaria.getGestorPedidos())));
        concesionaria.getUsuarios().add(new Vendedor("Ivan", "ivantestacc@gmail.com"));
        concesionaria.getUsuarios().add(new Vendedor("Adrian", "Adrianortega@gmail.com"));
        concesionaria.getUsuarios().add(new Comprador("Stalin", "Ioseff@Stalingrado.com",
                new Cliente("Stalin", "Vissarionovioh", "20.354.345", "Ioseff@Stalingrado.com", "11234534")));
        concesionaria.getUsuarios().add(new Comprador("Sofía", "sofqweasdia@gmail.com",
                new Cliente("Sofía", "López", "20.123.4526", "sofia@gmail.com", "1144001122")));
        concesionaria.getGestorVehiculos()
                .registrarVehiculo(new Auto("bmw", "Serie 3", "blue", "123456", "220", 50000));
        concesionaria.getGestorVehiculos()
                .registrarVehiculo(new Moto("Honda", "CBR600RR", "Rojo", "CHASIS789XYZ", "MOTOR456DEF", 12000));
        concesionaria.getGestorVehiculos()
                .registrarVehiculo(new Camion("Mercedes", "Actros", "Azul", "CHASIS123XYZ", "MOTOR987ABC", 50000));
        concesionaria.getGestorVehiculos()
                .registrarVehiculo(new Camioneta("Ford", "Ranger", "Rojo", "CHASIS789XYqweZ", "MOTOR456ABC", 45000));
                System.out.println(concesionaria.getCatalogoVehiculos().getVehiculos().size());
                System.out.println(concesionaria.getClientes().get(0));
        concesionaria.getGestorPedidos().crearPedido(concesionaria.getClientes().get(0),
                concesionaria.getCatalogoVehiculos().getVehiculos().get(0), (EstrategiaEntrega) new EntregaADomicilio(),
                (FormaPago) new PagoContado(), new DatosFacturacion("Papa", "Rammus 234", "2435325"),
                (Vendedor) concesionaria.vendedores.get(1), false, false, false);
        concesionaria.getUsuarios().add(new Vendedor("Carlos", "carlos@concesionaria.com"));
        concesionaria.getUsuarios().add(new Vendedor("Laura", "laura@concesionaria.com"));
        concesionaria.getUsuarios().add(new Vendedor("Miguel", "miguel@concesionaria.com"));
        concesionaria.getUsuarios().add(new Comprador("Sofía", "sofia@gmail.com",
                new Cliente("Sofía", "López", "20.123.456", "sofia@gmail.com", "1144001122")));
        concesionaria.getUsuarios().add(new Comprador("Diego", "diego@hotmail.com",
                new Cliente("Diego", "Alvarez", "20.331.122", "diego@hotmail.com", "1155223344")));
        concesionaria.getUsuarios().add(new Comprador("Valentina", "valentina@gmail.com",
                new Cliente("Valentina", "Gómez", "20.224.433", "valentina@gmail.com", "1166889900")));
        concesionaria.getGestorPedidos().crearPedido(
                concesionaria.getClientes().get(1),
                concesionaria.getCatalogoVehiculos().getVehiculos().get(0),
                (EstrategiaEntrega) new EntregaEnConcesionaria(),
                (FormaPago) new PagoContado(),
                new DatosFacturacion("Sofía", "Av. Siempre Viva 742", "1144001122"),
                (Vendedor) concesionaria.vendedores.get(2),
                false, false, false);
        concesionaria.getGestorPedidos().crearPedido(
                concesionaria.getClientes().get(2),
                concesionaria.getCatalogoVehiculos().getVehiculos().get(1),
                (EstrategiaEntrega) new EntregaADomicilio(),
                (FormaPago) new PagoTarjeta(),
                new DatosFacturacion("Diego", "Calle Falsa 123", "2233445566"),
                (Vendedor) concesionaria.vendedores.get(3),
                true, false, true);
        concesionaria.getGestorPedidos().crearPedido(
                concesionaria.getClientes().get(3),
                concesionaria.getCatalogoVehiculos().getVehiculos().get(2),
                (EstrategiaEntrega) new EntregaEnConcesionaria(),
                (FormaPago) new PagoTransferencia(),
                new DatosFacturacion("Valentina", "Pasaje 45", "1166889900"),
                (Vendedor) concesionaria.vendedores.get(4),
                false, true, false);

                concesionaria.iniciarInterfaz();
    }

}
