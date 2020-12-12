package com.example.view;

import com.example.MotorFactorApplication;
import com.example.models.Catalogue;
import com.example.models.Individual;
import com.example.models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Display extends JFrame {

    private int width, height;

    private String title;
    private MotorFactorApplication motorFactorApplication;

    private JMenuBar theMenuBar;
    private JMenu fileMenu, stockMenu, actionMenu, catalogueMenu, individualsMenu;
    private JMenuItem exitMenuItem, boughtStockMenuItem, addCatalogueItemMenuItem, showCatalogueMenuItem,
            soldStockMenuItem, currentStockMenuItem, buyStockMenuItem, sellStockMenuItem,
            suppliersMenuItem, customerMenuItem, allMenuItem;

    private JPanel mainPanel;

    private JTable productsTable, individualsTable, stockTable;

    public Display(int width, int height, String title, MotorFactorApplication motorFactorApplication) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.motorFactorApplication = motorFactorApplication;

        createFrame();
    }

    private void switchToProducts() {

        List<Product> products = new ArrayList<>(Catalogue.getProducts());

        String[] tableHeaders = {"ID", "Name", "Width", "Height", "Depth", "Weight", "Price"};

        Object[][] tableData = new Object[products.size()][tableHeaders.length];

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            tableData[i][0] = product.getId();
            tableData[i][1] = product.getName();
            tableData[i][2] = product.getX();
            tableData[i][3] = product.getY();
            tableData[i][4] = product.getZ();
            tableData[i][5] = product.getWeight();
            tableData[i][6] = product.getPrice();
        }


        productsTable = new JTable(tableData, tableHeaders);

        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(productsTable, BorderLayout.CENTER);
        mainPanel.repaint();

    }

    private void switchToIndividualsTable() {

        List<Individual> individuals = MotorFactorApplication.getIndividuals();

        String[] tableHeaders = {"ID", "Name", "Contact", "Group"};

        Object[][] tableData = new Object[individuals.size()][tableHeaders.length];

        for (int i = 0; i < individuals.size(); i++) {
            Individual individual = individuals.get(i);
            tableData[i][0] = individual.getId();
            tableData[i][1] = individual.getName();
            tableData[i][2] = individual.getContact();
            tableData[i][3] = individual.getGroup().toString();
        }

        individualsTable = new JTable(tableData, tableHeaders);

        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(individualsTable, BorderLayout.CENTER);

        mainPanel.repaint();

    }

    private void setUpMenuBar() {
        theMenuBar = new JMenuBar();

        fileMenu = new JMenu("File");

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(comp -> System.exit(0));
        fileMenu.add(exitMenuItem);

        theMenuBar.add(fileMenu);

        catalogueMenu = new JMenu("Catalogue");
        addCatalogueItemMenuItem = new JMenuItem("Add Item");
        catalogueMenu.add(addCatalogueItemMenuItem);

        showCatalogueMenuItem = new JMenuItem("Show Catalogue");
        showCatalogueMenuItem.addActionListener(c -> switchToProducts());
        catalogueMenu.add(showCatalogueMenuItem);

        theMenuBar.add(catalogueMenu);

        stockMenu = new JMenu("Stock");
        boughtStockMenuItem = new JMenuItem("Bought Stock");
        stockMenu.add(boughtStockMenuItem);
        soldStockMenuItem = new JMenuItem("Sold Stock");
        stockMenu.add(soldStockMenuItem);
        currentStockMenuItem = new JMenuItem("Current Stock");
        stockMenu.add(currentStockMenuItem);

        theMenuBar.add(stockMenu);

        actionMenu = new JMenu("Action");
        buyStockMenuItem = new JMenuItem("Buy Stock");
        actionMenu.add(buyStockMenuItem);
        sellStockMenuItem = new JMenuItem("Sell Stock");
        actionMenu.add(sellStockMenuItem);

        theMenuBar.add(actionMenu);

        individualsMenu = new JMenu("Individuals");
        allMenuItem = new JMenuItem("All Individuals");
        allMenuItem.addActionListener(comp -> switchToProducts());
        individualsMenu.add(allMenuItem);

        theMenuBar.add(individualsMenu);

    }

    private void createFrame() {
        this.setTitle(title);
        setSize(width, height);

        setUpMenuBar();

        setJMenuBar(theMenuBar);

        mainPanel = new JPanel();
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        switchToIndividualsTable();
        switchToProducts();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }
}
