package Vehiculos;

public class CreadorCamion extends CreadorVehiculo {
    @Override
    public Vehiculo crearVehiculo(
            String marca,
            String modelo,
            String color,
            String numeroChasis,
            String numeroMotor,
            double precioBase) {
        return new Camion(
                marca,
                modelo,
                color,
                numeroChasis,
                numeroMotor,
                precioBase);
    }
}
