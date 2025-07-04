package Modelo;

import java.util.NoSuchElementException;

import Vehiculos.Vehiculo;

public class GestorVehiculos {

    private CatalogoVehiculos catalogo;

    public GestorVehiculos() {
        catalogo = new CatalogoVehiculos();
    }

    public GestorVehiculos(CatalogoVehiculos catalogo) {
        System.out.println(catalogo.toString());
        System.out.println(this.catalogo);         
        this.catalogo = new CatalogoVehiculos();
        System.out.println(this.catalogo.toString());
    }

    public void registrarVehiculo(Vehiculo v) {
        if (catalogo.buscarPorCodigo(v.getNumeroChasis()).isPresent()) {
            throw new IllegalArgumentException(
                    "No se puede registrar: vehículo duplicado (chasis=" + v.getNumeroChasis() + ")");
        }
        catalogo.agregarVehiculo(v);
    }

    public String listarTodos() {
        StringBuilder lista = new StringBuilder();
        for (Vehiculo v : catalogo.getVehiculos()) {
            lista.append(v).append("\n");
        }
        return lista.toString();
    }

    public Vehiculo buscarVehiculo(String chasis) {
        return catalogo.buscarPorCodigo(chasis)
                .orElseThrow(() -> new NoSuchElementException(
                        "No existe vehículo buscado en la flota"));
    }

    public boolean eliminarVehiculo(String chasis) {
        return catalogo.eliminarVehiculo(chasis);
    }
}
