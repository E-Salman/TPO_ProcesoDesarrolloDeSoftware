package Modelo;


import java.util.List;

public class Vendedor extends Usuario {

    public Vendedor(String nombre, String email) {
        super(nombre, email);
    }

    public List<Vehiculo> verVehiculosDisponibles() {
        System.out.println("Vendedor: Mostrando vehículos disponibles...");
        return List.of();
    }
}