package Extra.FrasesInolvidablesDelCine;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BibiliotecaDeCine {
    private List<Categoria> listaDeCategorias; // Lista principal

    public BibiliotecaDeCine() {
        listaDeCategorias = new LinkedList<>(); // Porque añade y elimina de forma mas eficiente
    }

    /**
     * Método 1: addCategoria
     * Comprueba si existe la categoria y si existe devuelve una excepcion,
     * si pasa la comprobacion añade la categoria a la lista
     * @param categoriaNueva
     * @throws CineException
     */
    public void addCategoria(Categoria categoriaNueva) throws CineException {
        if(listaDeCategorias.contains(categoriaNueva)){
            throw new CineException("Ya existe la categoria en la lista");
        }
        listaDeCategorias.add(categoriaNueva);
    }

    /**
     * Método 2: addFrase (1 punto)
     * Este metodo se encarga de añadir una frase a una lista de categorías. Se añadirá a aquellas categorías en las que
     * todavía no exista. Si alguna de las categorías pasadas no existe, se lanzara una
     * excepción sin meter la frase en ninguna de las demás categorías donde pueda existir
     */
    public void addFrase(List<Categoria> categorias, Frase fraseAInsertar) throws CineException {
        String categoriasQueNoEstan = "";
        categoriasQueNoEstan = categorias.stream()
                .filter(categoriaDeLaListaDelMedodo -> listaDeCategorias.stream()
                        .noneMatch(categoriaDeLaListaPrincipal -> categoriaDeLaListaPrincipal.equals(categoriaDeLaListaDelMedodo)))
                .map(Categoria::toString)
                .collect(Collectors.joining("\n"));

        if(!categoriasQueNoEstan.isEmpty()){
            throw new CineException("Existen las siguientes categorias que no estan en el conjunto de categorias %s".formatted(categoriasQueNoEstan));
        }

        listaDeCategorias.stream()
                .filter(categoriaDeLaListaPrincipal -> categorias.stream()
                        .anyMatch(categoriaDeLaListaPrincipal::equals))
                .filter(categoria -> !categoria.existeFrase(fraseAInsertar))
                .forEach(categoria -> categoria.addFrase(fraseAInsertar));

        // Un flujo que mete en todas las categorias las frases
        // categorias.stream().filter(categoria -> !categoria.existeFrase(fraseAInsertar))
        //        .forEach(categoria -> categoria.addFrase(fraseAInsertar));
    }

    /*
     * Método 3: categoriasConFrasesSinValoracion
     * Este metodo devuelve un conjunto de categorias (Set) que tiene alguna frase sin valoracion
     */
    public Set<Categoria> categoriasQueContienenAlgunaFraseSinValoracion() throws CineException {
        if(listaDeCategorias.isEmpty()){
            throw new CineException("La lista de categorias esta vacia");
        }
        return listaDeCategorias.stream().filter(categoria -> categoria.todasLasFrasesDeLaCategoria()
                        .stream()
                        .anyMatch(frase -> frase.getValoracion()==0))
                .collect(Collectors.toSet());
    }

    /*
     * Método 4: categoriasDeFrase
     * Este metodo devuelve un conjunto de categorias que contienen una frase determinada
     */
    public Set<Categoria> categoriasALasQuePerteneceUnaFrase(Frase frase) throws CineException {
        if(listaDeCategorias.isEmpty()){
            throw new CineException("La lista de categorias esta vacia");
        }
        return listaDeCategorias.stream()
                .filter(categoria -> categoria.existeFrase(frase))
                .collect(Collectors.toSet());
    }

    /* Método 5: getTodasLasFrasesOrdenadasPorValoracion
     * Orden natural es de menor a mayor por eso uso un comparador inverso
     */
    public List<Frase> getTodasLasFrasesOrdenadasPorValoracion() throws CineException {
        if(listaDeCategorias.isEmpty()){
            throw new CineException("La lista de categorias esta vacia");
        }
        return listaDeCategorias.stream()
                .flatMap(categoria -> categoria.todasLasFrasesDeLaCategoria().stream())
                .distinct() // Como una frase puede estar en varias categorias es necesario usar distinct()
                .sorted(Comparator.comparingDouble(Frase::getValoracion).reversed()) // Mayor a menor
                .toList();
    }

    /* Método 6: eliminaFrase (0.8 puntos)
     * Elimina una frase de todas las categorías donde aparezca.
     */
    public void eliminaFrase(Frase frase) throws CineException {
        if(listaDeCategorias.isEmpty()){
            throw new CineException("La lista de categorias esta vacia");
        }
        listaDeCategorias.stream()
                .filter(categoria -> categoria.existeFrase(frase))
                .forEach(categoria -> categoria.removeFrase(frase));
    }

    /**
     * Método 7: frasesUltimoAnno (1.5 puntos)
     * Devuelve un conjunto con aquellas frases que han sido añadidas en el último año (a partir
     * de la fecha actual)
     * @return
     */
    public Set<Frase> frasesUltimoAnno() throws CineException {
        if(listaDeCategorias.isEmpty()){
            throw new CineException("La lista de categorias esta vacia");
        }
        return listaDeCategorias.stream()
                .flatMap(categoria -> categoria.todasLasFrasesDeLaCategoria().stream())
                .distinct() // Como una frase puede estar en varias categorias es necesario usar distinct()
                .filter(frase -> frase.getFechaIncorporacion().plusYears(1).isAfter(LocalDate.now().minusDays(1)))
                .collect(Collectors.toSet());
    }

    /**
     * Ejercicio Opcional 1: Filtrado de Frases
     * Implementa un método que devuelva una lista de frases cuya valoración sea superior a un
     * valor dado. Utiliza flujos (Stream API) para filtrar las frases.
     * @param puntuacion
     * @return
     */
    public List<Frase> frasesConUnaPuntuacionDe(int puntuacion) throws CineException {

        if(listaDeCategorias.isEmpty()){
            throw new CineException("La lista de categorias esta vacia");
        }

        List<Frase> fraseConPuntuacion = listaDeCategorias.stream()
                .flatMap(categoria -> categoria.todasLasFrasesDeLaCategoria().stream()) // Obtener todas las frases
                .distinct() // Eliminar duplicados
                .filter(frase -> frase.getValoracion() > puntuacion) // Filtrar por puntuación
                .toList();

        if(fraseConPuntuacion.isEmpty()){
            throw new CineException("No existen puntuaciones superiores a %d".formatted(puntuacion));
        }

        return fraseConPuntuacion;

        /* Solucion con Optional
          return Optional.of(listaDeCategorias.stream()
                        .flatMap(categoria -> categoria.todasLasFrasesDeLaCategoria().stream()) // Obtener todas las frases
                        .distinct() // Eliminar duplicados
                        .filter(frase -> frase.getValoracion() > puntuacion) // Filtrar por puntuación
                        .toList()) // Convertir a lista
                .filter(list -> !list.isEmpty())// Si la condición es falsa (false), el Optional se convierte en un Optional vacío
                .orElseThrow(() -> new CineException("No existen puntuaciones superiores a %d".formatted(puntuacion)));
                // () -> representa a ese Optional Vacio */
    }

    /*
     * Ejercicio Opcional 2: Estadísticas de Valoración
     * Implementa un método que calcule estadísticas sobre las frases, como:
     * • Valoración promedio de todas las frases.
     * • Frase con la valoración más alta. Utiliza flujos para procesar los datos.
     */
    public Map<String,Object> estadisticasDeValoracion() throws CineException {
        double media = listaDeCategorias.stream().flatMap(categoria -> categoria.todasLasFrasesDeLaCategoria().stream()) // Obtener todas las frases
                .distinct()
                .mapToDouble(Frase::getValoracion)
                .average()
                .orElseThrow(() -> new CineException("No hay datos para calcular la media")); // Eliminar duplicados

        Frase frase = listaDeCategorias.stream().flatMap(categoria -> categoria.todasLasFrasesDeLaCategoria().stream()) // Obtener todas las frases
                .distinct()
                .max(Comparator.comparingInt(Frase::getValoracion))
                .orElseThrow(() -> new CineException("No hay datos para hallar la frase con mayor valoración"));

        Map<String,Object> resultados = new HashMap<>();

        resultados.put("Media",media);
        resultados.put("FraseMayorPuntuacion",frase);

        return resultados;
    }

    // public Map<String,List<Frase>>
}
