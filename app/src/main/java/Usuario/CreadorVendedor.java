package Usuario;

public class CreadorVendedor extends CreadorUsuario {
    @Override
    public Usuario crearUsuario(String nombre, String email) {
        return new Vendedor(nombre, email);
    }
}