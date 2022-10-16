
package cat.xtec.ioc.db;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class dbConnection {
    
    
    
    
    
    
    

public  Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        InputStream resourceAsStream = null;
        Connection con = null;
        try {
          
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Gosletics","gosletics","gosletics");
                                   }
         catch (ClassNotFoundException | SQLException e) {
        } catch (InstantiationException ex) {
        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        }
        if (con!=null) {
            System.out.println ("\n dbConnection: Returnem con valida");
        }
        else {
            
                        System.out.println ("\n dbConnection: Returnem con FALLIDA");

                    
        
            
        }
        return con;
    }

   
    public  Connection getConnection22() throws SQLException, IOException {
        Properties props = new Properties();
        InputStream resourceAsStream = null;
        Connection con = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL urlResource = classLoader.getResource("db.properties");
            if (urlResource != null) {
                resourceAsStream = urlResource.openStream();
                props.load(resourceAsStream);
                Class.forName(props.getProperty("DB_DRIVER_CLASS"));
                con = DriverManager.getConnection(props.getProperty("DB_URL"),
                        props.getProperty("DB_USERNAME"),
                        props.getProperty("DB_PASSWORD"));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
        } finally {
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        }
        if (con!=null) {
            System.out.println ("\n dbConnection: Returnem con valida");
        }
        else {
            
                        System.out.println ("\n dbConnection: Returnem con FALLIDA");

                    
        
            
        }
        return con;
    }
    
}
