package Modelo;

public class Consesionaria {
    private String nombre;
    private String CUIT;
    private CatalogoVehiculos catalogoVehiculos;
    private GestorVehiculos gestorVehiculos;    
    private GestorPedidos gestorPedidos;

    public Consesionaria(){
        catalogoVehiculos = new CatalogoVehiculos();
        gestorVehiculos = new GestorVehiculos();
        gestorPedidos = new GestorPedidos();
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