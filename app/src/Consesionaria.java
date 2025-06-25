package tpo_procesodesarrollodesoftware;

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
}