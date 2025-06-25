package DetallesPago;

import Vehiculos.Vehiculo;

public class DetPagoCamioneta extends DetallesPago{
    
    public DetPagoCamioneta(double precio) {
        super(precio);
        impNacional = 0.1;
        impProvincialAdicional = 0.02;
        calcularTotal();
    }

    public DetPagoCamioneta(Vehiculo vehiculo) {
        super(vehiculo);
        impNacional = 0.1;
        impProvincialAdicional = 0.02;
        calcularTotal();
    }
}