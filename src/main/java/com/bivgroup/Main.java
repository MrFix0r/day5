package com.bivgroup;

import xmlSecondStructure.Faculty;
import xmlSecondStructure.University;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {
    public static void main(String[] args) {
        University uni = new University();

        Faculty faculty1 = new Faculty();
        faculty1.setName("ФСГН");
        Faculty faculty2 = new Faculty();
        faculty2.setName("ИСАУ");

        uni.getFaculty().add(faculty1);
        uni.getFaculty().add(faculty2);


        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(University.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(uni, System.out);

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
