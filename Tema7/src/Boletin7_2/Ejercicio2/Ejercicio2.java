package Boletin7_2.Ejercicio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Realizar un programa que solicite un nombre de un fichero de texto y realice los
 * siguiente:
 *  Muestre por pantalla cuantas palabras tiene. Considerar que la palabra tiene
 * cualquier conjunto de caracteres y separada por un espacio o por un salto de
 * línea.
 *  Solicite una palabra para buscarla en el fichero. Si encuentra la palabra en el
 * fichero debe escribir un fichero de texto con el resultado de la búsqueda llamado
 * “BuscandoPalabraXXXXX.txt”
 * El fichero contendrá las líneas y columnas donde se encontró la palabra, por
 * ejemplo:
 * Encontrada en línea 2 columna 7
 * Encontrada en línea 3 columna 1
 */
public class Ejercicio2 {
    public static void main(String[] args) {
        String buscarPalabra = "mundo";
        Path ficheroBuscandoPalabra = Path.of("BuscandoPalabraXXXXX.txt");
        Path fichero = Path.of("PruebaEJ2");
        try (Stream<String> flujo = Files.lines(fichero)) {
            System.out.println(flujo.mapToInt(s -> s.split("\\s+").length)
                    .sum());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        AtomicInteger contador = new AtomicInteger();
        try (Stream<String> flujo = Files.lines(fichero)) {
            Pattern p = Pattern.compile(buscarPalabra);
            flujo.map(s -> p.matcher(s)).forEach(m -> {
                contador.set(contador.get()+1);
                while (m.find()) {
                    try {
                        Files.writeString(ficheroBuscandoPalabra, String.format("Encontrada en línea %d, columna %d.\n",
                                contador.get(), m.start()), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
