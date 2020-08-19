package Controllers.Views;

import Controllers.MainApp;
import Controllers.Models.Room;
import Controllers.Models.RoomType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AuctionsCreatorScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private MainApp mainApp;
    private BorderPane borderPane;

    @FXML
    private TextField productName_TextField;
    @FXML
    private TextField productInitialPrice_TextField;
    @FXML
    private TextField productFinalPrice_TextField;
    @FXML
    private TextField productDescription_TextField;
    @FXML
    private TextField auctionStart_TextField;
    @FXML
    private TextField auctionEnd_TextField;
    @FXML
    private Button cancel_Button;
    @FXML
    private Button confirm_Button;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor

    //Handlers
    @FXML
    private void onHandleConfirm(){

        if(this.validateFields()){

            JSONObject outputJson = new JSONObject();
            outputJson.put("Request", 4);
            outputJson.put("AuctionRoom", this.getFields());
            outputJson.put("Username", this.mainApp.getActiveUser().getUsername());

            this.mainApp.getClient().write(outputJson.toJSONString());

            String inputString = this.mainApp.getClient().read();
            this.parseRequestState(inputString);

        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please enter all the fields");

            alert.showAndWait();
        }
    }

    private void parseRequestState(String inputString){

        JSONParser parser = new JSONParser();

        try{

            JSONObject inputJson = (JSONObject) parser.parse(inputString);
            boolean requestState = (boolean) inputJson.get("RequestState");

            if(requestState){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Auction Room Created");
                alert.setContentText("You create a new auction room");

                alert.showAndWait();

                this.mainApp.showAuctionRoom(RoomType.OWNER, new Room(this.productName_TextField.getText()));

            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error Creating Auction Room");
                alert.setContentText("Try with other product name");

                alert.showAndWait();
            }

        }catch (ParseException e){

            e.printStackTrace();
        }
    }

    private boolean validateFields(){

        if(this.productName_TextField.getText().equals("") || this.productDescription_TextField.getText().equals("")){

            return false;

        }else if( this.productInitialPrice_TextField.getText().equals("") || this.productFinalPrice_TextField.getText().equals("")){

            return false;

        }else if(this.auctionStart_TextField.getText().equals("") || this.auctionEnd_TextField.getText().equals("")){

            return false;
        }

        return true;
    }

    private JSONObject getFields(){

        JSONObject auctionData = new JSONObject();
        JSONObject productData = new JSONObject();

        productData.put("Name", this.productName_TextField.getText());
        productData.put("Description", this.productDescription_TextField.getText());
        productData.put("InitialPrice", Long.parseLong(this.productInitialPrice_TextField.getText()));
        productData.put("FinalPrice", Long.parseLong(this.productFinalPrice_TextField.getText()));

        auctionData.put("Product", productData);
        auctionData.put("Start", this.auctionStart_TextField.getText());
        auctionData.put("End", this.auctionEnd_TextField.getText());

        return auctionData;
    }

    @FXML
    private void onHandleCancel(){

        this.mainApp.showAuctionsManager(this.borderPane);
    }

    //Setters & Getters
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}
