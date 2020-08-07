package Controllers.Views;

import Controllers.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AuctionsCreatorScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private MainApp mainApp;

    @FXML
    private TextField productName_TextField;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
