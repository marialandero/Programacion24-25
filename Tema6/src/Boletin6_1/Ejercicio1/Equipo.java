package Boletin6_1.Ejercicio1;

import java.util.HashSet;
import java.util.Set;

public class Equipo {
    private String nombreEquipo;
    private Set<Alumno> alumnos;

    public Equipo(String nombreEquipo, Set<Alumno> alumnos) {
        this.nombreEquipo = nombreEquipo;
        // Inicializamos el conjunto de tipo HashSet en el constructor, pues es la clase que implementa la interfaz Set.
        this.alumnos = new HashSet<>();
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void anyadirAlumno(Alumno alumno) throws EquipoException {
        if (!alumnos.add(alumno)) { // Si el alumno no pertenece ya al conjunto "alumnos", se añade.
            throw new EquipoException("El alumno ya está en el equipo.");
        }
    }

    public void eliminarAlumno(Alumno alumno) throws EquipoException {
        if (!alumnos.remove(alumno)) {
            throw new EquipoException("El alumno no está en el equipo");
        }
    }
}
