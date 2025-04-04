package Extra.PlataformaStreaming;

import java.util.HashSet;
import java.util.Set;

public class PlataformaStreaming {
    private Set<Serie> series;
    private Set<Usuario> usuarios;

    public PlataformaStreaming(Set<Serie> series, Set<Usuario> usuarios) {
        this.series = new HashSet<>();
        this.usuarios = new HashSet<>();
    }


}
