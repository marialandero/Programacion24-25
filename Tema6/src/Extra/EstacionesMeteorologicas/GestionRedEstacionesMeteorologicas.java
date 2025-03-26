package Extra.EstacionesMeteorologicas;

import java.time.LocalDateTime;
import java.util.Map;

public class GestionRedEstacionesMeteorologicas {
    public static void main(String[] args) {
        EstacionMeteorologica estacionMeteorologica = new EstacionMeteorologica("Huelva", 20.3);
        Sensor sensor1 = new Sensor(TipoSensor.HUMEDAD, 5);
        Sensor sensor2 = new Sensor(TipoSensor.VIENTO, 6);
        Sensor sensor3 = new Sensor(TipoSensor.TEMPERATURA, 7);
        Sensor sensor4 = new Sensor(TipoSensor.RADIACION, 8);

        estacionMeteorologica.addSensor(sensor1);
        estacionMeteorologica.addSensor(sensor2);
        estacionMeteorologica.addSensor(sensor3);
        estacionMeteorologica.addSensor(sensor4);

        estacionMeteorologica.eliminarSensor(sensor3);

        sensor1.tomarMedicion();

        sensor1.addMedicion(LocalDateTime.of(2003,06,16,16,06), 8.32);
        sensor2.addMedicion(LocalDateTime.of(1996,04,39,5,30), 21.5);
        sensor4.addMedicion(LocalDateTime.of(1964,02,05,9,15), 6.0);

        sensor1.addMediciones(Map.of(LocalDateTime.now(), 1.4, LocalDateTime.of(2000, 5, 17, 8, 34), 7.4));

    }



}
