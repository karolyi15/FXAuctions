package Server;

import org.json.simple.JSONObject;

public class AuctionProduct {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private String name;
    private String description;
    private long initialPrice;
    private long finalPrice;
    private long actualPrice;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public AuctionProduct(){

        this.name  = "";
        this.description = "";
        this.initialPrice = 0;
        this.finalPrice = 0;
        this.actualPrice = this.initialPrice;
    }

    public AuctionProduct(String name, String description, long initialPrice, long finalPrice){

        this.name  = name;
        this.description = description;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
        this.actualPrice = this.initialPrice;

    }

    public AuctionProduct(JSONObject productData){

        this.name  = (String) productData.get("Name");
        this.description = (String) productData.get("Description");
        this.initialPrice = (long) productData.get("InitialPrice");
        this.finalPrice = (long) productData.get("FinalPrice");
        this.actualPrice = this.initialPrice;

    }

    //Setters & Getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(long initialPrice) {
        this.initialPrice = initialPrice;
    }

    public long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(long finalPrice) {
        this.finalPrice = finalPrice;
    }

    //Communication
    public JSONObject toJson(){

        JSONObject outputJson = new JSONObject();

        outputJson.put("Name", this.name);
        outputJson.put("Description", this.description);
        outputJson.put("InitialPrice", this.initialPrice);
        outputJson.put("FinalPrice", this.finalPrice);
        outputJson.put("ActualPrice", this.actualPrice);

        return outputJson;
    }
}
