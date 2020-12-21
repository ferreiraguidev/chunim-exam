package Chunim;

import Chunim.Admin;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private Admin admin;

    public String log() { // look in db

        if (username.equals("databaseroot") && password.equals("servlet2020")) {
            return "/Restricted/chunimR.xhtml?faces-redirect=true";

        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Invalid Password or Username ", ""));

        return null;
    }

    public LoginBean() {
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        admin = null;
        return "/testHome.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}