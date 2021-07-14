package com.example.creditcaculatorapp.model;

public class creditInfo {
    private String subject;
    private float credit;
    private float grade;

    public creditInfo(String subject, float credit, float grade){
        this.subject = subject;
        this.credit = credit;
        this.grade=grade;

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
}
