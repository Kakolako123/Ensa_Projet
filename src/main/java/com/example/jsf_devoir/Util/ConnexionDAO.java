package com.example.jsf_devoir.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDAO {

    public ConnexionDAO(){

    }
    public static Connection getConnexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jee?serverTimezone=UTC", "root", "PHW#84#jeor");
            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null ;
    }
}
