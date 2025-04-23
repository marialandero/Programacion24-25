package Boletin7_2.Ejercicio5;

import java.util.regex.Pattern;

public class Ejercicio5 {
    public static void main(String[] args) {

    }
    public static boolean validarFichero(String rutaFichero) {
        boolean ficheroValido = true;

        Pattern patron = Pattern.compile("^F\\s(?<NOMBRE>\\p{L}{3,}\\.\\p{L}{3})$");
        return ficheroValido;
    }
}
