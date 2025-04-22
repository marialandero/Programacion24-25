package Boletin7_2.Ejercicio4;

/*
Realizar un programa que solicite un nombre de un fichero de texto. El fichero contiene
en el nombre del modelo de coche, un espacio y su matrícula. El programa debe crear
otro fichero de texto llamado “MatriculasCorrectas.txt” donde sólo aparezcan las
matriculas con formato actual, una por línea. Las matriculas en formato actual están
formadas por 4 dígitos un guión ‘-’ y 3 letras mayúsculas.
 */

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio4 {
    public static void main(String[] args) {
        try {
            // comprobarMatriculas("Matriculas.txt");
            comprobarMatriculasV2("Matriculas.txt");
        } catch (MatriculaException e) {
            // Se muestra el mensaje personalizado si se lanza una excepción
            System.out.println(e.getMessage());
        }
    }

    /*
    * Este método lee un fichero de texto con nombres de modelos de coches y sus matrículas.
    * Extrae únicamente las matrículas que siguen el formato actual (####-AAA) y las guarda en otro fichero.
    */

    public static void comprobarMatriculas(String nombreDelFichero) throws MatriculaException {
        // Ruta al archivo de entrada
        Path archivo = Path.of("src/Boletin7_2/Ejercicio4/", nombreDelFichero);
        // Expresión regular que representa una matrícula válida: 4 dígitos + guión + 3 letras mayúsculas
        String regex = "\\d{4,4}-[A-Z]{3,3}";

        // Verifica si el archivo existe
        if (!Files.exists(archivo)) {
            throw new MatriculaException("El fichero no existe");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo.toFile()))) {
            String cadenaLeida;
            List<String> matriculasValidas = new LinkedList<>(); // Lista para almacenar matrículas válidas

            // Lectura línea a línea del fichero
            while ((cadenaLeida = br.readLine()) != null) {
                // Divide la línea por espacios (se espera que la matrícula esté al final)
                String[] parts = cadenaLeida.split(" ");

                // Compara la última parte de la línea (la matrícula) con el patrón
                Pattern patron = Pattern.compile(regex);
                Matcher comparador = patron.matcher(parts[parts.length - 1]);
                if (comparador.matches()) {
                    // Si coincide con el patrón, se añade a la lista
                    matriculasValidas.add(parts[parts.length - 1]);
                }
            }

            // Si no se encontró ninguna matrícula válida, se lanza una excepción
            if (matriculasValidas.isEmpty()) {
                throw new MatriculaException("No existen coincidencias en las matrículas");
            }

            // Ruta del nuevo archivo de salida
            Path nuevoArchivo = Path.of("src/Boletin7_2/Ejercicio4/", "MatriculasCorrectas.txt");

            // Si el archivo ya existe, se lanza una excepción
            if (Files.exists(nuevoArchivo)) {
                throw new MatriculaException("El fichero ya existe");
            }

            // Se crea el nuevo archivo
            Files.createFile(nuevoArchivo);

            // Se escribe cada matrícula válida en una nueva línea del fichero de salida
            try (PrintWriter pw = new PrintWriter(nuevoArchivo.toFile())) {
                matriculasValidas.forEach(pw::println);
            }
        } catch (FileAlreadyExistsException e) {
            // Esta excepción es redundante porque ya se comprobço antes
            throw new MatriculaException("El archivo que se intenta crear ya existe");
        } catch (IOException e) {
            // Captura cualquier otro error de entrada/salida
            throw new MatriculaException("Error en el buffer");
        }
    }

    public static void comprobarMatriculasV2(String nombreFichero) throws MatriculaException {
        // Ruta del archivo de matrículas
        Path archivo = Path.of("src/Boletin7_2/Ejercicio4/", nombreFichero);
        // Ruta del archivo de matrículas correctas
        Path archivoMatriculasCorrectas = Path.of("src/Boletin7_2/Ejercicio4/MatriculasCorrectas.txt");
        // Expresión regular
        String regex = "\\p{L}+ (?<MATRICULA>\\d{4}-[A-Z&&[^AEIOU]]{3})";
        // Flujo con try-with-resources
        try (Stream<String> lineas = Files.lines(archivo)) {
            // Si existe, se borra y se crea
            Files.deleteIfExists(archivoMatriculasCorrectas);
            Files.createFile(archivoMatriculasCorrectas);
            // Flujo de líneas
            Pattern patron = Pattern.compile(regex);
            // Flujo modificado
            lineas.map(patron::matcher).filter(Matcher::matches)
                    .forEach(matcher -> {
                        try {
                            Files.writeString(archivoMatriculasCorrectas, matcher.group("MATRICULA")+"\n", StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (FileNotFoundException e) {
            // Excepción si no se encuentra el archivo
            throw new MatriculaException("No se encuentra el archivo que estás intentando leer.");
        } catch (Exception e) {
            // Cualquier otro error de entrada/salida
            throw new MatriculaException("Otro tipo de error leyendo el fichero.");
        }
    }
}
