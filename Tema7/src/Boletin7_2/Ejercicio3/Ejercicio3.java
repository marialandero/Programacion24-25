package Boletin7_2.Ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;

/*
Realizar un programa que solicite un nombre de un fichero de texto e informe de si
cumple con el formato que se indica. Cada línea debe contener:
 • El nombre y dos apellidos, cada uno con dos caracteres alfabéticos como
mínimo. Estarán separadas por un espacio
 • A continuación su edad (1-99), separada por un espacio
*/

public class Ejercicio3 {
    public static void main(String[] args) {

        try (BufferedReader miLector = new BufferedReader(new FileReader("src/Boletin7_2/Ejercicio3/MiFichero.txt"))) {
            String linea;
            boolean esValido = true;
            while ((linea = miLector.readLine()) != null) {
                esValido= linea.matches("^\\p{L}{2,} \\p{L}{2,} \\p{L}{2,} [1-9]\\d?$");
            }
            if (esValido) {
                System.out.println("Cumple la condición.");
            } else {
                System.out.println("No cumple la condición.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
