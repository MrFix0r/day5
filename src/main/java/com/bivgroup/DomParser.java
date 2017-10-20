package com.bivgroup;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class DomParser {
    public static void Parse(String filePath) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Faculty");
            System.out.println("----------------------------");
            //
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("Current Element : " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Faculty name is : "
                            + eElement.getAttribute("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void modifyXmlFileTagArg (String fileName, String tagName, String argName, String argNewValue){
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            NodeList nlFaculty = doc.getElementsByTagName(tagName);
            for (int tmp=0;tmp < nlFaculty.getLength();tmp++)
            {
                Node node = nlFaculty.item(tmp);
                NamedNodeMap attr = node.getAttributes();
                Node nodeAttr = attr.getNamedItem(argName);
                nodeAttr.setTextContent(argNewValue);
            }

            // write the content on console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            transformer.transform(source, new StreamResult(fileName.replace(".xml","Modified.xml")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
