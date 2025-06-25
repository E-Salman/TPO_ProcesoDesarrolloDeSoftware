package Vehiculos;

import DetallesPago.DetallesPago;

public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private String color;
    private String numeroChasis;
    private String numeroMotor;
    private double precioBase;

    protected Vehiculo(String marca,
            String modelo,
            String color,
            String numeroChasis,
            String numeroMotor,
            double precioBase,
            DetallesPago estrategiaImpuestos) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.precioBase = precioBase;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", chasis='" + numeroChasis + '\'' +
                ", motor='" + numeroMotor + '\'' +
                ", precioBase=" + precioBase + '}';
    }
}
