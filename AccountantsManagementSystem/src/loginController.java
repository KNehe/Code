import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class loginController {
     double x,y;
    Connection connection;
    //used to retrieve db data
    String emails;
    String passwords;
    String roles;
    //used to retrieve db data

     @FXML private Label errorMessage;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button signInButton;
    @FXML
    private Label closeLabel;

    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @FXML
    private void initialize() {
        //creating database connection
        databaseHandler dc = new databaseHandler();
        connection = dc.connect();
    }

    //for closing tthe primary stage
    @FXML
    private void setCloseLabel() {
       // System.exit(0);
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.close();
    }

    public void Login(ActionEvent event) throws  Exception{
        String email = loginUsername.getText();
        String password = loginPassword.getText();
        if (email.isEmpty()) {
            errorMessage.setText("Please enter username!");
            loginUsername.requestFocus();
            return;
        }
        if(password.isEmpty()){
            errorMessage.setText("Please enter password!");
            loginPassword.requestFocus();
            return;
        }

        //retrieving database data that matches the above
        String query = "select * from users where emails ='"+email+"' ";

        ResultSet resultSet = connection.createStatement().executeQuery(query);

        while (resultSet.next())
        {
             emails = resultSet.getString("emails");
             passwords = resultSet.getString("passwords");
             roles = resultSet.getString("roles");
        }//end 0f while(rs.next)

        if(email.equals(emails) && password.equals(passwords)){
            if(roles.equals("Account"))
            {
                System.out.println(emails + passwords + roles);
                //close the current stage and start a new one
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                //undecorating the stage
                primaryStage.initStyle(StageStyle.UNDECORATED);
                



               Parent root = FXMLLoader.load(getClass().getResource("accountantWork.fxml"));
                root.setOnMousePressed(e->{
                    x = e.getX();
                    y = e.getY();
                });

                root.setOnMouseDragged(e->{
                    primaryStage.setX(e.getScreenX() - x);
                    primaryStage.setY(e.getScreenY() - y);
                });



                Scene scene= new Scene(root);

                primaryStage.setScene(scene);
                primaryStage.show();

            }else if (roles.equals("Admin"))
            {
                // System.out.println(emails + passwords + roles);
                //closing the current stage
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("adminDashboard.fxml"));

                Scene scene = new Scene(root);
                scene.getStylesheets().add(adminDashboardController.class.getResource("adminDashBoard.css").toExternalForm());

                //undecorating the stage
                primaryStage.initStyle(StageStyle.UNDECORATED);
                root.setOnMousePressed(e->{
                    x =e.getX();
                    y= e.getY();
                });
                root.setOnMouseDragged(e->{
                    primaryStage.setX(e.getScreenX() - x);
                    primaryStage.setY(e.getScreenY() - y);
                });

                //new stage with a new Scene
                primaryStage.setScene(scene);
                primaryStage.show();


            }
        }//end of if
        else //when login data is wrong or user not registered
        {
            errorMessage.setText("Wrong credentials or not Registered! ");
        }




        }//end of login

        }



