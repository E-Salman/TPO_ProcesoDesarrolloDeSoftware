package DetallesPago;

import Vehiculos.Vehiculo;

public class DetPagoCamion extends DetallesPago{

    public DetPagoCamion(double precio) {    
        super(precio);
        impNacional = 0;
        impProvincialAdicional = 0.02;
        calcularTotal();     
    }

    public DetPagoCamion(Vehiculo vehiculo) {
        super(vehiculo);
        impNacional = 0;
        impProvincialAdicional = 0.02;
        calcularTotal();
    }
}