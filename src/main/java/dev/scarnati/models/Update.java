package dev.scarnati.models;

public class Update {
    float amount;
    int employee;
    int request;

    public Update() {
    }

    public Update(float amount, int employee, int request) {
        this.amount = amount;
        this.employee = employee;
        this.request = request;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Update{" +
                "amount=" + amount +
                ", employee=" + employee +
                ", request=" + request +
                '}';
    }
}
