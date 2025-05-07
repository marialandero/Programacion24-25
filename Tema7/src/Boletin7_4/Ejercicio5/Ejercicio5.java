package Boletin7_4.Ejercicio5;
/*
5. Crea un programa que lea líneas del teclado y las añada a un archivo llamado
"datos.txt". Si el archivo ya existe, el programa deberá añadir las nuevas líneas al
final del archivo. Si el archivo no existe, deberá crearlo.
 */
import java.io.*;

public class Ejercicio5 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter salida = new PrintWriter(new FileWriter("src/Boletin7_4/Ejercicio5/datos.txt", true))) {

            String linea;
            while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("fin")) {
                salida.println(linea);
                salida.flush();
            }
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
