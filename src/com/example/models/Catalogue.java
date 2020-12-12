package com.example.models;

import java.util.HashSet;
import java.util.Set;

public class Catalogue {

    private static final Set<Product> PRODUCTS = new HashSet<>();

    public static Set<Product> getProducts() {
        return PRODUCTS;
    }

    public static boolean addProduct(Product product) {
        return PRODUCTS.add(product);
    }
}
