package Estado;

public interface EstadoPedido {
    public EstadoPedido cambiar(String estado);
    public String estado();
}