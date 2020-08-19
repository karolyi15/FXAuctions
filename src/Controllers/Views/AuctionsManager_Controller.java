package Controllers.Views;

import Controllers.MainApp;
import Controllers.Models.Room;
import Controllers.Models.RoomType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class AuctionsManager_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private MainApp mainApp;
    private BorderPane borderPane;
    private Thread updater;
    private boolean updating;
    private ObservableList<Room> activeRooms;

    @FXML
    private TableView<Room> activeAuctions_TableView;
    @FXML
    private TableColumn<Room,String> auctionName_TableColumn;
    @FXML
    private TableColumn<Room,String> productName_TableColumn;
    @FXML
    private TableColumn<Room,String> auctionState_TableColumn;
    @FXML
    private Button create_Button;
    @FXML
    private Button join_Button;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public AuctionsManager_Controller(){

        this.activeRooms = FXCollections.observableArrayList();
        this.updating = false;
    }

    @FXML
    private void initialize(){

        this.auctionName_TableColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        this.productName_TableColumn.setCellValueFactory(cellData -> cellData.getValue().productDescriptionProperty());
        this.auctionState_TableColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
    }

    private void initUpdater(){

        this.updating = true;

        this.updater = new Thread(){

            public void run(){

                JSONObject outputJson = new JSONObject();
                outputJson.put("Request", 3);

                while(updating){

                    try {

                        mainApp.getClient().write(outputJson.toJSONString());
                        String inputString = mainApp.getClient().read();
                        System.out.println(inputString);
                        parseUpdater(inputString);

                        updater.sleep(3000);

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        };

        this.updater.start();
    }

    private void parseUpdater(String inputString){

        JSONParser parser = new JSONParser();

        try{

            JSONObject inputJson = (JSONObject) parser.parse(inputString);
            JSONArray auctionRooms = (JSONArray) inputJson.get("ActiveAuctions");

            this.activeRooms.clear();
            //ArrayList<Room> auctionsList = new ArrayList<>();

            for(int room=0;room<auctionRooms.size();room++){

                JSONObject tempRoom = (JSONObject)  auctionRooms.get(room);
                //auctionsList.add(new Room(tempRoom));
                this.activeRooms.add(new Room(tempRoom));

            }

            //this.activeRooms.addAll(auctionsList);

            this.activeAuctions_TableView.setItems(this.activeRooms);

        }catch (ParseException e){

            e.printStackTrace();
        }
    }

    //Handlers
    @FXML
    private void onHandleCreate(){

        this.updating = false;
        this.mainApp.showAuctionCreatorScene(this.borderPane);
    }

    @FXML
    private void onHandleJoin(){

        int selectedIndex = this.activeAuctions_TableView.getSelectionModel().getSelectedIndex();

        if(selectedIndex>=0){

            JSONObject outputJson = new JSONObject();
            outputJson.put("Request", 5);
            outputJson.put("AuctionRoom", this.activeAuctions_TableView.getItems().get(selectedIndex).getProductName());
            outputJson.put("Username", this.mainApp.getActiveUser().getUsername());

            this.mainApp.getClient().write(outputJson.toJSONString());
            String inputString = this.mainApp.getClient().read();
            this.parseJoinRequest(inputString);

        }else{

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Room Selected");
            alert.setContentText("Please select an auction room");

            alert.showAndWait();

        }

    }

    private void parseJoinRequest(String inputString){
        System.out.println(inputString);
        JSONParser parser = new JSONParser();

        try{

            JSONObject inputJson = (JSONObject) parser.parse(inputString);
            boolean requestState = (boolean) inputJson.get("RequestState");

            if(requestState){
                this.updating = false;

                this.mainApp.showAuctionRoom(RoomType.CLIENT, this.activeAuctions_TableView.selectionModelProperty().get().getSelectedItem());

            }else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error Joining Auction Room");
                alert.setContentText("Try with other product");

                alert.showAndWait();

            }

        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    //Setters & Getters
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.initUpdater();
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}
