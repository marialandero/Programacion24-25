package Extra.EstacionesMeteorologicas;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Sensor {
    private int codUnico;
    private TipoSensor tipo;
    private Map<LocalDateTime, Double> registroMediciones;

    public Sensor(TipoSensor tipo, int codUnico) {
        this.tipo = tipo;
        this.codUnico = codUnico;
        this.registroMediciones = new HashMap<>();
    }

    public int getCodUnico() {
        return codUnico;
    }

    public TipoSensor getTipo() {
        return tipo;
    }

    public void tomarMedicion() {
        registroMediciones.put(LocalDateTime.now(), Math.random());
    }

    public void addMedicion(LocalDateTime fechaYHora, double medicion) {
        registroMediciones.putIfAbsent(fechaYHora, medicion);
    }

    public void addMediciones(Map<LocalDateTime, Double> mediciones) {
        registroMediciones.putAll(mediciones);
    }

    public Map<LocalDateTime,Double> historicoMediciones() {
        return new TreeMap<>(registroMediciones).reversed();
        /*
        ESTA SOLUCIÓN NO SIRVE
        return registroMediciones.entrySet().stream()
                .sorted(Map.Entry.<LocalDateTime, Double>comparingByKey().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
         */

    }
}
