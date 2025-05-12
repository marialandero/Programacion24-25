package ExamenSimpsons;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /* Esta será la ruta del directorio de origen, en el que tenemos que leer (entrada), por lo tanto,
         comprobamos que sea un directorio normal y que tenga permisos de lectura. */
        System.out.println("Introduce el directorio de origen:");
        String directorioLectura = sc.nextLine(); // Directorio de entrada, desde el que vamos a leer ficheros.
        File directorioOrigen = new File(directorioLectura);
        if (!directorioOrigen.isDirectory()) {
            System.out.println("No es un directorio.");
            return;
        }
        if (!directorioOrigen.canRead()) {
            System.out.println("Directorio sin permiso de lectura.");
            return;
        }

        /* Esta será la ruta del directorio de destino, en el que tenemos que escribir (salida), por lo tanto,
        comprobamos que sea un directorio normal y que tenga permisos de escritura */
        System.out.println("Introduce el directorio de destino:");
        String directorioEscritura = sc.nextLine(); // Directorio de salida, en el que vamos a copiar ficheros que cumplan las condiciones
        File directorioDestino = new File(directorioEscritura);
        if (!directorioDestino.isDirectory()) {
            System.out.println("No es un directorio.");
            return;
        }
        if (!directorioDestino.canWrite()) {
            System.out.println("Directorio sin permiso de escritura.");
            return;
        }

        /* Listamos los ficheros del directorio de origen y con una lambda dejamos pasar solo los ficheros
        que cumplan la condición (que sea un archivo regular, que la extensión sea .txt, y que pese más de 1024 bytes) */
        File[] ficheros = directorioOrigen.listFiles(f -> f.isFile() && f.getName().endsWith(".txt") && f.length() > 1024);
        // Añado un contador para los archivos copiados
        int contadorCopias = 0;
        // Iteramos sobre cada archivo que cumpla con las condiciones
        for (File fichero : ficheros) {
            // Nos aseguramos de que la primera palabra tenga un valor por defecto para capturarla, aunque luego se reemplace
            String primeraPalabra = "";
            // Con el buffer leemos la primera línea del fichero
            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                String linea = br.readLine();
                if (linea != null) {
                    primeraPalabra = linea.split(" ")[0]; // Con split se divide el string de linea en un array de palabras, y cogemos la primera posición
                }
            } catch (IOException e) {
                System.out.println("Error al leer el fichero "+fichero.getName()+": "+e.getMessage());
            }
            // Si la primera palabra no es "copiar", saltamos ese fichero
            if (!primeraPalabra.equalsIgnoreCase("copiar")) {
                continue;
            }
            // Si la primera palabra es "copìar", copiamos el fichero al directorio destino
            try { // .toPath convierte los ficheros File en Paths (se necesitan dos Paths para Files.copy(origen, destino))
                Files.copy(fichero.toPath(), new File(directorioDestino, fichero.getName()).toPath());
                contadorCopias++;
            } catch (IOException e) {
                System.out.println("Error al copiar el fichero "+fichero.getName()+": "+e.getMessage());
            }
        }
        System.out.printf("Se ha hecho la copia de %d archivo/s.",contadorCopias);
    }
}
