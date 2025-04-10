package Extra.FrasesInolvidablesDelCine;

import java.time.LocalDate;
import java.util.Objects;

public class Frase {
    private String texto;
    private String pelicula;
    private String actor;
    private LocalDate fechaIncorporacion;
    private int valoracion;

    public Frase(String texto, String pelicula, String actor, LocalDate fechaIncorporacion) {
        this.texto = texto;
        this.pelicula = pelicula;
        this.actor = actor;
        this.fechaIncorporacion = fechaIncorporacion;
        this.valoracion = 0;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public LocalDate getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frase frase)) return false;
        return Objects.equals(texto, frase.texto) && Objects.equals(pelicula, frase.pelicula) && Objects.equals(actor, frase.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texto, pelicula, actor);
    }

    @Override
    public String toString() {
        return "Frase{" +
                "texto='" + texto + '\'' +
                ", pelicula='" + pelicula + '\'' +
                ", actor='" + actor + '\'' +
                ", fechaIncorporacion=" + fechaIncorporacion +
                ", valoracion=" + valoracion +
                '}';
    }
}
