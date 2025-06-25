package Vehiculos;

public class CreadorAuto extends CreadorVehiculo {
    @Override
    public Vehiculo crearVehiculo(
            String marca,
            String modelo,
            String color,
            String numeroChasis,
            String numeroMotor,
            double precioBase) {
        return new Auto(
                marca,
                modelo,
                color,
                numeroChasis,
                numeroMotor,
                precioBase);
    }
}