package Vehiculos;

import Vehiculos.*;

public class CreadorCamioneta extends CreadorVehiculo {
    @Override
    public Vehiculo crearVehiculo(
            String marca,
            String modelo,
            String color,
            String numeroChasis,
            String numeroMotor,
            double precioBase) {
        return new Camioneta(
                marca,
                modelo,
                color,
                numeroChasis,
                numeroMotor,
                precioBase);
    }
}
