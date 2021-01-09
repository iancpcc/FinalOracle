/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaloracle.Conecciones;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author chris
 */
public class Oracle {
    public  String usuario;
    public String host;
    public  String puerto;
    public  String SID;
    public  String contrasenia;
    public  String nombreConn;
   
    
    public Oracle(){}
    
    public Oracle(String usuario,String host, String puerto, String SID, String contrasenia,String nombreConn){
        
        this.usuario=usuario;
        this.host=host;
        this.puerto=puerto;
        this.SID=SID;
        this.contrasenia=contrasenia;
        this.nombreConn=nombreConn;
                      
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombreConn() {
        return nombreConn;
    }

    public void setNombreConn(String nombreConn) {
        this.nombreConn = nombreConn;
    }
    
    
    
    
    
}




