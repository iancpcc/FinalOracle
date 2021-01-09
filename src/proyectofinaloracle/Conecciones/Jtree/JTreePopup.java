/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaloracle.Conecciones.Jtree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import proyectofinaloracle.Conecciones.Conexion;
import proyectofinaloracle.Conecciones.Consultas;
import proyectofinaloracle.UIGestor;
import proyectofinaloracle.UIRol;
import proyectofinaloracle.UIUsuarios;

/**
 *
 * @author chris
 */
public class JTreePopup extends JPopupMenu {

    public JTreePopup(Object obj) {

        JMenuItem menuItemAdd = new JMenuItem("Crear Usuario");
        JMenuItem menuItemUpdate = new JMenuItem("Editar usuario");
        JMenuItem menuItemDelete = new JMenuItem("Eliminar usuario");
        JMenuItem menuItemRol = new JMenuItem("Crear Roles");
       
        add(menuItemUpdate);
        addSeparator();
        add(menuItemAdd);
        add(menuItemRol);
        addSeparator();
        add(menuItemDelete);

        menuItemAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIUsuarios uiUser = new UIUsuarios();
                uiUser.setVisible(true);
            }
        });

        menuItemUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIUsuarios.accion = "editar";
                UIUsuarios.nomUsuario = obj.toString();
                UIUsuarios uiUser = new UIUsuarios();
                UIUsuarios.txtUsuario.setEnabled(false);
                UIUsuarios.BtnCrear.setText("Actualizar");
                uiUser.setVisible(true);
            }
        });
        
        menuItemRol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIRol rol = new UIRol();
                rol.setVisible(true);
            }
        });

        menuItemDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "Aviso");
                if (res == 0) {
                    Consultas query = new Consultas();
                    JArbol tree = new JArbol();
                    try {
                        int rs = query.eliminarUsuario(obj.toString());
                        if (rs == 0) {
                            JOptionPane.showMessageDialog(null, "Usuario Elimininado", "SQL", JOptionPane.INFORMATION_MESSAGE);
                            UIGestor.Arbol.setModel(tree.crearNodosxNombre(Conexion.or));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.toString(), "SQL", JOptionPane.INFORMATION_MESSAGE);

                    }
                }

            }
        });
    }
}
