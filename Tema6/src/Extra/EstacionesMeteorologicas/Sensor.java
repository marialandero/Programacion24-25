package Extra.EstacionesMeteorologicas;

import java.time.LocalDateTime;
import java.util.Map;

public class Sensor {
    private int codUnico;
    private TipoSensor tipo;
    private Map<LocalDateTime, Double> historialMediciones;

    public Sensor(TipoSensor tipo, int codUnico, Map<LocalDateTime, Double> historialMediciones) {
        this.tipo = tipo;
        this.codUnico = codUnico;
        this.historialMediciones = historialMediciones;
    }

    public int getCodUnico() {
        return codUnico;
    }

    public TipoSensor getTipo() {
        return tipo;
    }

    public void tomarMedicion() {
        historialMediciones.put(LocalDateTime.now(), Math.random());
    }
}
