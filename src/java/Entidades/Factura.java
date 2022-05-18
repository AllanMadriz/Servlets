package Entidades;

import java.util.Date;

public class Factura {
    
    //Atributos
    private int idFactura;
    private int idCliente;
    private int idEmpleado;
    private Date fechaFactura;
    private int totalMonto;
    private int descuento;
    private int montoPagar;
    private String tipoFactura;
    private boolean cancelada;

    //COnstructores
    public Factura() {

    }

    public Factura(int idFactura, int idCliente, int idEmpleado, Date fechaFactura, int totalMonto, int descuento, int montoPagar, String tipoFactura, boolean cancelada) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.fechaFactura = fechaFactura;
        this.totalMonto = totalMonto;
        this.descuento = descuento;
        this.montoPagar = montoPagar;
        this.tipoFactura = tipoFactura;
        this.cancelada = cancelada;
    }

    //Metodos get y set
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public int getTotalMonto() {
        return totalMonto;
    }

    public void setTotalMonto(int totalMonto) {
        this.totalMonto = totalMonto;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(int montoPagar) {
        this.montoPagar = montoPagar;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }
}
