package tpo_procesodesarrollodesoftware;

public class Administrador extends Usuario {

    public Administrador(String nombre, String email) {
        super(nombre, email);
    }

    public void generarInforme() {
        System.out.println("Administrador: Generando informe...");
    }

    public void gestionarClientes() {
        System.out.println("Administrador: Gestionando clientes...");
    }
}