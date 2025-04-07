package Extra.PlataformaStreaming;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class PlataformaStream {
    private Set<Usuario> usuarios;
    private Set<Serie> series;

    public PlataformaStream(Set<Usuario> usuarios, Set<Serie> series) {
        this.usuarios = usuarios;
        this.series = series;
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
     * Este método compara a los usuarios basándose en sus nombres.
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
}
