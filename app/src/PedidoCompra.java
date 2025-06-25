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
        this.estadoActual = estadoActual;
        
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EstadoPedido getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoPedido estadoActual) {
        this.estadoActual = estadoActual;
    }

    public List<EstadoPedido> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(List<EstadoPedido> historialEstados) {
        this.historialEstados = historialEstados;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Observador> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<Observador> observadores) {
        this.observadores = observadores;
    }
}