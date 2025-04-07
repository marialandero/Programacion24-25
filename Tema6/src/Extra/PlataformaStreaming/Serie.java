package Extra.PlataformaStreaming;

import java.util.*;

public class Serie {
    private String titulo;
    private String genero;
    private int temporadas;
    private Set<Usuario> suscriptores;

    public Serie(String titulo, String genero, int temporadas, Set<Usuario> suscriptores) {
        this.titulo = titulo;
        this.genero = genero;
        this.temporadas = temporadas;
        this.suscriptores = new HashSet<>();
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

    public Set<Usuario> getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(Set<Usuario> suscriptores) {
        this.suscriptores = suscriptores;
    }

    public void anyadirSuscriptor(Usuario seguidor) throws PlataformaException {
        if (suscriptores.contains(seguidor)) {
            throw new PlataformaException("El usuario ya está suscrito a la serie.");
        }
        suscriptores.add(seguidor);
    }

    public void eliminarSuscriptor(Usuario seguidor) throws PlataformaException {
        if (!suscriptores.contains(seguidor)) {
            throw new PlataformaException("El usuario no sigue la serie.");
        }
        suscriptores.remove(seguidor);
    }

    @Override
    public String toString() {
        return "Serie{" +
                "titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", temporadas=" + temporadas +
                ", suscriptores=" + suscriptores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return temporadas == serie.temporadas && Objects.equals(titulo, serie.titulo) && Objects.equals(genero, serie.genero) && Objects.equals(suscriptores, serie.suscriptores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, genero, temporadas, suscriptores);
    }
}
