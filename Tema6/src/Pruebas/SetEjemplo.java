package Pruebas;

import java.util.*;

public class SetEjemplo {
    public static void main(String[] args) {
        Set<String> nombres = new HashSet<>();

        nombres.add("Ana");
        nombres.add("Luis");
        nombres.add("Pedro");
        nombres.add("Ana"); // ¡Repetido!

        System.out.println("Nombres: " + nombres); // "Ana" solo aparece una vez

        if (nombres.contains("Luis")) {
            System.out.println("Luis está en el set.");
        }

        System.out.println("Tamaño del set: " + nombres.size());
    }
}

