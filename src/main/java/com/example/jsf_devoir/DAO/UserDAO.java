package com.example.jsf_devoir.DAO;

import com.example.jsf_devoir.Model.User;
import com.example.jsf_devoir.Util.ConnexionDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements ImplUserDAO{
    Connection con;
    ConnexionDAO cn = new ConnexionDAO();
    PreparedStatement statement = null;
    ResultSet resultset = null;

    public UserDAO() {

    }
    @Override
    public List<User> SelecterElementBD() {
        List<User> list = new ArrayList<User>();
        try {
            con = cn.getConnexion();
            statement = con.prepareStatement("Select * from user");
            resultset = statement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("id");
                String firstname = resultset.getString("firstName");
                String lastname = resultset.getString("lastName");
                String email = resultset.getString("email");
                String password = resultset.getString("password");
                String gender = resultset.getString("gender");
                java.sql.Date birthday = resultset.getDate("birthday");
                User user = new User(id, firstname, lastname, email, password,gender,birthday);
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
    @Override
    public void AjouterUtilisateur(User user) {
        try {
            cn = new ConnexionDAO();
            con = cn.getConnexion();
            statement = con.prepareStatement("insert into user (id,firstName,lastName,email,password,gender,birthday) values(?,?,?,?,?,?,?)");
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getGender());
            statement.setDate(7, new java.sql.Date(user.getBirthday().getTime()));
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void Update_User(User user) {
        try (Connection con = cn.getConnexion();
             PreparedStatement statement = con.prepareStatement("UPDATE user SET firstName=?, lastName=?, email=?,password=?, gender=?, birthday=? WHERE id=?")) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getGender());
            statement.setDate(6, new java.sql.Date(user.getBirthday().getTime()));
            statement.setInt(7, user.getId());
            statement.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void SupprimerUtilisateur(User user) {
        try {
            con = cn.getConnexion();
            statement = con.prepareStatement("Delete from user where id=?");
            statement.setInt(1,user.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
