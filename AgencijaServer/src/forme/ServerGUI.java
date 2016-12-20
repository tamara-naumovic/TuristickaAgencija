/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import baza.DbBroker;
import config.Config;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;
import kontroler.Kontroler;

/**
 *
 * @author Tami
 */
public class ServerGUI extends javax.swing.JFrame {

    /**
     * Creates new form ServerGUI
     */
    public ServerGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnPokreniServer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtxtVreme = new javax.swing.JTextField();
        jbtnStopServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbtnPokreniServer.setText("Pokreni server");
        jbtnPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPokreniServerActionPerformed(evt);
            }
        });

        jLabel1.setText("Vreme pokretanja servera");

        jtxtVreme.setEditable(false);

        jbtnStopServer.setText("Zaustavi server");
        jbtnStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnStopServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtnStopServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnPokreniServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jtxtVreme, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnPokreniServer)
                    .addComponent(jtxtVreme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbtnStopServer)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPokreniServerActionPerformed
        Config.getInstance().setPort(9999);
        Config.getInstance().setDbUrl("jdbc:mysql://localhost/prosoft_turisticka");
        Config.getInstance().setUsername("root");
        Config.getInstance().setPassword("");
        if (DbBroker.getInstance().connectToMySqlDatabase()) {
                Kontroler.getInstance().startServer(Config.getInstance().getPort());
                System.out.println("Server pokrenut, osluskivanje na portu " + Config.getInstance().getPort() + "\n");
                jbtnPokreniServer.setEnabled(false);
                jbtnStopServer.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Konekcija nije moguca", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        
        jtxtVreme.setText(""+ sdf.format(date));
    }//GEN-LAST:event_jbtnPokreniServerActionPerformed

    private void jbtnStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnStopServerActionPerformed
        try {
            Kontroler.getInstance().stopServer();
            System.out.println("Server stopiran.\n");
            jbtnPokreniServer.setEnabled(true);
            jbtnStopServer.setEnabled(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Serverski soket ne moze da se zatvori!");
        }
    }//GEN-LAST:event_jbtnStopServerActionPerformed
public Properties getConfigFile() throws IOException {
        try {
            Properties prop = new Properties();
            InputStream in = new FileInputStream("prop.properties");
            prop.load(in);
            return prop;
        } catch (Exception e) {
            throw new IOException("There is no config file: " + "props.properties");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbtnPokreniServer;
    private javax.swing.JButton jbtnStopServer;
    private javax.swing.JTextField jtxtVreme;
    // End of variables declaration//GEN-END:variables
}
