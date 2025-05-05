package Repaso.Ej2UnirLineas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ej2UnirLineas {
    public static void main(String[] args) {

        try (FileReader fr = new FileReader("src/Repaso/Ej1LeerLineas/Input.txt");
             BufferedReader br = new BufferedReader(fr)) {

            String linea;
            StringBuilder lineasUnidas = new StringBuilder();

            boolean esPrimeraLinea = true; // En la primera iteración, esPrimeraLinea será cierto, por lo tanto,
            // no se añade espacio

            while ((linea = br.readLine()) != null) {
                if (!esPrimeraLinea) {
                    lineasUnidas.append(" ");
                }
                lineasUnidas.append(linea);
                esPrimeraLinea = false; // A partir de que se añada la primera línea será falso, por lo tanto,
                // ya no es cierto que sea la primera línea, por lo que se añadirá el espacio
            }
            System.out.println(lineasUnidas);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
