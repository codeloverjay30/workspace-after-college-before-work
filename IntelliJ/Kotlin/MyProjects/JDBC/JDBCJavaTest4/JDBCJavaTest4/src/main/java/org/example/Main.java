package org.example;

import java.sql.DriverManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try{
            var availableDrivers = DriverManager.getDrivers();
            System.out.println("Drivers available");
            while(availableDrivers.hasMoreElements()){
                var driver = availableDrivers.nextElement();
                System.out.println(driver);
            }
            System.out.println("end of Drivers available");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        var url = "jdbc:mysql://35.194.200.2:3306/healthy";
        var user = "dev";
        var password = "0000";

        try{
            System.out.println("Connecting to database...");
            var connection = DriverManager.getConnection(url, user, password);
            var sqlCommand = "SELECT * FROM `user`;";
            var statement = connection.prepareStatement(sqlCommand);
            var resultSet = statement.executeQuery();
            System.out.println(resultSet);
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}