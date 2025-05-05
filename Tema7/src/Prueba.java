import java.io.*;
        import java.nio.file.*;
        import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer rutas de directorios
        System.out.print("Introduce el path del directorio de origen: ");
        String origenStr = scanner.nextLine();

        System.out.print("Introduce el path del directorio de destino: ");
        String destinoStr = scanner.nextLine();

        File origen = new File(origenStr);
        File destino = new File(destinoStr);

        // Validaciones
        if (!origen.isDirectory() || !origen.canRead()) {
            System.out.println("El directorio de origen no es válido o no tiene permiso de lectura.");
            return;
        }

        if (!destino.isDirectory() || !destino.canWrite()) {
            System.out.println("El directorio de destino no es válido o no tiene permiso de escritura.");
            return;
        }

        // Filtrar y copiar archivos
        File[] archivos = origen.listFiles();
        if (archivos == null) {
            System.out.println("Error al leer el directorio de origen.");
            return;
        }

        for (File archivo : archivos) {
            if (archivo.isFile()
                    && archivo.length() > 1024
                    && archivo.getName().toLowerCase().endsWith(".txt")) {

                try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                    String primeraLinea = reader.readLine();
                    if (primeraLinea != null) {
                        String[] palabras = primeraLinea.split("\\s+");
                        if (palabras.length > 0 && palabras[0].equalsIgnoreCase("COPIAR")) {
                            // Copiar archivo
                            Path origenPath = archivo.toPath();
                            Path destinoPath = Paths.get(destino.getAbsolutePath(), archivo.getName());
                            Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Archivo copiado: " + archivo.getName());
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error al procesar el archivo: " + archivo.getName());
                    e.printStackTrace();
                }
            }
        }

        scanner.close();
    }
}
