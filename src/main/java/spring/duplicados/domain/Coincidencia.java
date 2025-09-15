package spring.duplicados.domain;

import java.util.Objects;

public class Coincidencia {

    private int idContactoOrigen;
    private int idContactoCoincidencia;
    private String precision;

    // Constructor
    public Coincidencia(int idContactoOrigen, int idContactoCoincidencia, String precision) {
        this.idContactoOrigen = idContactoOrigen;
        this.idContactoCoincidencia = idContactoCoincidencia;
        this.precision = precision;
    }

    // Getters
    public int getIdContactoOrigen() {
        return idContactoOrigen;
    }

    public int getIdContactoCoincidencia() {
        return idContactoCoincidencia;
    }

    public String getPrecision() {
        return precision;
    }

    // Setters
    public void setIdContactoCoincidencia(int idContactoCoincidencia) {
        this.idContactoCoincidencia = idContactoCoincidencia;
    }

    public void setIdContactoOrigen(int idContactoOrigen) {
        this.idContactoOrigen = idContactoOrigen;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    // toString
    @Override
    public String toString() {
        return "Coincidencia{" +
                "idContactoOrigen=" + idContactoOrigen +
                ", idContactoCoincidencia=" + idContactoCoincidencia +
                ", precision='" + precision + '\'' +
                '}';
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coincidencia that = (Coincidencia) o;
        return idContactoOrigen == that.idContactoOrigen && idContactoCoincidencia == that.idContactoCoincidencia && Objects.equals(precision, that.precision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContactoOrigen, idContactoCoincidencia, precision);
    }
}
