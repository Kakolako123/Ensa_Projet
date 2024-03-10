package com.example.jsf_devoir.DAO;

import com.example.jsf_devoir.Model.User;

import java.util.List;

public interface ImplUserDAO {

    public List SelecterElementBD();
    public void AjouterUtilisateur(User user);
    public void SupprimerUtilisateur(User user) ;
    //modifier un utilisateur
    public void Update_User(User user);

}
