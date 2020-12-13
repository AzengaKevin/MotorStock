package com.example.view.components;

import com.example.MotorFactorApplication;
import com.example.controllers.StockController;
import com.example.models.Individual;
import com.example.models.Stock;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddStockPanel extends JPanel {

    private JLabel idLabel, typeLabel, individualLabel;
    private JTextField idField;

    private JComboBox<String> typeComboBox, individualComboBox;

    private JButton submitButton;

    private StockController stockController;

    public AddStockPanel() {
        setLayout(new GridBagLayout());

        stockController = new StockController();

        initComponents();
    }

    private void initComponents() {
        idLabel = new JLabel("ID: ");
        addComp(idLabel, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        idField = new JTextField("", 15);
        addComp(idField, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        Dimension idFieldDimension = idField.getPreferredSize();

        typeLabel = new JLabel("Type: ");
        addComp(typeLabel, 1, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        String[] types = {"Incoming", "Outgoing"};
        typeComboBox = new JComboBox<>(types);
        typeComboBox.setPreferredSize(idFieldDimension);
        addComp(typeComboBox, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        individualLabel = new JLabel("Individual: ");
        addComp(individualLabel, 1, 3, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        String[] individuals = MotorFactorApplication.getIndividuals().parallelStream().map(Individual::getName)
                .collect(Collectors.toList()).toArray(new String[]{});
        individualComboBox = new JComboBox<>(individuals);
        individualComboBox.setPreferredSize(idFieldDimension);
        addComp(individualComboBox, 2, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        submitButton = new JButton("Submit");
        addComp(submitButton, 2, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        submitButton.addActionListener(c -> {
            String id = idField.getText();
            Stock.Type type = Stock.Type.fromString(Objects.requireNonNull(typeComboBox.getSelectedItem()).toString());
            String individualName = Objects.requireNonNull(individualComboBox.getSelectedItem()).toString();

            //Get the selected individual
            Optional<Individual> maybeIndividual = MotorFactorApplication.getIndividuals().stream()
                    .filter(individual -> individual.getName().equalsIgnoreCase(individualName.trim()))
                    .findFirst();

            if (maybeIndividual.isPresent()) {

                Stock stock = new Stock(id, LocalDateTime.now(), type, maybeIndividual.get());

                if (stockController.addStock(stock)) {

                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

                    if (topFrame != null) {

                        new AddStockItemsDialog(topFrame, "Add Stock Items", stock, stockController);
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "The individual not found", "Adding Stock", JOptionPane.ERROR_MESSAGE);
            }
        });
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

        add(comp, gridConstraints);

    }
}

