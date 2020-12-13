package com.example.view.components;

import com.example.models.Catalogue;
import com.example.models.Product;
import com.example.models.StockItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StockItemsDialog extends JDialog {

    private JFrame frame;
    private List<StockItem> stockItems;

    public StockItemsDialog(JFrame frame, String title, List<StockItem> stockItems) {

        super(frame, title, false);
        this.frame = frame;
        this.stockItems = stockItems;

        initComponents();
    }

    private void initComponents() {

        String[] tableHeaders = {"Product", "Size", "Total Cost"};

        Object[][] tableData = new Object[stockItems.size()][tableHeaders.length];

        for (int i = 0; i < stockItems.size(); i++) {
            StockItem stockItem = stockItems.get(i);
            tableData[i][0] = stockItem.getProduct().getName();
            tableData[i][1] = stockItem.getSize();
            tableData[i][2] = stockItem.getProduct().getPrice() * stockItem.getSize();
        }


        JTable stockItemsTable = new JTable(tableData, tableHeaders);

        setLayout(new BorderLayout());
        add(new JScrollPane(stockItemsTable), BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);

        setVisible(true);
    }
}
