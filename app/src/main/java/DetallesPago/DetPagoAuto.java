package DetallesPago;

import Vehiculos.Vehiculo;

public class DetPagoAuto extends DetallesPago {    

    public DetPagoAuto(double precio) {
        super(precio);
    }

    public DetPagoAuto(Vehiculo vehiculo) {
        super(vehiculo);
    }
}
