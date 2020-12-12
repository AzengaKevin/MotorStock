package com.example.models;

import com.example.models.contracts.StockPhysicalDesc;

import java.util.Objects;

public class Product implements StockPhysicalDesc {

    public static final String FILENAME = "data/products.txt";

    protected int width, height, depth, weight;
    protected String id;
    protected String name;
    protected String description;
    protected double price;

    public Product(String id, String name, int width, int height, int depth, int weight, String description, double price) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int getX() {
        return width;
    }

    @Override
    public int getZ() {
        return depth;
    }

    @Override
    public int getY() {
        return height;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return width == product.width &&
                height == product.height &&
                depth == product.depth &&
                weight == product.weight &&
                Double.compare(product.price, price) == 0 &&
                id.equals(product.id) &&
                name.equals(product.name) &&
                description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, depth, weight, id, name, description, price);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%d,%d,%d,%d,%s,%.2f", id, name, width, height, depth, weight, description, price);
    }

    public static Product fromString(String productString) {

        String[] parts = productString.split(",");

        if (parts.length == 8) {

            try {

                String id = parts[0];
                String name = parts[1];
                int width = Integer.parseInt(parts[2]);
                int height = Integer.parseInt(parts[3]);
                int depth = Integer.parseInt(parts[4]);
                int weight = Integer.parseInt(parts[5]);
                String description = parts[6];
                double price = Double.parseDouble(parts[7]);

                return new Product(id, name, width, height, depth, weight, description, price);


            } catch (NumberFormatException exception) {
                System.err.println("Product Error: " + exception.getLocalizedMessage());

            }

        }

        return null;
    }
}
