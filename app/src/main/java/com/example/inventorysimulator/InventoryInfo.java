package com.example.inventorysimulator;

public class InventoryInfo {
    public String itemCode;
    public String name;
    public String description;

    public InventoryInfo(){}

    public InventoryInfo(String code, String name, String description){
        this.itemCode = code;
        this.name = name;
        this.description = description;
    }

    public String getItemCode(){
        return itemCode;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
}
