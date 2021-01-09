/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaloracle.Conecciones.Jtree;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.text.TabableView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import proyectofinaloracle.Conecciones.ConeccionesModel;
import proyectofinaloracle.Conecciones.Consultas;
import proyectofinaloracle.Conecciones.ManejarArchivos;
import proyectofinaloracle.Conecciones.Oracle;
import proyectofinaloracle.Conecciones.TablaModel;
import proyectofinaloracle.Conecciones.UsuariosModel;

/**
 *
 * @author chris
 */
public class JArbol extends DefaultTreeCellRenderer {

    private final ImageIcon iconbdd;
    private final ImageIcon icontablas;
    private final ImageIcon iconcolumnas;
    private final ImageIcon connection;
    private final ImageIcon folder;
        private final ImageIcon user;

    private JLabel label;

    public JArbol() {
        iconbdd = new ImageIcon("src\\imgs\\db.png");
        icontablas = new ImageIcon("src\\imgs\\table.png");
        iconcolumnas = new ImageIcon("src\\imgs\\columns.png");
        connection = new ImageIcon("src\\imgs\\connect.png");
        folder = new ImageIcon("src\\imgs\\folder.png");
        user=new ImageIcon("src\\imgs\\user.png");
        label = new JLabel();
    }

    DefaultTreeModel modelo;
    DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Conexiones");
    ManejarArchivos mc = new ManejarArchivos();
    Consultas query = new Consultas();
    ;
    List<String> tablas = new ArrayList();
    List<String> usuarios = new ArrayList();
    Oracle orc;

    public DefaultTreeModel crearNodos() throws Exception {
        clear();
        List<Oracle> conexiones = mc.obtenerConecciones();
        for (Oracle obj : conexiones) {
            DefaultMutableTreeNode nodos = new DefaultMutableTreeNode(new ConeccionesModel(obj.nombreConn.toUpperCase()));
            raiz.add(nodos);
        }
        modelo = new DefaultTreeModel(raiz);
        return modelo;
    }

    public DefaultTreeModel crearNodosxNombre(Oracle obj) throws SQLException, ClassNotFoundException  {
         clear();
        List<Oracle> conexiones = mc.obtenerConecciones();
        try {

            for (Oracle con : conexiones) {
                DefaultMutableTreeNode nodos = new DefaultMutableTreeNode(new ConeccionesModel(con.nombreConn.toUpperCase()));
                DefaultMutableTreeNode nodotablas = new DefaultMutableTreeNode("Tablas");
                DefaultMutableTreeNode nodousuarios = new DefaultMutableTreeNode("Usuarios");

                if (con.nombreConn.equals(obj.nombreConn)) {
                    tablas = query.obtenerTablas(obj);
                    usuarios = query.obtenerUsuarios(obj);

                    ArrayList<TablaModel> tablaList = new ArrayList();
                    ArrayList<UsuariosModel> usuariosList = new ArrayList();

                    for (String table : tablas) {
                        tablaList.add(new TablaModel(table, icontablas));
                    }

                    for (String user : usuarios) {
                        usuariosList.add(new UsuariosModel(user, folder));

                    }

                    ///
                    for (TablaModel tablaModel : tablaList) {

                        DefaultMutableTreeNode subnodos = new DefaultMutableTreeNode(tablaModel);
                        nodotablas.add(subnodos);
                    }

                    for (UsuariosModel user : usuariosList) {
                        DefaultMutableTreeNode subnodos = new DefaultMutableTreeNode(user);
                        nodousuarios.add(subnodos);
                    }
                    nodos.add(nodotablas);
                    nodos.add(nodousuarios);
                }

                raiz.add(nodos);
            }

            modelo = new DefaultTreeModel(raiz);
            return modelo;
        } catch (SQLException e) {
            System.out.println("desde crear nodos");
            throw e;
        }
    }

    public void clear() {
        raiz.removeAllChildren();
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        if (node.isRoot()) {
            setIcon(connection);
            setText("Conexiones");
           
        }
        else if(o instanceof String){
            setIcon(folder);
             setText(o.toString());
        }
        else if(o instanceof ConeccionesModel){
            ConeccionesModel cnModel= (ConeccionesModel)o;
            setIcon(iconbdd);
            setText(cnModel.Coneccion);
        }
        else if (o instanceof TablaModel) {
            TablaModel tbModel = (TablaModel) o;
            setIcon(icontablas);
            setText(tbModel.tabla);
        } else if (o instanceof UsuariosModel) {

           UsuariosModel usModel = (UsuariosModel) o;
           setIcon(user);
           setText(usModel.tabla);
        }
//        
        return this;
    }

    public static void setTreeExpandedState(JTree tree, boolean expanded, String rama) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getModel().getRoot();
        setNodeExpandedState(tree, node, expanded, rama);
    }

    public static void setNodeExpandedState(JTree tree, DefaultMutableTreeNode node, boolean expanded, String rama) {
        ArrayList<DefaultMutableTreeNode> list = Collections.list(node.children());
        for (DefaultMutableTreeNode treeNode : list) {

            setNodeExpandedState(tree, treeNode, expanded, rama);
        }

        if (!expanded && node.isRoot()) {
            return;
        }
        TreePath path = new TreePath(node.getPath());
        if (expanded) {
            tree.expandPath(path);
        } else {
            tree.collapsePath(path);
        }
    }

}
