/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Login {
    
    public String userName = "";
    
    public int login(String uname,String pwd) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/login_system";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,"root","3306");
            Statement stmt = con.createStatement();
            String query = "Select user_name,password from user_info where user_name = '"+uname+"' and password = '"+pwd+"';";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                userName = uname;
                return 1;
            }
            return 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
           InternetAddress emailAddr = new InternetAddress(email);
           emailAddr.validate();
        } catch (AddressException ex) {
           result = false;
        }
        return result;
    }
    
    
    private static void createUserDatabase(String uname) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,"root","3306");
            Statement stmt = con.createStatement();
            String query = "Create database "+uname+";";
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int register(String name,String email,String uname,String pwd) {
        if(Login.isValidEmailAddress(email)) {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/login_system";
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url,"root","3306");
                Statement stmt = con.createStatement();
                String query = "Select user_name from user_info where user_name = '"+uname+"';";
                ResultSet rs = stmt.executeQuery(query);
                if(rs.next())
                    return 0;
                String insert = "Insert into user_info values('"+name+"','"+email+"','"+uname+"','"+pwd+"');";
                stmt.executeUpdate(insert);
                userName = uname;
                Login.createUserDatabase(uname);
                return 1;
            } catch (Exception ex) {
                System.out.println(ex);
            }
            return 0;
        }
        return -1;
        
    }
    
}
