package Boletin6_1.Ejercicio7;

import java.util.*;

public class Receta {
    private String nombreReceta;
    private int tiempoPreparacion;
    private Set<Ingrediente> listaIngredientes;
    private List<String> pasos;

    public Receta(String nombreReceta, int tiempoPreparacion, Set<Ingrediente> listaIngredientes, List<String> pasos) {
        this.nombreReceta = nombreReceta;
        this.tiempoPreparacion = tiempoPreparacion;
        this.listaIngredientes = new HashSet<>();
        this.pasos = new LinkedList<>();
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Set<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(Set<Ingrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    public List<String> getPasos() {
        return pasos;
    }

    public void setPasos(List<String> pasos) {
        this.pasos = pasos;
    }

    public boolean necesitaIngrediente(String nombreIngrediente) throws RecetaException {

        return listaIngredientes.stream()
                .anyMatch(i -> i.getNombreIngrediente().equalsIgnoreCase(nombreIngrediente));
        /*
        return listaIngredientes.stream().
                map(i -> i.getNombreIngrediente())
                .filter(i -> i.equalsIgnoreCase(nombreIngrediente))
                .count() > 0;
         */
    }

    public void annadirIngrediente(Ingrediente ingredienteNuevo) throws RecetaException {

        if (listaIngredientes == null) {
            throw new RecetaException("La lista está vacía.");
        }
        boolean ingredienteEncontrado = false;
        // Se recorre la lista de ingredientes hasta encontrarlo.
        for (Ingrediente i : listaIngredientes) {
            if (i.getNombreIngrediente().equalsIgnoreCase(ingredienteNuevo.getNombreIngrediente())) {
                i.setCantidadIngrediente(i.getCantidadIngrediente()+ingredienteNuevo.getCantidadIngrediente());
                ingredienteEncontrado = true;
            }
        }
        if (!ingredienteEncontrado) {
            listaIngredientes.add(ingredienteNuevo);
        }
    }

    public void borrarIngrediente(Ingrediente ingredienteABorrar) throws RecetaException {

        if (listaIngredientes == null || listaIngredientes.isEmpty()) {
            throw new RecetaException("La lista está vacía.");
        }
        if (listaIngredientes.remove(ingredienteABorrar)) {
            ListIterator<String> iterator = pasos.listIterator();
            while (iterator.hasNext()) {
                String paso = iterator.next();
                if (paso.contains(ingredienteABorrar.getNombreIngrediente())) {
                    iterator.remove();
                }
            }
        }
    }

    public void annadirPasoDetrasDe(String pasoNuevo, String pasoExistente) throws RecetaException {

    }
}
