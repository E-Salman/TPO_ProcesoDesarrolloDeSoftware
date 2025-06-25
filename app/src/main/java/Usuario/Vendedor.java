package Usuario;

import java.util.Collections;
import java.util.List;

import Vehiculos.Vehiculo;

public class Vendedor extends Usuario {
    public Vendedor(String nombre, String email) {
        super(nombre, email);
    }

    public List<Vehiculo> verVehiculosFlota(List<Vehiculo> flota) {
        return Collections.unmodifiableList(flota);
    }
}