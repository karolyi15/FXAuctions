package Server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class FXAuctionsServer extends MultiThreadedServer{

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //Data Base
    private DataBase dataBase;

    //Auctions System
    private HashMap<String, AuctionRoom> auctionsRegister;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public FXAuctionsServer(){

        this.dataBase = DataBase.getInstance();

        this.auctionsRegister = new HashMap<String, AuctionRoom>();
    }

   @Override
   protected void createServerThread(Socket socket){

        try {

            new FXAuctionsServerThread(this, socket).start();

        }catch (Exception e){

            e.printStackTrace();
        }
   }

    //Data Base Access Management
    public synchronized JSONObject queryUser(String username, String password){

        JSONObject userData = this.dataBase.query("Users", username);
        JSONObject outputJson = new JSONObject();

        if(userData != null){

            if(userData.get("Password").equals(password)){

                outputJson.put("RequestState",true);
                outputJson.put("User",userData);

                return outputJson;
            }
        }

        outputJson.put("RequestState",false);
        return outputJson;
    }

    public synchronized JSONObject addUser(String username, JSONObject userData){

        boolean  requestState = this.dataBase.add("Users",username,userData);

        JSONObject outputJson = new JSONObject();

        outputJson.put("RequestState",requestState);

        return outputJson;
    }

    public synchronized  JSONObject deleteUser(String username){

        boolean requestState = this.dataBase.delete("Users",username);

        JSONObject outputJson = new JSONObject();

        outputJson.put("RequestState", requestState);

        return outputJson;
    }

    //Auctions Rooms Management
    public synchronized JSONObject getActiveAuctions(){

        JSONObject outputJson = new JSONObject();
        JSONArray activeAuctions = new JSONArray();

        for(String auction: this.auctionsRegister.keySet()){

            activeAuctions.add(this.auctionsRegister.get(auction).toJson());
        }

        outputJson.put("ActiveAuctions", activeAuctions);

        return outputJson;
    }

    public synchronized JSONObject createAuctionRoom(String username,FXAuctionsServerThread thread, JSONObject auctionData){

        JSONObject productData = (JSONObject) auctionData.get("Product");
        String auctionID = (String) productData.get("Name");
        JSONObject outputJson= new JSONObject();

        if(this.auctionsRegister.containsKey(auctionID)){

            outputJson.put("RequestState", false);

        }else{

            this.auctionsRegister.put(auctionID, new AuctionRoom(username, thread, auctionData));
            outputJson.put("RequestState", true);
        }

        return outputJson;
    }

    public synchronized JSONObject joinAuctionRoom(String auctionRoom, String username, FXAuctionsServerThread thread){

        JSONObject outputJson = new JSONObject();

        try {
            boolean requestState = this.auctionsRegister.get(auctionRoom).join(username, thread);

            outputJson.put("RequestState", requestState);

        }catch (Exception e){

            outputJson.put("RequestState", false);
        }

        return outputJson;

    }

    public synchronized JSONObject getAuctionRoom(String auctionRoom){

        JSONObject outputJson = new JSONObject();
        try{

            outputJson.put("AuctionRoom", this.auctionsRegister.get(auctionRoom).toJson());
            outputJson.put("RequestState", true);

        }catch (Exception e){
           outputJson.put("RequestState", false);
        }


        return outputJson;
    }

    public synchronized JSONObject newOffer(String auctionRoom, String username, long price){

        JSONObject outputJson = new JSONObject();

        try{

            this.auctionsRegister.get(auctionRoom).newOffer(username,price);
            outputJson.put("RequestState", true);

        }catch (Exception e){
            outputJson.put("RequestState", false);
        }

        return outputJson;
    }


    //Main
    public static void main(String[] args){

        iServer server = new FXAuctionsServer();
        server.start();
    }
}
