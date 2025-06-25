package Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import Estado.EstadoPedido;
import Modelo.PedidoCompra;
import Modelo.ReportesPedidos;

public class Administrador extends Usuario {
    private final ReportesPedidos servicioReportes;

    public Administrador(String nombre,
            String email,
            ReportesPedidos servicioReportes) {
        super(nombre, email);
        this.servicioReportes = servicioReportes;
    }

    public List<PedidoCompra> generarInforme(LocalDate desde,
            LocalDate hasta,
            EstadoPedido estado) {
        return servicioReportes.generarInforme(desde, hasta, estado);
    }

    public void generarInformeCSV(LocalDate desde,
            LocalDate hasta,
            EstadoPedido estado,
            String rutaArchivo) throws IOException {
        List<PedidoCompra> pedidos = servicioReportes.generarInforme(desde, hasta, estado);
        servicioReportes.exportarCSV(pedidos, rutaArchivo);
    }

    public void generarInformeTXT(LocalDate desde,
            LocalDate hasta,
            EstadoPedido estado,
            String rutaArchivo) throws IOException {
        List<PedidoCompra> pedidos = servicioReportes.generarInforme(desde, hasta, estado);
        servicioReportes.exportarTXT(pedidos, rutaArchivo);
    }
}
