package Repaso.Ej5ProgramaMenu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ej5ProgramaMenu {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            try {
                System.out.println("\n=== Menú ===\n");
                System.out.println("Elige una opción:\n");
                System.out.println("1. Crear directorio IO.");
                System.out.println("2. Crear directorio NIO.");
                System.out.println("3. Crear fichero de texto.");
                System.out.println("4. Borrar fichero de texto.");
                System.out.println("5. Mostrar todos los fichero de una carpeta.");
                System.out.println("6. Salir.");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        crearDirectorio("Tere");
                        crearDirectorio("Maria");
                        crearDirectorio("Angie");
                        break;

                    case 2:
                        crearDirectorioNIO("Angie");
                        crearDirectorioNIO("Maria");
                        crearDirectorioNIO("Tere");
                        break;

                    case 3:
                        crearFichero("AngieFortnitera.txt","La tía es una viciada al fortnite.");
                        crearFichero("RuskaRusketa.txt","La buena música ruski.");
                        crearFichero("AngieCotilla.txt","La tía cotilla de pacotilla");
                        break;

                    case 4:
                        borrarFichero("AngieCotilla.txt");
                        break;

                    case 5:
                        mostrarFicherosDeUnDirectorio("");
                        break;

                    case 6:
                        System.out.println("Saliendo... ¡Adiós!");
                        break;
                }


            } catch (DirectorioException e) {
                System.out.println("Error: "+e.getMessage());
            }
        } while (opcion != 6);
    }

    public static File crearDirectorio(String nombre) throws DirectorioException {
        File directorio = new File("src/Repaso/Ej5ProgramaMenu/"+ nombre);
        if (!directorio.mkdir()) { // Si no lo puede crear, lanza excepción
            throw new DirectorioException("No se puede crear el directorio.");
        }
        return directorio;
    }

    public static Path crearDirectorioNIO(String nombre) throws DirectorioException {
        try {
            Path directorioNIO = Path.of("src/Repaso/Ej5ProgramaMenu/", nombre);
            Files.createDirectory(directorioNIO);
            return directorioNIO;
        } catch (IOException | InvalidPathException e) {
            throw new DirectorioException("No se puede crear el directorio: "+e.getMessage());
        }
    }

    public static Path crearFichero(String nombre, String contenido) throws DirectorioException {
        try {
            Path ficheroNIO = Path.of("src/Repaso/Ej5ProgramaMenu/Angie/"+nombre);
            Files.createFile(ficheroNIO);
            Files.writeString(ficheroNIO, contenido);
            return ficheroNIO;
        } catch (IOException | InvalidPathException e) {
            throw new DirectorioException("No se puede crear el fichero: "+e.getMessage());
        }
    }

    public static void borrarFichero(String nombre) throws DirectorioException {
        try {
            Path fichero = Path.of("src/Repaso/Ej5ProgramaMenu/Angie/"+nombre);
            if (!Files.deleteIfExists(fichero)) { // Si no existe, se lanza excepción
                throw new DirectorioException("No existe el fichero.");
            } else {
                System.out.println("El fichero se ha borrado correctamente.");
            }
        } catch (IOException | InvalidPathException e) {
            throw new DirectorioException("No se puede borrar el fichero: "+e);
        }
    }

    public static void mostrarFicherosDeUnDirectorio(String nombre) throws DirectorioException {
        Path directorio = Path.of("src/Repaso/Ej5ProgramaMenu/Angie/"+nombre);
        try (Stream<Path> stream = Files.list(directorio)) { // Devuelve un flujo de rutas de ficheros dentro del directorio
            stream.filter(Files::isRegularFile) // Filtra los ficheros normales, no carpetas ni enlaces (Files::isRegularFile hace referencia al método)
                    .map(Path::getFileName)// Mapea cada ruta a su nombre únicamente
                    .sorted() // Ordena alfabéticamente los ficheros
                    .forEach(System.out::println); // Imprime línea por línea cada nombre de fichero
        } catch (IOException | InvalidPathException e) {
            throw new DirectorioException("Error: "+e);
        }
    }
}
