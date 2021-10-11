package dev.scarnati.models;

public class Approval {
    int id;
    boolean dsApproval;
    String dsDenialReason;
    boolean dhApproval;
    boolean bcApproval;
    boolean infoRequestE;
    boolean infoRequestDs;
    boolean infoRequestDh;
    String employeeInfo;
    String dsInfo;
    String dhInfo;
    boolean denied;

    public Approval() {
    }

    public Approval(int id, boolean dsApproval, String dsDenialReason, boolean dhApproval, boolean bcApproval, boolean infoRequestE, boolean infoRequestDs, boolean infoRequestDh, String employeeInfo, String dsInfo, String dhInfo, boolean denied) {
        this.id = id;
        this.dsApproval = dsApproval;
        this.dsDenialReason = dsDenialReason;
        this.dhApproval = dhApproval;
        this.bcApproval = bcApproval;
        this.infoRequestE = infoRequestE;
        this.infoRequestDs = infoRequestDs;
        this.infoRequestDh = infoRequestDh;
        this.employeeInfo = employeeInfo;
        this.dsInfo = dsInfo;
        this.dhInfo = dhInfo;
        this.denied = denied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDsApproval() {
        return dsApproval;
    }

    public void setDsApproval(boolean dsApproval) {
        this.dsApproval = dsApproval;
    }

    public String getDsDenialReason() {
        return dsDenialReason;
    }

    public void setDsDenialReason(String dsDenialReason) {
        this.dsDenialReason = dsDenialReason;
    }

    public boolean isDhApproval() {
        return dhApproval;
    }

    public void setDhApproval(boolean dhApproval) {
        this.dhApproval = dhApproval;
    }

    public boolean isBcApproval() {
        return bcApproval;
    }

    public void setBcApproval(boolean bcApproval) {
        this.bcApproval = bcApproval;
    }

    public boolean isInfoRequestE() {
        return infoRequestE;
    }

    public void setInfoRequestE(boolean infoRequestE) {
        this.infoRequestE = infoRequestE;
    }

    public boolean isInfoRequestDs() {
        return infoRequestDs;
    }

    public void setInfoRequestDs(boolean infoRequestDs) {
        this.infoRequestDs = infoRequestDs;
    }

    public boolean isInfoRequestDh() {
        return infoRequestDh;
    }

    public void setInfoRequestDh(boolean infoRequestDh) {
        this.infoRequestDh = infoRequestDh;
    }

    public String getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(String employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public String getDsInfo() {
        return dsInfo;
    }

    public void setDsInfo(String dsInfo) {
        this.dsInfo = dsInfo;
    }

    public String getDhInfo() {
        return dhInfo;
    }

    public void setDhInfo(String dhInfo) {
        this.dhInfo = dhInfo;
    }

    public boolean isDenied() {
        return denied;
    }

    public void setDenied(boolean denied) {
        this.denied = denied;
    }

    @Override
    public String toString() {
        return "Approval{" +
                "id=" + id +
                ", dsApproval=" + dsApproval +
                ", dsDenialReason='" + dsDenialReason + '\'' +
                ", dhApproval=" + dhApproval +
                ", bcApproval=" + bcApproval +
                ", infoRequestE=" + infoRequestE +
                ", infoRequestDs=" + infoRequestDs +
                ", infoRequestDh=" + infoRequestDh +
                ", employeeInfo='" + employeeInfo + '\'' +
                ", dsInfo='" + dsInfo + '\'' +
                ", dhInfo='" + dhInfo + '\'' +
                ", denied=" + denied +
                '}';
    }
}

