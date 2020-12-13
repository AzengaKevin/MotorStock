package com.example;

import com.example.models.Catalogue;
import com.example.models.Individual;
import com.example.utils.FileHandler;
import com.example.view.Display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MotorFactorApplication {

    private static final String TAG = MotorFactorApplication.class.getName();

    private int width, height;

    private String title;

    private Display display;

    private static final List<Individual> INDIVIDUALS = new ArrayList<>();

    public MotorFactorApplication(int width, int height, String title) {

        this.width = width;
        this.height = height;
        this.title = title;

        loadProducts();

        MotorFactorApplication.loadIndividuals();

        initFrame();

    }

    private void loadProducts() {

        Catalogue.loadProducts();

    }

    private void initFrame() {
        display = new Display(width, height, title, this);
    }


    public static void loadIndividuals() {

        INDIVIDUALS.clear();

        //Check whether the file exists else create one
        try {

            List<String> individualsString = FileHandler.getFileContent(Individual.FILENAME);

            individualsString.stream().map(String::trim)
                    .filter(parts -> parts.split(",").length == 4)
                    .map(Individual::fromString).forEach(INDIVIDUALS::add);

            Logger.getLogger(TAG).log(Level.INFO, "Individuals count: " + INDIVIDUALS.size());

        } catch (IOException e) {
            System.err.println("Loading Products: " + e.getLocalizedMessage());
        }
    }

    public static List<Individual> getIndividuals() {
        return INDIVIDUALS;
    }
}
