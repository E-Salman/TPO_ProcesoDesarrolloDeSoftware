package Vehiculos;

public abstract class CreadorVehiculo {

    public abstract Vehiculo crearVehiculo(String marca, String modelo, String color, String numeroChasis,
            String numeroMotor, double precioBase);
}