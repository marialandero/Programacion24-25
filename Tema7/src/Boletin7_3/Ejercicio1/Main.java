package Boletin7_3.Ejercicio1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Persona>  listaPersonas = new ArrayList<>();
        listaPersonas.add(new Persona("María", "45645787k", "45677886", LocalDate.of(1996, 4, 29)));
        listaPersonas.add(new Persona("Tere", "436554745j", "45646577", LocalDate.of(2003, 6, 16)));
        listaPersonas.add(new Persona("Abri", "34556558a", "32454657", LocalDate.of(1997, 10, 18)));
        listaPersonas.add(new Persona("Franci", "42524565f", "89756756", LocalDate.of(1993, 11, 26)));
        listaPersonas.add(new Persona("Rafa", "68764345r", "45765868", LocalDate.of(1999, 10, 26)));
        crearXML(listaPersonas);
    }

    public static void crearXML(List<Persona> listaPersonas) {

        File file = new File("src/Boletin7_3/Ejercicio1/ListaDePersonas.xml");


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            Element root = documento.createElement("personas"); // Nodo raíz
            documento.appendChild(root);

            for (Persona persona : listaPersonas) {
                Element elementoPersona = documento.createElement("persona");
                elementoPersona.setAttribute("dni", persona.getDni());

                Element nombre =  documento.createElement("nombre");
                nombre.setTextContent(persona.getNombre());

                Element telefono =  documento.createElement("telefono");
                telefono.setTextContent(persona.getTelefono());

                Element fechaNacimiento =  documento.createElement("fecha_nacimiento");
                fechaNacimiento.setTextContent(persona.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                elementoPersona.appendChild(nombre);
                elementoPersona.appendChild(telefono);
                elementoPersona.appendChild(fechaNacimiento);

                root.appendChild(elementoPersona);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            StreamResult result = new StreamResult(file);
            DOMSource source = new DOMSource(documento);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
