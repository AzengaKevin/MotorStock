package com.example.models;

public class Individual {

    public static final String FILENAME = "data/individuals.txt";

    public enum Group {
        Supplier, Customer;

        public static Group fromString(String group) {
            if (group.equalsIgnoreCase(Supplier.name())) return Supplier;
            if (group.equalsIgnoreCase(Customer.name())) return Customer;
            return null;
        }
    }

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

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", id, name, contact, group.toString());
    }

    public static Individual fromString(String individualString) {


        String[] parts = individualString.split(",");

        if (parts.length == 4) {

            try {

                String id = parts[0];
                String name = parts[1];
                String contact = parts[2];
                Group group = Group.fromString(parts[3]);

                return new Individual(id, name, contact, group);


            } catch (Exception exception) {
                System.err.println("Individual Error: " + exception.getLocalizedMessage());

            }

        }

        return null;
    }
}
