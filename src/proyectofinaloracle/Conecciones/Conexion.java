/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaloracle.Conecciones;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Conexion {

    Connection connect;

    public static boolean conexion;
    public static Oracle or=null;

    public Connection obtenerConecciones(String host, String puerto, String SID, String usuario, String contBD) {
        connect = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@"
                    + host + ":" + puerto + ":" + SID;
            connect = DriverManager.getConnection(BaseDeDatos, usuario, contBD);
            conexion = true;
        } catch (SQLException | ClassNotFoundException ex) {
            conexion = false;
            JOptionPane.showMessageDialog(null, ex, "SQL", JOptionPane.INFORMATION_MESSAGE);
        }
        return connect;
    }

    public Connection connecion() {
        connect = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@" + or.host+ ":" + or.puerto + ":" + or.SID;
            connect = DriverManager.getConnection(BaseDeDatos, or.usuario, or.contrasenia);
        } catch (ClassNotFoundException | SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex, "SQL", JOptionPane.INFORMATION_MESSAGE);
        }
        return connect;
    }
public Connection getConnection(){
    return connect;
}
    public boolean testConexion(Oracle orc) {
        obtenerConecciones(orc.host, orc.puerto, orc.SID, orc.usuario, orc.contrasenia);
        if (conexion) {
            return true;
        }
        return false;

    }

}
