package com.example.view;

import javax.swing.*;

public class Display extends JFrame {

    private int width, height;

    private String title;


    private JMenuBar theMenuBar;
    private JMenu fileMenu, stockMenu, actionMenu, catalogueMenu;
    private JMenuItem exitMenuItem, boughtStockMenuItem, addCatalogueItemMenuItem, soldStockMenuItem, currentStockMenuItem, buyStockMenuItem, sellStockMenuItem;

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
        catalogueMenu.add(addCatalogueItemMenuItem);

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


    }

    private void createFrame() {
        this.setTitle(title);
        setSize(width, height);

        setUpMenuBar();

        setJMenuBar(theMenuBar);

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
