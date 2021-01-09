/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaloracle.Conecciones.jTable;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyectofinaloracle.Conecciones.Consultas;
import proyectofinaloracle.Conecciones.ManejarArchivos;

/**
 *
 * @author chris
 */
public class Jtable {

    private final boolean[] editable = {false, true};

    Consultas query= new Consultas();
;

    ManejarArchivos files = new ManejarArchivos();
    ArrayList<String> listaRoles = new ArrayList<String>();
    ArrayList<String> listaPrivilegios = new ArrayList<String>();

    public void visualizar(JTable tabla, String Opcion) {
        tabla.setDefaultRenderer(Object.class, new RenderTable());

        DefaultTableModel dt = new DefaultTableModel(new String[]{Opcion, "Opciones"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        if (Opcion.equals("Roles")) {
            Object fila[] = new Object[2];
            for (String role : query.obtenerTodosRoles()) {
                fila[0] = role;
                fila[1] = false;
                dt.addRow(fila);
            }

        } else if (Opcion.equals("Privilegios")) {
            Object fila[] = new Object[2];
            for (String priv : query.obtenerTodosPrivilegios()) {
                fila[0] = priv;
                fila[1] = false;
                dt.addRow(fila);
            }
        }

        tabla.setModel(dt);

    }

    public void visualizarEditar(JTable tabla, String Opcion, String user) {
        tabla.setDefaultRenderer(Object.class, new RenderTable());
        DefaultTableModel dt = new DefaultTableModel(new String[]{Opcion, "Opciones"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        if (Opcion.equals("Roles")) {
            ArrayList<String> listRole = query.obtenerRolesxUsuario(user);
            Object fila[] = new Object[2];
            for (String role : query.obtenerTodosRoles()) {
                fila[0] = role;
                if (listRole.contains(role)) {
                    fila[1] = true;
                } else {
                    fila[1] = false;
                }
                dt.addRow(fila);
            }

        } else {
            ArrayList<String> listPriv = query.obtenerPrivilegiosxUsuario(user);
            Object fila[] = new Object[2];
            for (String priv : query.obtenerTodosPrivilegios()) {
                fila[0] = priv;
                if (listPriv.contains(priv)) {
                    fila[1] = true;
                } else {
                    fila[1] = false;
                }
                dt.addRow(fila);
            }
        }

        tabla.setModel(dt);

    }

    public void visualizarPrivilegiosxRol(JTable tabla, String rol) {
        tabla.setDefaultRenderer(Object.class, new RenderTable());

        DefaultTableModel dt = new DefaultTableModel(new String[]{"Privilegios", "Opciones"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        ArrayList<String> listPriv = query.obtenerPrivilegiosxRoles(rol);
        Object fila[] = new Object[2];
        for (String role : query.obtenerTodosPrivilegios()) {
            fila[0] = role;
            if (listPriv.contains(role)) {
                fila[1] = true;
            } else {
                fila[1] = false;
            }
            dt.addRow(fila);
        }

        tabla.setModel(dt);

    }

    public void limpiarTabla(JTable tabla) {
        tabla.setDefaultRenderer(Object.class, new RenderTable());

        DefaultTableModel dt = new DefaultTableModel(new String[]{"Privilegios", "Seleccione"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };
        Object fila[] = new Object[2];

        for (int i = 0; i < 6; i++) {
            dt.addRow(fila);
        }

        tabla.setModel(dt);

    }

}
