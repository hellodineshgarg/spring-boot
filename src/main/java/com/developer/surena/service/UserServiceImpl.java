package com.developer.surena.service;

import com.developer.surena.exception.*;
import com.developer.surena.model.User;
import com.developer.surena.repository.UserRepository;
import com.developer.surena.util.StringPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(long userId) throws UserNotFoundException {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User not founded with userId " + userId);
        }
    }

    @Override
    public User getUser(User user) throws UserNotFoundException {
        User newUser = userRepository.findByUserId(user.getUserId());
        if (newUser != null) {
            return newUser;
        } else {
            throw new UserNotFoundException("User not founded with user " + user.getUserId());
        }
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User not founded with username " + username);
        }
    }

    @Override
    public User getUser(String firstname, String lastname) throws UserNotFoundException {
        User user = userRepository.findByFirstnameAndLastname(firstname, lastname);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User not founded with firstname " + firstname + " and lastname " + lastname);
        }
    }

    @Override
    public User getUserByPassword(String pass) throws UserNotFoundException {
        User user = userRepository.findByPassword(pass);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User not founded with password " + pass);
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User not founded with username " + username);
        }
    }

    @Override
    @Transactional
    public void editUser(long userId, String password) throws UserNotFoundException {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            user.setPassword(password);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not founded with userId " + userId);
        }
    }

    @Override
    @Transactional
    public void editUser(long userId, String firstname, String lastname) throws UserNotFoundException {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            user.setFirstname(firstname);
            user.setLastname(lastname);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not founded with userId " + userId);
        }
    }


    @Override
    public List<User> getUsers() {
        List<User> users = null;
        if (userRepository.findAllBy().size() != 0)
            users = userRepository.findAllBy();
        return users;
    }

    @Override
    @Transactional
    public void addUser(String username, String password, String firstname, String lastname) throws UsernameIsBlank, PasswordIsBlank, LastnameIsBlank, FirstnameIsBlank {
        if (username.equals(StringPool.BLANK)) {
            throw new UsernameIsBlank();
        } else if (password.equals(StringPool.BLANK)) {
            throw new PasswordIsBlank();
        } else if (lastname.equals(StringPool.BLANK)) {
            throw new LastnameIsBlank();
        } else if (firstname.equals(StringPool.BLANK)) {
            throw new FirstnameIsBlank();
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setFirstname(firstname);
            user.setModifieddate(new Date());
            user.setLastname(lastname);
            user.setCreatedate(new Date());
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void updateUser(String username, String password, String firstname, String lastname) throws UsernameIsBlank, PasswordIsBlank, LastnameIsBlank, FirstnameIsBlank {
        if (username.equals(StringPool.BLANK)) {
            throw new UsernameIsBlank();
        } else if (password.equals(StringPool.BLANK)) {
            throw new PasswordIsBlank();
        } else if (lastname.equals(StringPool.BLANK)) {
            throw new LastnameIsBlank();
        } else if (firstname.equals(StringPool.BLANK)) {
            throw new FirstnameIsBlank();
        } else {
            User user = userRepository.findByUsername(username);
            user.setUsername(username);
            user.setPassword(password);
            user.setFirstname(firstname);
            user.setModifieddate(new Date());
            user.setLastname(lastname);
            user.setCreatedate(new Date());
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void deleteUser(long userId) {
        if (userId != 0)
            userRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public List<User> getUsersStartEnd(int page, int size) {
      // return userRepository.getUsersLimit(new PageRequest(page , size, null));
    return null;
    }


}
