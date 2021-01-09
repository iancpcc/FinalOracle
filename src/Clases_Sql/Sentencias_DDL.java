/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import proyectofinaloracle.Conecciones.Conexion;
import Entidades.Mensaje;
import proyectofinaloracle.Conecciones.Oracle;
/**
 *
 * @author Daniela 🌻
 */
public class Sentencias_DDL {
    Conexion conexion;
    Connection con;
    PreparedStatement ps;
    Oracle or = new Oracle();
    

    public Sentencias_DDL() throws ClassNotFoundException {
        this.conexion=new Conexion();
        
    }
    
    public Mensaje createStructure(String sql) {

        Mensaje msg;

        try {
            con = conexion.connecion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return null;
        } catch (SQLException e) {
            msg = new Mensaje("1", e.getMessage());
            return msg;
        }

    }
    
    public Mensaje dropStructure(String sql) {

        Mensaje msg;

        try {
            con = conexion.connecion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return null;
        } catch (SQLException e) {
            msg = new Mensaje("1", e.getMessage());
            return msg;
        }

    }
    
    public Mensaje alterStructure(String sql) {

        Mensaje msg;

        try {
            con = conexion.connecion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return null;
        } catch (SQLException e) {
            msg = new Mensaje("1", e.getMessage());
            return msg;
        }

    }
}
