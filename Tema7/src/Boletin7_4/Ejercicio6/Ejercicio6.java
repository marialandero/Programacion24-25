package Boletin7_4.Ejercicio6;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/*
6. Realiza un programa que lea líneas del teclado y las añada a un archivo llamado
"bitacora.txt", pero limitando su tamaño máximo. Si el archivo  supera dicho tamaño,
se deberá borrar la primera línea y añadir la nueva al final.
 */
public class Ejercicio6 {
    public static void main(String[] args) {
        Path rutaArchivo = Path.of("src/Boletin7_4/Ejercicio4/bitacora.txt"); // Se indica la ruta
        // El Path siempre fuera del try-with-resources
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // Guarda en el buffer con lo que se pide por la entrada del tecladp

            System.out.println("Añade líneas al texto.\nEscriba la palabra 'fin' para terminar.");
            String linea; // Se almacena cada línea que se lea del teclado
            while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("fin")) {
                // Mientras hayan líneas que leer y ninguna sea 'fin', se añaden

                Files.writeString(rutaArchivo, linea + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
            System.out.println("El texto ha sido añadido correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}