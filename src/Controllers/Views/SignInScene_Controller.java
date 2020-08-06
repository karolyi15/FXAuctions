package Controllers.Views;

import Controllers.MainApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SignInScene_Controller {

    private MainApp mainApp;

    @FXML
    private Label createAccount_Label;
    @FXML
    private TextField username_TextField;
    @FXML
    private PasswordField password_PasswordField;
    @FXML
    private Button signIn_Button;

    //Constructor
    @FXML
    private void initialize(){

        this.onHandleCreateAccount();
    }

    //Handlers
    @FXML
    private void onHandleSignIn(){

    }

    private void onHandleCreateAccount(){

        this.createAccount_Label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                    mainApp.showSignUpScene();
                }
            }
        });
    }

    //Setter & Getters
    public void setMainApp(MainApp mainApp){

        this.mainApp = mainApp;
    }
}
