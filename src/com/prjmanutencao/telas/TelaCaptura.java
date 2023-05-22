/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

//import com.prjmanutencao.dal.ModuloConexao;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamResolution;
import static com.prjmanutencao.telas.TelaArquivos.lblArq;
import static com.prjmanutencao.telas.TelaArquivos.ent1;
import static com.prjmanutencao.telas.TelaArquivos.txtNomAqu;
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
import static com.prjmanutencao.telas.TelaOs.txtNomArq;

/**
 *
 * @author Fábio
 */
public class TelaCaptura extends javax.swing.JFrame {

    private Dimension dimensao_default;
    private Webcam WEBCAM1;
    boolean executando = true;
//    public static byte[] BYTES_IMAGEM = null;
    public static byte[] BYTES_IMAGEM1 = null;
    public static String Imagem1;
//    String valor = null;

    /**
     * Creates new form TelaCaptura
     */
    public TelaCaptura() {

        initComponents();

        Iniciar();
//        btnCap.setEnabled(false);
//        btnStop.setEnabled(false);

//        Random aleatorio = new Random();
//        int valor = aleatorio.nextInt(30000) + 1;
//        txtNomFot.setText(Integer.toString(valor));
    }

//    private void id_os_foto() {
//        String sql = "select max(id_os) from os ";
//        try {
//            pst = conexao.prepareStatement(sql);
//            rs = pst.executeQuery();
//            if (rs.next()) {
////                txtIdOs.setText(rs.getString(1));
//                txtOsFot.setText(rs.getString(1));
//
//            } else {
//            }
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    private void start_camera() {

        new Thread() {
            public void run() {
                executando = true;
//                btnStart.setEnabled(false);
                lblImag.setText("Iniciando...");
                WEBCAM1.open();
                lblImag.setText("");
                Inicializa_video();
            }
        }.start();
//        btnCap.setEnabled(true);
//        btnStop.setEnabled(true);

    }

    public void capturar_foto() {
//
        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(30000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM1.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 600, Nova_Altura = 600; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtNomAqu.setText(Integer.toString(valor));
//            Imagem1 =  lblArq.getText() + valor + ".jpg";
            System.out.println(Imagem1);
//             ImageIO.write(new_img, "jpg", new File(Imagem1));
            ImageIO.write(new_img, "jpg", new File(Imagem1 + valor + ".jpg"));
            ent1 = Imagem1 + valor + ".jpg";

//            Imagem1 = File.pathSeparator;
            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "jpg", buff2);
//
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblArq.getWidth(), lblArq.getHeight(), 100));
            lblArq.setIcon(icon);

//            txtNomAqu.setText(Integer.toString(valor));
//
            WEBCAM1.close();
//            lblImag.setIcon(null);
            stop_camera();
            dispose();

        } catch (IOException e) {
            System.out.println();

        }

    }

    private void stop_camera() {

        new Thread() {
            public void run() {
                WEBCAM1.close();
                executando = false;
                lblImag.setIcon(null);
//                btnStart.setEnabled(true);
//                btnCap.setEnabled(false);
//                btnStop.setEnabled(false);
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

        lblImag = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnCap = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela Captura");
        setMinimumSize(new java.awt.Dimension(400, 480));
        setPreferredSize(new java.awt.Dimension(400, 498));
        setResizable(false);
        getContentPane().setLayout(null);

        lblImag.setBackground(new java.awt.Color(0, 0, 0));
        lblImag.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lblImag.setForeground(new java.awt.Color(51, 204, 0));
        lblImag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImag.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        lblImag.setOpaque(true);
        lblImag.setPreferredSize(new java.awt.Dimension(380, 380));
        getContentPane().add(lblImag);
        lblImag.setBounds(10, 14, 370, 380);

        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/Start.png"))); // NOI18N
        btnStart.setToolTipText("Start");
        btnStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        getContentPane().add(btnStart);
        btnStart.setBounds(60, 413, 64, 64);

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/Stop.png"))); // NOI18N
        btnStop.setToolTipText("Stop");
        btnStop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        getContentPane().add(btnStop);
        btnStop.setBounds(170, 413, 64, 64);

        btnCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/Captura.png"))); // NOI18N
        btnCap.setToolTipText("Capturar ");
        btnCap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapActionPerformed(evt);
            }
        });
        getContentPane().add(btnCap);
        btnCap.setBounds(270, 413, 64, 64);

        jLayeredPane1.setBackground(new java.awt.Color(204, 204, 204));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), null, new java.awt.Color(0, 102, 102)));
        jLayeredPane1.setOpaque(true);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(0, 0, 400, 410);

        setBounds(0, 0, 406, 517);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapActionPerformed
        // TODO add your handling code here:
        capturar_foto();
//        btnArqUpl.setEnabled(true);
//        btnArqDow.setEnabled(true);
//        id_os_foto();
    }//GEN-LAST:event_btnCapActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // TODO add your handling code here:        
        stop_camera();
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        start_camera();
    }//GEN-LAST:event_btnStartActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCaptura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCaptura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCaptura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCaptura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCaptura().setVisible(true);
            }
        });

//        btnArqUpl.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCap;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lblImag;
    // End of variables declaration//GEN-END:variables

    public void Iniciar() {
        try {
//           dimensao_default = new Dimension(176, 144); //UTILIZO A DIMENSÃO DE MINHA PREFERÊNCIA
            dimensao_default = WebcamResolution.VGA.getSize();//UTILIZA A DIMENSÃO PADRÃO DA WEBCAM
//            WEBCAM1 = Webcam.getDefault(); //UTILIZA A WEBCAM PADRÃO DO COMPUTADOR            
            List list = Webcam.getWebcams();
            for (int i = 0; i < list.size(); i++) {
                WEBCAM1 = (Webcam) list.get(i); //UTILIZA A ÚLTIMA WEBCAM ENCONTRADA, SE EXISTIR MAIS DE UMA PEGA A ÚLTIMA
            }
            WEBCAM1.setViewSize(dimensao_default);

            for (Dimension dimensao : WEBCAM1.getViewSizes()) { // AQUI EU CONSIGO SABER QUAIS SÃO RESOLUÇÕES SUPORTADA PELA WEBCAM
                System.out.println("Largura: " + dimensao.getWidth() + "  Altura: " + dimensao.getHeight());
            }
        } catch (WebcamException e) {
        }
    }

    public void Inicializa_video() {
        new Thread() {
            @Override
            public void run() {
                while (true && executando) {
                    try {
                        Image imagem = WEBCAM1.getImage();
                        ImageIcon icon = new ImageIcon(imagem);
                        icon.setImage(icon.getImage().getScaledInstance(lblImag.getWidth(), lblImag.getHeight(), 100));
                        lblImag.setIcon(icon);
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        System.out.println(e);

                    }

                }
            }
        }.start();
    }

    private boolean next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//
////    public void gerar_numero() {
////        Random aleatorio = new Random();
////        int valor = aleatorio.nextInt(30000) + 1;
////        String num = aleatorio.toString();
////        txtNomFoto.setText(num);
////    }
}
