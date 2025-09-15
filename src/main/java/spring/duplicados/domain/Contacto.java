package spring.duplicados.domain;

import java.util.Objects;

public class Contacto {
    private int idContacto;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String codigoPostal;
    private String direccion;

    public Contacto() {
    }

    public Contacto(int idContacto, String nombre, String apellido, String correoElectronico, String codigoPostal, String direccion) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.codigoPostal = codigoPostal;
        this.direccion = direccion;
    }

    // Getters
    public int getIdContacto() {
        return idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    // Setters
    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // toString
    @Override
    public String toString() {
        return "Contacto{" +
                "idContacto=" + idContacto +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacto)) return false;
        Contacto contacto = (Contacto) o;
        return idContacto == contacto.idContacto &&
                Objects.equals(nombre, contacto.nombre) &&
                Objects.equals(apellido, contacto.apellido) &&
                Objects.equals(correoElectronico, contacto.correoElectronico) &&
                Objects.equals(codigoPostal, contacto.codigoPostal) &&
                Objects.equals(direccion, contacto.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContacto, nombre, apellido, correoElectronico, codigoPostal, direccion);
    }
}
