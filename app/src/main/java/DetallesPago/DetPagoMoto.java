package DetallesPago;

import Vehiculos.Vehiculo;

public class DetPagoMoto extends DetallesPago{

    public DetPagoMoto(double precio) {
        super(precio);
    }

    public DetPagoMoto(Vehiculo vehiculo) {
        super(vehiculo);
    }
}