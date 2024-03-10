package com.example.jsf_devoir.Controller;


import com.example.jsf_devoir.DAO.UserDAO;
import com.example.jsf_devoir.Model.User;
import com.example.jsf_devoir.Service.UserService;
import jakarta.annotation.ManagedBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ViewScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {
    private List<User> users;
    private User newUser;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private Date birthday;
    private boolean editable;
    //pagination
    private int pageSize;
    private int currentPage = 0;
    private int totalPages;
    private String searchQuery;

    private UserService userService = new UserService(new UserDAO());
    public UserBean() throws SQLException{
        newUser = new User();
        users = userService.GetUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // tous les articles
    public List<User> GetUsers() {
        UserService userService = new UserService();
        users = userService.GetUsers();
        return users;
    }
    public void Adduser() {
        Date sqlDate = new Date(newUser.getBirthday().getTime());

        newUser = new User(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword(), newUser.getGender(), sqlDate);
        UserService employeeService = new UserService();
        System.out.println("User: " + newUser);
        System.out.println("helo");
        employeeService.AddUser(newUser);
    }
    public void updateUser(User user){
        userService.UpdateUser(user);
        users = userService.GetUsers();
    }

    public void DeleteUser(User user) {
        UserService userService = new UserService();
        userService.deleteUser(user);
        // Rafraîchissez la liste d'utilisateurs après suppression
        users = GetUsers();
    }

    public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {
            FacesMessage message = new FacesMessage("Email Address is Valid");
            throw new ValidatorException(message);
        }
    }
    public List<User> displayedUser(){
        int index = currentPage * 5;
        int toIndex =Math.min(index + 5,users.size());
        return users.subList(index, toIndex);
    }
    public void navigateToPreviousPage() {
        if (currentPage > 0) {
            currentPage--;
        }
    }

    public void navigateToNextPage() {
        currentPage++;
    }

    public void searchUser() {
        System.out.println("Search Query: " + searchQuery);
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            // Si la requête de recherche est vide, affichez tous les utilisateurs
            System.out.println("Search Query is empty. Showing all users.");
            UserService userService = new UserService();
            users = userService.GetUsers();
        } else {
            // Filtrer la liste des utilisateurs en fonction de la requête de recherche
            System.out.println("Searching for users with last name containing: " + searchQuery);
            users = users.stream()
                    .filter(e -> e.getLastName().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }
    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean getEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

   public void switchEditable(User user){
       user.switchEditable();
   }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
