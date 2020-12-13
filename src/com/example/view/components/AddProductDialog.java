package com.example.view.components;

import com.example.controllers.ProductsController;
import com.example.models.Product;

import javax.swing.*;
import java.awt.*;

public class AddProductDialog extends JDialog {

    private JPanel mainPanel;
    private JLabel nameLabel, widthLabel, heightLabel, depthLabel, weightLabel, descriptionLabel, priceLabel;

    private JTextField nameField, widthField, heightField, depthField, weightField, priceField;

    private JTextArea descriptionField;

    private JButton cancelButton, submitButton;

    private ProductsController productsController;

    private JFrame frame;

    public AddProductDialog(JFrame frame, String title) {

        super(frame, title, true);

        this.frame = frame;

        productsController = new ProductsController();

        initComponents();
    }

    private void initComponents() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        nameLabel = new JLabel("Name");
        addComp(nameLabel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        nameField = new JTextField("", 15);
        addComp(nameField, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        priceLabel = new JLabel("Price");
        addComp(priceLabel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        priceField = new JTextField("", 15);
        addComp(priceField, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        widthLabel = new JLabel("Width");
        addComp(widthLabel, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        widthField = new JTextField("", 15);
        addComp(widthField, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        heightLabel = new JLabel("Height");
        addComp(heightLabel, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        heightField = new JTextField("", 15);
        addComp(heightField, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        depthLabel = new JLabel("Depth");
        addComp(depthLabel, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        depthField = new JTextField("", 15);
        addComp(depthField, 1, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        weightLabel = new JLabel("Weight");
        addComp(weightLabel, 0, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        weightField = new JTextField("", 15);
        addComp(weightField, 1, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        descriptionLabel = new JLabel("Description");
        addComp(descriptionLabel, 0, 6, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        descriptionField = new JTextArea(3, 20);
        addComp(descriptionField, 0, 7, 2, 3, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(c -> dispose());
        addComp(cancelButton, 0, 11, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(c -> {

            try {
                //nameField, widthField, heightField, depthField, weightField, priceField; description

                String name = nameField.getText();
                String description = descriptionField.getText();
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                int depth = Integer.parseInt(depthField.getText());
                int weight = Integer.parseInt(weightField.getText());
                double price = Double.parseDouble(priceField.getText());

                Product product = new Product("P" + (++Product.productCount), name, width, height, depth, weight, description, price);

                if (productsController.addProduct(product)) {
                    JOptionPane.showMessageDialog(this, "Product added to the catalogue", "Adding Product", JOptionPane.INFORMATION_MESSAGE);

                    dispose();
                }

            } catch (NumberFormatException e) {

                System.err.println("Error getting field data, confirm correct data in correct fields");
            }
        });
        addComp(submitButton, 1, 11, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        add(mainPanel);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setSize(new Dimension(360, 480));

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
