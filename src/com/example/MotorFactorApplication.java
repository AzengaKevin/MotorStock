package com.example;

import com.example.models.Catalogue;
import com.example.view.Display;

public class MotorFactorApplication {

    private int width, height;

    private String title;

    private Display display;

    public MotorFactorApplication(int width, int height, String title) {

        this.width = width;
        this.height = height;
        this.title = title;

        initFrame();

        loadProducts();
    }

    private void loadProducts() {

        Catalogue.loadProducts();

    }

    private void initFrame() {
        display = new Display(width, height, title);
    }
}
