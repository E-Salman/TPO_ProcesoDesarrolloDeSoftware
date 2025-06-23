package tpo_procesodesarrollodesoftware;

public class Cliente {
    private String nombre;
    private String apellido;
    private String documento;
    private String email;
    private String telefono;
    private EstrategiaEntrega estrategiaEntrega;

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

    public void setEstrategiaEntrega(EstrategiaEntrega estrategiaEntrega) {
        this.estrategiaEntrega = estrategiaEntrega;
    }

    public void realizarEntrega() {
        if (estrategiaEntrega != null) {
            estrategiaEntrega.entregar();
        } else {
            System.out.println("No se ha definido una estrategia de entrega.");
        }
    }
}
