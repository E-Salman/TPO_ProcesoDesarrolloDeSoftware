package Modelo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
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
import Usuario.Comprador;
import Usuario.CreadorAdministrador;
import Usuario.CreadorComprador;
import Usuario.CreadorVendedor;
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
    private CreadorAdministrador creadorAdministrador;
    private CreadorComprador creadorComprador;
    private CreadorVendedor creadorVendedor;
    private Usuario usuarioLogueado;

    public Concesionaria() {
        catalogoVehiculos = new CatalogoVehiculos();
        gestorVehiculos = new GestorVehiculos(catalogoVehiculos);
        gestorPedidos = new GestorPedidos();
        usuarios = new ArrayList<Usuario>();
        clientes = new ArrayList<Cliente>();
        creadorAdministrador = new CreadorAdministrador(new ReportesPedidos(gestorPedidos));
        creadorComprador = new CreadorComprador();
        creadorVendedor = new CreadorVendedor();
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
            int opcion = sc.nextInt();
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
                nuevo = creadorAdministrador.crearUsuario(nombre, email);
                break;

            case "vendedor":
                nuevo = creadorVendedor.crearUsuario(nombre, email);
                break;

            case "comprador":
                System.out.print("Ingrese apellido del cliente: ");
                String apellido = sc.nextLine();

                System.out.print("Ingrese DNI del cliente: ");
                String dni = sc.nextLine();

                System.out.print("Ingrese telefono del cliente: ");
                String telefono = sc.nextLine();
                creadorComprador.setCliente(new Cliente(nombre, apellido, dni, email, telefono));
                nuevo = creadorComprador.crearUsuario(nombre, email);
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
        } else if (usuario instanceof Comprador) {
            menuComprador((Comprador) usuario, sc);
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
            System.out.println("0. Cerrar sesión");

            opcion = sc.nextInt();
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
            }
        } while (opcion != 0);
    }

    private void menuComprador(Comprador comprador, Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Menú Comprador ---");
            System.out.println("1. Ver vehículos disponibles");
            System.out.println("2. Ver mis pedidos");
            System.out.println("0. Cerrar sesión");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(catalogoVehiculos.toString());
                    ;
                    break;

                case 2:
                    System.out.println(gestorPedidos.listarPedidosComprador(comprador).toString());
                    break;
            }
        } while (opcion != 0);
    }

    private void menuVendedor(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Menú Vendedor ---");
            System.out.println("1. Ver vehículos disponibles");
            System.out.println("0. Cerrar sesión");

            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                catalogoVehiculos.toString();
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

            clientePedido = new Cliente(nombre, apellido, dni, email, telefono);
            creadorComprador.setCliente(clientePedido);
            creadorComprador.crearUsuario(nombre, email);
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
            case 1 -> new PagoTarjeta();
            case 2 -> new PagoTransferencia();
            case 3 -> new PagoContado();
            default -> {
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
                        ((Administrador) usuarioLogueado).generarInformeCSV(desde, hasta, estado, rutaCsv);
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
                        ((Administrador) usuarioLogueado).generarInformeTXT(d2, h2, estado, rutaTxt);
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
}
