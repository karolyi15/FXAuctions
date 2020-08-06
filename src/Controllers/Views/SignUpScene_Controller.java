package Controllers.Views;

import Controllers.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpScene_Controller {

    private MainApp mainApp;

    @FXML
    private TextField username_TextField;
    @FXML
    private PasswordField password_PasswordField;

    public void setMainApp(MainApp mainApp){

        this.mainApp = mainApp;
    }
}
