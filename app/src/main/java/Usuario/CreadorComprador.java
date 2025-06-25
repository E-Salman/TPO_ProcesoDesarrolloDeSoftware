package Usuario;

public class CreadorComprador extends CreadorUsuario {
    @Override
    public Usuario crearUsuario(String nombre, String email) {
        return new Comprador(nombre, email);
    }
}
