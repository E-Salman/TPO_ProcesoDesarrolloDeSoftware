package Modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import Modelo.GestorPedidos;
import Modelo.PedidoCompra;
import Estado.EstadoPedido;

public class ReportesPedidos {
    private final GestorPedidos gestorPedidos;

    public ReportesPedidos(GestorPedidos gestorPedidos) {
        this.gestorPedidos = gestorPedidos;
    }

    public List<PedidoCompra> generarInforme(LocalDateTime desde, LocalDateTime hasta, EstadoPedido estado) {
        return gestorPedidos.listarPedidos().stream()
                .filter(p -> !p.getFechaCreacion().isBefore(desde))
                .filter(p -> !p.getFechaCreacion().isAfter(hasta))
                .filter(p -> estado == null || p.getEstadoActual().equals(estado))
                .collect(Collectors.toList());
    }

    public void exportarCSV(List<PedidoCompra> pedidos, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Cabecera
            writer.write("Número;Fecha;Estado;Cliente;Vehículo;FormaPago;Total");
            writer.newLine();

            // Filas
            for (PedidoCompra p : pedidos) {
                writer.write(String.join(";",
                        p.getNumero(),
                        p.getFechaCreacion().toString(),
                        p.getEstadoActual().estado(),
                        p.getCliente().getNombre(),
                        p.getVehiculo().getNumeroChasis(),
                        p.getFormaPago().getNombre(),
                        String.format("%.2f", p.calcularTotal())));
                writer.newLine();
            }
        }
    }

    public void exportarTXT(List<PedidoCompra> pedidos, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (PedidoCompra p : pedidos) {
                writer.write(p.toString());
                writer.newLine();
                writer.write("--------------------------------------------------");
                writer.newLine();
            }
        }
    }
}
