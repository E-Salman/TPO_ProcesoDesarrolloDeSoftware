package tpo_procesodesarrollodesoftware;

import java.util.ArrayList;

public class PedidoCompra {
    private int numero;
    private EstadoPedido estadoActual;
    private List<EstadoPedido> historialEstados;
    private Date fechaCreacion;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private List<Observador> observadores;
    private EstrategiaEntrega estrategiaEntrega;
    private FormaPago formaPago;

    public PedidoCompra(int numero, Cliente cliente, Vehiculo vehiculo, EstrategiaEntrega estrategiaEntrega, FormaPago formaPago) {
        this.numero = numero;
        estadoActual = new Reservado();
        historialEstados = new ArrayList<EstadoPedido>();
        historialEstados.add(estadoActual);
        fechaCreacion = DateTime.Today();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        observadores = new ArrayList<Observador>();
        this.estrategiaEntrega = estrategiaEntrega;
        this.formaPago = formaPago;
    }

    public void agregarObservador(Observador observador){
        observadores.add(observador);        
    }

    public void quitarObservador(Observador observador){
        observadores.remove(observador);
    }

    public void notificarObservadores(){
        for (Observador observador : observadores){
            observador.actualizar(this);
        }
    }
    
    public void cambiarEstado(String nEstado) {
        estadoActual.cambiar(nEstado);
        historialEstados.add(estadoActual);
    }

    public void cambiarEstrategia(EstrategiaEntrega estrategiaEntrega){
        this.estrategiaEntrega = estrategiaEntrega;
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

    public EstrategiaEntrega getEstrategiaEntrega() {
        return estrategiaEntrega;
    }

    public void setEstrategiaEntrega(EstrategiaEntrega estrategiaEntrega) {
        this.estrategiaEntrega = estrategiaEntrega;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }
}