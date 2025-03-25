package Extra.EstacionesMeteorologicas;

import java.util.Set;

public class EstacionMeteorologica {
    private int idUnico;
    private static int contadorSensores = 0;
    private String ubicacion;
    private double altitud;
    private Set<Sensor> sensoresInstalados;

    public EstacionMeteorologica(String ubicacion, double altitud) {
        this.idUnico = contadorSensores++;
        this.ubicacion = ubicacion;
        this.altitud = altitud;
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

    public void anyadirSensor(Sensor sensor) {
        sensoresInstalados.add(sensor);
    }
}
