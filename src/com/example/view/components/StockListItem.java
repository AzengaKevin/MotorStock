package com.example.view.components;

import com.example.models.Stock;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class StockListItem extends JLabel implements ListCellRenderer<Stock> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Stock> list, Stock value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(String.format("%6s  %10s  %30s  %20s",
                value.getId(),
                value.getStockType().toString(),
                value.getIndividual().getName(),
                value.getTransactionTime().format(DateTimeFormatter.ISO_LOCAL_DATE)));

        return this;
    }

}
