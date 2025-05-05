package Repaso.Ej3EntradaTeclado;

import java.io.*;

public class Ej3EntradaTeclado {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             // Guarda en la memoria buffer lo que entra al programa por teclado
             PrintWriter salida = new PrintWriter(new FileWriter("salidaEj3.txt", true))) {
            // PrintWriter escribe lo que entra por teclado en el archivo que FileWriter abre para escribir en él sin borrar lo que ya hay

            System.out.println("Añade líneas al texto.\nEscriba la palabra 'fin' para terminar.");
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.equalsIgnoreCase("fin")) { // Hasta que no se escriba 'fin', se puede seguir añadiendo texto y líneas
                    break;
                }
                salida.println(linea); // Imprime cada línea como salida en el archivo
                salida.flush(); // Vacía el buffer y asegura que se escriba en el archivo inmediatamente
            }
            System.out.println("Texto añadido al archivo con éxito.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
