package com.example.jadwalperkuliahan;

public class Schedule {
    private String subject;
    private String lecturer;
    private String time;

    public Schedule(String subject, String lecturer, String time) {
        this.subject = subject;
        this.lecturer = lecturer;
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getTime() {
        return time;
    }
}
