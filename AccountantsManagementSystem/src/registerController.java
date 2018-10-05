import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class registerController {

    private static String gender;
    @FXML
    private ToggleGroup sex;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtSecondName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<String> comboRole;
    @FXML
    private RadioButton radioMale;
    @FXML
    private RadioButton radioFemale;

    //working ont he table and its columns
    @FXML
    private TableView<modalTable> tableUsers;

    @FXML
    private  TableColumn<modalTable,String> columnFirstName;
    @FXML
    private  TableColumn<modalTable,String> columnSecondName;
    @FXML
    private  TableColumn<modalTable,String> columnUserName;
    @FXML
    private  TableColumn<modalTable,String> columnPassword;
    @FXML
    private  TableColumn<modalTable,String> columnRoles;
    @FXML
    private  TableColumn<modalTable,String> columnGenders;
    @FXML
    private  TableColumn<modalTable,String> columnEmails;
    @FXML
    private  TableColumn<modalTable,String> loadData;

    //intialize observable list to hold database data
    ObservableList<modalTable> data;
    private databaseHandler dc;

    @FXML
    private URL Location;
    @FXML
    private ResourceBundle resources;

    @FXML
    private void initialize(){
        dc = new databaseHandler();

        ObservableList<String> ComboBoxdata =FXCollections.observableArrayList("Admin","Accountant");
        comboRole.setItems(ComboBoxdata);
    }


    //these are the textfields that get text when a row on the table has been selected
    //the are right below the table
    @FXML
    private TextField changeFName;
    @FXML
    private TextField changeSName;
    @FXML
    private TextField changeUName;
    @FXML
    private TextField changePass;
    @FXML
    private TextField changeRole;
    @FXML
    private TextField changeEName;
    //end

    //for the button on the vbox to save registration details
    @FXML
    private void SaveData(ActionEvent event){
        String email = txtEmail.getText();
        String firstName = txtFirstName.getText().trim();
        String secondName = txtSecondName.getText().trim();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String role = comboRole.getSelectionModel().getSelectedItem();

        if (radioFemale.isSelected()){
            gender = "Female";
        }
        if (radioMale.isSelected()){
            gender ="Male";
        }
         if(email.isEmpty()){
            txtEmail.requestFocus();
            return;
         }
        if(firstName.isEmpty()){
            txtFirstName.requestFocus();
            return;
        }
        if(secondName.isEmpty()){
            txtSecondName.requestFocus();
            return;
        }
        if(userName.isEmpty()){
            txtUserName.requestFocus();
            return;
        }
        if(password.isEmpty()){
            txtPassword.requestFocus();
            return;
        }

        try{
            Connection con = dc.connect();
            String query = "INSERT INTO `users`(`firstName`, `secondName`, `userName`, `passwords`, `roles`, `genders`, `emails`) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,firstName);
            ps.setString(2,secondName);
            ps.setString(3,userName);
            ps.setString(4,password);
            ps.setString(5,role);
            ps.setString(6,gender);
            ps.setString(7,email);
            ps.execute();
            System.out.println("insertion successfull ");

            txtEmail.setText("");
            txtFirstName.setText("");
            txtSecondName.setText("");
            txtPassword.setText("");
            txtUserName.setText("");

        }catch (SQLException e){
            System.out.println("THE ERROR IS "+e.getMessage());
        }

    }//end of save()

  //clearing the fields of the registration section
    @FXML
    private void clear(){
        txtEmail.setText("");
        txtFirstName.setText("");
        txtSecondName.setText("");
        txtPassword.setText("");
        txtUserName.setText("");
    }

    //populating the table view
    @FXML
    private void loadData(ActionEvent event){
        try{
            Connection conn = dc.connect();

            data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM users");

           while (rs.next()){
              data.add(new modalTable(rs.getString("firstName"),
                      rs.getString("secondName"),rs.getString("userName"),
                      rs.getString("passwords"),rs.getString("roles"),
                      rs.getString("genders"),rs.getString("emails")));
           }

       }catch (SQLException e){
            System.out.println("error is  "+ e.getMessage());

       }
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnSecondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        columnUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("passwords"));
        columnRoles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        columnGenders.setCellValueFactory(new PropertyValueFactory<>("genders"));
        columnEmails.setCellValueFactory(new PropertyValueFactory<>("emails"));
        //tableUsers.setItems(null);
        tableUsers.setItems(data);

    }
    //getting selected row from the table to the textfields
    @FXML
    private void selectDataFromTableToTextFields(){

             modalTable person = tableUsers.getSelectionModel().getSelectedItem();
             if (person==null){
                 changeFName.setText("no row selected");
                 changeUName.setText("no row selected");
                 changeSName.setText("no row selected");
                 changePass.setText("no row selected");
                 changeRole.setText("no row selected");
                 changeEName.setText("no row selected");
             }else {
                 String fName = person.getFirstName();
                 String sName = person.getSecondName();
                 String uName = person.getUserName();
                 String pass = person.getPasswords();
                 String role = person.getRoles();
                 String emails =person.getEmails();

                 changeFName.setText(fName);
                 changeSName.setText(sName);
                 changeUName.setText(uName);
                 changePass.setText(pass);
                 changeRole.setText(role);
                 changeEName.setText(emails);
                 }
             }

             //delete selected row from database and table
    @FXML
    private void deleteAccountant(ActionEvent event){
        modalTable person = tableUsers.getSelectionModel().getSelectedItem();
        if (person==null){
            //message here
        }else {
            String fName = person.getFirstName();
            String sName = person.getSecondName();
            String uName = person.getUserName();
            String pass = person.getPasswords();
            String role = person.getRoles();
            String emails =person.getEmails();
            try{
                Connection conn = dc.connect();
                String query ="DELETE FROM `users` WHERE firstName=? AND secondName=? AND userName=? AND passwords=? AND roles=? AND emails=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1,fName);
                ps.setString(2,sName);
                ps.setString(3,uName);
                ps.setString(4,pass);
                ps.setString(5,role);
                ps.setString(6,emails);
                ps.execute();
                System.out.println("record deleted");
                changeFName.setText("");
                changeUName.setText("");
                changeSName.setText("");
                changePass.setText("");
                changeRole.setText("");
                changeEName.setText("");


            }catch (SQLException E){
                System.out.println("THE DELETE ERROR IS "+ E.getMessage());
            }
        }

    }//end of deleteAccountant()

    @FXML
    private void modifyAccountantData(ActionEvent event) {
        modalTable person = tableUsers.getSelectionModel().getSelectedItem();
        if (person == null) {

        } else {
            String emails = person.getEmails();
            String pass = person.getPasswords();
            try {
                Connection conn = dc.connect();
                String query = "UPDATE `users` SET `firstName`=?,`secondName`=?,`userName`=?,`passwords`=?,`roles`=?,`emails`=? WHERE emails='"+emails+"' AND passwords='"+pass+"'";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, changeFName.getText());
                ps.setString(2, changeSName.getText());
                ps.setString(3, changeUName.getText());
                ps.setString(4, changePass.getText());
                ps.setString(5, changeRole.getText());
                ps.setString(6, changeEName.getText());
                ps.executeUpdate();
                System.out.println("record updated");
                changeFName.setText("");
                changeUName.setText("");
                changeSName.setText("");
                changePass.setText("");
                changeRole.setText("");
                changeEName.setText("");
            } catch (SQLException e) {
                System.out.println("THE UPDATE ERROR is " + e.getMessage());
            }

        }
    }

}
