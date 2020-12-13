package com.example.view;

import com.example.MotorFactorApplication;
import com.example.models.Catalogue;
import com.example.models.Individual;
import com.example.models.Product;
import com.example.models.Stock;
import com.example.view.components.AddIndividualDialog;
import com.example.view.components.AddProductDialog;
import com.example.view.components.AddStockPanel;
import com.example.view.components.StockItemsDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Display extends JFrame {

    private int width, height;

    private String title;

    private JMenuBar theMenuBar;
    private JMenu fileMenu, stockMenu, actionMenu, catalogueMenu, individualsMenu;
    private JMenuItem exitMenuItem, boughtStockMenuItem, addCatalogueItemMenuItem, showCatalogueMenuItem,
            soldStockMenuItem, currentStockMenuItem, buyStockMenuItem, sellStockMenuItem,
            addMenuItem, allMenuItem;

    private JPanel mainPanel;

    private JTable productsTable, individualsTable, stockTable;

    public Display(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        createFrame();
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
        addCatalogueItemMenuItem.addActionListener(c -> {
            new AddProductDialog(this, "Add Product");
        });
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
        buyStockMenuItem.addActionListener(c -> switchToAddStock());
        actionMenu.add(buyStockMenuItem);
        sellStockMenuItem = new JMenuItem("Sell Stock");
        actionMenu.add(sellStockMenuItem);

        theMenuBar.add(actionMenu);

        individualsMenu = new JMenu("Individuals");
        allMenuItem = new JMenuItem("All Individuals");
        allMenuItem.addActionListener(comp -> switchToIndividualsTable());
        individualsMenu.add(allMenuItem);

        addMenuItem = new JMenuItem("Add Individual");
        addMenuItem.addActionListener(c -> new AddIndividualDialog(this, "Add Individual"));
        individualsMenu.add(addMenuItem);

        theMenuBar.add(individualsMenu);

    }

    private void createFrame() {
        this.setTitle(title);
        setSize(width, height);

        setUpMenuBar();

        setJMenuBar(theMenuBar);

        mainPanel = new JPanel();
        setLayout(new BorderLayout());
        switchToShowStock();
        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void switchToShowStock() {

        List<Stock> stock = MotorFactorApplication.getStock();

        String[] tableHeaders = {"ID", "Individual", "Stock Type", "Date", "Item Count"};

        Object[][] tableData = new Object[stock.size()][tableHeaders.length];

        for (int i = 0; i < stock.size(); i++) {
            Stock stockItem = stock.get(i);
            tableData[i][0] = stockItem.getId();
            tableData[i][1] = stockItem.getIndividual().getName();
            tableData[i][2] = stockItem.getStockType().toString();
            tableData[i][3] = stockItem.getTransactionTime().format(DateTimeFormatter.BASIC_ISO_DATE);
            tableData[i][4] = stockItem.getStockItems().size();
        }

        stockTable = new JTable(tableData, tableHeaders);

        JPopupMenu popupMenu = new JPopupMenu("Table Actions");

        popupMenu.add(new AbstractAction("Stock Items") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = stockTable.getValueAt(stockTable.getSelectedRow(), 0).toString();

                stock.parallelStream()
                        .filter(stock -> stock.getId().equalsIgnoreCase(id)).findFirst()
                        .ifPresent(stockInstance -> {

                            new StockItemsDialog(Display.this, id + " stock items", stockInstance.getStockItems());
                        });

                System.out.println("Stock ID: " + id);
                //@TODO Launch a new dialog
            }
        });

        stockTable.setComponentPopupMenu(popupMenu);

        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new JScrollPane(stockTable), BorderLayout.CENTER);
        mainPanel.updateUI();
    }


    private void switchToAddStock() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new AddStockPanel(), BorderLayout.CENTER);
        mainPanel.updateUI();
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
        mainPanel.add(new JScrollPane(productsTable), BorderLayout.CENTER);
        mainPanel.updateUI();

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
        mainPanel.add(new JScrollPane(individualsTable), BorderLayout.CENTER);

        mainPanel.updateUI();

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
