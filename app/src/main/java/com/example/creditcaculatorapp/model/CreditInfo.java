package com.example.creditcaculatorapp.model;

public class CreditInfo {
    private String subject;
    private float credit;
    private float grade;
    private int semester;

    public CreditInfo(String subject, float credit, float grade, int semester){
        this.subject = subject;
        this.credit = credit;
        this.grade=grade;
        this.semester=semester;
    }

    public String getSubject() {
        return subject;
    }

    public float getCredit() {
        return credit;
    }

    public float getGrade() {
        return grade;
    }

    public int getSemester() { return semester; }
}
