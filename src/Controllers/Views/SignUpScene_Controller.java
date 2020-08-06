package Controllers.Views;

import Controllers.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignUpScene_Controller {

    private MainApp mainApp;

    @FXML
    private TextField username_TextField;

    //Handlers
    @FXML
    private void onHandleCancel(){

        this.mainApp.showSignInScene();
    }

    //Setters & Getters
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
