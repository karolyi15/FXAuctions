package Server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.Socket;

public class FXAuctionsServerThread extends WriterServerThread{

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private FXAuctionsServer fxAuctionsServer;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public FXAuctionsServerThread(FXAuctionsServer FXAuctionsServer, Socket socket){

        super(FXAuctionsServer,socket);
        this.fxAuctionsServer = (FXAuctionsServer) this.server;
    }

    //Client Communication
    @Override
    protected void parseInputString(String inputString){

        JSONParser parser = new JSONParser();

        try{

            JSONObject inputJson = (JSONObject) parser.parse(inputString);
            long requestID = (long) inputJson.get("Request");

            if(requestID == -1){
                //Terminate Thread
                this.terminate();

            }else if(requestID == 0){
                //Create User
                JSONObject userData = (JSONObject) inputJson.get("User");
                String username = (String) userData.get("Username");

                JSONObject requestState = this.fxAuctionsServer.addUser(username,userData);
                this.write(requestState.toJSONString());

            }else if(requestID == 1){
                //Access User
                String username = (String) inputJson.get("Username");
                String password = (String) inputJson.get("Password");

                JSONObject userData = this.fxAuctionsServer.queryUser(username,password);
                this.write(userData.toJSONString());

            }else  if(requestID == 2){
                //Remove User
                String username = (String) inputJson.get("Username");
                JSONObject requestState =this.fxAuctionsServer.deleteUser(username);

                this.write(requestState.toJSONString());

            }else if (requestID  == 3){
                //Get active Auctions List
                this.write(this.fxAuctionsServer.getActiveAuctions().toJSONString());

            }else if(requestID ==4){
                //Create Auction Room
                JSONObject auctionData = (JSONObject) inputJson.get("AuctionRoom");
                String username = (String) inputJson.get("Username");

                this.write(this.fxAuctionsServer.createAuctionRoom(username, this, auctionData).toJSONString());

            }else if(requestID ==5){
                //Join Auction Room
                String auctionRoom = (String) inputJson.get("AuctionRoom");
                String username = (String) inputJson.get("Username");

                this.write(this.fxAuctionsServer.joinAuctionRoom(auctionRoom, username, this).toJSONString());

            }else if(requestID ==7){

                String username = (String) inputJson.get("Username");
                String auctionRoom = (String) inputJson.get("AuctionRoom");
                long price = (long) inputJson.get("Price");

                this.fxAuctionsServer.newOffer(auctionRoom,username,price);
            }

        }catch (ParseException e){

            e.printStackTrace();
        }
    }
}
