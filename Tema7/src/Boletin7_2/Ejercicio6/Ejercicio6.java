package Boletin7_2.Ejercicio6;

import javax.imageio.IIOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio6 {
    public static void main(String[] args) {
        Path ficheroNombres = Path.of("src/Boletin7_2/Ejercicio6/ListadoAlumnos.txt");
        Path directorioDestino = Path.of("src/Boletin7_2/Ejercicio6/Directorios");

        Pattern patron = Pattern.compile("^(?<NOMBRE>\\p{L}+)\\s+(?<APELLIDO1>\\p{L}+)\\s+(?<APELLIDO2>\\p{L}+)\\s+(?<CURSO>.+)$");

        try (BufferedReader br = new BufferedReader(new FileReader((ficheroNombres.toFile())))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                Matcher matcher = patron.matcher(linea);
                if (matcher.matches()) {
                    Path directorioCreado = Files.createDirectories(Path.of(directorioDestino.toString(), matcher.group("CURSO"),
                            matcher.group("APELLIDO1") + matcher.group("APELLIDO2") + matcher.group("NOMBRE")));
                    System.out.println("Se ha creado el directorio: " + directorioCreado);
                } else {
                    System.out.println("La línea ("+linea+") no es correcta.");
                }
            }
        } catch (IIOException e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
