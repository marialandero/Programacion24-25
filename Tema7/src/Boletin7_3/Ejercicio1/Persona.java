package Boletin7_3.Ejercicio1;

import java.time.LocalDate;

public class Persona {
    private String nombre;
    private String dni;
    private String telefono;
    private LocalDate fechaNacimiento;

    public Persona(String nombre, String dni, String telefono, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}
