package Extra.PlataformaStreaming;

public class Main {
    public static void main(String[] args) {
        PlataformaStream plataformaStream = new PlataformaStream();

        // Creamos los usuarios
        Usuario usuario1 = new Usuario("Maria", "maria@gmail.com");
        Usuario usuario2 = new Usuario("Tere", "tere@gmail.com");
        Usuario usuario3 = new Usuario("Rubén", "ruben@gmail.com");

        // Creamos las series
        Serie serie1 = new Serie("The Rookie", "Policíaca", 6);
        Serie serie2 = new Serie("Arcane", "Acción", 2);
        Serie serie3 = new Serie("This is Us", "Drama", 8);

        // Registramos los usuarios
        plataformaStream.registrarUsuario(usuario1);
        plataformaStream.registrarUsuario(usuario2);
        plataformaStream.registrarUsuario(usuario3);

        // Registramos las series
        plataformaStream.registrarNuevaSerie(serie1);
        plataformaStream.registrarNuevaSerie(serie2);
        plataformaStream.registrarNuevaSerie(serie3);

        try {
            // Añadimos suscriptores
            plataformaStream.anyadirSuscriptorASerie(serie1, usuario1);
            plataformaStream.anyadirSuscriptorASerie(serie1, usuario2);
            plataformaStream.anyadirSuscriptorASerie(serie2, usuario1);
            plataformaStream.anyadirSuscriptorASerie(serie2, usuario2);
            plataformaStream.anyadirSuscriptorASerie(serie2, usuario3);
            plataformaStream.anyadirSuscriptorASerie(serie3, usuario1);

            // Mostramos usuarios que siguen una serie
            System.out.println("Usuarios que siguen 'The Rookie:");
            for (Usuario u : plataformaStream.usuariosQueSiguenUnaSeriePorOrdenAlfabetico(serie1)) {
                System.out.println(u.getNombre());
            }

            // Mostramos las series que sigue un usuario
            System.out.println("Series que sigue Tere:");
            for (Serie s : plataformaStream.listaDeSeriesQueSigueUnUsuarioOrdenadasPorCantidadSeguidores(usuario2)) {
                System.out.println(s.getTitulo()+" con "+s.getTemporadas()+" temporadas.");
            }

            // Mostramos las series más populares
            System.out.println("Series más populares:");
            for (Serie s : plataformaStream.seriesMasPopulares()) {
                System.out.println(s.getTitulo()+" con "+s.getSuscriptores()+" seguidores.");
            }

            // Mostramos el género más popular
            System.out.println("Género mñas popular: "+plataformaStream.obtenerGeneroMasPopular());

        } catch (PlataformaException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
