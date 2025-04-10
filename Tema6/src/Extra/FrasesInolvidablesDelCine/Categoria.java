package Extra.FrasesInolvidablesDelCine;

import java.util.LinkedHashSet;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Categoria {
    private String nombre;
    private Set<Frase> conjuntoDeFrases;

    public Categoria(String nombre) {
        this.nombre = nombre;
        // LinkedHashSet porque hay eliminaciones y adiciones
        // y es mas eficiente en este aspecto
        conjuntoDeFrases = new LinkedHashSet<>();
    }

    /**
     * Metodo auxiliar para añadir frase nueva
     * devuelve false si la frase ya existe en el conjuntoDeFrases
     * devuelve true si consigue añadir la frase al conjunto de frases
     * @param fraseNueva
     * @throws CineException
     */
    public boolean addFrase(Frase fraseNueva) {
        return conjuntoDeFrases.add(fraseNueva);
    }

    /**
     * Metodo auxiliar para añadir quitar una frase
     * devuelve false si la frase no ha sido eliminada del conjuntoDeFrases (porque no existe)
     * devuelve true si consigue eliminar la frase al conjunto de frases
     * @param fraseAQuitar
     * @throws CineException
     */
    public boolean removeFrase(Frase fraseAQuitar){
        return conjuntoDeFrases.remove(fraseAQuitar);
    }

    /**
     * Este metodo comprueba que no exista la frase en el conjunto de frases
     * si es nulo el argumento devuelve o no existe devuelve false, si existe
     * devuelve true
     * @param frase
     * @return
     */
    public boolean existeFrase(Frase frase){
        if(frase==null) {
            return false;
        }
        return conjuntoDeFrases.contains(frase);
    }

    /**
     * Este método auxiliar retorna una lista de frases de la categoria
     * no expongo el conjunto de datos
     * @return
     */
    public List<Frase> todasLasFrasesDeLaCategoria(){
        return conjuntoDeFrases.stream().toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria categoria)) return false;
        return Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return "Categoria: " + nombre;
    }
}