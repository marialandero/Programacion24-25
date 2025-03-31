package Boletin7_1.Ejercicio2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 2. Dado el fichero anterior, léelo y haz que todas las líneas se junten en una sola cadena
 * que se mostrará por pantalla.
 */

public class Ejercicio2 {
    public static void main(String[] args) {

        try (FileReader fr = new FileReader("src/Boletin7_1/Ejercicio1/input.txt");

            BufferedReader br = new BufferedReader(fr)) {
            StringBuilder sb = new StringBuilder();
            String l;
            while ((l = br.readLine()) != null) {
                sb.append(l);
            }
            System.out.println(sb);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
