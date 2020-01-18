/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/10/2020 inside the package - Exercise3_3TierArchitecture
 **
 */

package Exercise3_ThreeTierArchitecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class DatabaseManagementTier {
    private String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    private String DATABASE_URL = "jdbc:mysql://localhost:3306/mydbuser";
    private String USER = "root";
    private String PASSWORD = "";
    private boolean isAnUser = false;

    public boolean readDatabase(String accountName) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

//            Set the class name , so register the JDBC driver
            Class.forName(JDBC_Driver);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            /*Difference between JDBC and JPA that manipulate java objects instead of sql tables(JDBC) */
//            Native sql queries
            String sqlCommand = "Select * from usertable";
//            Result set - all the entries in DB
            resultSet = statement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                String name = resultSet.getString("username");
                //if exist in Database then return true
                if (name.equals(accountName)) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();//we must close the result
                statement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

class DatabaseControllerMainClass {
    public static void main(String[] args) throws IOException {
        DatabaseManagementTier dbManagementTier = new DatabaseManagementTier();

        ServerSocket serverSocket = new ServerSocket(9091);
        System.out.println("Database ServerClass Started, waiting for main servers to request a check of user...");

        Socket socket = serverSocket.accept();
        //Sending message to the client
        PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
        pwOut.println("Hello main controller server!!! ");

        //reading from the client
        BufferedReader bfrInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientInput = bfrInput.readLine();
//        check if user exist

        if(dbManagementTier.readDatabase(clientInput)){
System.out.println("User exists!!!");
    }else
        {
            System.out.println("User NOT exists!!!");
        }
        //closing stream and socket
        bfrInput.close();
        pwOut.close();
        socket.close();
    }
}


