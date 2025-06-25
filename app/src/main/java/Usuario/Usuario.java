package Usuario;

public abstract class Usuario {
    protected final String nombre;
    protected final String email;

    protected Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}