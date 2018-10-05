import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminDashboardController {

    Parent root;
    @FXML
    private BorderPane borderPane;

     @FXML private Label username;
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    //receive login data from login controller
    //@FXML public void getCurrentUserInfo(String email){}
    public void initialize(String email){
        username.setText(email);
    }
    @FXML
    private void initialize(){

    }

    //close window x
    @FXML
    private void closeWindow(){
        Stage stage = (Stage)borderPane.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void  adminRegister(MouseEvent event){
      loadUI("adminRegister");
    }
    @FXML
    private void  adminMonitor(MouseEvent event){
        loadUI("adminEntertainment");
    }
    @FXML
    private void  adminWork(MouseEvent event){
        loadUI("adminWork");
    }


private void loadUI(String ui){
         root =null;
        try{
            root = FXMLLoader.load(getClass().getResource(ui +".fxml"));

        }catch (IOException ex){
            System.out.println("cant load ui because " + ex);

        }
        borderPane.setCenter(root);

}

}
