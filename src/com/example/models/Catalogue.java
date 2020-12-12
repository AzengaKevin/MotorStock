package com.example.models;

import com.example.utils.FileHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Catalogue {

    private static final String TAG = Catalogue.class.getName();


    private static final Set<Product> PRODUCTS = new HashSet<>();

    public static Set<Product> getProducts() {
        return PRODUCTS;
    }

    public static boolean addProduct(Product product) {
        return PRODUCTS.add(product);
    }

    public static void loadProducts() {

        PRODUCTS.clear();

        //Check whether the file exists else create one
        try {

            List<String> productsString = FileHandler.getFileContent(Product.FILENAME);

            productsString.stream().map(String::trim)
                    .filter(parts -> parts.split(",").length == 9 || parts.split(",").length == 8)
                    .map(parts -> {
                        if (parts.split(",").length == 9) return VehiclePart.fromString(parts);
                        return Product.fromString(parts);
                    }).forEach(PRODUCTS::add);

            Logger.getLogger(TAG).log(Level.INFO, "Products count: " + PRODUCTS.size());

        } catch (IOException e) {
            System.err.println("Loading Products: " + e.getLocalizedMessage());
        }

    }
}
