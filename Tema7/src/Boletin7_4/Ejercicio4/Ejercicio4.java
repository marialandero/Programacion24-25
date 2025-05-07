package Boletin7_4.Ejercicio4;
/*
4. Escribe un programa que lea líneas del teclado y las añada a un archivo llamado
"log.txt". Si el archivo supera un tamaño máximo, renómbralo con un nombre que
incluya la fecha y hora de creación y crea un nuevo archivo (rotación de logs).
 */
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ejercicio4 {
    public static void main(String[] args) {
        File fichero = new File("src/Boletin7_4/Ejercicio4/log.txt");
        if (fichero.exists()) {
            fichero.delete();
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter salida = new PrintWriter(new FileWriter(fichero))) {
            String linea;

            while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("fin")) {
                salida.println(linea);
                salida.flush();
            }
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
        if (fichero.length() > 20) {
            String renombrado = "log" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss")) + ".txt";
            if (fichero.renameTo(new File("src/Boletin7_4/Ejercicio4/"+renombrado))) {

            } else {
                System.out.println("Fichero no creado.");
            }
        }
    }
}
