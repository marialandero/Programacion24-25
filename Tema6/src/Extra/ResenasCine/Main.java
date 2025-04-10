package Extra.ResenasCine;

public class Main {
    public static void main(String[] args) {
        try {
            CriticasCine criticasCine = new CriticasCine();

            // Crear películas
            Pelicula p1 = new Pelicula("Inception", 2010, "Christopher Nolan", "Ciencia Ficción");
            Pelicula p2 = new Pelicula("Interstellar", 2014, "Christopher Nolan", "Ciencia Ficción");
            Pelicula p3 = new Pelicula("La La Land", 2016, "Damien Chazelle", "Musical");
            Pelicula p4 = new Pelicula("Whiplash", 2014, "Damien Chazelle", "Drama");
            Pelicula p5 = new Pelicula("Whiplaso", 2014, "Damien Chazelle", "Drama");
            Pelicula p6 = new Pelicula("Whiplasu", 2014, "Damien Chazelle", "Drama");

            // Añadir películas al sistema
            criticasCine.addPelicula(p1);
            criticasCine.addPelicula(p2);
            criticasCine.addPelicula(p3);
            criticasCine.addPelicula(p4);
            criticasCine.addPelicula(p5);
            criticasCine.addPelicula(p6);

            // Crear reseñas
            Resena r1 = new Resena("Ana", 9, "Muy buena");
            Resena r2 = new Resena("Luis", 10, "Obra maestra");
            Resena r3 = new Resena("Pedro", 2, "No me gustó");
            Resena r4 = new Resena("Ana", 7, "Entretenida");
            Resena r5 = new Resena("Carlos", 3, "Aburrida");

            // Añadir reseñas
            criticasCine.addResena("Inception", 2010, r1);
            criticasCine.addResena("Interstellar", 2014, r2);
            criticasCine.addResena("Interstellar", 2014, r3);
            criticasCine.addResena("La La Land", 2016, r4);
            criticasCine.addResena("Whiplash", 2014, r5);

            // Mostrar géneros más populares
            System.out.println("Géneros más populares:");
            criticasCine.getGenerosMasPopulares().forEach(System.out::println);

            // Mostrar películas con reseñas negativas
            System.out.println("\nPelículas con reseñas negativas:");
            criticasCine.peliculasConResenasNegativas().forEach(System.out::println);

            // Mostrar películas ordenadas por valoración media
            System.out.println("\nPelículas ordenadas por valoración media:");
            criticasCine.getPeliculasOrdenadasPorValoracionMedia().forEach(p ->
                    System.out.println(p.getTitulo() + " - Media: " + p.valoracionMedia()));

            // Eliminar reseñas de usuario Ana
            criticasCine.eliminarResenasDeUsuario("Ana");

            // Mostrar reseñas recientes (probablemente vacío si se ejecuta justo ahora)
            System.out.println("\nUsuarios con reseñas recientes:");
            criticasCine.usuariosConResenasRecientes().forEach(System.out::println);

        } catch (PeliculaException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
