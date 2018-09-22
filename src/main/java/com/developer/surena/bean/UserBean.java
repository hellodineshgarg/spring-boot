package com.developer.surena.bean;

import com.developer.surena.exception.FirstnameIsBlank;
import com.developer.surena.exception.LastnameIsBlank;
import com.developer.surena.exception.PasswordIsBlank;
import com.developer.surena.exception.UsernameIsBlank;
import com.developer.surena.model.User;
import com.developer.surena.service.UserService;
import com.developer.surena.util.EncryptionDecryption;
import com.developer.surena.util.StringPool;
import com.developer.surena.util.language.LanguageUtil;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class UserBean extends SpringBeanAutowiringSupport implements Serializable {

    FacesContext context = FacesContext.getCurrentInstance();
    private Logger logger = Logger.getLogger(UserBean.class.getName());

    private List<User> userList;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Date createdate;
    private Date modifieddate;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    @Autowired
    UserService userService;

    @PostConstruct
    public void after() {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        List<User> users = userService.getUsersStartEnd(0,6);
        this.userList = users;
    }

    public void remove(long userId) {
        userService.deleteUser(userId);
        this.userList = userService.getUsers();
        logger.log(Level.INFO, "User with userid " + userId + " removed.");
    }

    public void addUser() {
        try {
            userService.addUser(username, new EncryptionDecryption().getMD5(password),firstname,lastname);
            this.username = StringPool.BLANK;
            this.password = StringPool.BLANK;
            this.firstname = StringPool.BLANK;
            this.lastname = StringPool.BLANK;

        } catch (UsernameIsBlank usernameIsBlank) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("username_is_required"), ""));
        } catch (FirstnameIsBlank firstnameIsBlank) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("firstname_is_required"), ""));
        } catch (PasswordIsBlank passwordIsBlank) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("password_is_required"), ""));
        } catch (LastnameIsBlank lastnameIsBlank) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("lastname_is_required"), ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("user_is_exsist"), ""));
        }
        userList = userService.getUsers();
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void paginate(){

    }


    public void onCellEdit(CellEditEvent event) {
        DataTable o = (DataTable) event.getSource();
        User user = (User) o.getRowData();
        try {
            userService.updateUser(user.getUsername(),user.getPassword(),user.getFirstname(),user.getLastname());
        } catch (UsernameIsBlank usernameIsBlank) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("username_is_required"), ""));
        } catch (FirstnameIsBlank firstnameIsBlank) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("firstname_is_required"), ""));
        } catch (PasswordIsBlank passwordIsBlank) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("password_is_required"), ""));
        } catch (LastnameIsBlank lastnameIsBlank) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("lastname_is_required"), ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, LanguageUtil.get("user_is_exsist"), ""));
        }
        userList = userService.getUsers();
        logger.log(Level.INFO, "User with userId " + user.getUserId() + " updated.");
    }
}
