package com.example.controllers;

import com.example.MotorFactorApplication;
import com.example.models.Individual;
import com.example.utils.FileHandler;

import java.io.IOException;

public class IndividualController {

    public void addIndividual(Individual individual) {

        //Add to the file
        try {
            FileHandler.appendToFile(Individual.FILENAME, individual.toString());

            MotorFactorApplication.loadIndividuals();

        } catch (IOException exception) {
            System.err.println("Error adding individual: " + exception.getLocalizedMessage());
        }

    }
}
