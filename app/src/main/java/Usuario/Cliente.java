package Usuario;

import java.util.ArrayList;
import java.util.List;

import Modelo.PedidoCompra;

public class Cliente extends Usuario{
    private String nombre;
    private String apellido;
    private String documento;
    private String email;
    private String telefono;
    private String cuit;
    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public List<PedidoCompra> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoCompra> pedidos) {
        this.pedidos = pedidos;
    }

    private List<PedidoCompra> pedidos;

    public Cliente(String nombre, String apellido, String documento, String email, String telefono, String cuit) {     
        super(nombre, email);   
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
        this.cuit = cuit;
        pedidos = new ArrayList<PedidoCompra>();
    }

    public void agregarPedido(PedidoCompra pedido){
        pedidos.add(pedido);
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
}