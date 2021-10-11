package dev.scarnati.models;

public class Info {
    int id;
    String info;

    public Info() {
    }

    public Info(int id, String info) {
        this.id = id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }



    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }
}
