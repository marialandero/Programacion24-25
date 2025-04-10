package Extra.ResenasCine;

import java.util.*;

public class Pelicula {

    // Creamos los atributos
    private String titulo;
    private int anoEstreno;
    private String director;
    private String genero;
    private Set<Resena> resenas;

    // Creamos el constructor
    public Pelicula(String titulo, int anoEstreno, String director, String genero) throws PeliculaException {
        this.titulo = titulo;
        setAnoEstreno(anoEstreno);
        this.director = director;
        this.genero = genero;
        this.resenas = new HashSet<>();
    }

    // Hacemos los get y set
    public String getTitulo() {
        return titulo;
    }

    public int getAnoEstreno() {
        return anoEstreno;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    private void setAnoEstreno(int anoEstreno) throws PeliculaException {
        if (anoEstreno < 1975 || anoEstreno > 2025) {
            throw new PeliculaException("El año debe estar en una fecha válida");
        }
        this.anoEstreno = anoEstreno;
    }

    /**
     * Auxiliar
     * @param r
     * @return
     */
    public void addResena (Resena r) throws PeliculaException{
        if(resenas.contains(r)){
            throw new PeliculaException("La reseña ya existe para esta pelicula");
        }
        resenas.add(r);
    }

    public void removeResenas (Collection<Resena> resenasAQuitar) throws PeliculaException{
        if(!resenas.containsAll(resenasAQuitar)){
            throw new PeliculaException("Algunas de las reseñas que se desean quitar no existen");
        }
        this.resenas.removeAll(resenasAQuitar);
    }

    public void removeResenasV(Collection<Resena> resenasAQuitar){
        this.resenas.removeAll(resenasAQuitar);
    }

    public Double valoracionMedia(){
        return resenas.stream().mapToInt(Resena::getValoracion).average().orElse(0);
    }

    // Auxiliar tiene valoracion negativa
    public boolean valoracionNegativa(){
        return resenas.stream()
                .mapToInt(Resena::getValoracion)
                .filter(valoracion -> valoracion <= 3).findAny().isPresent();
    }

    public List<Resena> getResenasDeUsuario(String usuario){
        return resenas.stream()
                .filter(resena -> resena.getUsuario().equalsIgnoreCase(usuario.trim()))
                .toList();
    }

    public Set<Resena> getResenas() {
        return resenas;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null && getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return anoEstreno == pelicula.anoEstreno && Objects.equals(titulo, pelicula.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, anoEstreno);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Título: %s, Año estreno: %d, Director: %s, Género: %s, Reseñas: %s",
                this.titulo, this.anoEstreno, this.director, this.genero, this.resenas);
    }
}
