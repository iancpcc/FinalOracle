/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaloracle;

import Clases_Sql.Sentencias_DDL;
import Clases_Sql.Sentencias_DML;
import Entidades.Mensaje;
import Entidades.Mensaje_Select;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniela 🌻
 */
public class UIConsola extends javax.swing.JFrame {

    /**
     * Creates new form UIConsola
     */
    public UIConsola() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imgs/sqlConsola.png"));

        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigos = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consola SQL");
        setIconImage(getIconImage());
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 0, 11))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N

        txtCodigos.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        txtCodigos.setName("txtCodigos"); // NOI18N
        jScrollPane1.setViewportView(txtCodigos);
        txtCodigos.getAccessibleContext().setAccessibleName("txtCodigos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ejecutar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N

        jScrollPane2.setViewportView(txtSalida);

        jTabbedPane1.addTab("Mensaje", jScrollPane2);

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Datos"
            }
        ));
        jScrollPane3.setViewportView(tblDatos);

        jTabbedPane1.addTab("Datos", jScrollPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (txtCodigos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo Input No Válido.", "Error.", JOptionPane.ERROR_MESSAGE);
            txtCodigos.requestFocus();
        } else {
            try {
                String sql = txtCodigos.getText();
                verificarSentencia(sql);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void verificarSentencia(String sql) throws ClassNotFoundException {
        String[] textoSQL = sql.split(";");
        ArrayList<String> sentencias = new ArrayList<>();

        //Verificar sentencias validas;
        for (String sentencia : textoSQL) {
            if (!sentencia.contains("--")) {
                sentencias.add(sentencia);
            }
        }

        //Analizar sentencias a ejecutar 
        analizarSentencia(sentencias);

    }

    public void analizarSentencia(ArrayList<String> sentencias) throws ClassNotFoundException {

        Sentencias_DDL ddl;
        Sentencias_DML dml;
        Mensaje msg;

        for (String sentencia : sentencias) {

            String sentenciaAux = sentencia.toUpperCase();
            String[] analizador = sentenciaAux.split(" ");

            analizador[0] = analizador[0].replace("\n", "");
            analizador[0] = analizador[0].replace("\r", "");

            switch (analizador[0]) {

                case "INSERT":
                    dml = new Sentencias_DML();
                    msg = dml.insertData(sentencia);
                    if (msg != null) {
                        txtSalida.setText(txtSalida.getText() + msg.mensaje + "\n");
                    } else {
                        txtSalida.setText(txtSalida.getText() + "INSERT Realizado Correctamente!!!" + "\n");
                    }
                    break;
                case "UPDATE":
                    dml = new Sentencias_DML();
                    msg = dml.updateData(sentencia);
                    if (msg != null) {
                        txtSalida.setText(txtSalida.getText() + msg.mensaje + "\n");
                    } else {
                        txtSalida.setText(txtSalida.getText() + "UPDATE Realizado Correctamente!!!" + "\n");
                    }
                    break;
                case "DELETE":
                    dml = new Sentencias_DML();
                    msg = dml.deleteData(sentencia);
                    if (msg != null) {
                        txtSalida.setText(txtSalida.getText() + msg.mensaje + "\n");
                    } else {
                        txtSalida.setText(txtSalida.getText() + "DELETE Realizado Correctamente!!!" + "\n");
                    }
                    break;
                case "CREATE":
                    ddl = new Sentencias_DDL();
                    msg = ddl.createStructure(sentencia);
                    if (msg != null) {
                        txtSalida.setText(txtSalida.getText() + msg.mensaje + "\n");
                    } else {
                        txtSalida.setText(txtSalida.getText() + "CREATE Realizado Correctamente!!!" + "\n");
                    }
                    break;
                case "DROP":
                    ddl = new Sentencias_DDL();
                    msg = ddl.dropStructure(sentencia);
                    if (msg != null) {
                        txtSalida.setText(txtSalida.getText() + msg.mensaje + "\n");
                    } else {
                        txtSalida.setText(txtSalida.getText() + "DROP Realizado Correctamente!!!" + "\n");
                    }
                    break;
                case "ALTER":
                    ddl = new Sentencias_DDL();
                    msg = ddl.alterStructure(sentencia);
                    if (msg != null) {
                        txtSalida.setText(txtSalida.getText() + msg.mensaje + "\n");
                    } else {
                        txtSalida.setText(txtSalida.getText() + "ALTER Realizado Correctamente!!!" + "\n");
                    }
                    break;
                case "SELECT":
                    dml = new Sentencias_DML();
                    if (sentencia.contains("*") && !sentencia.toUpperCase().contains("COUNT")) {
                        Mensaje_Select data;
                        sentencia = sentencia.toUpperCase();
                        String[] table = sentencia.split("FROM");
                        if (sentencia.contains("WHERE")) {
                            table = table[1].split("WHERE");
                            data = dml.selectAll(table[0].trim(), sentencia);
                        } else {
                            data = dml.selectAll(table[table.length - 1].trim(), sentencia);
                        }
                        if (data.getMsgError() != null) {
                            txtSalida.setText(txtSalida.getText() + data.getMsgError() + "\n");
                        } else {
                            //String fields : data.data.get(0)
                            DefaultTableModel modelo = new DefaultTableModel();
                            for (String fields : data.data.get(0)) {
                                modelo.addColumn(fields);
                            }

                            for (int i = 1; i < data.data.size(); i++) {
                                modelo.addRow(data.data.get(i).toArray());
                            }

                            tblDatos.setModel(modelo);

                        }
                    } else {
                        dml = new Sentencias_DML();
                        Mensaje_Select data;
                        sentencia = sentencia.toUpperCase();
                        String[] table = sentencia.split("FROM");
                        String[] fields = table[0].replace("SELECT ", "").split(",");
                        data = dml.selectFields(sentencia, fields);

                        if (data.getMsgError() != null) {
                            txtSalida.setText(txtSalida.getText() + data.getMsgError() + "\n");
                        } else {

                            DefaultTableModel modelo = new DefaultTableModel();
                            for (String field : data.data.get(0)) {
                                modelo.addColumn(field);
                            }

                            for (int i = 1; i < data.data.size(); i++) {
                                modelo.addRow(data.data.get(i).toArray());
                            }

                            tblDatos.setModel(modelo);

                        }
                    }
                    break;
                default:
                    txtSalida.setText(txtSalida.getText() + "Sentencia SQL No Valida!!!" + "\n");
                    break;
            }

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIConsola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIConsola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIConsola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIConsola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIConsola().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextPane txtCodigos;
    private javax.swing.JTextPane txtSalida;
    // End of variables declaration//GEN-END:variables
}
