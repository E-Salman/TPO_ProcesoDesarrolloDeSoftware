package Modelo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import Estado.EstadoPedido;
import EstrategiaEntrega.EntregaADomicilio;
import EstrategiaEntrega.EntregaEnConcesionaria;
import EstrategiaEntrega.EstrategiaEntrega;
import FormaPago.FormaPago;
import FormaPago.PagoContado;
import FormaPago.PagoTarjeta;
import FormaPago.PagoTransferencia;
import Usuario.Administrador;
import Usuario.Cliente;
import Usuario.Usuario;
import Usuario.Vendedor;
import Vehiculos.Vehiculo;

public class Concesionaria {
    private String nombre;
    private String CUIT;
    private CatalogoVehiculos catalogoVehiculos;
    private GestorVehiculos gestorVehiculos;
    private GestorPedidos gestorPedidos;
    private List<Cliente> clientes;
    private List<Usuario> usuarios;
    private Usuario usuarioLogueado;
    private ReportesPedidos reportador;

    public Concesionaria() {
        catalogoVehiculos = new CatalogoVehiculos();
        gestorVehiculos = new GestorVehiculos(catalogoVehiculos);
        gestorPedidos = new GestorPedidos();
        usuarios = new ArrayList<Usuario>();
        clientes = new ArrayList<Cliente>();
        reportador = new ReportesPedidos(gestorPedidos);
        CUIT = "ConcesionariaPH";
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void iniciarInterfaz() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== BIENVENIDO AL SISTEMA DE CONCESIONARIA ===");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = -1;
            try {
                System.out.println("Entrada inválida. Ingrese un númersadzxo.");
                opcion = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarUsuario(sc);
                    break;

                case 2:
                    Usuario usuario = login(sc);
                    if (usuario != null) {
                        usuarioLogueado = usuario;
                        mostrarMenuPorPerfil(usuario, sc);
                    }
                    break;
                    
                case 0:
                    System.out.println("Hasta luego.");
                    return;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    private void registrarUsuario(Scanner sc) {
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese nombre de email: ");
        String email = sc.nextLine();
        System.out.print("Ingrese tipo de usuario (admin/vendedor/comprador): ");
        String tipo = sc.nextLine().toLowerCase();

        Usuario nuevo = null;

        switch (tipo) {
            case "admin":
                nuevo = new Administrador(nombre, email);
                break;

            case "vendedor":
                nuevo = new Vendedor(nombre, email);
                break;

            case "cliente":
                System.out.print("Ingrese apellido del cliente: ");
                String apellido = sc.nextLine();

                System.out.print("Ingrese DNI del cliente: ");
                String dni = sc.nextLine();

                System.out.print("Ingrese telefono del cliente: ");
                String telefono = sc.nextLine();

                System.out.print("Ingrese cuit del cliente: ");
                String cuit = sc.nextLine();

                nuevo = new Cliente(nombre, apellido, dni, email, telefono, cuit);
                break;

            default:
                System.out.println("Tipo de usuario inválido.");
                return;
        }

        usuarios.add(nuevo);
        System.out.println("Usuario registrado correctamente.");
    }

    private Usuario login(Scanner sc) {
        System.out.print("Ingrese su email: ");
        String email = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                System.out.println("Login exitoso como " + u.getClass().getSimpleName());
                return u;
            }
        }

        System.out.println("Usuario no encontrado.");
        return null;
    }

    private void mostrarMenuPorPerfil(Usuario usuario, Scanner sc) {
        if (usuario instanceof Administrador) {
            menuAdministrador(sc);
        } else if (usuario instanceof Cliente) {
            menuComprador((Cliente) usuario, sc);
        } else if (usuario instanceof Vendedor) {
            menuVendedor(sc);
        }
    }

