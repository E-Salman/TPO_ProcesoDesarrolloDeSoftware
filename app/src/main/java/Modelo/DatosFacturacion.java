package Modelo;

import java.util.regex.Pattern;

public class DatosFacturacion {
    private String razonSocial;
    private String direccion;
    private String cuit;

    public DatosFacturacion(String razonSocial, String direccion, String cuit) {
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public boolean validarCUIT() {
        return Pattern.matches("\\d{11}", this.cuit);
    }

    @Override
    public String toString() {
        return String.format(
                "Facturación{razonSocial='%s', direccion='%s', cuit='%s'}",
                razonSocial, direccion, cuit);
    }
}
