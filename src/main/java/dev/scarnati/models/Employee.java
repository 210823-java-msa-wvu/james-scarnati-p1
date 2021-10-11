package dev.scarnati.models;

public class Employee {
    int id;
    String fName;
    String lName;
    String username;
    String pass;
    String title;
    float startingAmount;
    float pendingAmount;
    float approvedAmount;

    public Employee() {
    }

    public Employee(String username) {
        this.username = username;
    }

    public Employee(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Employee(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public Employee(int id, String fName, String lName, String username, String pass, String title) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.pass = pass;
        this.title = title;
    }

    public Employee(int id, String fName, String lName, String username, String pass, String title, float startingAmount, float pendingAmount, float approvedAmount) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.pass = pass;
        this.title = title;
        this.startingAmount = startingAmount;
        this.pendingAmount = pendingAmount;
        this.approvedAmount = approvedAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }


    public String getlName() {
        return lName;
    }


    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getStartingAmount() {
        return startingAmount;
    }


    public float getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(float pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public float getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(float approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", title='" + title + '\'' +
                ", startingAmount=" + startingAmount +
                ", pendingAmount=" + pendingAmount +
                ", approvedAmount=" + approvedAmount +
                '}';
    }
}

