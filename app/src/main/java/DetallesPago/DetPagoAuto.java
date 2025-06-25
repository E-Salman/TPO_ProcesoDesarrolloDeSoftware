package DetallesPago;

import Vehiculos.Vehiculo;

public class DetPagoAuto extends DetallesPago {    

    public DetPagoAuto(Vehiculo vehiculo) {
        super(vehiculo);
        impNacional = 0.21;
        impProvincialAdicional = 0.01;
        calcularTotal();
    }
}
