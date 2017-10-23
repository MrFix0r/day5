package com.bivgroup;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Map;

public class MyHandler extends DefaultHandler {

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bNickName = false;
    boolean bMarks = false;

    int sumRating = 0;
    int markCount = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("group")){
//            String groupName = attributes.getValue("name");
//            Map<String,Integer> groupMap;
        }
        if (qName.equalsIgnoreCase("mark"))
        {
            String rating = attributes.getValue("rating");
            sumRating += Integer.parseInt(rating);
            markCount++;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equalsIgnoreCase("group")) {
//            System.out.println("Group : " + qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {

//        if (bFirstName) {
//            System.out.println("First Name: " + new String(ch, start, length));
//            bFirstName = false;
//        } else if (bLastName) {
//            System.out.println("Last Name: " + new String(ch, start, length));
//            bLastName = false;
//        } else if (bNickName) {
//            System.out.println("Nick Name: " + new String(ch, start, length));
//            bNickName = false;
//        } else if (bMarks) {
//            System.out.println("Marks: " + new String(ch, start, length));
//            bMarks = false;
//        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println();
        System.out.println("Marks Count = " + markCount + ", MarkAverage = " + (double)sumRating/markCount);
    }
}
