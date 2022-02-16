/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author joeda
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * A simple data source for getting database connections.
 */
public class JDBCUtil {

    private static String url;
    private static String username;
    private static String password;

    /**
     * Initializes the data source.
     *
     * @param fileName the name of the property file that contains the database
     * driver, URL, username, and password
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void init(File fileName)
            throws IOException, ClassNotFoundException {

        Properties props = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);
        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        if (username == null) {
            username = "";
        }
        password = props.getProperty("jdbc.password");
        if (password == null) {
            password = "";
        }
        if (driver != null) {
            Class.forName(driver);
        }
    }

    /**
     * Gets a connection to the database.
     * @return the database connection *
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    
    //métodos desenvolvidos para percorrer o banco
    public static boolean goFirst(ResultSet result) throws SQLException{
        if(result.first())
            return true;
        
        return false;
    }
    
     public static boolean goLast(ResultSet result) throws SQLException{
        if(result.last())
            return true;
        
        return false;
    }
     
     public static boolean goNext(ResultSet result) throws SQLException{
        try {
                                 
            if(result.isLast() == false){
                if(result.next()){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                System.out.println("ultimo elemento ja acessado");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("error para ir pro proximo elemento: " +e);
        }
        return false;
     }
     
     public static boolean goPrevious(ResultSet result) throws SQLException{
        try {
            if(result.isFirst() == false){
                if(result.previous()){
                    return true;
                }
                else{
                    return false;
                }                     
            }
            else{
                System.out.println("ultimo elemento ja acessado");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("error para ir pro proximo elemento: " +e);
        }
        return false;
     }
     

     
}