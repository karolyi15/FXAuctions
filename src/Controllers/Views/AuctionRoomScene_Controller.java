package Controllers.Views;

import Controllers.MainApp;
import Controllers.Models.Room;
import Controllers.Models.RoomType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AuctionRoomScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private MainApp mainApp;
    private RoomType type;
    private Thread updateListener;
    private Room auctionRoom;

    @FXML
    private TableView offers_TableView;
    @FXML
    private TableColumn offerUsername_TableColumn;
    @FXML
    private Button push_Button;
    @FXML
    private Button accept_Button;
    @FXML
    private Button close_Button;
    @FXML
    private Button cancel_Button;
    @FXML
    private TextField pushValue_TextField;
    @FXML
    private Label auctionName_Label;
    @FXML
    private Label Start_Label;
    @FXML
    private Label end_Label;
    @FXML
    private Label basePrice_Label;
    @FXML
    private Label topPriceLabel;
    @FXML
    private Label actualPrice_Label;
    @FXML
    private Label productDescription;




    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    public AuctionRoomScene_Controller() {
    }

    @FXML
    private void initialize(){


    }

    private void initUpdateListener(){

        this.updateListener = new Thread(){

          public void run(){

              while(auctionRoom.getStatus().equals("OPEN")){

                  String inputString = mainApp.getClient().read();
                  parseInputString(inputString);

              }

              mainApp.showMenuScene();

          }

        };
        this.updateListener.start();
    }

    private void parseInputString(String inputString){

        JSONParser parser = new JSONParser();

        System.out.println(inputString);

        try{

            JSONObject inputJson = (JSONObject) parser.parse(inputString);
            long requestID = (long) inputJson.get("Request");

            if(requestID == 6){

                JSONObject auctionRoom = (JSONObject) inputJson.get("AuctionRoom");
                JSONObject product = (JSONObject) auctionRoom.get("Product");
                this.auctionRoom = new Room(auctionRoom);
                this.showAuctionInformation(product);

            }

        }catch (ParseException e){

            e.printStackTrace();
        }
    }

    private void showAuctionInformation(JSONObject product) {

        this.auctionName_Label.setText((String) product.get("Name"));
        this.actualPrice_Label.setText(String.valueOf(product.get("ActualPrice")));
        this.basePrice_Label.setText(String.valueOf(product.get("InitialPrice")));
        this.topPriceLabel.setText(String.valueOf(product.get("FinalPrice")));
        this.productDescription.setText((String) product.get("Description"));
        this.end_Label.setText("");
        this.Start_Label.setText("");
    }

    private void validateRoomType(){

        if(this.type == RoomType.CLIENT){

            this.offers_TableView.setVisible(false);
            this.offers_TableView.setDisable(true);

            this.accept_Button.setVisible(false);
            this.accept_Button.setDisable(true);

            this.accept_Button.setVisible(false);
            this.accept_Button.setDisable(true);

            this.cancel_Button.setVisible(false);
            this.cancel_Button.setDisable(true);

        }else if(this.type ==RoomType.OWNER){

            this.push_Button.setVisible(false);
            this.push_Button.setDisable(true);

            this.pushValue_TextField.setDisable(true);
            this.pushValue_TextField.setVisible(false);
        }
    }

    //Handlers

    @FXML
    private void onHandlePush(){

        long price = Long.parseLong(this.pushValue_TextField.getText());

        JSONObject outputJson = new JSONObject();
        outputJson.put("Request", 7);
        outputJson.put("AuctionRoom", this.auctionRoom.getProductName());
        outputJson.put("Username", this.mainApp.getActiveUser().getUsername());
        outputJson.put("Price", price);

        this.mainApp.getClient().write(outputJson.toJSONString());

    }

    //Setters & Getters
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    public void setType(RoomType type) {
        this.type = type;
        this.validateRoomType();
    }

    public void setAuctionRoom(Room auctionRoom) {
        this.auctionRoom = auctionRoom;
        this.initUpdateListener();
    }
}
