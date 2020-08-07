package Controllers.Views;

import Controllers.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class AuctionsManager_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private MainApp mainApp;
    private BorderPane borderPane;

    @FXML
    private TableView activeAuctions_TableView;
    @FXML
    private Button create_Button;
    @FXML
    private Button join_Button;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Handlers
    @FXML
    private void onHandleCreate(){

        this.mainApp.showAuctionCreatorScene(this.borderPane);
    }

    @FXML
    private void onHandleJoin(){

    }

    //Setters & Getters
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}
