package DetallesPago;

import Vehiculos.Vehiculo;

public abstract class DetallesPago  {
    protected double precioBase;
    protected double impNacional;
    protected double impProvincial;
    protected double impProvincialAdicional;
    protected double precioTotal;

    public DetallesPago(Vehiculo vehiculo){
        precioBase = vehiculo.getPrecioBase();
        impProvincial = 0.05;
    }

    public DetallesPago(double precio){
        precioBase = precio;
    }

    protected void calcularTotal(){
        precioTotal = precioBase + precioBase * impNacional + precioBase * impProvincial + precioBase * impProvincialAdicional;
    }

    public double getBase(){
        return precioBase;
    }

    public void setBase(double precioBase){
        this.precioBase = precioBase;
        calcularTotal();
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

    public double getTotal(){
        return precioTotal;
    }
}