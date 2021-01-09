/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Sql;

/**
 *
 * @author Daniela 🌻
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import proyectofinaloracle.Conecciones.Conexion;
import Entidades.Mensaje;
import Entidades.Mensaje_Select;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import proyectofinaloracle.Conecciones.Oracle;

public class Sentencias_DML {

    Conexion conexion;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Sentencias_DML() throws ClassNotFoundException {
        this.conexion = new Conexion();
    }

    public Mensaje insertData(String sql) {

        Mensaje msg;

        try {
            con = conexion.connecion();
            //  con = conexion.obtenerConecciones(or.host, or.puerto, or.SID, or.usuario, or.contrasenia);
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return null;
        } catch (SQLException e) {
            msg = new Mensaje("1", e.getMessage());
            return msg;
        }

    }

    public Mensaje deleteData(String sql) {

        Mensaje msg;

        try {
            con = conexion.connecion();
            //con = conexion.obtenerConecciones(or.host, or.puerto, or.SID, or.usuario, or.contrasenia);
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return null;
        } catch (SQLException e) {
            msg = new Mensaje("1", e.getMessage());
            return msg;
        }

    }

    public Mensaje updateData(String sql) {

        Mensaje msg;

        try {
            con = conexion.connecion();
            //  con = conexion.obtenerConecciones(or.host, or.puerto, or.SID, or.usuario, or.contrasenia);
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return null;
        } catch (SQLException e) {
            msg = new Mensaje("1", e.getMessage());
            return msg;
        }

    }

    public Mensaje_Select selectAll(String table, String sqlRequest) {

        String sql = "SELECT COUNT(*) as cantidad"
                + " FROM ALL_TAB_COLUMNS "
                + "WHERE table_name = ?";

        ArrayList<ArrayList<String>> filas = new ArrayList();
        Mensaje_Select msg;

        try {
            //Obtener el número de columnas de la tabla
            String Cantidad = "";
            ArrayList<String> columnas = new ArrayList();
            con = conexion.connecion();

            // con = conexion.obtenerConecciones(or.host, or.puerto="1521", or.SID, or.usuario, or.contrasenia);
            ps = con.prepareStatement(sql);
            ps.setString(1, table);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cantidad = rs.getString("CANTIDAD");
            }
            //Obtener los nombres de las columnas
            sql = "SELECT COLUMN_NAME\n"
                    + "FROM ALL_TAB_COLUMNS \n"
                    + "WHERE table_name = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, table);
            rs = ps.executeQuery();
            while (rs.next()) {
                columnas.add(rs.getString("COLUMN_NAME"));
            }
            filas.add(columnas);

            //Obtener los datos de la tabla
            sql = sqlRequest;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                columnas = new ArrayList();
                for (int i = 0; i < Integer.valueOf(Cantidad); i++) {
                    columnas.add(rs.getString(filas.get(0).get(i)));
                }
                filas.add(columnas);
            }

            msg = new Mensaje_Select("0", filas, null);
            return msg;

        } catch (SQLException e) {

            msg = new Mensaje_Select("1", null, e.getMessage());
            return msg;

        }

    }

    public Mensaje_Select selectFields(String sqlRequest, String[] fields) {

        ArrayList<ArrayList<String>> filas = new ArrayList();
        Mensaje_Select msg;

        try {
            ArrayList<String> columnas = new ArrayList();
            //Obtener los campos de la tabla
            columnas.addAll(Arrays.asList(fields));
            filas.add(columnas);
            //Obtener los datos de la tabla
            String sql = sqlRequest;
            //  
            //con = conexion.obtenerConecciones(or.host, or.puerto="1521", or.SID, or.usuario, or.contrasenia);
            con = conexion.connecion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                columnas = new ArrayList();
                for (int i = 1; i < fields.length + 1; i++) {
                    columnas.add(rs.getString(i));
                }
                filas.add(columnas);
            }

            msg = new Mensaje_Select("0", filas, null);
            return msg;

        } catch (SQLException e) {

            msg = new Mensaje_Select("1", null, e.getMessage());
            return msg;

        }

    }
}
