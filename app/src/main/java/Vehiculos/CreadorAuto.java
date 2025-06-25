package Vehiculos;

import DetallesPago.DetPagoAuto;
import DetallesPago.DetallesPago;

public class CreadorAuto extends CreadorVehiculo {
    @Override
    public Vehiculo crearVehiculo(String marca, String modelo, String color, String numeroChasis, String numeroMotor,
            double precioBase) {
        DetallesPago impuesto = new DetPagoAuto(precioBase);
        return new Auto(marca, modelo, color, numeroChasis, numeroMotor, precioBase, impuesto);
    }
}