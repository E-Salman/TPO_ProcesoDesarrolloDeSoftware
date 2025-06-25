package Modelo;

import java.util.List;
import java.util.Optional;

public class AreaResponsable {
    public static final AreaResponsable VENTAS = new AreaResponsable("Ventas", "ventas@autoplus.com");
    public static final AreaResponsable COBRANZAS = new AreaResponsable("Cobranzas", "cobranzas@autoplus.com");
    public static final AreaResponsable EMBARQUE = new AreaResponsable("Embarque", "embarque@autoplus.com");
    public static final AreaResponsable ENTREGA = new AreaResponsable("Entrega", "entrega@autoplus.com");

    private final String nombre;
    private final String correoInterno;

    private AreaResponsable(String nombre, String correoInterno) {
        this.nombre = nombre;
        this.correoInterno = correoInterno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoInterno() {
        return correoInterno;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static List<AreaResponsable> values() {
        return List.of(VENTAS, COBRANZAS, EMBARQUE, ENTREGA);
    }

    public static Optional<AreaResponsable> fromNombre(String nombre) {
        return values().stream()
                .filter(a -> a.nombre.equalsIgnoreCase(nombre))
                .findFirst();
    }
}
