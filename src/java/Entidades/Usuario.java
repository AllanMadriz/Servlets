package Entidades;

public class Usuario {
    
    //Atributos
    private int idUsuario;
    private int idUsuarioRol;
    private String correo;
    private String password;
    private boolean activo;

    //Constructores
    public Usuario() {

    }

    public Usuario(int idUsuario, int idUsuarioRol, String correo, String password, boolean activo) {
        this.idUsuario = idUsuario;
        this.idUsuarioRol = idUsuarioRol;
        this.correo = correo;
        this.password = password;
        this.activo = activo;
    }

    //Metodos get y set
    public int getIdUsuario() {

        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {

        this.idUsuario = idUsuario;
    }

    public int getIdUsuarioRol() {

        return idUsuarioRol;
    }

    public void setIdUsuarioRol(int idUsuarioRol) {

        this.idUsuarioRol = idUsuarioRol;
    }

    public String getCorreo() {

        return correo;
    }

    public void setCorreo(String correo) {

        this.correo = correo;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public boolean isActivo() {

        return activo;
    }

    public void setActivo(boolean activo) {

        this.activo = activo;
    }
}
