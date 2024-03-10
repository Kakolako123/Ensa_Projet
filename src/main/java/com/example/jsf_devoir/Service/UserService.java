package com.example.jsf_devoir.Service;

import com.example.jsf_devoir.DAO.UserDAO;
import com.example.jsf_devoir.Model.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(){
        this.userDAO=new UserDAO();
    }
    public UserService(UserDAO userDAO){
        this.userDAO=new UserDAO();
    }
    public List<User> GetUsers(){
        try {
            List<User> users = userDAO.SelecterElementBD();

            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void AddUser(User user){
        userDAO.AjouterUtilisateur(user);
    }

    public void UpdateUser(User user){ userDAO.Update_User(user); }

    public void deleteUser(User user) {
        userDAO.SupprimerUtilisateur(user);
    }

}
