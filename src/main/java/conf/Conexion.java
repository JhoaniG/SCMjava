/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jhoan {}
 */
public class Conexion {
    private final String url = "jdbc:mysql://localhost:3306/scm";
    private final String user = "root";
    private final String pass = "";

    public Connection Conexion() throws ClassNotFoundException {
        Connection conn = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion exitosa");

        } catch (SQLException e) {
            System.out.println("ERROR AL CONECTAR CON la base de datos");
            e.printStackTrace();

        }

        return conn;

    }

}
