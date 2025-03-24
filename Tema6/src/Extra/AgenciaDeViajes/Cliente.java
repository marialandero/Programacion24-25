package Extra.AgenciaDeViajes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cliente {
    private String nombre;
    private int id;
    private static int idContador = 0;
    private Map<String,Ruta> rutas;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.id = ++idContador;
        rutas = new HashMap<>();
    }

    // Getters y Setters

    public Map<String, Ruta> getRutas() {
        return rutas;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Comprueba si no existe la ruta y la añade al mapa
     * si la ruta ya existe arroja una excepción
     * @param ruta
     * @throws AgenciaException
     */
    public void addRuta(Ruta ruta) throws AgenciaException{
        if(rutas.containsKey(ruta.getNombre())){
            throw new AgenciaException("La ruta ya existe");
        }
        rutas.put(ruta.getNombre(),ruta);
    }

    /**
     * Comprueba si existe la ruta y la borra del mapa
     * si la ruta no existe en el mapa arroja una excepción
     * @param ruta
     * @throws AgenciaException
     */
    public void removeRuta(Ruta ruta) throws AgenciaException {
        if(!rutas.containsKey(ruta.getNombre())){
            throw new AgenciaException("La ruta no existe");
        }
        rutas.remove(ruta.getNombre());
    }

    /**
     * Este metodo una parada a una ruta concreta de un cliente.
     * Si la parada ya existe en la ruta, no se duplica, si la parada no existe
     * arroja una excepcion
     * @param nombreRuta
     * @param parada
     * @throws AgenciaException
     */
    public void addParadaARuta(String nombreRuta, String parada) throws AgenciaException {
        Ruta rutaBuscada = rutas.get(nombreRuta);
        if(rutaBuscada!=null){
            rutaBuscada.addParada(parada);
        }else{
            throw new AgenciaException("La ruta no existe");
        }
    }

    /**
     *
     * @param nombreRuta
     * @param parada
     * @throws AgenciaException
     */
    public void eliminarParadaDeRuta(String nombreRuta, String parada) throws AgenciaException {
        Ruta rutaBuscada = rutas.get(nombreRuta);
        if(rutaBuscada!=null){
            rutaBuscada.eliminarParada(parada);
        }else{
            throw new AgenciaException("La ruta no existe");
        }
    }

    /**
     *
     * @return
     */
    public List<String> getParadasUnicasOrdenadas(){
        return rutas.values().stream()
                .flatMap(ruta -> ruta.getParadas().stream())
                .distinct().sorted().toList();
    }

    public String mostrarRutas(){
        StringBuilder sb = new StringBuilder();
        rutas.values().forEach(ruta ->{
            sb.append(ruta.getNombre());
            sb.append("Destino:").append(ruta.getDestino());
            sb.append("Paradas:").append(ruta.getParadas().stream()
                    .sorted().collect(Collectors.joining(",")));
        });
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return id == cliente.id && Objects.equals(nombre, cliente.nombre) && Objects.equals(rutas, cliente.rutas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, id, rutas);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
