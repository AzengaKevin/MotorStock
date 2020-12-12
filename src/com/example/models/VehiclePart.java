package com.example.models;

public class VehiclePart extends Product {

    private String vehicleModel;

    public VehiclePart(String id, String name, int width, int height, int depth, int weight, String description, double price, String vehicleModel) {
        super(id, name, width, height, depth, weight, description, price);
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    @Override
    public String toString() {
        return super.toString().concat("," + vehicleModel);
    }

    public static Product fromString(String vehiclePartString) {

        String[] parts = vehiclePartString.split(",");

        if (parts.length == 9) {

            try {

                String id = parts[0];
                String name = parts[1];
                int width = Integer.parseInt(parts[2]);
                int height = Integer.parseInt(parts[3]);
                int depth = Integer.parseInt(parts[4]);
                int weight = Integer.parseInt(parts[5]);
                String description = parts[6];
                double price = Double.parseDouble(parts[7]);
                String vehicleModel = parts[8];

                return new VehiclePart(id, name, width, height, depth, weight, description, price, vehicleModel);


            } catch (NumberFormatException exception) {
                System.err.println("VehiclePart Error: " + exception.getLocalizedMessage());

            }

        }

        return null;
    }
}
