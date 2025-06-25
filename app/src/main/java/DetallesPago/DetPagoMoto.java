package DetallesPago;

import Vehiculos.Vehiculo;

public class DetPagoMoto extends DetallesPago{

    public DetPagoMoto(double precio) {
        super(precio);
        impNacional = 0;
        impProvincialAdicional = 0.01;
        calcularTotal();
    }

    public DetPagoMoto(Vehiculo vehiculo) {
        super(vehiculo);
        impNacional = 0;
        impProvincialAdicional = 0.01;
        calcularTotal();
    }
}