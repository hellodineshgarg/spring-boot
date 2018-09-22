package com.developer.surena.service;

import com.developer.surena.exception.*;
import com.developer.surena.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {


    public List<User> getUsers();
    public User getUser(long userId) throws UserNotFoundException;
    public User getUser(User user) throws UserNotFoundException;
    public User getUser(String username) throws UserNotFoundException;
    public User getUser(String firstname, String lastname) throws UserNotFoundException;
    public User getUserByPassword(String password) throws UserNotFoundException;
    public User getUserByUsername(String username) throws UserNotFoundException;
    public void editUser(long userId ,  String newPassword) throws UserNotFoundException;
    public void editUser(long userId ,  String firstname , String lastname) throws UserNotFoundException;
    public void addUser(String username, String password, String firstname, String lastname) throws UsernameIsBlank, PasswordIsBlank, LastnameIsBlank, FirstnameIsBlank;
    public void updateUser(String username, String password, String firstname, String lastname) throws UsernameIsBlank, PasswordIsBlank, LastnameIsBlank, FirstnameIsBlank;
    public void deleteUser(long userId);
    public void deleteUser(String username);
    public List<User> getUsersStartEnd(int page , int size);




}

