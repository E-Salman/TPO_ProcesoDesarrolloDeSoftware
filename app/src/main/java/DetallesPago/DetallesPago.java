package DetallesPago;

import Vehiculos.Vehiculo;

public abstract class DetallesPago  {
    private double precioBase;
    private double impNacional;
    private double impProvincial;
    private double impProvincialAdicional;
    private double precioTotal;

    public DetallesPago(Vehiculo vehiculo){
        setBase(vehiculo.getPrecioBase());
    }

    public DetallesPago(double precio){
        setBase(precio);
    }

    public double calcularPara(){
        precioTotal = precioBase + precioBase * impNacional + precioBase * impProvincial + precioBase * impProvincialAdicional;
        return precioTotal;
    }

    public double getBase(){
        return precioBase;
    }

    public void setBase(double precioBase){
        this.precioBase = precioBase;
        precioTotal = calcularPara();
    }
    public double getImpNacional(){
        return precioBase * impNacional;
    }

    public double getImpProvincial(){
        return precioBase * impProvincial;
    }

    public double getImpProvincialAdicional(){
        return precioBase * impProvincialAdicional;
    }
}