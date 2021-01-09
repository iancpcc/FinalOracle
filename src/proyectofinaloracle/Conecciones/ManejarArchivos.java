/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaloracle.Conecciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class ManejarArchivos {

    private ArrayList<Oracle> conexiones = new ArrayList<>();
    private ArrayList<String> roles = new ArrayList<>();
    private ArrayList<String> privilegios = new ArrayList<>();

    private ArrayList<Oracle> tablas = new ArrayList<>();
    Consultas query;

// private  DefaultTreeModel modeloArbol;
    public boolean insertarConneccion(Oracle orc) throws Exception {
        obtenerConecciones();
        query = new Consultas();
        boolean contiene = false;

        try {
            for (Oracle obj : conexiones) {
                if (orc.nombreConn.equals(obj.nombreConn)) {
                    contiene = true;
                    break;
                }
            }

            if (!contiene) {
                conexiones.add(orc);
                query.obtenerTablas(orc);
            }
            return contiene;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Oracle> obtenerConecciones() {
        leerConneciones();
        return conexiones;
    }

    public void guardarConfiguracion(Oracle orc) {

        try {
            File f = new File("C:\\ConnecionesOracle\\usuarios.txt");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(f, true), "UTF8"));

            bw.write(cadenaPropiedades(orc));//escribimos en el archivo
            File.listRoots();
            bw.close();
            //JOptionPane.showMessageDialog(null, "Configuración guardada");
        } catch (IOException e) {
        };
    }

    public String cadenaPropiedades(Oracle orc) {
        return orc.host + "," + orc.usuario + "," + orc.contrasenia + "," + orc.puerto + ","
                + orc.SID + "," + orc.nombreConn + "\n";
    }

    public void leerConneciones() {
        conexiones.clear();
        File f = new File("C:\\ConnecionesOracle\\usuarios.txt");
        BufferedReader entrada;
        try {
            entrada = new BufferedReader(new FileReader(f));
            while (entrada.ready()) {
                String datos = entrada.readLine();
                String respuesta[] = datos.split(",");
                Oracle orc = new Oracle(respuesta[1], respuesta[0], respuesta[3], respuesta[4], respuesta[2], respuesta[5]);
                conexiones.add(orc);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrio un error al leer el archivo C:/ConnecionesOracle/usuarios.txt", "SQL", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public Oracle obtenerConeccionxNombre(String nomConexion) {
        File f = new File("C:\\ConnecionesOracle\\usuarios.txt");
        BufferedReader entrada;
        Oracle or= new Oracle();
        try {
            entrada = new BufferedReader(new FileReader(f));
            while (entrada.ready()) {
                String datos = entrada.readLine();
                String respuesta[] = datos.split(",");
                if (respuesta[5].toUpperCase().equals(nomConexion)) {
                    or= new Oracle(respuesta[1], respuesta[0], respuesta[3], respuesta[4], respuesta[2], respuesta[5]);
                    return or;
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrio un error al leer el archivo C:/ConnecionesOracle/usuarios.txt", "SQL", JOptionPane.INFORMATION_MESSAGE);

        }
        return new Oracle();
    }

}
