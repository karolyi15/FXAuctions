package Controllers;

import Controllers.Models.User;
import Controllers.Views.SignInScene_Controller;
import Controllers.Views.SignUpScene_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private User activeUser;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FXAuctions");
        this.primaryStage.setResizable(false);

        this.initRootLayout();
        this.showSignInScene();
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

    public static void main(String[] args) {
        launch(args);
    }
}
