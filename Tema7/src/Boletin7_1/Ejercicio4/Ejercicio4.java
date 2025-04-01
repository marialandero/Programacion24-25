package Boletin7_1.Ejercicio4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * 4. Realiza el ejercicio anterior pero utilizando clases del paquete java.nio.
 */

public class Ejercicio4 {
    public static void main(String[] args) {

        // Misma manera que el Ejercicio 3.
        /*
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter os = new PrintWriter(new FileWriter("src/Boletin7_1/Ejercicio4/salidaEj4.txt", true))) {

            String l;
            System.out.println("Agrega líneas en el archivo de texto. Para terminar, escribe 'fin'.");

            while ((l = br.readLine()) != null) {
                if (l.equalsIgnoreCase("fin")) {
                    break;
                }
                os.println(l);
                os.flush(); // Asegura que se escriba inmediatamente en el archivo
            }

            System.out.println("Texto añadido al archivo salidaEj3.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

         */

        Path emptyfile = Path.of("src/Boletin7_1/Ejercicio4/salidaEj4.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String l;
            System.out.println("Agrega líneas al archivo salidaEj4.txt. Escribe 'fin' para terminar.");
            while ((l = br.readLine()) != null && !l.equalsIgnoreCase("fin")) {
                Files.writeString(emptyfile, l+"\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