    private void menuAdministrador(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Listar vehículos");
            System.out.println("2. Registrar pedido");
            System.out.println("3. Ver todos los pedidos");            
            System.out.println("4. Generar informe");
            System.out.println("5. Ver todos los usuarios");
            System.out.println("0. Cerrar sesión");
            opcion = -1;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(catalogoVehiculos.smallString());
                    break;
                case 2:
                    registrarPedido(sc);
                    break;
                case 3:
                    System.out.println(gestorPedidos.listarPedidos().toString());
                    break;
                case 4:
                    menuGenerarInforme();
                    break;
                case 5:
                    System.out.println(usuarios.toString());
                    break;
                default:
                    break;
            }
        } while (opcion != 0);
    }

    private void menuComprador(Cliente cliente, Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Menú Comprador ---");
            System.out.println("1. Ver vehículos disponibles");
            System.out.println("2. Ver mis pedidos");
            System.out.println("0. Cerrar sesión");
            opcion = -1;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(catalogoVehiculos.toString());
                    ;
                    break;

                case 2:
                    System.out.println(gestorPedidos.listarPedidosCliente(cliente).toString());
                    break;
            }
        } while (opcion != 0);
    }

    private void menuVendedor(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Menú Vendedor ---");
            System.out.println("1. Ver vehículos disponibles");
            System.out.println("2. Registrar pedido");
            System.out.println("3. Listar pedidos");
            System.out.println("0. Cerrar sesión");
            opcion = -1;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.println(catalogoVehiculos.toString());
                    break;
                
                case 2:
                    registrarPedido(sc);
                    break;
                    
                case 3:
                    System.out.println(gestorPedidos.listarPedidos().toString());
                    break;
                                
                default:
                    break;
            }
        } while (opcion != 0);
    }

    private void registrarPedido(Scanner sc) {
        System.out.println("\n--- Registro de Pedido ---");

        System.out.println("¿Es un nuevo cliente?");
        String nCliente = sc.nextLine();

        Cliente clientePedido = null;
        if (nCliente.equalsIgnoreCase("no")) {
            System.out.println("\nIngrese el email del cliente");
            String emailTemp = sc.nextLine();
            for (Cliente cliente : clientes) {
                if (cliente.getEmail().equalsIgnoreCase(emailTemp)) {
                    clientePedido = cliente;
                    break;
                }
            }
            if (clientePedido == null){
                System.out.println("No se encontro el email registrado");
                return;
            }                

        } else {
            System.out.print("Ingrese nombre del cliente: ");
            String nombre = sc.nextLine();

            System.out.print("Ingrese apellido del cliente: ");
            String apellido = sc.nextLine();

            System.out.print("Ingrese DNI del cliente: ");
            String dni = sc.nextLine();

            System.out.print("Ingrese email del cliente: ");
            String email = sc.nextLine();

            System.out.print("Ingrese telefono del cliente: ");
            String telefono = sc.nextLine();

            System.out.print("Ingrese cuit del cliente: ");
            String cuit = sc.nextLine();

            clientePedido = new Cliente(nombre, apellido, dni, email, telefono, cuit);
        }

        System.out.println("\nVehículos disponibles:");
        List<Vehiculo> disponibles = catalogoVehiculos.getVehiculos();
        for (int i = 0; i < disponibles.size(); i++) {
            System.out.println(i + ". " + disponibles.get(i).toString());
        }
        System.out.print("Seleccione el número de vehículo: ");
        int indexVehiculo = sc.nextInt();
        sc.nextLine();
        Vehiculo vehiculoSeleccionado = disponibles.get(indexVehiculo);
        
        Optional<PedidoCompra> resultado = gestorPedidos.buscarPedidoPorVehiculo(vehiculoSeleccionado);
        PedidoCompra pedidoSeleccionado = null;
        if (resultado.isPresent()) {
        pedidoSeleccionado = resultado.get();
        System.out.println("Pedido Encontrado");
            } else {
        System.out.println("No existe ningún pedido para ese vehículo.");
            }

        System.out.println("\nSeleccione tipo de entrega:");
        System.out.println("1. Retiro en concesionaria");
        System.out.println("2. Envío a domicilio");
        int tipoEntrega = sc.nextInt();
        sc.nextLine();
        EstrategiaEntrega estrategiaEntrega;
        if (tipoEntrega == 1) {
            estrategiaEntrega = new EntregaEnConcesionaria();
        } else {
            estrategiaEntrega = new EntregaADomicilio();
        }
        System.out.println("\nSeleccione forma de pago:");
        System.out.println("1. Tarjeta");
        System.out.println("2. Transferencia");
        System.out.println("3. Pago contado");
        int tipoPago = sc.nextInt();
        sc.nextLine();

        FormaPago formaPago = switch (tipoPago) {
            case 1 : new PagoTarjeta(); gestorPedidos.cambiarEstado(pedidoSeleccionado, "Confirmado");
            case 2 : new PagoTransferencia(); gestorPedidos.cambiarEstado(pedidoSeleccionado, "Confirmado");
            case 3 : new PagoContado(); gestorPedidos.cambiarEstado(pedidoSeleccionado, "Confirmado");
            default : {
                System.out.println("Forma de pago inválida. Se usará contado.");
                yield new PagoContado();
            }
        };

        System.out.println("Razon social: ");
        String razonSocial = sc.nextLine();
        System.out.print("CUIT para facturación: ");
        String cuit = sc.nextLine();
        System.out.print("Direccion: ");
        String direccion = sc.nextLine();
        DatosFacturacion datosFact = new DatosFacturacion(razonSocial, direccion, cuit);

        System.out.println("\n¿Desea agregar equipamiento extra? (s/n): ");
        boolean equipamiento = sc.nextLine().equalsIgnoreCase("s");

        System.out.println("¿Desea garantía extendida? (s/n): ");
        boolean garantia = sc.nextLine().equalsIgnoreCase("s");

        System.out.println("¿Desea accesorios? (s/n): ");
        boolean accesorios = sc.nextLine().equalsIgnoreCase("s");

        gestorPedidos.crearPedido(
                clientePedido,
                vehiculoSeleccionado,
                estrategiaEntrega,
                formaPago,
                datosFact,
                usuarioLogueado,
                equipamiento,
                garantia,
                accesorios);

        System.out.println("\nPedido registrado con éxito.");
    }

    private LocalDate pedirFechaLocalDate(Scanner sc, String prompt) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print(prompt);
            String línea = sc.nextLine();
            try {
                fecha = LocalDate.parse(línea, fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Usar: AAAA-MM-DD");
            }
        }
        return fecha;
    }

    private void menuGenerarInforme() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1) Generar informe CSV");
            System.out.println("2) Generar informe TXT");
            System.out.println("0) Salir");
            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    LocalDate desde = pedirFechaLocalDate(sc, "Fecha desde (YYYY-MM-DD): ");
                    LocalDate hasta = pedirFechaLocalDate(sc, "Fecha hasta (YYYY-MM-DD): ");
                    EstadoPedido estado = null;
                    System.out.print("Ruta CSV: ");
                    String rutaCsv = sc.nextLine();
                    //String rutaCsv = "C:\\temp\\reporte.csv";

                    try {
                        generarInformeCSV(desde, hasta, estado, rutaCsv);
                        System.out.println("Informe CSV generado en " + rutaCsv);
                    } catch (IOException e) {
                        System.err.println("Error al exportar CSV: " + e.getMessage());
                    }
                    break;

                case 2:
                    estado = null;
                    LocalDate d2 = pedirFechaLocalDate(sc, "Fecha desde (YYYY-MM-DD): ");
                    LocalDate h2 = pedirFechaLocalDate(sc, "Fecha hasta (YYYY-MM-DD): ");
                    System.out.print("Ruta TXT: ");
                    String rutaTxt = sc.nextLine();

                    try {
                        generarInformeTXT(d2, h2, estado, rutaTxt);
                        System.out.println("Informe TXT generado en " + rutaTxt);
                    } catch (IOException e) {
                        System.err.println("Error al exportar TXT: " + e.getMessage());
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String cUIT) {
        CUIT = cUIT;
    }

    public CatalogoVehiculos getCatalogoVehiculos() {
        return catalogoVehiculos;
    }

    public void setCatalogoVehiculos(CatalogoVehiculos catalogoVehiculos) {
        this.catalogoVehiculos = catalogoVehiculos;
    }

    public GestorVehiculos getGestorVehiculos() {
        return gestorVehiculos;
    }

    public void setGestorVehiculos(GestorVehiculos gestorVehiculos) {
        this.gestorVehiculos = gestorVehiculos;
    }

    public GestorPedidos getGestorPedidos() {
        return gestorPedidos;
    }

    public void setGestorPedidos(GestorPedidos gestorPedidos) {
        this.gestorPedidos = gestorPedidos;
    }

        public List<PedidoCompra> generarInforme(LocalDate desde,
            LocalDate hasta,
            EstadoPedido estado) {
        return reportador.generarInforme(desde, hasta, estado);
    }

    public void generarInformeCSV(LocalDate desde,
            LocalDate hasta,
            EstadoPedido estado,
            String rutaArchivo) throws IOException {
        List<PedidoCompra> pedidos = reportador.generarInforme(desde, hasta, estado);
        reportador.exportarCSV(pedidos, rutaArchivo);
    }

    public void generarInformeTXT(LocalDate desde,
            LocalDate hasta,
            EstadoPedido estado,
            String rutaArchivo) throws IOException {
        List<PedidoCompra> pedidos = reportador.generarInforme(desde, hasta, estado);
        reportador.exportarTXT(pedidos, rutaArchivo);
    }

}