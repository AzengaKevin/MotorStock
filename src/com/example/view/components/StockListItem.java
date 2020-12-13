package com.example.view.components;

import com.example.models.Stock;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class StockListItem extends JLabel implements ListCellRenderer<Stock> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Stock> list, Stock value, int index, boolean isSelected, boolean cellHasFocus) {
        //S001,OutGoing,I002,2020-12-13T10:45:33.1667729
//        setText(String.format("%1$5s  %1$10s  %1$20s  %1$20s",
        setText(String.format("%6s  %10s  %20s  %20s",
                value.getId(),
                value.getStockType().toString(),
                value.getIndividual().getName(),
                value.getTransactionTime().format(DateTimeFormatter.ISO_TIME)));

        return this;
    }

}
