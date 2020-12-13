package com.example.view.components;

import com.example.controllers.IndividualController;
import com.example.models.Individual;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class AddIndividualDialog extends JDialog {

    private JPanel mainPanel;
    private JTextField idField, nameField, contactField;
    private JComboBox<String> groupComboBox;

    private JLabel idLabel, nameLabel, contactLabel, groupLabel;
    private JButton addIndividualButton;
    private JFrame frame;

    private IndividualController individualController;

    public AddIndividualDialog(JFrame frame, String title) {
        super(frame, title, true);
        this.frame = frame;

        individualController = new IndividualController();

        initComponents();
    }

    private void initComponents() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        idLabel = new JLabel("ID: ");
        addComp(idLabel, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        idField = new JTextField("", 15);
        addComp(idField, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        nameLabel = new JLabel("Name: ");
        addComp(nameLabel, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        nameField = new JTextField("", 15);
        addComp(nameField, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        contactLabel = new JLabel("Contact: ");
        addComp(contactLabel, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        contactField = new JTextField("", 15);
        addComp(contactField, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        groupLabel = new JLabel("Group: ");
        addComp(groupLabel, 0, 3, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        String[] groups = {"Supplier", "Customer"};
        groupComboBox = new JComboBox<>(groups);
        addComp(groupComboBox, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        addIndividualButton = new JButton("Add Individual");
        addIndividualButton.addActionListener(c -> {

            String id = idField.getText();
            String name = nameField.getText();
            String contact = contactField.getText();
            Individual.Group group = Individual.Group.fromString(Objects.requireNonNull(groupComboBox.getSelectedItem()).toString().trim());

            individualController.addIndividual(new Individual(id, name, contact, group));

            dispose();

        });
        addComp(addIndividualButton, 1, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        add(mainPanel);

        pack();
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
