package com.example.models;

import java.util.Optional;

public class StockItem {

    public static final String FILENAME = "data/stock_items.txt";

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
        return String.format("%s,%s,%d,", stock.getId(), product.getId(), size);
    }

    public static StockItem fromString(String stockItemsString, Stock stock) {

        String[] parts = stockItemsString.split(",");

        if (parts.length == 3) {

            try {

                String productId = parts[1];
                int size = Integer.parseInt(parts[2]);

                Optional<Product> maybeProduct = Catalogue.getProducts().parallelStream().filter(product -> product.id.equals(productId)).findFirst();

                if (maybeProduct.isPresent()) {
                    return new StockItem(maybeProduct.get(), size, stock);
                }


            } catch (Exception exception) {
                System.err.println("StockItem Error: " + exception.getLocalizedMessage());

            }

        }

        return null;
    }
}
