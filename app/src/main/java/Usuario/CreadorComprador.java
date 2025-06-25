package Usuario;

import Modelo.Cliente;

public class CreadorComprador extends CreadorUsuario {
  private Cliente cliente;

  public CreadorComprador(){}

  public CreadorComprador(Cliente c) {
    this.cliente = c;
  }

  public void setCliente(Cliente cliente){
    this.cliente = cliente;
  }

  @Override
  public Usuario crearUsuario(String nombre, String email) {
    return new Comprador(nombre, email, cliente);
  }
}