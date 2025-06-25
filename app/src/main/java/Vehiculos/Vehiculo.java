package vehiculos;

import estrategia.ImpuestoStrategy;

public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private String color;
    private String numeroChasis;
    private String numeroMotor;
    private double precioBase;
    private ImpuestoStrategy estrategiaImpuestos;

    protected Vehiculo(String marca,
            String modelo,
            String color,
            String numeroChasis,
            String numeroMotor,
            double precioBase,
            ImpuestoStrategy estrategiaImpuestos) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.precioBase = precioBase;
        this.estrategiaImpuestos = estrategiaImpuestos;
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

    public ImpuestoStrategy getEstrategiaImpuestos() {
        return estrategiaImpuestos;
    }

    public void setEstrategiaImpuestos(ImpuestoStrategy estrategiaImpuestos) {
        this.estrategiaImpuestos = estrategiaImpuestos;
    }

    public double calcularTotal() {
        double impuesto = estrategiaImpuestos.calcularPara(this);
        return precioBase + impuesto;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", chasis='" + numeroChasis + '\'' +
                ", motor='" + numeroMotor + '\'' +
                ", precioBase=" + precioBase +
                ", impuesto=" + estrategiaImpuestos.getClass().getSimpleName() +
                '}';
    }
}
