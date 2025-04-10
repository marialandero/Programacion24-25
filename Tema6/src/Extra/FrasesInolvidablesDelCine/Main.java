package Extra.FrasesInolvidablesDelCine;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        BibiliotecaDeCine biblioteca = new BibiliotecaDeCine();

        try {
            // Crear categorías
            Categoria drama = new Categoria("Drama");
            Categoria comedia = new Categoria("Comedia");

            // Añadir categorías a la biblioteca
            biblioteca.addCategoria(drama);
            biblioteca.addCategoria(comedia);

            // Crear frases
            Frase frase1 = new Frase("Píntame como una de tus chicas francesas, Jack", "Titanic", "Kate Winslet", LocalDate.of(1997, 12, 1));
            Frase frase2 = new Frase("¿Por qué tan serio?", "El caballero oscuro", "Joker", LocalDate.of(2008, 7, 18));
            Frase frase3 = new Frase("No podré cargar el anillo, pero puedo cargarlo a usted", "El señor de los anillos", "Sam", LocalDate.now());

            // Asignar puntuación a algunas frases
            frase1.setValoracion(9);
            frase2.setValoracion(8);
            frase3.setValoracion(10);

            // Añadir frases a categorías
            List<Categoria> categorias1 = List.of(drama);
            biblioteca.addFrase(categorias1, frase1);
            biblioteca.addFrase(List.of(drama, comedia), frase2);
            biblioteca.addFrase(List.of(comedia), frase3);

            // Mostrar frases ordenadas por valoración
            System.out.println("Frases ordenadas por valoración:");
            biblioteca.getTodasLasFrasesOrdenadasPorValoracion().forEach(System.out::println);

            // Mostrar categorías con frases sin valorar
            Set<Categoria> sinValoracion = biblioteca.categoriasQueContienenAlgunaFraseSinValoracion();
            System.out.println("\nCategorías con frases sin valoración:");
            sinValoracion.forEach(System.out::println);

            // Mostrar estadísticas
            Map<String, Object> estadisticas = biblioteca.estadisticasDeValoracion();
            System.out.println("\nEstadísticas:");
            System.out.println("Media de valoraciones: " + estadisticas.get("Media"));
            System.out.println("Frase con mayor puntuación: " + estadisticas.get("FraseMayorPuntuacion"));

            // Frases añadidas en el último año
            System.out.println("\nFrases añadidas en el último año:");
            biblioteca.frasesUltimoAnno().forEach(System.out::println);

        } catch (CineException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
