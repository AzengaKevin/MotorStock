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

    private final int width;
    private final int height;

    private final String title;

    private JMenuBar theMenuBar;

    private JPanel mainPanel;

    private JTable stockTable;

    public Display(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        createFrame();
    }

    private void setUpMenuBar() {
        theMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(comp -> System.exit(0));
        fileMenu.add(exitMenuItem);

        theMenuBar.add(fileMenu);

        JMenu viewMenu = new JMenu("View");

        JMenuItem showCatalogueMenuItem = new JMenuItem("View Catalogue");
        showCatalogueMenuItem.addActionListener(c -> switchToProducts());
        viewMenu.add(showCatalogueMenuItem);

        JMenuItem showIndividuals = new JMenuItem("View Individuals");
        showIndividuals.addActionListener(comp -> switchToIndividualsTable());
        viewMenu.add(showIndividuals);

        JMenuItem showStockMenuItem = new JMenuItem("View Stock");
        showStockMenuItem.addActionListener(c -> switchToShowStock());
        viewMenu.add(showStockMenuItem);

        theMenuBar.add(viewMenu);

        JMenu actionMenu = new JMenu("Action");

        JMenuItem addCatalogueItemMenuItem = new JMenuItem("Add Catalogue Item");
        addCatalogueItemMenuItem.addActionListener(c -> new AddProductDialog(this, "Add Product"));
        actionMenu.add(addCatalogueItemMenuItem);

        JMenuItem addIndividualMenuItem = new JMenuItem("Add Individual");
        addIndividualMenuItem.addActionListener(c -> new AddIndividualDialog(this, "Add Individual"));
        actionMenu.add(addIndividualMenuItem);

        JMenuItem mutateStock = new JMenuItem("Change Stock");
        mutateStock.addActionListener(c -> switchToAddStock());
        actionMenu.add(mutateStock);

        theMenuBar.add(actionMenu);

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
                        .ifPresent(stockInstance -> new StockItemsDialog(Display.this, id + " stock items", stockInstance.getStockItems()));

                System.out.println("Stock ID: " + id);
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


        JTable productsTable = new JTable(tableData, tableHeaders);

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

        JTable individualsTable = new JTable(tableData, tableHeaders);

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
