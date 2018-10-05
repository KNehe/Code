import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.beans.binding.Bindings;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import  javafx.stage.FileChooser.ExtensionFilter;

public class entertainmentController {
    File file;
    //database connection
   private databaseHandler databaseConnection;
    @FXML private Slider volumeSlider;
    @FXML private ListView mediaList;
    @FXML
    private MediaView mediaView;

    @FXML
    private MediaPlayer mediaPlayer ;

    @FXML
    private Media  media ;

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resourceBundle;
    @FXML
    private void initialize()
    {
        databaseConnection = new databaseHandler();
    }


    @FXML
    private void playVideo(){

        try
        {
            String mediaStringUrl2 = mediaList.getSelectionModel().getSelectedItem().toString();
            //URL mediaURL = getClass().getResource("gotham.mp4");
            //String mediaStringUrl = mediaURL.toExternalForm();

            media =new Media(mediaStringUrl2);

            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

            mediaView.setMediaPlayer(mediaPlayer);

            mediaView.setSmooth(true);

            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            width.bind(Bindings.selectDouble(mediaView.parentProperty(),"width"));
            height.bind(Bindings.selectDouble(mediaView.parentProperty(),"height"));


            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            {
                mediaPlayer.stop();
                mediaPlayer.play();
            }
            else
            {
                mediaPlayer.play();
            }

            //working on the volume slider
            volumeSlider.setValue(mediaPlayer.getVolume()*50);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue()/50);
                }
            });
        }catch (Exception e)
        {
            System.out.println(" error is " + e.getMessage());
        }

    }

    @FXML
    private void pauseVideo(ActionEvent actionEvent)
    {
        try{
            mediaPlayer.pause();
            if(mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED)
            {
                mediaPlayer.play();
            }
        }catch (Exception e)
        {

        }

    }

    @FXML
    private void stopVideo(ActionEvent actionEvent)
    {
        try
        {
            mediaPlayer.stop();
        }catch (Exception e){}

    }

    //media selected added to database and the list view
    @FXML private void chooseMedia(ActionEvent actionEvent)
    {
        FileChooser fileChooser = new FileChooser();
        //fileChooser.getExtensionFilters().addAll(new ExtensionFilter("VIDEOS", "*.videos"));
         file = fileChooser.showOpenDialog(null);
        if(file!= null)
        {
            try
            {
                Connection con = databaseConnection.connect();
                String query ="INSERT INTO `media`(`Videos`) VALUES(?)";
                PreparedStatement p = con.prepareStatement(query);
                p.setString(1,file.getAbsoluteFile().toURI().toString());
                p.execute();
                System.out.println("added to database "+file.getAbsoluteFile().toURI().toString());

                String query2 = "SELECT * FROM `media`";
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query2);

                List<String> videoList = new ArrayList<>();
                while (rs.next()) {
                    videoList.add(rs.getString("Videos"));
                }
                ObservableList mediaData = FXCollections.observableArrayList(videoList);
                mediaList.setItems(mediaData);

            }catch (SQLException e)
            {
                System.out.println("SQL Error is "+ e.getMessage());
            }
        }else
        {
            System.out.println("no file selected");
        }

    }

    //viewing the media in the list view
    @FXML private  void setMediaList(ActionEvent actionEvent)
    {
        try
        {
            Connection con = databaseConnection.connect();
            //retrieving and adding it to the list view
            String query2 = "SELECT * FROM `media`";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query2);

               List<String> videoList = new ArrayList<>();
                while (rs.next()) {
                    videoList.add(rs.getString("Videos"));
                }
                ObservableList mediaData = FXCollections.observableArrayList(videoList);
                mediaList.setItems(mediaData);

        }catch (SQLException e)
        {
            System.out.println("The SQL Error is "+ e.getMessage());
        }

    }
    }


