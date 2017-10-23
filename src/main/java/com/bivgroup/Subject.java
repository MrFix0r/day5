package com.bivgroup;

public class Subject {
    String name;
    int marksCount = 0;
    int sumRating = 0;

    public Subject(String name) {
        this.name = name;
    }

    public int getMarksCount() {
        return marksCount;
    }

    public int getSumRating() {
        return sumRating;
    }

    public String getName() {
        return name;
    }

    public void incMarkCount() {
        marksCount++;
    }
    public void incMarkCount(int markCount) {
        marksCount+=markCount;
    }
    public void incSumRating(int rating) {
        sumRating+=rating;
    }

}
