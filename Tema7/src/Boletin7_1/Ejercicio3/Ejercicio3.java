package Boletin7_1.Ejercicio3;


import java.io.*;

/**
 *3. Realiza un programa que acepte líneas del teclado y las añada a un fichero llamado
 * salidaEj3.txt, sin sobreescribir el contenido que ya hubiera en dicho fichero. La entrada
 * deberá terminar cuando el usuario escriba una línea que tenga únicamente la palabra “fin”.
 */

public class Ejercicio3 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter os = new PrintWriter(new FileWriter("salidaEj3.txt", true))) {

            String l;
            System.out.println("Introduce líneas de texto. Escribe 'fin' para terminar:");

            while ((l = br.readLine()) != null) {
                if (l.equalsIgnoreCase("fin")) {
                    break;
                }
                os.println(l);
                os.flush(); // Asegura que se escriba inmediatamente en el archivo
            }

            System.out.println("Texto añadido al archivo salidaEj3.txt");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}