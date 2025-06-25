package Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DetallesPago.DetPagoAuto;
import DetallesPago.DetPagoCamion;
import DetallesPago.DetPagoCamioneta;
import DetallesPago.DetPagoMoto;
import DetallesPago.DetallesPago;
import Estado.EstadoPedido;
import Estado.Reservado;
import EstrategiaEntrega.EstrategiaEntrega;
import Observador.Observador;
import Usuario.Vendedor;
import Vehiculos.Vehiculo;
import FormaPago.FormaPago;

public class PedidoCompra {
    private int numero;
    private EstadoPedido estadoActual;
    private List<EstadoPedido> historialEstados;
    private LocalDateTime fechaCreacion;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private List<Observador> observadores;
    private EstrategiaEntrega estrategiaEntrega;
    private FormaPago formaPago;
    private DatosFacturacion datosFacturacion;
    private Vendedor vendedor;
    private boolean equipamientoExtra;
    private boolean garantiaExtendida;
    private boolean accesorios;
    private AreaResponsable areaResponsableActual;
    private DetallesPago detallesPago;



    public PedidoCompra(int numero, Cliente cliente, Vehiculo vehiculo, EstrategiaEntrega estrategiaEntrega,
            FormaPago formaPago, DatosFacturacion datosFacturacion, Vendedor vendedor, boolean equipamientoExtra,
            boolean garantiaExtendida, boolean accesorios) {
        this.numero = numero;
        estadoActual = new Reservado();
        historialEstados = new ArrayList<EstadoPedido>();
        historialEstados.add(estadoActual);
        fechaCreacion = LocalDateTime.now();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        observadores = new ArrayList<Observador>();
        this.estrategiaEntrega = estrategiaEntrega;
        this.formaPago = formaPago;
        this.datosFacturacion = datosFacturacion;
        this.vendedor = vendedor;
        this.equipamientoExtra = equipamientoExtra;
        this.garantiaExtendida = garantiaExtendida;
        this.accesorios = accesorios;
        this.areaResponsableActual = AreaResponsable.VENTAS;

        switch (vehiculo.getClass().getName()){
            case "Auto":
                detallesPago = new DetPagoAuto(vehiculo);
                break;
                            
            case "Camion":
                detallesPago = new DetPagoCamion(vehiculo);
                break;

            case "Camioneta":
                detallesPago = new DetPagoCamioneta(vehiculo);
                break;

            case "Moto":
                detallesPago = new DetPagoMoto(vehiculo);
                break;
        }
    }
    
    public DetallesPago getDetallesPago() {
        return detallesPago;
    }

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.actualizar(this);
        }
    }

    public void cambiarEstado(String nEstado) {
        EstadoPedido temp = estadoActual.cambiar(nEstado);
        if (temp != null) {
            estadoActual = temp;
            historialEstados.add(estadoActual);
        }
    }

    public void cambiarEstrategia(EstrategiaEntrega estrategiaEntrega) {
        this.estrategiaEntrega = estrategiaEntrega;
    }

    public DetallesPago detallesPago(){
        return detallesPago;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setEstadoActual(EstadoPedido estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public DatosFacturacion getDatosFacturacion() {
        return datosFacturacion;
    }

    public void setDatosFacturacion(DatosFacturacion datosFacturacion) {
        this.datosFacturacion = datosFacturacion;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public boolean isEquipamientoExtra() {
        return equipamientoExtra;
    }

    public void setEquipamientoExtra(boolean equipamientoExtra) {
        this.equipamientoExtra = equipamientoExtra;
    }

    public boolean isGarantiaExtendida() {
        return garantiaExtendida;
    }

    public void setGarantiaExtendida(boolean garantiaExtendida) {
        this.garantiaExtendida = garantiaExtendida;
    }

    public boolean isAccesorios() {
        return accesorios;
    }

    public void setAccesorios(boolean accesorios) {
        this.accesorios = accesorios;
    }

    public AreaResponsable getAreaResponsableActual() {
        return areaResponsableActual;
    }

    public void setAreaResponsableActual(AreaResponsable areaResponsableActual) {
        this.areaResponsableActual = areaResponsableActual;
    }

}