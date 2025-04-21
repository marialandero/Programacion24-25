package Boletin7_1.Ejercicio5;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * 5. Realizar un programa que presente el siguiente menú:
 *      1- Crear directorio: Se solicitará un nombre de directorio y se intentará crear en
 *      eldirectorio actual. Si ya existe el fichero se mostrará un mensaje de error.
 *      Semostrará un mensaje de si se ha podido crear o no correctamente.
 *      2- Crear fichero de texto: Se solicitará el nombre del fichero y se
 *      creará unfichero de texto que contenga una cadena de caracteres solicitada por
 *      teclado. Siya existe un fichero con ese nombre se mostrará un mensaje de error.
 *      3- Borrar fichero de texto: Se solicitará el nombre del fichero y se borrará. Semostrará
 *      un mensaje si no se ha podido borrar.
 *      4- Mostrar los ficheros que contiene una carpeta: Se solicitará el nombre de
 *      lacarpeta y mostrará un listado ordenado por nombre con todos los ficheros (nolos
 *      directorios) que contiene.
 *      5- Salir.
 */
public class Ejercicio5 {
    public static void main(String[] args) {
       try {
           // crearDirectorioNio("Ruben");
           // crearFicheroDeTexto("Luis", "Me llamo Luis");
           // borrarFicheroDeTexto("Luis");
           mostrarFicherosDeUnaCarpeta("Hola");
       } catch (DirectorioException e) {
           System.out.println(e.getMessage());
       }
    }

    // Método para crear directorio normal
    public static File crearDirectorio(String nombre) throws DirectorioException {
        File directorio = new File("src/Boletin7_1/Ejercicio5/"+nombre);
        if (!directorio.mkdir()) {
            throw new DirectorioException("No se puede crear el directorio.");
        }
        return directorio;
    }

    // Método para crear directorio NIO
    public static Path crearDirectorioNio(String nombre) throws DirectorioException {
        try {
            Path directorioNio = Path.of("src/Boletin7_1/Ejercicio5/",nombre);
            Files.createDirectory(directorioNio);
            return directorioNio;
        } catch (FileAlreadyExistsException i) {
            throw new DirectorioException(i.getMessage());
        } catch (IOException e) {
            throw new DirectorioException(e.getMessage());
        } catch (InvalidPathException e) {
            throw new DirectorioException(e.getMessage());
        }
    }

    // Método para crear fichero de texto
    public static Path crearFicheroDeTexto(String nombre, String contenido) throws DirectorioException {
        try {
            Path ficheroNio = Path.of("src/Boletin7_1/Ejercicio5/",nombre);
            Files.createFile(ficheroNio);
            Files.writeString(ficheroNio, contenido);
            return ficheroNio;
        } catch (FileAlreadyExistsException i) {
            throw new DirectorioException("No se puede crear: "+i.getMessage());
        } catch (IOException e) {
            throw new DirectorioException(e.getMessage());
        } catch (InvalidPathException e) {
            throw new DirectorioException(e.getMessage());
        }
    }

    // Método para borrar un fichero de texto
    public static void borrarFicheroDeTexto() throws DirectorioException {
        try {
            Path ficheroNio = Path.of("src/Boletin7_1/");
            if (!Files.deleteIfExists(ficheroNio)) {
                throw new DirectorioException("No existe el archivo");
            }
        } catch (IOException e) {
            throw new DirectorioException("No se puede crear: "+e.getMessage());
        } catch (InvalidPathException e) {
            throw new DirectorioException(e.getMessage());
        }
    }

    // Método para listar y mostrar los ficheros que contiene un directorio.
    public static void mostrarFicherosDeUnaCarpeta(String nombre) throws DirectorioException {
        Path carpetaNio = Path.of("src/Boletin7_1/Ejercicio5/"+nombre);
        try (Stream<Path> flujo = Files.list(carpetaNio)) { // Devuelve un flujo de ficheros
            flujo.filter(Files::isRegularFile) // Comprueba si es un archivo normal, no algún archivo especial
                    .sorted(Comparator.comparing(Path::getFileName)) // Ordena por orden de nombre
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new DirectorioException(e.getMessage());
        }
    }
}
