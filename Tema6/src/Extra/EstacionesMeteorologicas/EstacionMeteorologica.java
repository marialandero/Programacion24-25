package Extra.EstacionesMeteorologicas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EstacionMeteorologica {
    private int idUnico;
    private static int contadorSensores = 0;
    private String ubicacion;
    private double altitud;
    private Set<Sensor> conjuntoSensoresInstalados;

    public EstacionMeteorologica(String ubicacion, double altitud) {
        this.idUnico = contadorSensores++;
        this.ubicacion = ubicacion;
        this.altitud = altitud;
        this.conjuntoSensoresInstalados = new HashSet<>();
    }

    public int getIdUnico() {
        return idUnico;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getAltitud() {
        return altitud;
    }

    public boolean addSensor(Sensor sensor) {
        return conjuntoSensoresInstalados.add(sensor);
    }

    public boolean eliminarSensor(Sensor sensor) {
        return conjuntoSensoresInstalados.remove(sensor);
    }
}