package ExamenSimpsons;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) {

        //episodiosAPartirDe1992();
        //generarFicheroSinopsisConMasDe30Palabras();
        generarFicheroNombresModificados();
    }

    private static void episodiosAPartirDe1992() {

        StringBuilder sb = new StringBuilder(); // Para ir guardando texto sobre los episodios que se imprimirán
        try {
            // Preparamos el parser para leer el documento
            File ficheroXML = new File("src/ExamenSimpsons/simpsons.xml"); // Crea un objeto File que apunta al archivo simpsons.xml
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // para crear el parser
            DocumentBuilder db = dbf.newDocumentBuilder(); // el parser
            Document documento = db.parse(ficheroXML); // lee el archivo y lo convierte en un objeto Document que representa todo el xml con nodos

            // Obtenemos el nodo raíz
            Element raiz = documento.getDocumentElement();

            /* Capturamos de la raíz todos los nodos <capitulo> en una NodeList
            También se podría hacer directamente: NodeList listaNodosCapitulo = documento.getElementsByTagName("capitulo"); */
            NodeList listaNodosCapitulo = raiz.getElementsByTagName("capitulo");

            // Imprime el encabezado si existe al menos algún nodo <capitulo>
            if (listaNodosCapitulo.getLength() > 0) {
                System.out.println("=== Capítulos estrenados a partir de 1992 ===\n");
            }

            // Recorre todos los episodios uno por uno
            for (int i = 0; i < listaNodosCapitulo.getLength(); i++) {

                //if(capitulo.getNodeType() == Node.ELEMENT_NODE){ nos aseguramos de que los nodos sean de tipo Element

                // Obtenemos el nodo convirtiéndolo en Element para acceder a sus hijos
                Element elementoCapitulo = (Element) listaNodosCapitulo.item(i);
                // Obtenemos el elemento fecha de dentro de capitulo
                Element elementoFecha = (Element) elementoCapitulo.getElementsByTagName("fecha_emision").item(0);

                // La fecha es un String donde lo único que nos interesa es la última palabra, que representa el año
                String[] fechaSplit = elementoFecha.getTextContent().split(" "); // Spliteamos el String y cogemos el último (fechaSplit.length -1)
                int anyo = Integer.parseInt(fechaSplit[fechaSplit.length - 1]); // Convertimos el String en int

                if (anyo > 1992) { // Ahora podemos hacer la comprobación con el número entero
                    // Concatenamos con el StringBuilder la información de los capítulos que han pasado
                    sb.append("Nombre: " + elementoCapitulo.getElementsByTagName("nombre").item(0).getTextContent() + "\n"); // el index 0 indica el primer (y único) elemento <nombre>
                    sb.append("Sinopsis: " + elementoCapitulo.getElementsByTagName("sinopsis").item(0).getTextContent() + "\n");
                    sb.append("Fecha emisión: " + elementoCapitulo.getElementsByTagName("fecha_emision").item(0).getTextContent() + "\n\n");
                }
                //}
            }
            System.out.println(sb);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println("Error al parsear el fichero: " + e.getMessage());
        }
    }

    private static void generarFicheroSinopsisConMasDe30Palabras() {
        try {
            // Cargamos el xml original para que se lea
            File ficheroOriginal = new File("src/ExamenSimpsons/simpsons.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documentoOriginal = db.parse(ficheroOriginal);

            // Creamos un nuevo documento
            Document nuevoDocumento = db.newDocument();
            // Creamos la raíz del nuevo documento
            Element nuevaRaiz = nuevoDocumento.createElement("simpsons");
            nuevoDocumento.appendChild(nuevaRaiz);

            // Listamos los nodos <capitulo> del documento original
            NodeList listaNodosCapitulo = documentoOriginal.getElementsByTagName("capitulo");

            // Recorremos todos los nodos <capitulo>
            for (int i = 0; i < listaNodosCapitulo.getLength(); i++) {

                Element elementoCapitulo = (Element) listaNodosCapitulo.item(i); // Por cada elemento capitulo...
                Element elementoSinopsis = (Element) elementoCapitulo.getElementsByTagName("sinopsis").item(0); // obtenemos el elemento sinopsis
                String textoSinopsis = elementoSinopsis.getTextContent().trim(); // Extrae el texto de la sinopsis y lo guarda en textoSinopsis sin espacios en blanco por defecto con .trim()

                // Spliteamos el texto de la sinopsis y comprobamos si tiene más de 30 palabras
                String[] sinopsisSlipt = textoSinopsis.split(" ");
                if (sinopsisSlipt.length > 30) {

                    // Creamos los nuevos nodos del documento nuevo
                    Element nuevoCapitulo = nuevoDocumento.createElement("capitulo");
                    Element nuevoNombre = nuevoDocumento.createElement("nombre");
                    nuevoNombre.setTextContent(elementoCapitulo.getElementsByTagName("nombre").item(0).getTextContent());
                    Element nuevaSinopsis = nuevoDocumento.createElement("sinopsis");
                    nuevaSinopsis.setTextContent(textoSinopsis);
                    Element nuevaFecha = nuevoDocumento.createElement("fecha_emision");
                    nuevaFecha.setTextContent(elementoCapitulo.getElementsByTagName("fecha_emision").item(0).getTextContent());

                    nuevoCapitulo.appendChild(nuevoNombre);
                    nuevoCapitulo.appendChild(nuevaSinopsis);
                    nuevoCapitulo.appendChild(nuevaFecha);

                    nuevaRaiz.appendChild(nuevoCapitulo);
                }
            }

            // Guardamos el resultado en un nuevo archivo XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // Asignamos las propiedades de salida para el transformador
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(nuevoDocumento); // Declaramos el documento fuente
            StreamResult result = new StreamResult(new File("src/ExamenSimpsons/simpsons_sinopsis30.xml")); // Declaramos el flujo de salida
            transformer.transform(source, result); // Guardamos el documento cargado en memoria

            System.out.println("Archivo generado correctamente.");

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new SimpsonsException("Error: " + e.getMessage());
        } catch (TransformerFactoryConfigurationError | TransformerException i) {
            throw new SimpsonsException("Error: " + i.getMessage());
        }
    }

    private static void generarFicheroNombresModificados() {

        try {
            // Cargamos el archivo original
            File ficheroOriginal = new File("src/ExamenSimpsons/simpsons.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documentoOriginal = db.parse(ficheroOriginal);

            // Creamos un nuevo documento
            Document nuevoDocumento = db.newDocument();
            // Creamos la raíz del nuevo documento
            Element nuevaRaiz = nuevoDocumento.createElement("simpsons");
            nuevoDocumento.appendChild(nuevaRaiz);

            // Obtenemos todos los nodos <capitulo> del original
            NodeList listaNodosCapitulo = documentoOriginal.getElementsByTagName("capitulo");

            // Recorremos todos los nodos <capitulo>
            for (int i = 0; i < listaNodosCapitulo.getLength(); i++) {
                Element elementoCapitulo = (Element) listaNodosCapitulo.item(i); // Por cada elemento capitulo...
                Element elementoSinopsis = (Element) elementoCapitulo.getElementsByTagName("sinopsis").item(0); // obtenemos el elemento sinopsis
                String textoSinopsis = elementoSinopsis.getTextContent().trim(); // Extrae el texto de la sinopsis y lo guarda en textoSinopsis sin espacios en blanco

                // Reemplazamos los nombres de los personajes usando la expresión regular
                textoSinopsis = textoSinopsis.replaceAll("(Homer|Marge|Bart|Lisa|Maggie)", "**$1**");

                // Creamos los nuevos nodos del documento nuevo
                Element nuevoCapitulo = nuevoDocumento.createElement("capitulo");

                // Creamos el nodo <nombre>
                Element nuevoNombre = nuevoDocumento.createElement("nombre");
                nuevoNombre.setTextContent(elementoCapitulo.getElementsByTagName("nombre").item(0).getTextContent());

                // Creamos el nodo <sinopsis>
                Element nuevaSinopsis = nuevoDocumento.createElement("sinopsis");
                nuevaSinopsis.setTextContent(textoSinopsis);

                // Creamos el nodo <fecha_emision>
                Element nuevaFecha = nuevoDocumento.createElement("fecha_emision");
                nuevaFecha.setTextContent(elementoCapitulo.getElementsByTagName("fecha_emision").item(0).getTextContent());

                // Añadimos los nodos al nuevo nodo <capitulo>
                nuevoCapitulo.appendChild(nuevoNombre);
                nuevoCapitulo.appendChild(nuevaSinopsis);
                nuevoCapitulo.appendChild(nuevaFecha);

                // Añadimos el nuevo capítulo a la raíz del nuevo documento
                nuevaRaiz.appendChild(nuevoCapitulo);
            }
            // Guardamos el resultado en un nuevo archivo XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // Asignamos las propiedades de salida para el transformador
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(nuevoDocumento); // Declaramos el documento fuente
            StreamResult result = new StreamResult(new File("src/ExamenSimpsons/simpsons_nombres_modificados.xml")); // Declaramos el flujo de salida
            transformer.transform(source, result); // Guardamos el documento cargado en memoria

            System.out.println("Archivo generado correctamente.");
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            throw new SimpsonsException("Error: " + e.getMessage());
        } catch (TransformerFactoryConfigurationError | TransformerException i) {
            throw new SimpsonsException("Error: " + i.getMessage());
        }
    }
}
