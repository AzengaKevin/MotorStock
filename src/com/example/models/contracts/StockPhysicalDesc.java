package com.example.models.contracts;

public interface StockPhysicalDesc {

    //The width of the packaged item in mm
    int getX();
    //The depth of the packaged item in mm
    int getZ();
    //The height of the packaged item in mm
    int getY();
    //The weight of the packaged item in grams
    int getWeight();

}
