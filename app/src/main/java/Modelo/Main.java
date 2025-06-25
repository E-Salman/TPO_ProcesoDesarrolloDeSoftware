package Modelo;
public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Juan", "Pérez", "12345678", "juan@example.com", "1122334455");

        if (cliente1.validarDatos()) {
            cliente1.setEstrategiaEntrega(new EntregaEnConcesionaria());
            cliente1.realizarEntrega();

            cliente1.setEstrategiaEntrega(new EntregaADomicilio());
            cliente1.realizarEntrega();
        } else {
            System.out.println("Datos del cliente inválidos.");
        }
    }
}
