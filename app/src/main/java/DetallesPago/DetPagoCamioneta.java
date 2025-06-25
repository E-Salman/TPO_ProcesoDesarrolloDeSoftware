package DetallesPago;

import Modelo.Vehiculo;

public class DetPagoCamioneta extends DetallesPago{
    
    public DetPagoCamioneta(double precio) {
        super(precio);
    }

    public DetPagoCamioneta(Vehiculo vehiculo) {
        super(vehiculo);
    }
}