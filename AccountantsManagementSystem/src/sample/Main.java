package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    //declaring offsets for x and y axis to enable drag on undecorated stage
    private static double xOffset;
    private static double yOffset;

    //class root
     Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //loading the fxml file
        root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);
        //adding css file
       root.getStylesheets().add(getClass().getResource("login.css").toExternalForm());

       //undecorating the stage to remove the default bar having the minimize and close button
        primaryStage.initStyle(StageStyle.UNDECORATED);

       //making the undecorated stage dragable
        //getting offset from scene i.e grabbing root
        root.setOnMousePressed(e->{
            xOffset = e.getX();
            yOffset = e.getY();
        });
        //move mouse around here
        root.setOnMouseDragged(e-> {
                primaryStage.setX(e.getScreenX() -xOffset);
                primaryStage.setY(e.getScreenY() - yOffset);

            });

        //primaryStage.setTitle("ACCOUNT"); //not required since stage is undecorated
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
