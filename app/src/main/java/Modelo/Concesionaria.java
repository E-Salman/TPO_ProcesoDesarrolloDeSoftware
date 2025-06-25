package Modelo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Estado.EstadoPedido;
import EstrategiaEntrega.EstrategiaEntrega;
import FormaPago.FormaPago;
import FormaPago.PagoContado;
import FormaPago.PagoTarjeta;
import FormaPago.PagoTransferencia;
import Usuario.Administrador;
import Usuario.Comprador;
import Usuario.CreadorAdministrador;
import Usuario.CreadorComprador;
import Usuario.CreadorUsuario;
import Usuario.CreadorVendedor;
import Usuario.Usuario;
import Usuario.Vendedor;
import Vehiculos.Auto;
import Vehiculos.Camion;
import Vehiculos.Camioneta;
import Vehiculos.CreadorAuto;
import Vehiculos.Moto;
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
        gestorVehiculos = new GestorVehiculos();
        gestorPedidos = new GestorPedidos();
        usuarios = new ArrayList<Usuario>();
        clientes = new ArrayList<Cliente>();
        creadorAdministrador = new CreadorAdministrador();
        creadorComprador = new CreadorComprador();
        creadorVendedor = new CreadorVendedor();
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
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombre)) {
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
                    catalogoVehiculos.toString();
                    break;
                case 2:
                    gestorPedidos.crearPedido(null, null, null, null, null, null, false, false, false);
                    break;
                case 3:
                    gestorPedidos.listarPedidos().toString();
                    break;
                case 4:
                    // lógica de generar informe
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
                    catalogoVehiculos.toString();
                    break;

                case 2:
                    gestorPedidos.listarPedidosComprador(comprador).toString();
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

        Cliente clientePedido;
        if (nCliente.equalsIgnoreCase("no")) {
            System.out.println("\nIngrese el email del cliente");
            String emailTemp = sc.nextLine();
            for (Cliente cliente : clientes) {
                if (cliente.getEmail().equalsIgnoreCase(emailTemp)) {
                    clientePedido = cliente;
                    break;
                }
            }
            if (clientePedido == null)
                break;

        } else {

            // Ingreso de Cliente
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
        }
        // Selección de vehículo
        System.out.println("\nVehículos disponibles:");
        List<Vehiculo> disponibles = catalogoVehiculos.getVehiculos();
        for (int i = 0; i < disponibles.size(); i++) {
            System.out.println(i + ". " + disponibles.get(i).toString());
        }
        System.out.print("Seleccione el número de vehículo: ");
        int indexVehiculo = sc.nextInt();
        sc.nextLine();
        Vehiculo vehiculoSeleccionado = disponibles.get(indexVehiculo);

        // Estrategia de entrega
        System.out.println("\nSeleccione tipo de entrega:");
        System.out.println("1. Retiro en concesionaria");
        System.out.println("2. Envío a domicilio");
        int tipoEntrega = sc.nextInt();
        sc.nextLine();
        EstrategiaEntrega estrategiaEntrega = (tipoEntrega == 1)
                ? new RetiroEnConcesionaria()
                : new EnvioADomicilio();

        // Forma de pago
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

        // Datos de facturación
        System.out.print("CUIT para facturación: ");
        String cuit = sc.nextLine();
        System.out.print("Condición frente al IVA: ");
        String condicionIVA = sc.nextLine();
        DatosFacturacion datosFact = new DatosFacturacion(cuit, condicionIVA);

        // Selección de vendedor (simplificada)
        System.out.println("\nIngrese nombre del vendedor asignado: ");
        String nombreVendedor = sc.nextLine();
        Vendedor vendedor = new Vendedor(nombreVendedor);

        // Opcionales
        System.out.println("\n¿Desea agregar equipamiento extra? (s/n): ");
        boolean equipamiento = sc.nextLine().equalsIgnoreCase("s");

        System.out.println("¿Desea garantía extendida? (s/n): ");
        boolean garantia = sc.nextLine().equalsIgnoreCase("s");

        System.out.println("¿Desea accesorios? (s/n): ");
        boolean accesorios = sc.nextLine().equalsIgnoreCase("s");

        // Registrar pedido
        gestorPedidos.crearPedido(
                cliente,
                vehiculoSeleccionado,
                estrategiaEntrega,
                formaPago,
                datosFact,
                vendedor,
                equipamiento,
                garantia,
                accesorios);

        System.out.println("\n✅ Pedido registrado con éxito.");
    }

    private LocalDateTime pedirFecha(Scanner sc, String prompt) {
        System.out.print(prompt);
        return LocalDateTime.parse(sc.nextLine());
    }

    private void menuGenerarInforme(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1) Generar informe CSV");
            System.out.println("2) Generar informe TXT");
            System.out.println("0) Salir");
            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    LocalDateTime desde = pedirFecha(sc, "Fecha desde (YYYY-MM-DDTHH:MM): ");
                    LocalDateTime hasta = pedirFecha(sc, "Fecha hasta (YYYY-MM-DDTHH:MM): ");
                    EstadoPedido estado = null;
                    System.out.print("Ruta CSV: ");
                    String rutaCsv = sc.nextLine();

                    try {
                        ((Administrador)usuarioLogueado).generarInformeCSV(desde, hasta, estado, rutaCsv);
                        System.out.println("Informe CSV generado en " + rutaCsv);
                    } catch (IOException e) {
                        System.err.println("Error al exportar CSV: " + e.getMessage());
                    }
                    break;

                case 2:
                    estado = null;
                    LocalDateTime d2 = pedirFecha(sc, "Fecha desde: ");
                    LocalDateTime h2 = pedirFecha(sc, "Fecha hasta: ");
                    System.out.print("Ruta TXT: ");
                    String rutaTxt = sc.nextLine();

                    try {
                       ((Administrador)usuarioLogueado).generarInformeTXT(d2, h2, estado, rutaTxt);
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

    public void menuLogueado() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE CONCESIONARIA ===");
            System.out.println("1. Listar vehículos");
            System.out.println("2. Agregar vehículo");
            System.out.println("3. Registrar pedido");
            System.out.println("4. Consultar pedidos");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    catalogoVehiculos.toString();
                    break;

                case 2:
                    System.out.print("Ingrese tipo (Auto/Camion/Camioneta/Moto): ");
                    String tipo = sc.nextLine();
                    System.out.print("Ingrese modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Ingrese marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Ingrese el numero de chasis: ");
                    String chasis = sc.nextLine();
                    System.out.print("Ingrese el numero del motor: ");
                    String motor = sc.nextLine();
                    System.out.print("Ingrese el color del auto: ");
                    String color = sc.nextLine();
                    System.out.print("Ingrese precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    switch (tipo.toLowerCase()) {
                        case "auto":
                            gestorVehiculos.registrarVehiculo(new Auto(marca, modelo, color, chasis, motor, precio));
                            break;

                        case "camion":
                            gestorVehiculos.registrarVehiculo(new Camion(marca, modelo, color, chasis, motor, precio));
                            break;

                        case "camioneta":
                            gestorVehiculos
                                    .registrarVehiculo(new Camioneta(marca, modelo, color, chasis, motor, precio));
                            break;

                        case "moto":
                            gestorVehiculos.registrarVehiculo(new Moto(marca, modelo, color, chasis, motor, precio));
                            break;

                        default:
                            break;
                    }
                    break;

                case 3:
                    System.out.print("Ingrese DNI del cliente: ");
                    String dni = sc.nextLine();
                    gestorPedidos.crearPedido(null, null, null, null, null, null, false, false, false);
                    break;

                case 4:
                    gestorPedidos.listarPedidos()();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
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
