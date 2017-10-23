package com.bivgroup;

import java.util.ArrayList;
import java.util.List;

public class Group {
    String name;
    protected List<Subject> subjectsList;

    public List<Subject> getSubjectsList() {
        return subjectsList;
    }

    public Group(String name) {
        this.name = name;
        subjectsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
