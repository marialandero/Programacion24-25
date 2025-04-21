package Boletin7_1.Ejercicio7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Ejercicio7 {
    public static void main(String[] args) {

        // listarDirectorioYPeso()
    }

    public static void listarDirectorioYPeso(String ruta) throws Ejercicio7Exception {

    }

    public static void listarFicherosPorComienzo(String ruta, String ultima) throws Ejercicio7Exception {

    }

    public static void listarFicherosTerminadosEn(String ruta, String ultima) throws Ejercicio7Exception {

    }

    public static void mostrarFicherosPorExtension(String ruta, String extension) throws Ejercicio7Exception {

        /*Path directorio = Path.of(ruta);
        try (Stream<Path> flujo = Files.list(directorio)) {
            *//* Para ver en qué termina el fichero que estamos buscando hay que pasarlo a Files y utilizar .getName()
             * para ver el nombre del fichero como un String *//*
            flujo.filter(p -> {
                        // Hacemos un stream y miramos si la última parte del array es la extensión
                        String[] array = (p.toFile().getName().split("\\."));
                        return array[array.length - 1].equalsIgnoreCase(extension);
                    })
                    .map(p -> {
                        if (Files.isDirectory(p)) {
                            return String.format("Nombre : %s", p.getFileName());
                        } else {
                            System.out.println();
                        }
                    })
        }*/
    }

    public static List<Path> buscarRecursivamenteFicheroEnDirectorio(String directorioP, String fichero) throws Ejercicio7Exception {
        try {
            Path directorio = Path.of(directorioP);
            try (Stream<Path> flujo = Files.walk(directorio)) {
                return flujo.filter(p -> p.toFile().getName().equalsIgnoreCase(fichero)).toList();
            } catch (IOException e) {
                throw new Ejercicio7Exception(e.getMessage());
            }
        } catch (InvalidPathException e) {
            throw new Ejercicio7Exception(e.getMessage());
        }
    }
}