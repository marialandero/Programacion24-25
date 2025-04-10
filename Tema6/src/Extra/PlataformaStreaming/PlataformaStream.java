package Extra.PlataformaStreaming;

import java.util.*;

public class PlataformaStream {
    private Set<Usuario> usuarios;
    private Set<Serie> series;

    public PlataformaStream(Set<Usuario> usuarios, Set<Serie> series) {
        this.usuarios = new LinkedHashSet<>();
        this.series = new HashSet<>();
    }

    public PlataformaStream() {

    }

    /**
     * Devuelve true si se añade, false si no se añade.
     * @param serie
     * @return
     */
    public boolean registrarNuevaSerie(Serie serie) {
        return series.add(serie);
    }

    /**
     * Devuelve true si se añade, false si no se añade.
     * @param usuario
     * @return
     */
    public boolean registrarUsuario(Usuario usuario) {
        return usuarios.add(usuario);
    }

    /**
     * Comprueba que la serie exista y que el usuario esté registrado para poder añadirlo a la serie.
     * Si las condiciones no se dan, arroja excepciones.
     * @param serie
     * @param usuario
     * @throws PlataformaException
     */
    public void anyadirSuscriptorASerie(Serie serie, Usuario usuario) throws PlataformaException {
        if (!series.contains(serie)) {
            throw new PlataformaException("Esta serie no se encuentra disponible en nuestra plataforma.");
        }
        if (!usuarios.contains(usuario)) {
            throw new PlataformaException("El usuario no está registrado en la plataforma.");
        }
        serie.anyadirSuscriptor(usuario);
    }

    /**
     * Comprueba que la serie exista y que el usuario esté registrado para poder eliminarlo del seguimiento de la serie.
     * Si las condiciones no se dan, arroja excepciones.
     * @param serie
     * @param usuario
     * @throws PlataformaException
     */
    public void eliminarSuscriptorDeSerie(Serie serie, Usuario usuario) throws PlataformaException {
        if (!series.contains(serie)) {
            throw new PlataformaException("Esta serie no se encuentra disponible en nuestra plataforma.");
        }
        if (!usuarios.contains(usuario)) {
            throw new PlataformaException("El usuario no está registrado en la plataforma.");
        }
        serie.eliminarSuscriptor(usuario);
    }

    /**
     * Este método compara a los usuarios basándose en sus nombres y devuelve una lista ordenada alfabéticamente.
     * @param serie
     * @return
     * @throws PlataformaException
     */
    public List<Usuario> usuariosQueSiguenUnaSeriePorOrdenAlfabetico (Serie serie) throws PlataformaException {
        if (!series.contains(serie)) {
            throw new PlataformaException("Esta serie no se encuentra disponible en nuestra plataforma.\n");
        }
        List<Usuario> suscriptoresOrdenados = serie.getSuscriptores().stream()
                .sorted(Comparator.comparing(Usuario::getNombre))
                .toList();

        if (suscriptoresOrdenados.isEmpty()) {
            throw new PlataformaException("La serie no tiene suscriptores.\n");
        }
        return suscriptoresOrdenados;
    }

    /**
     * Obtiene la lista de series que sigue un usuario ordenads por cantidad de temporadas (de mayor a menor).
     * @param usuario
     * @return
     * @throws PlataformaException
     */
    public List<Serie> listaDeSeriesQueSigueUnUsuarioOrdenadasPorCantidadSeguidores (Usuario usuario) throws PlataformaException {
        if (!usuarios.contains(usuario)) {
            throw new PlataformaException("El usuario no está registrado en la plataforma.\n");
        }
        return series.stream()
                .filter(serie -> serie.getSuscriptores().contains(usuario))
                .sorted((seriaA, serieB) -> Integer.compare(serieB.getTemporadas(), seriaA.getTemporadas()))
                .toList();
    }

    /**
     * Para este método se ha implementado la interfaz Comparable en la calse Serie para poder usar el método
     * compareTo y así comparar las series que tengan el número más grande de seguidores.
     * @return
     * @throws PlataformaException
     */
    public List<Serie> seriesMasPopulares() throws PlataformaException {
        Serie serieMasPopular = series.stream()
                .max((serieA, serieB) -> {
                    return serieB.compareTo(serieA);
                }).orElseThrow(() -> new PlataformaException("No hay series, no puedo obtener la serie más famosa."));

        return series.stream()
                .filter(serie -> serie.getSuscriptores().size() == serieMasPopular.getSuscriptores().size())
                .toList();
    }

    public String obtenerGeneroMasPopular() {
        Map<String, Integer> generoConSeguidores = new HashMap<>();
        for (Serie serie : series) {
            generoConSeguidores.put(serie.getGenero().trim().toLowerCase(), serie.getSuscriptores().size());
        }
        // generoConSeguidores.keySet().stream().max()
        return generoConSeguidores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No hay géneros disponibles");
    }
}
