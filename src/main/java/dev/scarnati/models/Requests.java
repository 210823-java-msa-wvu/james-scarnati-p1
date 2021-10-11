package dev.scarnati.models;

import java.sql.Date;

public class Requests {
    int id;
    int employeeId;
    String requestDate;
    String courseStart;
    String passingGrade;
    float courseCost;
    float reimbursementAmount;
    int courseType;
    boolean pass;
    boolean approval;
    boolean denial;
    String grade;
    boolean dsCheck;
    String file;
    boolean urgent;
    int hours;
    String location;
    String reason;
    boolean complete;

    public Requests() {
    }



    public Requests(int id, int employeeId, String requestDate, String courseStart, String passingGrade,
                    float courseCost, float reimbursementAmount, int courseType, boolean pass,
                    boolean approval, boolean denial, String grade,
                    boolean dsCheck, String file, boolean urgent, int hours, String location, String reason, boolean complete) {
        this.id = id;
        this.employeeId = employeeId;
        this.requestDate = requestDate;
        this.courseStart = courseStart;
        this.passingGrade = passingGrade;
        this.courseCost = courseCost;
        this.reimbursementAmount = reimbursementAmount;
        this.courseType = courseType;
        this.pass = pass;
        this.approval = approval;
        this.denial = denial;
        this.grade = grade;
        this.dsCheck = dsCheck;
        this.file = file;
        this.urgent = urgent;
        this.hours = hours;
        this.location = location;
        this.reason = reason;
        this.complete = complete;
    }
    public Requests(int id, int employeeId, String requestDate, String courseStart, String passingGrade,
                    float courseCost, float reimbursementAmount, int courseType, boolean pass,
                    boolean approval, boolean denial, String grade,
                    boolean dsCheck, String file, boolean urgent, int hours, String location, String reason) {
        this.id = id;
        this.employeeId = employeeId;
        this.requestDate = requestDate;
        this.courseStart = courseStart;
        this.passingGrade = passingGrade;
        this.courseCost = courseCost;
        this.reimbursementAmount = reimbursementAmount;
        this.courseType = courseType;
        this.pass = pass;
        this.approval = approval;
        this.denial = denial;
        this.grade = grade;
        this.dsCheck = dsCheck;
        this.file = file;
        this.urgent = urgent;
        this.hours = hours;
        this.location = location;
        this.reason = reason;
    }

    public Requests(int id, int employeeId, String requestDate, String courseStart, String passingGrade, float courseCost, float reimbursementAmount, int courseType, boolean pass, boolean approval) {
        this.id = id;
        this.employeeId = employeeId;
        this.requestDate = requestDate;
        this.courseStart = courseStart;
        this.passingGrade = passingGrade;
        this.courseCost = courseCost;
        this.reimbursementAmount = reimbursementAmount;
        this.courseType = courseType;
        this.pass = pass;
        this.approval = approval;
    }

    public Requests(int employeeId, String requestDate, String courseStart, String passingGrade, float courseCost, float reimbursementAmount, int courseType, boolean pass, boolean approval, boolean denial) {
        this.employeeId = employeeId;
        this.requestDate = requestDate;
        this.courseStart = courseStart;
        this.passingGrade = passingGrade;
        this.courseCost = courseCost;
        this.reimbursementAmount = reimbursementAmount;
        this.courseType = courseType;
        this.pass = pass;
        this.approval = approval;
        this.denial = denial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(String passingGrade) {
        this.passingGrade = passingGrade;
    }

    public float getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(float courseCost) {
        this.courseCost = courseCost;
    }

    public float getReimbursementAmount() {
        return reimbursementAmount;
    }

    public void setReimbursementAmount(float reimbursementAmount) {
        this.reimbursementAmount = reimbursementAmount;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }



    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public boolean isDenial() {
        return denial;
    }

    public void setDenial(boolean denial) {
        this.denial = denial;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isDsCheck() {
        return dsCheck;
    }

    public void setDsCheck(boolean dsCheck) {
        this.dsCheck = dsCheck;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", requestDate='" + requestDate + '\'' +
                ", courseStart='" + courseStart + '\'' +
                ", passingGrade='" + passingGrade + '\'' +
                ", courseCost=" + courseCost +
                ", reimbursementAmount=" + reimbursementAmount +
                ", courseType=" + courseType +
                ", pass=" + pass +
                ", approval=" + approval +
                ", denial=" + denial +
                ", grade='" + grade + '\'' +
                ", dsCheck=" + dsCheck +
                ", file='" + file + '\'' +
                ", urgent=" + urgent +
                ", hours=" + hours +
                ", location='" + location + '\'' +
                ", reason='" + reason + '\'' +
                ", complete=" + complete +
                '}';
    }
}
