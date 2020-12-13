package com.example.controllers;

import com.example.models.Stock;
import com.example.models.StockItem;
import com.example.utils.FileHandler;

import java.io.IOException;

public class StockController {

    public boolean addStock(Stock stock) {
        //Add to the file
        try {
            FileHandler.appendToFile(Stock.FILENAME, stock.toString());

            //@TODO Load stock
            return true;

        } catch (IOException exception) {
            System.err.println("Error adding stock: " + exception.getLocalizedMessage());
        }

        return false;
    }

    public boolean addStockItem(StockItem stockItem) {
        //Add to the file
        try {
            FileHandler.appendToFile(StockItem.FILENAME, stockItem.toString());

            //@TODO Load stock
            return true;

        } catch (IOException exception) {
            System.err.println("Error adding stock item: " + exception.getLocalizedMessage());
        }

        return false;
    }
}
