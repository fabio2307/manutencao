/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import com.prjmanutencao.dal.ModuloConexao;
//import com.prjmanutencao.telas.TelaOs;
import static com.prjmanutencao.telas.TelaUsuarios.ent2;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import static com.prjmanutencao.telas.TelaEletricaMen.lblFotEltMen;
import static com.prjmanutencao.telas.TelaUsuarios.txtNomDir;
import static com.prjmanutencao.telas.TelaUsuarios.lblFotUsu;
import static com.prjmanutencao.telas.TelaUsuarios.btnAltFot;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Fábio
 */
public class TelaWebCamUsu extends javax.swing.JFrame {

    private Dimension dimensao_defaul;
    private Webcam WEBCAM;
    boolean executando = true;
    String Imagem;

//    public String dir;
    public static byte[] BYTES_IMAGEM2 = null;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaWebCam
     */
    public TelaWebCamUsu() {
        initComponents();
        conexao = ModuloConexao.conector();
        btnCapCam.setEnabled(false);
        btnParCam.setEnabled(false);
        Iniciar();

    }

    private void iniciar_camera() {

        new Thread() {
            public void run() {
                executando = true;
                btnIniCam.setEnabled(false);
                lblImaCam.setText("Iniciando...");
                WEBCAM.open();
                lblImaCam.setText("");
                Inicializa_video();
            }
        }.start();
        btnCapCam.setEnabled(true);
        btnParCam.setEnabled(true);
    }

    private void capturar_imagem() {

//        String valor = null;
        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;
        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 720, Nova_Altura = 480; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtNomDir.setText(Integer.toString(valor));
            
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            ent2 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
//            ent2 = Imagem + valor + ".jpg";
            BYTES_IMAGEM2 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM2);
            icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 200));
            lblFotUsu.setIcon(icon);

//            lblFotEltMen.setIcon(icon);
//            txtNomFot.setText(Integer.toString(valor));
//
            WEBCAM.close();
            btnAltFot.setEnabled(true);
//            lblFotoOs.setIcon(null);
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void parar_camera() {

        new Thread() {
            public void run() {
                WEBCAM.close();
                executando = false;
                lblImaCam.setIcon(null);
                btnIniCam.setEnabled(true);
                btnCapCam.setEnabled(false);
                btnParCam.setEnabled(false);
            }
        }.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        lblImaCam = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        btnIniCam = new javax.swing.JButton();
        btnParCam = new javax.swing.JButton();
        btnCapCam = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Webcam System O.S");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));

        lblImaCam.setBackground(new java.awt.Color(102, 102, 102));
        lblImaCam.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lblImaCam.setForeground(new java.awt.Color(153, 255, 0));
        lblImaCam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImaCam.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        lblImaCam.setOpaque(true);
        jLayeredPane1.add(lblImaCam);
        lblImaCam.setBounds(10, 10, 280, 280);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(100, 0, 300, 300);

        jLayeredPane2.setBackground(new java.awt.Color(153, 153, 153));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jLayeredPane2.setOpaque(true);

        btnIniCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/Start.png"))); // NOI18N
        btnIniCam.setToolTipText("Iniciar WebCam");
        btnIniCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniCamActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btnIniCam);
        btnIniCam.setBounds(18, 10, 64, 64);

        btnParCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/Stop.png"))); // NOI18N
        btnParCam.setToolTipText("Parar WebCam");
        btnParCam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnParCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParCamActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btnParCam);
        btnParCam.setBounds(18, 90, 64, 64);

        btnCapCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/Captura.png"))); // NOI18N
        btnCapCam.setToolTipText("Captura");
        btnCapCam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapCamActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btnCapCam);
        btnCapCam.setBounds(18, 170, 64, 64);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/webcam.png"))); // NOI18N
        jLabel1.setToolTipText("Webcam System");
        jLayeredPane2.add(jLabel1);
        jLabel1.setBounds(26, 240, 50, 50);

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(0, 0, 100, 300);

        setSize(new java.awt.Dimension(416, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniCamActionPerformed
        // TODO add your handling code here:
        iniciar_camera();

    }//GEN-LAST:event_btnIniCamActionPerformed

    private void btnCapCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapCamActionPerformed
        // TODO add your handling code here:
        capturar_imagem();

    }//GEN-LAST:event_btnCapCamActionPerformed

    private void btnParCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParCamActionPerformed
        // TODO add your handling code here:
        parar_camera();
    }//GEN-LAST:event_btnParCamActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        parar_camera();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(TelaWebCamUsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaWebCamUsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaWebCamUsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaWebCamUsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaWebCamUsu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapCam;
    private javax.swing.JButton btnIniCam;
    private javax.swing.JButton btnParCam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLabel lblImaCam;
    // End of variables declaration//GEN-END:variables

    private void Iniciar() {
        try {
//            dimensao_defaul = new Dimension(176, 144); //UTILIZO A DIMENSÃO DE MINHA PREFERÊNCIA
            dimensao_defaul = WebcamResolution.VGA.getSize();//UTILIZA A DIMENSÃO PADRÃO DA WEBCAM
//            WEBCAM = Webcam.getDefault(); //UTILIZA A WEBCAM PADRÃO DO COMPUTADOR            
            List list = Webcam.getWebcams();
            for (int i = 0; i < list.size(); i++) {
                WEBCAM = (Webcam) list.get(i); //UTILIZA A ÚLTIMA WEBCAM ENCONTRADA, SE EXISTIR MAIS DE UMA PEGA A ÚLTIMA
            }
            WEBCAM.setViewSize(dimensao_defaul);

            for (Dimension dimensao : WEBCAM.getViewSizes()) { // AQUI EU CONSIGO SABER QUAIS SÃO RESOLUÇÕES SUPORTADA PELA WEBCAM
                System.out.println("Largura: " + dimensao.getWidth() + "  Altura: " + dimensao.getHeight());
            }
//         
        } catch (Exception e) {
//            e.printStackTrace();            
        }
    }

    private void Inicializa_video() {
        new Thread() {
            @Override
            public void run() {
                while (true && executando) {
                    try {
                        Image imagem = WEBCAM.getImage();
                        ImageIcon icon = new ImageIcon(imagem);
                        icon.setImage(icon.getImage().getScaledInstance(lblImaCam.getWidth(), lblImaCam.getHeight(), 500));
                        lblImaCam.setIcon(icon);
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }

}