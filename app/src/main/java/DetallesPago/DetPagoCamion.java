package DetallesPago;

import Vehiculos.Vehiculo;

public class DetPagoCamion extends DetallesPago{

    public DetPagoCamion(double precio) {
        super(precio);
    }

    public DetPagoCamion(Vehiculo vehiculo) {
        super(vehiculo);
    }
}