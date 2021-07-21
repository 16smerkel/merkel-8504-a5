package ucf.assignments;

public class InventoryItem {
    public String thePrice;
    public String theSerial;
    public String theName;

    public InventoryItem(){
        this.thePrice = "$0.00";
        this.theSerial = "XXXXXXXXX";
        this.theName = "";
    }

    public InventoryItem(String thePrice, String theSerial, String theName){
        this.thePrice = thePrice;
        this.theSerial = theSerial;
        this.theName = theName;
    }

    public String getThePrice(){
        return thePrice;
    }

    public void setThePrice(String thePrice){
        this.thePrice = thePrice;
    }

    public String getTheSerial(){
        return theSerial;
    }

    public void setTheSerial(String theSerial){
        this.theSerial = theSerial;
    }
    public String getTheName(){
        return theName;
    }

    public void setTheName(String theName){
        this.theName = theName;
    }

    @Override
    public String toString(){
        return ("Price: " + thePrice + " Serial Number: " + theSerial + " Name: " + theName);
    }

}