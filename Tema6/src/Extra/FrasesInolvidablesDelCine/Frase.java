package Extra.FrasesInolvidablesDelCine;

import java.time.LocalDate;

public class Frase {
    private String textoFrase;
    private String nombrePelicula;
    private String actor;
    private LocalDate fechaIncorporacion;
    private int valoracion;

    public Frase(String textoFrase, String nombrePelicula, String actor, LocalDate fechaIncorporacion, int valoracion) {
        this.textoFrase = textoFrase;
        this.nombrePelicula = nombrePelicula;
        this.actor = actor;
        this.fechaIncorporacion = fechaIncorporacion;
        this.valoracion = valoracion;
    }
}
