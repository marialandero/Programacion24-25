package Extra.AgenciaDeViajes;

import java.util.LinkedList;
import java.util.List;


public class Ruta {
    private String nombre;
    private String destino;
    private List<String> paradas;

    public Ruta(String nombre, String destino) {
        this.nombre = nombre;
        this.destino = destino;
        paradas = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDestino() {
        return destino;
    }

    public List<String> getParadas() {
        return paradas;
    }

    /**
     * Este metodo no admite valores nulos
     * @param parada
     */
    public void addParada(String parada) throws AgenciaException {
        if(paradas.stream().anyMatch(p -> p.equalsIgnoreCase(parada.trim()))){
            throw new AgenciaException("La parada ya existe");
        }
        paradas.add(parada.trim());
    }

    public void eliminarParada(String parada) throws AgenciaException {
        if(paradas.stream().noneMatch(p -> p.equalsIgnoreCase(parada.trim()))){
            throw new AgenciaException("La parada no existe");
        }

        paradas.remove(parada.trim());

        /* Solución básica
         if(!paradas.remove(parada)){
            throw new AgenciaException("La parada no existe");
        }*/
    }

    public boolean contieneParada(String nombreParada){
        return paradas.stream().anyMatch(p -> p.equalsIgnoreCase(nombreParada.trim()));
    }

    @Override
    public String toString() {
        return  "nombre='" + nombre + "\n" +
                "destino='" + destino + "\n" +
                "paradas=" + String.join(",", paradas) + "\n";
    }
}
