/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import java.sql.*;
import com.prjmanutencao.dal.ModuloConexao;
//import static com.prjmanutencao.telas.TelaPrevMensal.id;
import static com.prjmanutencao.telas.TelaAcesso.nome;
import java.awt.Color;
import java.awt.HeadlessException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fábio
 */
public class TelaLogin extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String ipDaMaquina = null;
    String nomeDaMaquina = null;

    public static String id = null;
    public static String tip = null;

    public void logar() {
        ip_maqui();
        String senha = null;
        String sql = "select * from usuarios where login_usuario=? and senha_usuario=? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            senha = new String(txtSenha.getPassword());
            if (jclSen.isSelected()) {
                senha = (txtSenMos.getText());
            }

            pst.setString(2, senha);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getString(1);
                nome = rs.getString(2);
                String perfil = rs.getString(3);
                tip = rs.getString(3);
                acesso();
//                ip_maqui();
//                System.out.println(id);
                if (perfil.equals("Superadmin")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.menCad.setEnabled(true);
                    TelaPrincipal.menPreSemEnc.setEnabled(true);
                    TelaPrincipal.menRel.setEnabled(true);
                    TelaPrincipal.menPreAbr.setEnabled(true);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    TelaPrincipal.lblUsuario.setForeground(Color.CYAN);
                    TelaPrincipal.menOpcAce.setEnabled(true);
                    this.dispose();
                    conexao.close();

                }
                if (perfil.equals("Admin")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.menCad.setEnabled(true);
                    TelaPrincipal.menPreSemEnc.setEnabled(true);
                    TelaPrincipal.menPreAbr.setEnabled(true);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    TelaPrincipal.lblUsuario.setForeground(Color.BLUE);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    this.dispose();
                    conexao.close();

                }
                if (perfil.equals("User")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    TelaPrincipal.menPreSemEnc.setEnabled(true);
                    principal.setVisible(true);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    TelaPrincipal.lblUsuario.setForeground(Color.red);
                    this.dispose();
                    conexao.close();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido!");

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {
        initComponents();

        conexao = ModuloConexao.conector();
        //System.out.println(conexao);
        if (conexao != null) {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/dbok.png")));
        } else {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/dberro.png")));
        }
    }

    private void ip_maqui() {

        try {
            //pegamos o ip da maquina.
            ipDaMaquina = InetAddress.getLocalHost().getHostAddress();
            //nome da maquina.
            nomeDaMaquina = InetAddress.getLocalHost().getHostName();

        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void acesso() {

        String data;

        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);
        data = format;

        String sql = "insert into acesso (ip_maqui, nome_maqui, iduser, data_entr) values (?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, ipDaMaquina);
            pst.setString(2, nomeDaMaquina);
            pst.setString(3, id);
            pst.setString(4, data);
            int Adicionar = pst.executeUpdate();
            if (Adicionar > 0) {
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSenha = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jclSen = new javax.swing.JCheckBox();
        txtSenMos = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema - Login");
        setResizable(false);
        getContentPane().setLayout(null);

        txtSenha.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSenha.setText("12345");
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(270, 240, 250, 30);

        btnLogin.setBackground(new java.awt.Color(191, 191, 191));
        btnLogin.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(51, 51, 255));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/55827_lock_padlock_private_icon.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(390, 300, 130, 40);

        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/dberro.png"))); // NOI18N
        getContentPane().add(lblStatus);
        lblStatus.setBounds(340, 300, 40, 40);

        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setText("fabio@silva");
        txtUsuario.setName(""); // NOI18N
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(270, 170, 250, 30);
        txtUsuario.getAccessibleContext().setAccessibleName("");

        jclSen.setOpaque(false);
        jclSen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jclSenActionPerformed(evt);
            }
        });
        getContentPane().add(jclSen);
        jclSen.setBounds(270, 280, 20, 20);

        txtSenMos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtSenMos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtSenMos);
        txtSenMos.setBounds(270, 240, 250, 30);

        jLabel1.setText("Mostrar Senha");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(292, 284, 90, 14);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/2.jpg"))); // NOI18N
        jLabel2.setDoubleBuffered(true);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 600, 400);

        setSize(new java.awt.Dimension(600, 400));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        logar();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void jclSenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jclSenActionPerformed
        // TODO add your handling code here:
//        String sql = "select * from usuarios where login_usuario=? and senha_usuario=? ";

        try {
//            pst = conexao.prepareStatement(sql);

            if (jclSen.isSelected()) {
                String senha = new String(txtSenha.getPassword());
                txtSenMos.setText(senha);

                txtSenha.setVisible(false);
                txtSenMos.setVisible(true);
            } else {
                String senha = (txtSenMos.getText());
                txtSenha.setText(senha);

                txtSenha.setVisible(true);
                txtSenMos.setVisible(false);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jclSenActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox jclSen;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextField txtSenMos;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
