package Usuario;

import Modelo.Cliente;

public class CreadorComprador extends CreadorUsuario {
  private final Cliente cliente;

  public CreadorComprador(Cliente c) {
    this.cliente = c;
  }

  @Override
  public Usuario crearUsuario(String nombre, String email) {
    return new Comprador(nombre, email, cliente);
  }
}