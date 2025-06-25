package Modelo;

public class Vehiculo {
    private String marca;
    private String modelo;
    private String color;
    private String numeroChasis;
    private String numeroMotor;
    private double precioBase;
    private ImpuestoStrategy estrategioImpuestos;

    public Vehiculo(String marca, String modelo, String color, String numeroChasis, String numeroMotor,
            double precioBase, ImpuestoStrategy estrategioImpuestos) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.precioBase = precioBase;
        this.estrategioImpuestos = estrategioImpuestos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public ImpuestoStrategy getEstrategioImpuestos() {
        return estrategioImpuestos;
    }

    public void setEstrategioImpuestos(ImpuestoStrategy estrategioImpuestos) {
        this.estrategioImpuestos = estrategioImpuestos;
    }

    public String toString() {
        return "Vehiculo{" +
                ", modelo: '" + modelo + '\'' +
                ", color: '" + color + '\'' +
                ", numeroChasis: '" + numeroChasis + '\'' +
                ", precio base: '" + precioBase + '\'' +
                '}';
    }
}
