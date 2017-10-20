package com.bivgroup;

import xmlSecondStructure.Faculty;
import xmlSecondStructure.University;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class Main {
    public static void main(String[] args){
        System.out.println();

        //2 пункт: JAXB - сохранение объекта  в xml (создать полную иерархию, заполнить произвольно, сохранить в xml)
        University uni = createModel();
        JAXBParseAndSaveToFile(uni);

        //3 пункт: валидация xml по xsd (проверить ранее сохраненную xml, испортить ее и проверить, проверить другую xml)
        System.out.println();
        validateXMLByXSD(new File("./src/main/resources/xml/uni.xml"), new File("./src/main/resources/xml/uni3.xsd"));
        validateXMLByXSD(new File("./target/test2.xml"), new File("./src/main/resources/xml/uni3.xsd") );

        //4 пункт: DOM - десериализация xml, редактирование, сохранение (распарсить xml, изменить произвольное значение, сохранить изменения)
        System.out.println();
        System.out.println("-----DOMParser - parsed file above-----");
        DomParser.Parse("./target/test.xml");
        System.out.println();
        DomParser.modifyXmlFileTagArg("./target/test.xml","Faculty","name","Хихи");

        //5 пункт: SAX - чтение и анализ (посчитать и вывести среднюю оценку по группе по предметам)
//        MySAXParser.parse("./src/main/resources/xml/uni.xml");

    }

    private static void JAXBParseAndSaveToFile(University uni) {
        try {

            File file = new File("./target/test.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(University.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(uni, file);
            jaxbMarshaller.marshal(uni, System.out);

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static University createModel() {
        University uni = new University();

        Faculty faculty1 = new Faculty();
        faculty1.setName("ФСГН");
        Faculty faculty2 = new Faculty();
        faculty2.setName("ИСАУ");

        uni.getFaculty().add(faculty1);
        uni.getFaculty().add(faculty2);
        return uni;
    }

    public static void validateXMLByXSD(File xml, File xsd) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (Exception e) {
            System.out.println("Вылетел Exception (почему конкретно не валидны), но я его не вывел, потому что некрасиво :)");
//            e.printStackTrace();
            System.out.println("Файл \'" + xml.getName() + "\' не валиден с файлом \'" + xsd.getName() + "\'");
            return;
        }
        System.out.println("Файл \'" + xml.getName() + "\' валиден с файлом \'" + xsd.getName() + "\'");;
    }
}
