package com.example.models;

public class StockItem {

    public static final String FILENAME = "stock_items.txt";

    private Product product;
    private int size;
    private Stock stock;

    public StockItem(Product product, int size, Stock stock) {
        this.product = product;
        this.size = size;
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Stock getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return String.format("%s,%d", product.getId(), size);
    }
}
