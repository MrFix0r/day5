package com.bivgroup;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    Group tmpGroup;
    Subject tmpSubject;

    boolean f = false;
    protected List<Group> groupList = new ArrayList<>();


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("group")){
            tmpGroup = new Group(attributes.getValue("name"));
        }
        if (qName.equalsIgnoreCase("subject"))
        {
            tmpSubject = new Subject(attributes.getValue("name"));
        }
        if (qName.equalsIgnoreCase("mark"))
        {
            tmpSubject.incMarkCount();
            tmpSubject.incSumRating(Integer.parseInt(attributes.getValue("rating")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equalsIgnoreCase("group")) {
            groupList.add(tmpGroup);
        }
        if (qName.equalsIgnoreCase("subject")){
            List<Subject> subjectList = tmpGroup.getSubjectsList();
            for (Subject sub:subjectList) {
                if (sub.getName().equals(tmpSubject.getName())){
                    f = true;
                    for (Subject subject: subjectList) {
                        if (subject.getName().equals(tmpSubject.getName()))
                        {
                            subject.incMarkCount(tmpSubject.getMarksCount());
                            subject.incSumRating(tmpSubject.getSumRating());
                        }
                    }
                }
            }
            if (!f) tmpGroup.getSubjectsList().add(tmpSubject);
            f=false;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        for (Group group: groupList) {
            System.out.println("----------------------------------");
            System.out.println("Группа - " + group.getName());
            for (Subject subject: group.getSubjectsList()) {
                System.out.println(subject.getName() + " | Средний балл - " + (double)subject.getSumRating()/subject.getMarksCount());
            }
        }
    }
}
