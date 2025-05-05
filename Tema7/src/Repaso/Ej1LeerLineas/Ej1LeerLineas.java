package Repaso.Ej1LeerLineas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ej1LeerLineas {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("src/Repaso/Ej1LeerLineas/Input.txt"); // Abre el archivo y lo prepara para ser leído carácter por carácter
             BufferedReader br = new BufferedReader(fr)) { // Optimiza la lectura haciendo que se lea por bloques
            int contador = 0;
            while (br.readLine() != null) { // Lee del buffer hasta que se encuentre con null, que sería el fin de una línea
                contador++; //  Entonces el contador aumentará
            }
            System.out.println("El archivo tiene "+contador+" líneas.");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
