package com.system;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String crunchifyFile = "src/Company.xml";

        try {
            // Reads text from a character-input stream
            BufferedReader crunchifyReader = new BufferedReader(new InputStreamReader(System.in));

            // Defines a factory API that enables applications to obtain a parser that produces DOM object trees from
            // XML documents.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //XML54
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

            // The Document interface represents the entire HTML or XML document. Conceptually, it is the root of the
            // document tree, and provides the primary access to the document's data.
            Document doc = factory.newDocumentBuilder().parse(crunchifyFile);
            //CREATE XML DOCUMENT 54
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc54 = docBuilder.newDocument();


            //Root element 54
            Element rootElement = doc54.createElement("Company");
            doc54.appendChild(rootElement);

            //Child Element 54
            Element staff = doc54.createElement("Staff");
            rootElement.appendChild(staff);

            //set attribute to staff element
            staff.setAttribute("id","1");


            //New Element
            Element firstname = doc54.createElement("firstname");
            firstname.appendChild(doc54.createTextNode("yong"));
            staff.appendChild(firstname);






            // write dom document to a file
            try (FileOutputStream output =
                         new FileOutputStream("src/djayja-dom.xml")) {
                writeXml(doc54, output);
            } catch (IOException e) {
                e.printStackTrace();
            }


            // Get input element from user
           // System.out.print("Enter element name: ");

            // readLine() reads a line of text. A line is considered to be terminated by any one of a line feed ('\n'),
            // a carriage return ('\r'), a carriage return followed immediately by a line feed, or by reaching the end-of-file (EOF).
            String element = crunchifyReader.readLine();

            // Returns a NodeList of all the Elements in document order with a given tag name and are contained in the document.
            NodeList nodes = doc.getElementsByTagName("Company");
            System.out.println("\n Here you go => Total # of Elements: " + nodes.getLength());

        } catch (Exception e) {
            System.out.println(e);
        }
    }




    // write doc to output stream
    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
