package Controllers.Views;

import Controllers.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MenuScene_Controller {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private MainApp mainApp;

    @FXML
    private BorderPane displayPanel_BorderPane;
    @FXML
    private ImageView userImage_ImageView;
    @FXML
    private Label username_Label;
    @FXML
    private Button auctions_Button;
    @FXML
    private Button notifications_Button;
    @FXML
    private Button famous_Button;
    @FXML
    private Button settings_Button;
    @FXML
    private Button about_Button;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    @FXML
    private void initialize(){

        Image userIcon = new Image("file:Resources/Imgs/UserIcon1.png");
        this.userImage_ImageView.setImage(userIcon);

    }

    private void initUser(){

        String username = this.mainApp.getActiveUser().getUsername();
        this.username_Label.setText(username);
    }

    //Setters & Getters
    public void setMainApp(MainApp mainApp) {

        this.mainApp = mainApp;

        this.initUser();
    }
}
