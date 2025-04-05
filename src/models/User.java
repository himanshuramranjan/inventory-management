package models;

public abstract class User {
    protected final int id;
    private String name;
    private  String mail;

    public User(int id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }
}
