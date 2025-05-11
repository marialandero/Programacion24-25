package ExamenVideojuegos;

// Creamos la clase Videojuego para que se puedan almacenar todos los datos de un videojuego
public class Videojuego {

    // Declaramos los atributos
    private String id;
    private String titulo;
    private String desarrollador;
    private String lanzamiento;
    private String plataforma;
    private String genero;
    private String descripcion;
    private int pegi;

    // Inicializamos un constructor para los videojuegos que tengan descripción
    public Videojuego(String id, String titulo, String desarrollador, String lanzamiento, String plataforma, String genero, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        this.lanzamiento = lanzamiento;
        this.plataforma = plataforma;
        this.genero = genero;
        this.descripcion = descripcion;
    }

    // Inicializamos otro constructor para los videojuegos que no tengan descripción
    public Videojuego(String id, String titulo, String desarrollador, String lanzamiento, String plataforma, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        this.lanzamiento = lanzamiento;
        this.plataforma = plataforma;
        this.genero = genero;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public String getLanzamiento() {
        return lanzamiento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Creamos los métodos getPegi y setPegi aparte para extraer luego el PEGI de la descripción
    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "titulo='" + titulo + '\'' +
                ", desarrollador='" + desarrollador + '\'' +
                ", lanzamiento='" + lanzamiento + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", genero='" + genero + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", pegi=" + (getPegi() == 0 ? "No disponible":"PEGI: %d".formatted(getPegi())) +
                '}';
    }
}
