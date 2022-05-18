package Entidades;

import java.util.Date;

public class Empleado extends Persona {

    //Atributos
    private int idEmpleado;
    private Date fechaContratacion;

    //Constructores
    public Empleado() {
    }

    public Empleado(int idEmpleado, Date fechaContratacion, int cedula, String nombre, String primerApellido, String segundoApellido, String direccionResidencia, 
            Date fechaNacimiento,
            int telefono, String genero, int idUsuario, boolean activo) {
        super(cedula, nombre, primerApellido, segundoApellido, direccionResidencia, fechaNacimiento, telefono, genero, idUsuario, activo);
        this.idEmpleado = idEmpleado;
        this.fechaContratacion = fechaContratacion;
    }

    //Metodos get y set
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

}
