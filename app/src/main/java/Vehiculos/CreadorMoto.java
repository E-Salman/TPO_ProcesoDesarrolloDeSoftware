package Vehiculos;

import Vehiculos.*;

public class CreadorMoto extends CreadorVehiculo {
    @Override
    public Vehiculo crearVehiculo(
            String marca,
            String modelo,
            String color,
            String numeroChasis,
            String numeroMotor,
            double precioBase) {
        return new Moto(
                marca,
                modelo,
                color,
                numeroChasis,
                numeroMotor,
                precioBase);
    }
}
