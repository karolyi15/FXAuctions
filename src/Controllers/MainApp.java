package Controllers;

import Controllers.Models.User;
import Controllers.Views.SignInScene_Controller;
import Controllers.Views.SignUpScene_Controller;
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

    //Setters & Getters
    public Client getClient() {
        return this.client;
    }

    //Main
    public static void main(String[] args) {
        launch(args);
    }
}
