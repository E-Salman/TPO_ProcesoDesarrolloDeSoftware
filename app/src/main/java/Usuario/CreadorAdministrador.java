package Usuario;

import Modelo.ReportesPedidos;

public class CreadorAdministrador extends CreadorUsuario {
    private final ReportesPedidos servicioReportes;

    public CreadorAdministrador(ReportesPedidos servicioReportes) {
        this.servicioReportes = servicioReportes;
    }

    @Override
    public Usuario crearUsuario(String nombre, String email) {
        return new Administrador(nombre, email, servicioReportes);
    }
}
