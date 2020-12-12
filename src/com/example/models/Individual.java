package com.example.models;

public class Individual {

    public static final String FILENAME = "individuals.txt";

    public enum Group {Supplier, Customer}

    private String id;
    private String name;
    private String contact;
    private Group group;

    public Individual(String id, String name, String contact, Group group) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
