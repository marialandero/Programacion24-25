package ExamenSimpsons;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) {

        episodiosAPartirDe1992();
    }

    private static void episodiosAPartirDe1992() {

        StringBuilder sb = new StringBuilder();
        try { // Preparamos el parser para leer el documento
            File ficheroXML = new File("src/ExamenSimpsons/simpsons.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(ficheroXML);

            // Obtenemos el nodo raíz
            Element raiz = documento.getDocumentElement();
            // Capturamos los nodos <capitulo> en una NodeList
            NodeList listaNodosCapitulo = raiz.getElementsByTagName("capitulo");

            // Comprueba que exista algún nodo <capitulo>
            if (listaNodosCapitulo.getLength() > 0) {
                System.out.println("===Capítulos estrenados a partir de 1992===\n");
            }

            for (int i = 0; i < listaNodosCapitulo.getLength(); i++) {
                // Obtenemos el nodo
                Element elementoCapitulo = (Element) listaNodosCapitulo.item(i);
                // Obtenemos el elemento fecha
                Element elementoFecha = (Element) elementoCapitulo.getElementsByTagName("fecha_emision").item(0);

                // La fecha es un String donde lo único que nos interesa es la última palabra, que representa el año
                String[] fechaSplit = elementoFecha.getTextContent().split(" ");
                int anyo = Integer.parseInt(fechaSplit[fechaSplit.length - 1]);

                if (anyo > 1992) {
                    sb.append("Nombre: "+elementoCapitulo.getElementsByTagName("nombre").item(0).getTextContent()+"\n");
                    sb.append("Sinopsis: "+elementoCapitulo.getElementsByTagName("sinopsis").item(0).getTextContent()+"\n");
                    sb.append("Fecha emisión: "+elementoCapitulo.getElementsByTagName("fecha_emision").item(0).getTextContent()+"\n\n");
                }
            }
            System.out.println(sb);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new SimpsonsException(e.getMessage());
        }
    }
}
