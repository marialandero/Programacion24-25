package Extra.PlataformaStreaming;

import java.util.*;

public class Usuario {
    private String nombre;
    private String correo;
    private Set<Serie> seriesEnSeguimiento;

    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.seriesEnSeguimiento = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Set<Serie> getSeriesEnSeguimiento() {
        return seriesEnSeguimiento;
    }

    public void setSeriesEnSeguimiento(Set<Serie> seriesEnSeguimiento) {
        this.seriesEnSeguimiento = seriesEnSeguimiento;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", seriesEnSeguimiento=" + seriesEnSeguimiento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) && Objects.equals(correo, usuario.correo) && Objects.equals(seriesEnSeguimiento, usuario.seriesEnSeguimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, correo, seriesEnSeguimiento);
    }
}
