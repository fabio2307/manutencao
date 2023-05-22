/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

//import static com.prjmanutencao.telas.TesteWeb1.pnlWebTes;
import com.sun.prism.paint.Gradient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
//import javafx.embed.swing.JFXLabel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

/**
 *
 * @author Fábio
 */
public class TesteWeb1 extends javax.swing.JFrame {

    JFXPanel javafxPanel;
    WebView webComponent;
    JTextField urlField;
    JButton goButton;
    JPanel urlPanel = new JPanel();
//    Graphics estilo;
    String url = null;

//    JPanel url;
    /**
     * Creates new form TesteWeb1
     */
    public TesteWeb1() {
        initComponents();

////        JPanel urlPanel = new JPanel();
//        this.setLayout(new BorderLayout());
//        urlPanel.setVisible(true);
//        urlField = new JTextField();
//        urlField.setVisible(true);
//        urlField.setColumns(50);
//        urlPanel.add(urlField);
//        this.add(urlPanel);
//        Dimension dimensao1 = this.getSize();
////        urlPanel.setSize((dimensao1.width - 6), (dimensao1.height - 30));
//        goButton = new JButton("Go");
//        goButton.setVisible(true);
//        urlPanel.add(goButton);
//        this.add(urlPanel, BorderLayout.NORTH);
////
        javafxPanel = new JFXPanel();
        javafxPanel.setVisible(true);
        Dimension dimensao = this.getSize();
        javafxPanel.setSize((dimensao.width - 6), (dimensao.height - 90));
        javafxPanel.setLocation(WIDTH, 60);
        this.add(javafxPanel, BorderLayout.CENTER);

//        url = txtPesUrl.getText();
        url = "http://google.com/";
//        jpaDes = new jPanielGradient();

//        desing();;
        teste_web();
//        
    }

    class jPanielGradient extends JPanel {

        protected void paintComponent(Graphics estilo) {

            Graphics2D g2d = (Graphics2D) estilo;
            int width = getWidth();
            int height = getHeight();
            Color cor1 = new Color(52, 143, 85);
            Color cor2 = new Color(86, 180, 211);
            GradientPaint gp = new GradientPaint(0, 0, cor1, 0, height, cor2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);

        }
    }

    private void inicio() {
//        protected void paintComponent(Graphics estilo) {

//            Graphics2D g2d = (Graphics2D) estilo;
//            int width = getWidth();
//            int height = getHeight();
//            Color cor1 = new Color(52, 143, 85);
//            Color cor2 = new Color(86, 180, 211);
//            GradientPaint gp = new GradientPaint(0, 0, cor1, 180, height, cor2);
//            g2d.setPaint(gp);
//            g2d.fillRect(0, 0, width, height);
    }

    private void teste_web() {
        Dimension dimensao = this.getSize();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    BorderPane borderPane = new BorderPane();
                    webComponent = new WebView();
//                    url = txtPesUrl.getText();
                    webComponent.getEngine().load(url);
//

//                    javafxPanel.setSize((dimensao.width - 6), (dimensao.height - 30));
                    borderPane.setCenter(webComponent);
                    Scene scene = new Scene(borderPane, (dimensao.width - 6), (dimensao.height - 90));
                    javafxPanel.setScene(scene);
                    javafxPanel.setLocation(WIDTH, 60);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                    System.out.println(e);
                }

            }
        });
    }

    private void initSwingComponents() {
        Dimension dimensao = this.getSize();
////        JPanel mainPanel;
////        mainPanel = new JPanel();
//        this.setLayout(new BorderLayout());
//        this.add(javafxPanel, BorderLayout.CENTER);
//
//        JPanel urlPanel = new JPanel(new FlowLayout());
//        urlPanel.setVisible(true);
//        JTextField urlField = new JTextField();
//        urlField.setColumns(50);
//        urlPanel.add(urlField);
//        JButton goButton = new JButton("Go");

        /**
         * Handling the loading of new URL, when the user enters the URL and
         * clicks on Go button.
         */
//        goButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
        BorderPane borderPane = new BorderPane();
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String url1 = ("http://") + txtPesUrl.getText();
                    if (url1 != null && url1.length() > 0) {
                        webComponent.getEngine().load(url1);
                        borderPane.setCenter(webComponent);
                        Scene scene = new Scene(borderPane, (dimensao.width - 6), (dimensao.height - 90));
                        javafxPanel.setScene(scene);
                        javafxPanel.setLocation(WIDTH, 60);

                    }
                }
            });

//            }
//        });
//
//        urlPanel.add(goButton);
//        this.add(urlPanel, BorderLayout.NORTH);
//
////        this.add(urlPanel);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(700, 600);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
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

        jpaDes = new jPanielGradient();
        txtPesUrl = new javax.swing.JTextField();
        btnPesUrl = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        jpaDes.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        jpaDes.setForeground(new java.awt.Color(22, 113, 128));
        jpaDes.setPreferredSize(new java.awt.Dimension(900, 60));
        jpaDes.setLayout(null);

        txtPesUrl.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jpaDes.add(txtPesUrl);
        txtPesUrl.setBounds(100, 15, 650, 28);

        btnPesUrl.setText("Pesquisar");
        btnPesUrl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesUrlActionPerformed(evt);
            }
        });
        jpaDes.add(btnPesUrl);
        btnPesUrl.setBounds(770, 15, 100, 28);

        getContentPane().add(jpaDes);
        jpaDes.setBounds(0, 0, 908, 60);

        setBounds(0, 0, 924, 524);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesUrlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesUrlActionPerformed
        // TODO add your handling code here:
        initSwingComponents();
//        teste_web();
    }//GEN-LAST:event_btnPesUrlActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
//        Dimension dimensao = this.getMaximumSize();
//        jpaDes.setSize((dimensao.width ), (60));
//        txtPesUrl.setVisible(true);
//        txtPesUrl.setSize((1300 ), (28));
//        BorderLayout l = new BorderLayout();
//        jpaDes.setLayout(new BorderLayout(6,0));
//        txtPesUrl.setAlignmentX(TOP_ALIGNMENT);
//        this.add(jpaDes, GridBagConstraints.CENTER);
//        setExtendedState(WIDTH);
//        this.setSize(dimensao);

    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(TesteWeb1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesteWeb1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesteWeb1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesteWeb1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new TesteWeb1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesUrl;
    private javax.swing.JPanel jpaDes;
    private javax.swing.JTextField txtPesUrl;
    // End of variables declaration//GEN-END:variables
}
