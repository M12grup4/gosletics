/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.domain;

import cat.xtec.ioc.db.dbConnection;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author juanrodriguezguardeno
 */
@Configuration
public class LoginDAO {

    public LoginDAO() {

    }

    public Login validaLogin(String login) throws SQLException, IOException {
        
               // Gson
   Gson objGson = new Gson();
 Login log = objGson.fromJson(login,Login.class);

        
        
        String qry = "select aes_decrypt(email, 'AES')as email, aes_decrypt(pass, 'AES') as pass"
                + " FROM GL_clientes"
              + " WHERE aes_decrypt(email, 'AES')='" + log.getMail() + "' "
                + " and aes_decrypt(pass, 'AES')='" + log.getPass()+"'";

        System.out.println("Query del login : " + qry);

        dbConnection dbConnection = new dbConnection();
        List<Activitats_dia> activitats_dia_list = new ArrayList();

        try (
                Connection conn = (Connection) dbConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            Login resLog = null;
            Boolean isOK;
            Boolean isAdmin;
            String error;
 System.out.println ("antes de rs.next");
            while (rs.next()) {
                System.out.println ("dentro de rs.next");
                
                String pass = rs.getString("pass");
                String email = rs.getString("email");
                
                System.out.println ("email:" +email);
                System.out.println ("pass:" + pass);
                if (email == log.getMail() && pass == log.getPass()) {
                    if (email == "admin@gostetic.com") {
                        isOK = true;
                        isAdmin = true;
                        System.out.println ("VALIDADO !!!!!");
                        resLog = new Login(isAdmin, isOK);
                    } else {
                        isOK = true;
                        System.out.println ("VALIDACION ALLIDA!!!!!");
                    }
                    isAdmin = false;
                    resLog = new Login(isAdmin, isOK);

                } else {
                    isOK = false;
                    isAdmin = false;
                    error = "mail o pass incorrectos";
                    resLog = new Login(error, isAdmin, isOK);
                }

            }
            return resLog;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        System.out.println ("fuera de rs.next");
        return null;

    }

}
