package com.bivgroup;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class MySAXParser {
    public static void parse(String fileName) {
        try {
            File inputFile = new File(fileName);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser mySaxParser = factory.newSAXParser();
            MyHandler userhandler = new MyHandler();
            mySaxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
