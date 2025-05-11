package ExamenVideojuegos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneradorFichasApp {

    /**
     * Llamamos al método cargarXML() para parsear el XML y crear objetos Videojuego que serán devueltos en una lista.
     * Luego llamamos al método generarFicheroSalida() para generar los ficheros de texto de cada juego.
     * Si hay algún error, se captura.
     * @param args
     */
    public static void main(String[] args) {

        try {
            File archivoXML = new File("src/ExamenVideojuegos/catalogo_videojuegos.xml");
            List<Videojuego> listaVideojuegos = cargarXML(archivoXML);
            System.out.println("Número de fichas generadas: "+generarFicheroSalida(listaVideojuegos));
        } catch (VideojuegoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método lee el archivo XML y convierte los nodos en objetos Videojuego que devolverá en una lista.
     * @param archivoXML
     * @return
     * @throws VideojuegoException
     */
    public static List<Videojuego> cargarXML(File archivoXML) throws VideojuegoException {
        List<Videojuego> listaVideojuegos = new ArrayList<>();

        try {
            // Preparamos el parser
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(archivoXML);
            documento.getDocumentElement().normalize(); // En este caso no sería necesario normalizar el documento porque de por sí está bastante limpio, pero lo uso porque es una buena práctica.

            NodeList listaNodosVideojuegos = documento.getElementsByTagName("videojuego"); // Buscamos todos los nodos <videojuego> del XML y creamos una lista con ellos.
            // Se recorre cada nodo de la lista
            for (int i = 0; i < listaNodosVideojuegos.getLength(); i++) {
                Node nodoVideojuego = listaNodosVideojuegos.item(i);

                if (nodoVideojuego.getNodeType() == Node.ELEMENT_NODE) { // Nos aseguramos de que los nodos sean de tipo elemento
                    Element elemento = (Element) nodoVideojuego; // Casteamos el node a element

                    // Almacenamos los valores del xml en las siguientes variables

                    String id = elemento.getAttribute("id"); // Buscamos el valor del atributo id de <videojuego>
                    /*
                    Cada getElementsByTagName devuelve una NodeList de los elementos con cada etiqueta que se pide,
                    pero como en este XML solo hay una etiqueta de cada tipo, se devuelve solo un elemento, que serían
                    los .item(0), el primer y único elemento con el nombre de cada etiqueta, con su texto correspondiente.
                     */
                    String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
                    String desarrollador = elemento.getElementsByTagName("desarrollador").item(0).getTextContent();
                    String lanzamiento = elemento.getElementsByTagName("lanzamiento").item(0).getTextContent();
                    String plataforma = elemento.getElementsByTagName("plataforma").item(0).getTextContent();
                    String genero = elemento.getElementsByTagName("genero").item(0).getTextContent();
                    String descripcion = "";
                    NodeList nodoDescripcion = elemento.getElementsByTagName("descripcion"); // Se crea una NodeList para buscar la etiqueta <descripcion> dentro del videojuego actual
                    if (nodoDescripcion.getLength() > 0 ) { // Si se encuentra alguna (por lo tanto la longitud es > 0)...
                        descripcion = nodoDescripcion.item(0).getTextContent(); // Se accede al primer nodo (index 0), y se obtiene el texto
                        // Usamos el constructor con descripción
                        Videojuego videojuego1 = new Videojuego(id, titulo, desarrollador, lanzamiento, plataforma, genero, descripcion);
                        videojuego1.setPegi(extraerPEGI(descripcion)); // Si hay PEGI, se extrae
                        listaVideojuegos.add(videojuego1); // Se añade el videojuego con descripción a la lista de videojuegos
                    } else {
                        descripcion = "No disponible";
                        // Usamos el constructor sin descripción
                        Videojuego videojuego2 = new Videojuego(id, titulo, desarrollador, lanzamiento, plataforma, genero);
                        videojuego2.setPegi(0); // Se le da el valor de 0 a setPegi porque al no haber descripción, tampoco hay PEGI
                        listaVideojuegos.add(videojuego2); // Se añade el videojuego sin descripción a la lista de videojuegos
                    }

                    /*if (!descripcion.isBlank()) {
                        // Usamos el constructor con descripción
                        Videojuego videojuego1 = new Videojuego(id, titulo, desarrollador, lanzamiento, plataforma, genero, descripcion);
                        videojuego1.setPegi(extraerPEGI(descripcion)); // Si hay PEGI, se extrae
                        listaVideojuegos.add(videojuego1);
                    } else {
                        // Usamos el constructor sin descripción
                        Videojuego videojuego2 = new Videojuego(id, titulo, desarrollador, lanzamiento, plataforma, genero);
                        videojuego2.setPegi(0);
                        listaVideojuegos.add(videojuego2);
                    }*/
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new VideojuegoException("Error: "+e.getMessage());
        }
        System.out.printf("Número total de videojuegos leídos del XML: %d\n",listaVideojuegos.size());
        return listaVideojuegos;
    }

    public static int extraerPEGI(String descripcion) {
        // Expresión regular para buscar un número PEGI
        String regex = "PEGI:\\s(?<numero>\\d{1,2})"; // Usamos un grupo con nombre para facilitar el manejo (?<nombreGrupo>expresion)
        Pattern patron = Pattern.compile(regex); // Se compila la regex en un patrón para hacerla coincidir con el texto
        Matcher matcher = patron.matcher(descripcion); // Usamos matcher para encontrar la coincidencia entre la regex y el texto, en este caso la descripción
        if (matcher.find()) { // Si se encuentra la coincidencia...
            return Integer.parseInt(matcher.group("numero")); // Se devuelve el número PEGI extraído, convirtiéndolo a entero
        } else {
            return 0; // Si no se encuentra, se devuelve 0 como valor predeterminado
        }
    }

    public static int generarFicheroSalida(List<Videojuego> videojuegos) {
        AtomicInteger contadorEscritura = new AtomicInteger(); // Es una variable mutable para usarla de contador dentro de una lambda
        // Recorremos la lista de videojuegos
        videojuegos.forEach(videojuego -> {
            File nuevoFichero = new File("src/ExamenVideojuegos/%s_%s.txt".formatted(videojuego.getTitulo().toLowerCase() // Generamos el archivo con el título según las regex
                    .replaceAll("\\W","_").replaceAll("_+","_"),videojuego.getId()));

            // Escribiremos en un buffer que acabará vaciando de manera eficiente todo el contenido en el fichero del disco
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nuevoFichero))) {
                bw.write("====================================\n");
                bw.write("\t\tFICHA DEL VIDEOJUEGO\n");
                bw.write("====================================\n");
                bw.write("ID: "+videojuego.getId()+"\n");
                bw.write("Título: "+videojuego.getTitulo()+"\n");
                bw.write("Desarrollador: "+videojuego.getDesarrollador()+"\n");
                bw.write("Año de lanzamiento: "+videojuego.getLanzamiento()+"\n");
                bw.write("Género: "+videojuego.getGenero()+"\n");
                bw.write("Plataforma: "+videojuego.getPlataforma()+"\n");
                bw.write("PEGI: "+(videojuego.getPegi() == 0 ? "No disponible.":videojuego.getPegi())+"\n");
                bw.write("------------------------------------\n");
                bw.write("Descripción: "+(videojuego.getDescripcion() == null ? "No disponible.":videojuego.getDescripcion())+"\n");
                bw.write("====================================\n");
                // Se incrementa el contador por cada archivo que se haya escrito correctamente
                contadorEscritura.addAndGet(1);
            } catch (IOException _) {
                 /* Si algún dichero falla, capto la excepción pero la ignoro para que no se detenga todo el proceso
                 y el bucle continúe con el siguiente videojuego.
                 El contador no se incrementará, por lo tanto se sabrá cuántos ficheros se han escrito correctamente.*/
            }
            System.out.printf("El fichero nº %d se llama %s\n",contadorEscritura.get(),nuevoFichero.getName());
        });
        return contadorEscritura.get();
    }

    /* Para hacerlo con un for normal

    public static int generarFicheroSalida(List<Videojuego> videojuegos) {
        int contadorEscritura = 0;

        for (Videojuego videojuego : videojuegos) {
            File nuevoFichero = new File("src/ExamenVideojuegos/%s_%s.txt".formatted(
                    videojuego.getTitulo().toLowerCase()
                            .replaceAll("\\W", "_")
                            .replaceAll("_*_", "_"),
                    videojuego.getId()));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nuevoFichero))) {
                bw.write("====================================\n");
                bw.write("\t\tFICHA DEL VIDEOJUEGO\n");
                bw.write("====================================\n");
                bw.write("ID: " + videojuego.getId() + "\n");
                bw.write("Título: " + videojuego.getTitulo() + "\n");
                bw.write("Desarrollador: " + videojuego.getDesarrollador() + "\n");
                bw.write("Año de lanzamiento: " + videojuego.getLanzamiento() + "\n");
                bw.write("Género: " + videojuego.getGenero() + "\n");
                bw.write("Plataforma: " + videojuego.getPlataforma() + "\n");
                bw.write("PEGI: " + (videojuego.getPegi() == 0 ? "No disponible." : videojuego.getPegi()) + "\n");
                bw.write("------------------------------------\n");
                bw.write("Descripción: " + (videojuego.getDescripcion() == null ? "No disponible." : videojuego.getDescripcion()) + "\n");
                bw.write("====================================\n");

                contadorEscritura++;
                System.out.printf("El fichero nº %d se llama %s\n", contadorEscritura, nuevoFichero.getName());

            } catch (IOException e) {
                System.err.println("Error escribiendo el fichero: " + nuevoFichero.getName());
            }
        }

        return contadorEscritura;
    }*/

}
