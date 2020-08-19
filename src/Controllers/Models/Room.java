package Controllers.Models;

import Server.AuctionStatus;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.json.simple.JSONObject;

public class Room {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private StringProperty productName;
    private StringProperty productDescription;
    private StringProperty status;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public Room(String name){
        this.productName = new SimpleStringProperty(name);
        this.productDescription = new SimpleStringProperty("");
        this.status = new SimpleStringProperty("OPEN");
    }

    public Room(JSONObject auctionRoomData){

        JSONObject productData = (JSONObject) auctionRoomData.get("Product");

        this.productName = new SimpleStringProperty((String) productData.get("Name"));
        this.productDescription = new SimpleStringProperty((String) productData.get("Description"));
        this.status = new SimpleStringProperty((String) auctionRoomData.get("Status"));
    }

    //Setters & Getters

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public StringProperty productDescriptionProperty() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription.set(productDescription);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
