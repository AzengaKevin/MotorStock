package com.example.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Stock {

    public static final String FILENAME = "data/stocks.txt";

    public enum Type {
        Incoming, Outgoing;

        public static Type fromString(String type) {
            if (type.equalsIgnoreCase(Incoming.name())) return Incoming;
            if (type.equalsIgnoreCase(Outgoing.name())) return Outgoing;
            return null;
        }
    }

    private String id;
    private List<StockItem> stockItems;
    private LocalDateTime transactionTime;
    private Type stockType;
    private Individual individual;

    public Stock(String id, LocalDateTime transactionTime, Type stockType, Individual individual) {
        this.id = id;
        this.transactionTime = transactionTime;
        this.stockType = stockType;
        this.individual = individual;

        stockItems = new ArrayList<>();
    }

    public void addStockItem(StockItem stockItem) {
        stockItems.add(stockItem);
    }

    public void addAllStockItems(StockItem... stockItems) {
        this.stockItems.addAll(Arrays.asList(stockItems));
    }

    public List<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Type getStockType() {
        return stockType;
    }

    public void setStockType(Type stockType) {
        this.stockType = stockType;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", id, stockType.toString(), individual.getId(), transactionTime.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    public static Stock fromString(String stockString) {

        String[] parts = stockString.split(",");

        if (parts.length == 4) {

            try {

                String id = parts[0];
                Type type = Type.fromString(parts[1]);
                int size = Integer.parseInt(parts[2]);
                LocalDateTime transactionTime = LocalDateTime.from(LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_DATE_TIME));


                return new Stock(id, transactionTime, type, null);


            } catch (Exception exception) {
                System.err.println("StockItem Error: " + exception.getLocalizedMessage());

            }

        }

        return null;
    }
}
