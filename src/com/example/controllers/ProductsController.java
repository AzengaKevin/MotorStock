package com.example.controllers;

import com.example.MotorFactorApplication;
import com.example.models.Catalogue;
import com.example.models.Individual;
import com.example.models.Product;
import com.example.utils.FileHandler;

import java.io.IOException;

public class ProductsController {

    public boolean addProduct(Product product) {
        //Add to the file
        try {
            FileHandler.appendToFile(Product.FILENAME, product.toString());

            Catalogue.loadProducts();

            return true;

        } catch (IOException exception) {
            System.err.println("Error adding individual: " + exception.getLocalizedMessage());
        }

        return false;
    }
}
