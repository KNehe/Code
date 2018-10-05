import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseHandler {


    public  Connection connect(){
        String url ="jdbc:mysql://localhost:3306/accountantManagementSystem";
        String user ="root";
        String pass ="";
        try{

            Connection con = DriverManager.getConnection(url,user,pass);
            System.out.println("CONNECTION SUCCESSFUL");
             return con;
        }catch (SQLException e){
            System.out.println("THE DATABASE CONNECTION ERROR IS "+e.getMessage());
        }
     return  null;
    }



    //inserting data into the specific tables
     public static void insertData(String tableName,String values[]){

     }
}
