package Extra.PlataformaStreaming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Serie {
    private String titulo;
    private String genero;
    private int temporadas;
    private List<Usuario> suscriptores;

    public Serie(String titulo, String genero, int temporadas, List<Usuario> suscriptores) {
        this.titulo = titulo;
        this.genero = genero;
        this.temporadas = temporadas;
        this.suscriptores = new LinkedList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public List<Usuario> getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(List<Usuario> suscriptores) {
        this.suscriptores = suscriptores;
    }
}
