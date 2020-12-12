package com.example.models;

public class VehiclePart extends Product {

    private String vehicleModel;

    public VehiclePart(int width, int height, int depth, int weight, String id, String name, String description, String vehicleType, String model, double price, String vehicleModel) {
        super(width, height, depth, weight, id, name, description, vehicleType, model, price);
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
}
