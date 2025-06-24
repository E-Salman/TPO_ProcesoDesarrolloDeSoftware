package tpo_procesodesarrollodesoftware;

public class PedidoCompra {
    private int numero;
    private EstadoPedido estadoActual;
    private List<EstadoPedido> historialEstados;
    private Date fechaCreacion;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private List<Observador> observadores;

    public PedidoCompra(int numero, EstadoPedido estadoActual) {
        this.numero = numero;
        this.estadoActual = 
    }

    public int getId() {
        return numero;
    }

    public EstadoPedido getEstado() {
        return estadoActual;
    }
}