package Entidades;

import java.util.Date;

public class Persona {

    //Clase para heredar a empleado y cliente, aqui nos daran los atributos base de cada uno
    private int cedula;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String direccionResidencia;
    private Date fechaNacimiento;
    private int telefono;
    private String genero;
    private int idUsuario;
    private boolean activo;

    //Constructores
    public Persona() {
    }

    public Persona(int cedula, String nombre, String primerApellido, String segundoApellido, String direccionResidencia, Date fechaNacimiento, int telefono, String genero,
            int idUsuario, boolean activo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.direccionResidencia = direccionResidencia;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.genero = genero;
        this.idUsuario = idUsuario;
        this.activo = activo;
    }

    //Metodos get y set
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
