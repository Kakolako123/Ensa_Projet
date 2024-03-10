package com.example.jsf_devoir.Test;


import com.example.jsf_devoir.Controller.UserBean;
import com.example.jsf_devoir.Model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Started");

        String birthdayString = "2024-03-20";

        // Définir le formatteur de date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parser la chaîne de date en LocalDate
        LocalDate birthday = LocalDate.parse(birthdayString, formatter);

        // Convertir LocalDate en java.util.Date
        java.sql.Date date = java.sql.Date.valueOf(birthday);

        // Définir votre utilisateur avec la date
        User user = new User(8, "C", "C", "B", "B", "B", date);

        // Utiliser votre utilisateur comme nécessaire
        System.out.println(user);
        UserBean userBean = new UserBean();

    }
}