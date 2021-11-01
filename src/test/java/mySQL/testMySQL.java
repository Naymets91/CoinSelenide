package mySQL;

import org.testng.annotations.Test;

import java.sql.*;

public class testMySQL {
    @Test
    public void test (){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/coins?user=root&password=root");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, email, name FROM au_users");
            while (rs.next()){
                Integer id = rs.getInt("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                System.out.println("id:" + id + "  Name:" + name + "  email:" + email);
            }
            rs.close();
            st.close();
            conn.close();
            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
