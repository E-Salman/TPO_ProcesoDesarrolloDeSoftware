package Modelo;
public class Cliente {
    private String nombre;
    private String apellido;
    private String documento;
    private String email;
    private String telefono;

    public Cliente(String nombre, String apellido, String documento, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
    }

    public boolean validarDatos() {
        return nombre != null && !nombre.isEmpty()
                && apellido != null && !apellido.isEmpty()
                && documento != null && !documento.isEmpty()
                && email != null && !email.isEmpty()
                && telefono != null && !telefono.isEmpty();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
<<<<<<< HEAD:app/src/Cliente.java

    public EstrategiaEntrega getEstrategiaEntrega() {
        return estrategiaEntrega;
    }

}
"buenas tardes mundo"
=======
}
>>>>>>> 53f1fb0e89bae2ccbc13305d75ce61cfe824fe0a:app/src/main/java/Modelo/Cliente.java
