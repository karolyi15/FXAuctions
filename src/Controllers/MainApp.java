package Controllers;

import Controllers.Models.Room;
import Controllers.Models.RoomType;
import Controllers.Models.User;
import Controllers.Views.*;
import Server.Client;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.simple.JSONObject;

import java.io.IOException;

public class MainApp extends Application {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Stage primaryStage;
    private BorderPane rootLayout;

    private Client client;
    private User activeUser;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public MainApp(){

        this.client = new Client();
        this.client.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FXAuctions");
        this.primaryStage.setResizable(false);
        this.customCloseRequest();

        this.initRootLayout();
        this.showSignInScene();
    }

    private void customCloseRequest(){

        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {

                JSONObject outputJson = new JSONObject();
                outputJson.put("Request", -1);

                client.write(outputJson.toJSONString());
            }
        });
    }

    private void initRootLayout(){

        try {

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Views/RootLayout.fxml"));
            this.rootLayout = (BorderPane) loader.load();

            //Create Scene
            Scene rootLayoutScene = new Scene(this.rootLayout);

            //Set
            this.primaryStage.setScene(rootLayoutScene);
            this.primaryStage.show();

        }catch (IOException e){

            e.printStackTrace();
        }

    }


    public void showSignInScene(){

        try {

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Views/SignInScene_UI.fxml"));
            AnchorPane signInScene = (AnchorPane) loader.load();

            //Set Controller
            SignInScene_Controller controller = loader.getController();
            controller.setMainApp(this);

            //Set
            this.rootLayout.setCenter(signInScene);

        }catch (IOException e){

            e.printStackTrace();
        }

    }

    public void showSignUpScene(){

        try {

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Views/SignUpScene_UI.fxml"));
            AnchorPane signUpScene = (AnchorPane) loader.load();

            //Set Controller
            SignUpScene_Controller controller = loader.getController();
            controller.setMainApp(this);

            //Set
            this.rootLayout.setCenter(signUpScene);

        }catch (IOException e){

            e.printStackTrace();
        }

    }

    public void showMenuScene(){

        try {

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Views/MenuScene_UI.fxml"));
            AnchorPane menuScene = (AnchorPane) loader.load();

            //Set Controller
            MenuScene_Controller controller = loader.getController();
            controller.setMainApp(this);

            //Set
            this.rootLayout.setCenter(menuScene);

        }catch (IOException e){

            e.printStackTrace();
        }

    }

    public void showAuctionsManager(BorderPane borderPane){

        try {

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Views/AuctionsManager_UI.fxml"));
            AnchorPane auctionManagerScene = (AnchorPane) loader.load();

            //Set Controller
            AuctionsManager_Controller controller = loader.getController();
            controller.setMainApp(this);
            controller.setBorderPane(borderPane);

            //Set
           borderPane.setCenter(auctionManagerScene);

        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public void showAuctionCreatorScene(BorderPane borderPane){

        try {

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Views/AuctionsCreatorScene_UI.fxml"));
            AnchorPane auctionsCreatorScene = (AnchorPane) loader.load();

            //Set Controller
            AuctionsCreatorScene_Controller controller = loader.getController();
            controller.setMainApp(this);
            controller.setBorderPane(borderPane);

            //Set
            borderPane.setCenter(auctionsCreatorScene);

        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public void showSettingsScene(BorderPane borderPane){

        try {

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Views/SettingsScene_UI.fxml"));
            AnchorPane settingsScene = (AnchorPane) loader.load();

            //Set Controller
            SettingsScene_Controller controller = loader.getController();
            controller.setMainApp(this);
            controller.setBorderPane(borderPane);

            //Set
            borderPane.setCenter(settingsScene);

        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public void showAuctionRoom(RoomType type, Room room){

        try {

            //Load Fxml File
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Views/AuctionRoomScene_UI.fxml"));
            AnchorPane auctionRoomScene = (AnchorPane) loader.load();

            //Set Controller
            AuctionRoomScene_Controller controller = loader.getController();
            controller.setMainApp(this);
            controller.setType(type);
            controller.setAuctionRoom(room);

            //Set
           this.rootLayout.setCenter(auctionRoomScene);

        }catch (IOException e){

            e.printStackTrace();
        }

    }

    //Setters & Getters
    public Client getClient() {
        return this.client;
    }

    public User getActiveUser() {
        return this.activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    //Main
    public static void main(String[] args) {
        launch(args);
    }
}
