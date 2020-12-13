package com.example.view.components;

import com.example.MotorFactorApplication;
import com.example.controllers.StockController;
import com.example.models.Catalogue;
import com.example.models.Product;
import com.example.models.Stock;
import com.example.models.StockItem;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddStockItemsDialog extends JDialog {

    private JLabel sizeLabel, productLabel;
    private JPanel mainPanel;
    private JTextField sizeField;
    private JComboBox<String> productComboBox;
    private JButton submitButton, cancelButton;
    private JFrame frame;
    private Stock stock;
    private StockController stockController;


    public AddStockItemsDialog(JFrame frame, String title, Stock stock, StockController stockController) {
        super(frame, title, true);
        this.frame = frame;
        this.stock = stock;
        this.stockController = stockController;

        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        productLabel = new JLabel("Product: ");
        addComp(productLabel, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        sizeLabel = new JLabel("Size: ");
        addComp(sizeLabel, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        sizeField = new JTextField("", 15);
        addComp(sizeField, 1, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        String[] products = Catalogue.getProducts().stream().map(Product::getName)
                .collect(Collectors.toList()).toArray(new String[]{});
        productComboBox = new JComboBox<>(products);
        productComboBox.setPreferredSize(sizeField.getPreferredSize());
        addComp(productComboBox, 1, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(c -> dispose());
        addComp(cancelButton, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        submitButton = new JButton("Add Product");
        submitButton.addActionListener(c -> {

            try {

                String productName = Objects.requireNonNull(productComboBox.getSelectedItem()).toString();
                int size = Integer.parseInt(sizeField.getText().trim());

                Optional<Product> maybeProduct = Catalogue.getProducts().stream()
                        .filter(product -> product.getName().equalsIgnoreCase(productName))
                        .findFirst();

                if (maybeProduct.isPresent()) {
                    StockItem stockItem = new StockItem(maybeProduct.get(), size, stock);

                    if (stockController.addStockItem(stockItem)) {
                        JOptionPane.showMessageDialog(this, "Stock Item Added Successfully", "Adding Stock Items", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Stock Item Addition Failed", "Adding Stock Items", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (NumberFormatException exception) {
                System.err.println("Adding Stock Item Error: " + exception.getLocalizedMessage());
            }
        });

        addComp(submitButton, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        add(mainPanel);

        setSize(300, 200);
        setLocationRelativeTo(frame);

        setVisible(true);

    }


    private void addComp(JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch) {


        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        mainPanel.add(comp, gridConstraints);

    }
}
