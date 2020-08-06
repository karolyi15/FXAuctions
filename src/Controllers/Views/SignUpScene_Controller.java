package Controllers.Views;

import Controllers.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignUpScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private MainApp mainApp;

    @FXML
    private TextField username_TextField;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

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
