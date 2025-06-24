import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CatalogoVehiculos {

    private final List<Vehiculo> vehiculos = new ArrayList<>();

    public List<Vehiculo> getVehiculos() {
        return Collections.unmodifiableList(vehiculos);
    }

    public void agregarVehiculo(Vehiculo v) {
        if (buscarPorCodigo(v.getNumeroChasis()).isPresent()) {
            throw new IllegalArgumentException(
                    "Vehículo duplicado: chasis " + v.getNumeroChasis());
        }
        vehiculos.add(v);
    }

    public boolean eliminarVehiculo(String chasis) {
        Optional<Vehiculo> encontrado = buscarPorCodigo(chasis);
        if (encontrado.isPresent()) {
            return vehiculos.remove(encontrado.get());
        }
        return false;
    }

    public Optional<Vehiculo> buscarPorCodigo(String chasis) {
        return vehiculos.stream()
                .filter(v -> v.getNumeroChasis().equals(chasis))
                .findFirst();
    }

}
