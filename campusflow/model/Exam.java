package com.campusflow.model;

public class Exam {
    private int id;
    private String subject;
    private String date;
    private String room;

    public Exam(int id, String subject, String date, String room) {
        this.id = id;
        this.subject = subject;
        this.date = date;
        this.room = room;
    }

    public Exam(String subject, String date, String room) {
        this.subject = subject;
        this.date = date;
        this.room = room;
    }

    public int getId() { return id; }
    public String getSubject() { return subject; }
    public String getDate() { return date; }
    public String getRoom() { return room; }
}