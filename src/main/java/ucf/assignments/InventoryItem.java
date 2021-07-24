/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Sean Merkel
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

public class InventoryItem {
    public SimpleStringProperty thePrice;
    public SimpleStringProperty theSerial;
    public SimpleStringProperty theName;

    public InventoryItem(String thePrice, String theSerial, String theName){
        this.thePrice = new SimpleStringProperty(thePrice);
        this.theSerial = new SimpleStringProperty(theSerial);
        this.theName = new SimpleStringProperty(theName);
    }

    public InventoryItem(){
        this.thePrice = new SimpleStringProperty("");
        this.theSerial = new SimpleStringProperty("");
        this.theName = new SimpleStringProperty("");
    }

    public String getThePrice(){
         return thePrice.get();
    }
    public SimpleStringProperty priceProperty() {
        return thePrice;
    }
    public void setThePrice(String thePrice){
        this.thePrice.set(thePrice);
    }

    public String getTheSerial(){
        return theSerial.get();
    }
    public SimpleStringProperty serialProperty() {
        return theSerial;
    }
    public void setTheSerial(String theSerial){
        this.theSerial.set(theSerial);
    }
    public String getTheName(){
        return theName.get();
    }
    public SimpleStringProperty nameProperty() {
        return theName;
    }
    public void setTheName(String theName){
        this.theName.set(theName);
    }

    @Override
    public String toString(){
        return ("Price: " + getThePrice() + " Serial Number: " + getTheSerial() + " Name: " + getTheName());
    }

    public String toTab(){
        return (getThePrice() + "\t" + getTheSerial() + "\t" + getTheName());
    }

}