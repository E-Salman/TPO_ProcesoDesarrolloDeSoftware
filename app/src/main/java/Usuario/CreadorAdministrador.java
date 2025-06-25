package Usuario;

public class CreadorAdministrador extends CreadorUsuario {
    @Override
    public Usuario crearUsuario(String nombre, String email) {
        return new Administrador(nombre, email);
    }

    public void generarInforme() {
        System.out.println("CreadorAdministrador: Generando informe.");
    }

    public void gestionarClientes() {
        System.out.println("CreadorAdministrador: Gestionando clientes.");
    }
}