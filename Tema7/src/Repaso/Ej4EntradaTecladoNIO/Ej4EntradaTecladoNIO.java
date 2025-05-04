package Repaso.Ej4EntradaTecladoNIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Ej4EntradaTecladoNIO {
    public static void main(String[] args) {
        Path rutaArchivo = Path.of("src/Repaso/Ej4EntradaTecladoNIO/salidaEj4.txt"); // Se indica la ruta
        // El Path siempre fuera del try-with-resources
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // Guarda en el buffer con lo que se pide por la entrada del tecladp

            System.out.println("Añade líneas al texto.\nEscriba la palabra 'fin' para terminar.");
            String linea; // Se almacena cada línea que se lea del teclado
            while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("fin")) {
                // Mientras hayan líneas que leer y ninguna sea 'fin', se añaden

                Files.writeString(rutaArchivo, linea+"\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                // Para que el texto se añada en el archivo, hay que indicar la ruta, el contenido almacenado
                // en la variable linea. Además, con las StandardOpenOption se indica que si el archivo no existe,
                // se cree, y si existe, se añada el contenido con append sin sobreescribirlo.
            }
            System.out.println("El texto ha sido añadido correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
