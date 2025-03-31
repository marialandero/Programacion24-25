package Boletin7_1.Ejercicio1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 1. Dado el contenido del siguiente archivo, cuenta cuántas líneas tiene.
 * Hola. Esta es la primera línea
 * de un fichero
 * que tiene algunas
 * líneas más.
 */

public class Ejercicio1 {
    public static void main(String[] args) {

        try (FileReader fr = new FileReader("src/Boletin7_1/Ejercicio1/input.txt");
             BufferedReader br = new BufferedReader(fr)) {
            int contador = 0;
            while (br.readLine() != null) {
                contador++;
            }
            System.out.println("El fichero tiene "+contador+" líneas.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
