package Server;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class AuctionRoom {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//


    private AuctionProduct product;
    private String start;
    private String end;
    private AuctionStatus status;
    private HashMap<String, FXAuctionsServerThread> participants;
    private FXAuctionsServerThread owner;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public AuctionRoom(){


        this.product = new AuctionProduct();
        this.start = "";
        this.end = "";
        this.status = AuctionStatus.OPEN;
        this.participants = new HashMap<>();
    }

    public AuctionRoom(String username, FXAuctionsServerThread thread,JSONObject auctionData){

        this.product = new AuctionProduct((JSONObject) auctionData.get("Product"));
        this.start =( String) auctionData.get("Start");
        this.end = (String) auctionData.get("End");
        this.status = AuctionStatus.OPEN;
        this.participants = new HashMap<>();

        this.participants.put(username, thread);
        this.owner = thread;
    }

    //Join Auction Room
    public boolean join(String username, FXAuctionsServerThread thread){

        try {

            this.participants.put(username, thread);

            return true;

        }catch (Exception e){

            return false;
        }
    }

    public void newOffer(String username, long price){

    }

    //Setters & Getters
    public AuctionProduct getProduct() {
        return product;
    }

    public void setProduct(AuctionProduct product) {
        this.product = product;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }

    //Communication
    public JSONObject toJson(){

        JSONObject outputJson = new JSONObject();


        outputJson.put("Product", this.product.toJson());
        outputJson.put("Start", this.start);
        outputJson.put("End", this.end);
        outputJson.put("Status", this.status.toString());

        return outputJson;
    }
}
