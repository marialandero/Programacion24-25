package Extra.ResenasCine;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CriticasCine {
    private Set<Pelicula> peliculas;

    public CriticasCine() {
        peliculas = new HashSet<>();
    }

    public void addPelicula(Pelicula p) throws PeliculaException {
        if(!peliculas.add(p)){
            throw new PeliculaException("La pelicula ya existe en el conjunto");
        }
    }

    public void addResena(String titulo, int anno, Resena n) throws PeliculaException {
        peliculas.stream()
                .filter(pelicula -> pelicula.getTitulo().equalsIgnoreCase(titulo.trim()) && pelicula.getAnoEstreno()==anno)
                .findFirst().orElseThrow(() -> new PeliculaException("No existe la pelicula en el sistema")).addResena(n);
    }

    public List<Pelicula> getPeliculasConGenero(String genero){
        return peliculas.stream()
                .filter(pelicula -> pelicula.getGenero().equalsIgnoreCase(genero.trim()))
                .sorted(Comparator.comparing(Pelicula::getTitulo))
                .toList();
    }

    public double mediaValoraciones(String titulo, int anno) {
        return peliculas.stream()
                .filter(pelicula -> pelicula.getTitulo().equalsIgnoreCase(titulo.trim()) && pelicula.getAnoEstreno()==anno)
                .flatMap(pelicula -> pelicula.getResenas().stream())
                .mapToInt(Resena::getValoracion)
                .average()
                .orElse(0);
    }

    public Set<String> usuariosConResenasRecientes(){
        // Por partes peliculas del ultimo mes
        List<Resena> resenasUltimoMes = peliculas.stream()
                .flatMap(pelicula -> pelicula.getResenas().stream())
                .filter(resena -> resena.getFechaPublicacion().plusMonths(1).isAfter(LocalDate.now())).toList();

        return resenasUltimoMes.stream().map(Resena::getUsuario).collect(Collectors.toSet());
    }

    /*
     * (1.5 puntos): Devuelve una lista de películas ordenadas por su media de valoraciones de mayor a menor.
     */
    public List<Pelicula> getPeliculasOrdenadasPorValoracionMedia(){
        return peliculas.stream()
                .sorted(Comparator.comparingDouble(Pelicula::valoracionMedia))
                .toList().reversed();
    }

    /*
     * (1.2 puntos): Devuelve un conjunto de películas que tengan al menos una reseña con valoración menor o igual a 3.
     */
    public Set<Pelicula> peliculasConResenasNegativas(){
        return peliculas.stream()
                .filter(Pelicula::valoracionNegativa)
                .collect(Collectors.toSet());
    }

   /*   public void eliminarResenasDeUsuario(String usuario){
        List<Resena> resenasDeUsuarios = peliculas.stream().flatMap(pelicula -> pelicula.getResenasDeUsuario(usuario).stream()).toList();
        peliculas.forEach(pelicula ->  pelicula.removeResenasV(resenasDeUsuarios));
    } */

    // Delegar responsabilidad a Pelicula
    public void eliminarResenasDeUsuario(String usuario) {
        peliculas.forEach(pelicula ->
                pelicula.getResenas().removeIf(resena -> resena.getUsuario().equalsIgnoreCase(usuario))
        );
    }

    public List<String> getGenerosMasPopulares() {
        // Primer flujo: construir el mapa de género -> cantidad de películas
        Map<String, Long> conteoPorGenero = peliculas.stream()
                .collect(Collectors.groupingBy(
                        Pelicula::getGenero, // clave
                        Collectors.counting() // valor: cuántas veces aparece ese género
                ));

        // Segundo flujo: ordenar los géneros por cantidad descendente y obtener solo los nombres
        return conteoPorGenero.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // mayor a menor
                .map(Map.Entry::getKey) // extraemos solo el nombre del género
                .toList();

      /*  return conteoPorGenero.entrySet().stream()
                .sorted((valorA, valorB) -> {
                    if(valorA.getValue().compareTo(valorB.getValue())==0){
                        // Sin son iguales que se comparen por nombre
                    }
                }) // mayor a menor
                .map(Map.Entry::getKey) // extraemos solo el nombre del género
                .toList(); */
    }
}
