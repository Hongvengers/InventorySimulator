package com.example.inventorysimulator;

public class Items {
    public String itemCode;
    public String name;
    public String description;

    public Items(){}

    public Items(String code,String name, String description){
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

//    public int getCode(int code){
//        if (Integer.toString(code) == itemCode)
//            return Integer.parseInt(itemCode);
//        return 0;
//    }

//    private int iconDrawble;
//    private String contentStr, titleStr;

//    public void setTitle(String title){
//        titleStr = title;
//    }
//
//    public void setIcon(int icon){
//        iconDrawble = icon;
//    }
//
//    public void setContent(String content){
//        contentStr = content;
//    }
//
//
//    public int getIcon(){
//        return this.iconDrawble;
//    }
//
//    public String getTitle(){
//        return this.titleStr;
//    }
//
//    public String getContent(){
//        return this.contentStr;
//    }
}
