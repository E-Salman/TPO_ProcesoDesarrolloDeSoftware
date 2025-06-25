package Usuario;

import java.util.List;

import Vehiculos.Vehiculo;

public class CreadorVendedor extends CreadorUsuario {
    @Override
    public Usuario crearUsuario(String nombre, String email) {
        return new Vendedor(nombre, email);
    }

    public List<Vehiculo> verVehiculosDisponibles() {
        System.out.println("CreadorVendedor: Mostrando vehículos.");
        return List.of();
    }
}