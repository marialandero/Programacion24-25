package Extra.PlataformaStreaming;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String correo;
    private List<Serie> seriesEnSeguimiento;

    public Usuario(String nombre, String correo, List<Serie> seriesEnSeguimiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.seriesEnSeguimiento = new LinkedList<>();
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

    public List<Serie> getSeriesEnSeguimiento() {
        return seriesEnSeguimiento;
    }

    public void setSeriesEnSeguimiento(List<Serie> seriesEnSeguimiento) {
        this.seriesEnSeguimiento = seriesEnSeguimiento;
    }


}
