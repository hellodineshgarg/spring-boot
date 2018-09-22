package com.developer.surena;

import com.developer.surena.exception.*;
import com.developer.surena.model.User;
import com.developer.surena.util.EncryptionDecryption;
import com.developer.surena.service.UserService;
import com.developer.surena.util.StringPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {
    @Autowired
    UserService userService;

    @Test
    public void testGetUsers() throws Exception {
        //assertThat(userService.getUsers()).isNotEqualTo(null);
        try {
            User user =  userService.getUser("asd");
            System.out.println(user.toString());
        } catch (UserNotFoundException e) {
            System.err.println(e);
        }
    }

    @Test
    public void testGetUser() {
        try {
           User user =  userService.getUser(1111);
           System.out.println(user.toString());
        } catch (UserNotFoundException e) {
            System.err.println(e);
        }
    }


    @Test
    public void testInsertUser() {
        String password = StringPool.BLANK;
        try {
            password = new EncryptionDecryption().getMD5("ALIALI");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            userService.addUser("",password,"","");
        } catch (UsernameIsBlank usernameIsBlank) {
            usernameIsBlank.printStackTrace();
        } catch (FirstnameIsBlank firstnameIsBlank) {
            firstnameIsBlank.printStackTrace();
        } catch (PasswordIsBlank passwordIsBlank) {
            passwordIsBlank.printStackTrace();
        } catch (LastnameIsBlank lastnameIsBlank) {
            lastnameIsBlank.printStackTrace();
        }
    }


    @Test
    public void testInsertNewUser() throws Exception {

            User user = new User();
            user.setPassword(new EncryptionDecryption().getMD5("21444"));
            user.setCreatedate(new Date());
            user.setFirstname("13311");
            user.setLastname("112211");
            user.setModifieddate(new Date());
            user.setUsername("41121144");
          //  userService.addUser(user);

    }


    @Test
    public void testDeleteUser() throws Exception {
        userService.deleteUser(5);
    }

    @Test
    public void testDeleteUser2() throws Exception {
        userService.deleteUser("username2");
    }



    @Test
    public void testEditUser() throws Exception {
        try {
            userService.editUser(2,new EncryptionDecryption().getMD5("myjava123"));
        } catch (UserNotFoundException e) {
            System.err.println(e);
        }
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        //assertThat(userService.getUser("username")).isNotEqualTo(null);
        try {
            User user =  userService.getUser("sss123");
            System.out.println(user.toString());
        } catch (UserNotFoundException e) {
            System.err.println(e);
        }

    }

    @Test
    public void testGetNotDuplicateUserByUsername() throws Exception {
        //assertThat(userService.getUserByUsername("first")).isNotEqualTo(null);
        try {
            User user =  userService.getUserByUsername("sss123");
            System.out.println(user.toString());
        } catch (UserNotFoundException e) {
            System.err.println(e);
        }
    }

    @Test
    public void testGetUserByPassword() throws Exception {
        //assertThat(userService.getUserByPassword(new EncryptionDecryption().getMD5("MYJAVA123"))).isNotEqualTo(null);
        try {
            User user =  userService.getUserByPassword("passsssss");
            System.out.println(user.toString());
        } catch (UserNotFoundException e) {
            System.err.println(e);
        }

    }


    @Test
    public void testGetUserByFirstnameAndLastName() throws Exception {
       // assertThat(userService.getUser("first","pass")).isNotEqualTo(null);
        try {
            User user =  userService.getUser("firs","las");
            System.out.println(user.toString());
        } catch (UserNotFoundException e) {
            System.err.println(e);
        }
    }

}
