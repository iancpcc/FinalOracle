/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaloracle.Conecciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Consultas {

    Conexion cnn = new Conexion();
    Connection con;

//    public Consultas() {
//        con = cnn.connecion();
//    }
    public Connection retornarConeccion() {
        return cnn.connecion();
    }

    public List<String> obtenerTablas(Oracle obj) throws SQLException {
        List<String> listaTables = new ArrayList<String>();

        try {
            con = cnn.obtenerConecciones(obj.host, obj.puerto, obj.SID, obj.usuario, obj.contrasenia);
            PreparedStatement st = con.prepareStatement("SELECT TABLE_NAME FROM USER_TABLES");
//            //Envio los usuarios para obtener las tablas
//            st.setString(1, obj.usuario);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listaTables.add(rs.getString("table_name"));
            }
            con.close();
            return listaTables;
        } catch (SQLException ex) {
            throw ex;
        }

    }

    public List<String> obtenerTablesapace() throws SQLException {
        List<String> listaTablespaces = new ArrayList<String>();

        try {
            con = retornarConeccion();
            PreparedStatement st = con.prepareStatement("SELECT tablespace_name FROM user_tablespaces WHERE tablespace_name <> ?");
            st.setString(1, "TEMP");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listaTablespaces.add(rs.getString("tablespace_name"));
            }
            con.close();
            return listaTablespaces;
        } catch (SQLException ex) {
            throw ex;
        }

    }

    public List<String> obtenerUsuarios(Oracle obj) throws SQLException, ClassNotFoundException {
        List<String> listaUsuarios = new ArrayList<String>();
        try {
            con = cnn.obtenerConecciones(obj.host, obj.puerto, obj.SID, obj.usuario, obj.contrasenia);
            PreparedStatement st = con.prepareStatement("SELECT USERNAME FROM DBA_USERS WHERE USERNAME <> ?");
            //Envio los usuarios para obtener las tablas
            st.setString(1, obj.usuario);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listaUsuarios.add(rs.getString("USERNAME"));
            }
            con.close();
            return listaUsuarios;
        } catch (SQLException ex) {
            throw ex;
        }

    }

    public List<String> obtenerUsuariosSytem() throws SQLException, ClassNotFoundException {
        List<String> listaUsuarios = new ArrayList<String>();
        Oracle obj = new Oracle("SYSTEM", "DESKTOP-T9885UU", "1521", "ORCL", "1804898755.Ian", "SYSTEM");
        String usuario = Conexion.or.usuario;
        try {
            con = cnn.obtenerConecciones(obj.host, obj.puerto, obj.SID, obj.usuario, obj.contrasenia);
            PreparedStatement st = con.prepareStatement("SELECT USERNAME FROM DBA_USERS WHERE USERNAME <> ? ORDER BY USERNAME ASC");
            //Envio los usuarios para obtener las tablas
            st.setString(1, usuario);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listaUsuarios.add(rs.getString("USERNAME"));
            }
            con.close();
            return listaUsuarios;
        } catch (SQLException ex) {
            throw ex;
        }

    }

    public ArrayList<String> obtenerRolesxUsuario(String user) throws SQLException {
        ArrayList<String> listaRoles = new ArrayList<String>();
        try {
            con = retornarConeccion();
            PreparedStatement st = con.prepareStatement("select granted_role from dba_role_privs WHERE GRANTEE=?");
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listaRoles.add(rs.getString("granted_role"));
            }
            con.close();

            return listaRoles;

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public ArrayList<String> obtenerTodosRoles() throws SQLException {
        ArrayList<String> listaRoles = new ArrayList<String>();
        try {
            con = retornarConeccion();
            PreparedStatement st = con.prepareStatement("SELECT DISTINCT ROLE FROM DBA_ROLES ORDER BY ROLE ASC");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listaRoles.add(rs.getString("ROLE"));
            }
            con.close();

            return listaRoles;

        } catch (SQLException e) {
            throw e;
        }
    }

    public ArrayList<String> obtenerPrivilegiosxRoles(String rol) {
        ArrayList<String> listaPriv = new ArrayList<String>();

        try {
            con = retornarConeccion();

            PreparedStatement st = con.prepareStatement("select privilege from dba_sys_privs where grantee = ?");
            st.setString(1, rol);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listaPriv.add(rs.getString("privilege"));
            }
            con.close();

            return listaPriv;

        } catch (Exception e) {
        }
        return listaPriv;
    }

    public int crearRoles(String rol, ArrayList<PrivObj> lista) throws SQLException {
        PreparedStatement st;
        int rs = -1;
        try {
            con = retornarConeccion();
            st = con.prepareStatement("CREATE ROLE " + rol + "");
            rs = st.executeUpdate();
            if (rs == 0) {
                return asignarPrivilegiosaRoles(lista, rol);
            }
            return rs;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int asignarPrivilegiosaRoles(ArrayList<PrivObj> lista, String rol) throws SQLException {
        String sentencia = "";
        int rs = -1;
        con = retornarConeccion();
        try {
            for (PrivObj priv : lista) {
                if (priv.valor) {
                    sentencia = "GRANT " + priv.privilegio + " TO " + rol + " ";
                }
                PreparedStatement st = con.prepareStatement(sentencia);
                rs = st.executeUpdate();
            }
            con.close();
            return rs;

        } catch (SQLException ex) {
            throw ex;
        }

    }

    public int actualizarPrivilegiosaRoles(ArrayList<PrivObj> lista, String rol) throws SQLException {
        String sentencia = "";
        int rs = -1;
        con = retornarConeccion();
        try {
            for (PrivObj priv : lista) {
                if (priv.valor) {
                    sentencia = "GRANT " + priv.privilegio + " TO " + rol + " ";
                } else {
                    sentencia = "REVOKE " + priv.privilegio + " FROM " + rol + " ";
                }
                PreparedStatement st = con.prepareStatement(sentencia);
                rs = st.executeUpdate();
            }
            con.close();
            return rs;

        } catch (SQLException ex) {
            throw ex;
        }

    }
    //select privilege from dba_sys_privs where grantee = 'RESOURCE'

    public ArrayList<String> obtenerPrivilegiosxUsuario(String user) throws SQLException {
        ArrayList<String> listapriv = new ArrayList<String>();
        try {
            con = retornarConeccion();
            PreparedStatement st = con.prepareStatement("SELECT PRIVILEGE FROM DBA_SYS_PRIVS WHERE GRANTEE =? ");
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listapriv.add(rs.getString("PRIVILEGE"));
            }
            con.close();

            return listapriv;

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public ArrayList<String> obtenerTodosPrivilegios() {
        ArrayList<String> listapriv = new ArrayList<String>();

        try {
            con = retornarConeccion();
            PreparedStatement st = con.prepareStatement("SELECT DISTINCT PRIVILEGE from DBA_SYS_PRIVS ORDER BY PRIVILEGE ASC");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listapriv.add(rs.getString("PRIVILEGE"));
            }
            con.close();

            return listapriv;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "SQL", JOptionPane.INFORMATION_MESSAGE);
        }
        return listapriv;
    }

    public boolean crearUsuario(String user, String pass) throws SQLException {
        try {
            con = retornarConeccion();
            PreparedStatement st = con.prepareStatement("CREATE USER " + user + " IDENTIFIED BY " + pass + "");
            ResultSet rs = st.executeQuery();
            con.close();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
            throw ex;
        }

    }
// CREATE USER ELVIS IDENTIFIED BY 1234 DEFAULT TABLESPACE ACADEMO;

    public boolean crearUsuarioTablespace(String user, String pass, String tablespace) throws SQLException {
        try {
            con = retornarConeccion();
            int rs = -1;
            PreparedStatement st = con.prepareStatement("CREATE USER " + user + " IDENTIFIED BY " + pass + " DEFAULT TABLESPACE " + tablespace + "");
             rs = st.executeUpdate();
            if (rs >=0) {
                 return true;   
            }
            con.close();
            return false;

        } catch (SQLException ex) {
            throw ex;
        }

    }

    public boolean actualizarUsuario(String user, String pass) throws SQLException {
        try {
            con = retornarConeccion();
            PreparedStatement st = con.prepareStatement("ALTER USER " + user + " IDENTIFIED BY " + pass + "");
            ResultSet rs = st.executeQuery();
            con.close();
            return true;

        } catch (SQLException ex) {
            throw ex;
        }

    }

    public int crearEditarRolesxUsuario(ArrayList<RolesObj> list, String user) throws SQLException {
        try {
            con = retornarConeccion();

            String sentencia = "";
            int rs = -1;
            for (RolesObj rol : list) {
                if (rol.valor) {
                    sentencia = "GRANT " + rol.rol + " TO " + user + " ";
                } else {
                    sentencia = "REVOKE " + rol.rol + " FROM " + user + " ";
                }
                PreparedStatement st = con.prepareStatement(sentencia);
                rs = st.executeUpdate();
            }
            con.close();
            return rs;
        } catch (SQLException ex) {
            throw ex;

        }
    }

    public int crearEditarPrivilegiosxUsuario(ArrayList<PrivObj> list, String user) throws SQLException {
        try {
            con = retornarConeccion();
            String sentencia = "";
            int rs = -1;
            for (PrivObj priv : list) {
                if (priv.valor) {
                    sentencia = "GRANT " + priv.privilegio + " TO " + user + " ";
                } else {
                    sentencia = "REVOKE " + priv.privilegio + " FROM " + user + " ";
                }
                PreparedStatement st = con.prepareStatement(sentencia);
                rs = st.executeUpdate();
            }
            con.close();
            return rs;
        } catch (SQLException ex) {
            throw ex;

        }
    }

    public int eliminarUsuario(String user) throws SQLException {
        try {
            con = retornarConeccion();
            int rs = -1;
            PreparedStatement st = con.prepareStatement("DROP USER " + user + "");
            rs = st.executeUpdate();
            con.close();
            return rs;

        } catch (SQLException ex) {
            throw ex;
        }
    }
}
