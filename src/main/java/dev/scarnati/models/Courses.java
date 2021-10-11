package dev.scarnati.models;

public class Courses {

int id;
String course;
float reimbursementPercent;

    public Courses() {
    }

    public Courses(int id) {
        this.id = id;
    }

    public Courses(int id, float reimbursementPercent) {
        this.id = id;
        this.reimbursementPercent = reimbursementPercent;
    }

    public Courses(int id, String course, float reimbursementPercent) {
        this.id = id;
        this.course = course;
        this.reimbursementPercent = reimbursementPercent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public float getReimbursementPercent() {
        return reimbursementPercent;
    }

    public void setReimbursementPercent(float reimbursementPercent) {
        this.reimbursementPercent = reimbursementPercent;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", course='" + course + '\'' +
                ", reimbursementPercent=" + reimbursementPercent +
                '}';
    }
}
