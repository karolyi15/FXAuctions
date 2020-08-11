package Controllers.Views;

import Controllers.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AuctionsCreatorScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private MainApp mainApp;
    private BorderPane borderPane;

    @FXML
    private TextField productName_TextField;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Handlers
    @FXML
    private void onHandleConfirm(){

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
