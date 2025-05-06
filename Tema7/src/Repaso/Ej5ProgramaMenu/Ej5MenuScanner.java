package Repaso.Ej5ProgramaMenu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ej5MenuScanner {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            try {
                System.out.println("\n=== Menú ===\n");
                System.out.println("1. Crear directorio IO.");
                System.out.println("2. Crear directorio NIO.");
                System.out.println("3. Crear fichero de texto.");
                System.out.println("4. Borrar fichero de texto.");
                System.out.println("5. Mostrar todos los ficheros de un directorio.");
                System.out.println("6. Salir.");
                System.out.println("\nElige una opción.");
                String opcionStr = sc.nextLine();
                opcion = Integer.parseInt(opcionStr);


                switch (opcion) {
                    case 1:
                        crearDirectorioIO(sc);
                        break;
                    case 2:
                        crearDirectorioNIO(sc);
                        break;
                    case 3:
                        crearFicheroDeTexto(sc);
                        break;
                    case 4:
                        borrarFicheroDeTexto(sc);
                        break;
                    case 5:
                        mostrarFicherosDeUnDirectorio(sc);

                        break;
                    case 6:
                        System.out.println("Saliendo... ¡Hasta pronto!");
                        break;

                    default:
                        System.out.println("Elige una opción válida.");

                }
            } catch (DirectorioSCException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 6);
    }

    /**
     * Método para crear un directorio IO pedido por escáner
     *
     * @param sc
     * @throws DirectorioSCException
     */
    public static void crearDirectorioIO(Scanner sc) throws DirectorioSCException {
        System.out.println("Introduce el nombre del directorio que deseas crear.");
        String nombre = sc.nextLine();
        File directorio = new File("src/Repaso/Ej5ProgramaMenu/" + nombre);
        if (!directorio.mkdir()) {
            throw new DirectorioSCException("No se puede crear el directorio.");
        } else {
            System.out.println("Directorio " + nombre + " creado.");
        }
    }

    /**
     * Método para crear un directorio NIO pedido por escáner
     *
     * @param sc
     * @throws DirectorioSCException
     */
    public static void crearDirectorioNIO(Scanner sc) throws DirectorioSCException {
        System.out.println("Introduce el nombre del directorio que deseas crear.");
        String nombre = sc.nextLine();
        try {
            Path ruta = Path.of(nombre);
            if (Files.exists(ruta)) {
                throw new DirectorioSCException("Ya existe ese directorio.");
            } else {
                Files.createDirectory(ruta);
                System.out.println("Directorio " + nombre + " creado.");
            }
        } catch (IOException | InvalidPathException e) {
            throw new DirectorioSCException("No se pudo crear el directorio: " + e.getMessage());
        }
    }

    public static void crearFicheroDeTexto(Scanner sc) throws DirectorioSCException {
        System.out.println("Introduce el nombre del fichero que deseas crear con su extensión .txt");
        String nombre = sc.nextLine();
        try {
            Path ruta = Path.of("src/Repaso/Ej5ProgramaMenu/" + nombre);
            if (Files.exists(ruta)) {
                throw new DirectorioSCException("El fichero ya existe.");
            } else {
                Files.createFile(ruta);
                System.out.println("Fichero " + nombre + " creado.");
            }
        } catch (IOException | InvalidPathException e) {
            throw new DirectorioSCException("No se pudo crear el fichero: " + e.getMessage());
        }
    }

    /**
     * Método para eliminar un fichero pedido por escáner
     *
     * @param sc
     * @throws DirectorioSCException
     */
    public static void borrarFicheroDeTexto(Scanner sc) throws DirectorioSCException {
        System.out.println("Introduce el nombre del fichero que deseas eliminar con su extensión .txt");
        String nombre = sc.nextLine();
        try {
            Path ruta = Path.of("src/Repaso/Ej5ProgramaMenu/" + nombre);
            if ((Files.exists(ruta)) && (Files.isRegularFile(ruta))) {
                Files.delete(ruta);
                System.out.println("Fichero " + nombre + " eliminado.");
            } else {
                throw new DirectorioSCException("El fichero no existe.");
            }
        } catch (IOException | InvalidPathException e) {
            throw new DirectorioSCException("No se pudo crear el fichero: " + e.getMessage());
        }
    }

    /**
     * Con un flujo se muestra una lista de los ficheros que son normales y ordenados
     *
     * @param sc
     * @throws DirectorioSCException
     */
    public static void mostrarFicherosDeUnDirectorio(Scanner sc) throws DirectorioSCException {
        System.out.println("Introduce el nombre del directorio cuyos ficheros deseas mostrar.");
        String nombre = sc.nextLine();
        Path ruta = Path.of("src/Repaso/Ej5ProgramaMenu/" + nombre);
        if ((!Files.exists(ruta)) || (!Files.isDirectory(ruta))) {
            System.out.println("El directorio no existe.");
        } else {
            try (Stream<Path> stream = Files.list(ruta)) { // Se crea un flujo que devuelva una lista de ficheros
                stream.filter(Files::isRegularFile) // Se filtran los ficheros que sean normales, no carpetas ni enlaces
                        .map(Path::getFileName) // Se mapean las rutas de los ficheros a sus nombres únicamente
                        .sorted() // Se ordenan alfabéticamente
                        .forEach(System.out::println); // Se imprimen los ficheros que han pasado por el filtro
            } catch (IOException | InvalidPathException e) {
                throw new DirectorioSCException("No se encuentran los ficheros: " + e.getMessage());
            }
        }
    }
}
