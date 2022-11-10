/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.domain;


import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/**
 *
 * @author juanrodriguezguardeno
 */

public class Login {
    @SerializedName("mail")
    private String mail;
    @SerializedName("pass")
    private String pass;
    @SerializedName("error")
    private String error;
    @SerializedName("isAdmin")
    private boolean isAdmin;
    @SerializedName("isOK")
    private boolean isOK;

    public Login() {
    }

     
     public Login( String mail, String pass,String error, boolean isAdmin) {
        this.mail = mail;
        this.pass = pass;
        this.error = error;
        this.isAdmin = isAdmin;
    }

    public Login(String mail, String pass, boolean isAdmin) {
        this.mail = mail;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }
        public Login( boolean isAdmin, boolean isOK) {
        this.isAdmin = isAdmin;
        this.isOK = isOK;
    }


    public Login(String error, boolean isAdmin, boolean isOK) {
        this.error = error;
        this.isAdmin = isAdmin;
        this.isOK = isOK;
    }

    public boolean isIsOK() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Login{" + "mail=" + mail + ", pass=" + pass + ", error=" + error + ", isAdmin=" + isAdmin + ", isOK=" + isOK + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.mail);
        hash = 47 * hash + Objects.hashCode(this.pass);
        hash = 47 * hash + Objects.hashCode(this.error);
        hash = 47 * hash + (this.isAdmin ? 1 : 0);
        hash = 47 * hash + (this.isOK ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Login other = (Login) obj;
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        if (this.isOK != other.isOK) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.error, other.error)) {
            return false;
        }
        return true;
    }

    

    
    
}
