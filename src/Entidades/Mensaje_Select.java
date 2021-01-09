/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;

/**
 *
 * @author Daniela 🌻
 */
public class Mensaje_Select {
    public String codigo;
    public ArrayList<ArrayList<String>> data;
    public String msgError;

    public Mensaje_Select(String codigo, ArrayList<ArrayList<String>> data, String msgError) {
        this.codigo = codigo;
        this.data = data;
        this.msgError = msgError;
    }

    public Mensaje_Select() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
}
