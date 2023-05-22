/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import static com.mysql.cj.conf.PropertyKey.logger;
import java.sql.*;
import com.prjmanutencao.dal.ModuloConexao;
import static com.prjmanutencao.telas.TelaOs.txtData;
import static java.lang.Thread.sleep;
import java.security.SecureRandom;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javafx.scene.web.WebView;
import java.net.*;
//import java.net.HttpURLConnection;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.zone;
import java.util.Calendar;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author Fábio
 */
public class teste2 extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    int seg = 0;
    int min = 0;
    int hor = 0;
    int dia = 0;
    boolean estado = true;

    int i = 0;

    String segu = null;
    String minu = null;
    String hora = null;
    String dia1 = null;

    String tem = null;

    JFXPanel javafxPanel = new JFXPanel();
    JPanel mainPanel = new JPanel();

//    private JDateChooser date = new JDateChooser();
//    String dateStop = null;
    /**
     * Creates new form teste2
     */
    public teste2() {
        initComponents();

//        JFXPanel javafxPanel = null;
        WebView webComponent;
//        JPanel mainPanel = null;
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(javafxPanel, BorderLayout.CENTER);

        conexao = ModuloConexao.conector();
        lblCronMin.getText();

        ImageIcon icon = new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\data.png");
        jdcData.setIcon(icon);
        jdcData.getJCalendar();
//        jdcData.setDate(date);

//        jdcData.setHorizontalAlignment(com.toedter.calendar.JDateChooser.WIDTH);
//        jdcData.setSize(20, 20);
        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);
//        dateStop = data_hora.format(zdtNow);
        txtTempo.setText(format);

    }

    private void teste_net() {

        try {

            URL myURL = new URL("http://www.google.com/");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void leitor_url() {

        try {
            String url = "https://www.climatempo.com.br";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String inputLine = br.readLine();

                System.out.println(inputLine);
                jEditorPane1.setPage(con.getURL());

            } else {
                System.out.println("erro " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Erro de leitura");
        }

    }

    private void teste_web() {
        WebView webComponent;
        webComponent = new WebView();

        webComponent.getEngine().load("http://google.com/");
//        jEditorPane1.setPage(page);

    }

    private void t() {
        int tam = 0;
        String[] objetos = {"garrafa", "copo", "litro"};

        for (tam = objetos.length - 1; tam >= 0; tam--) {

            System.out.print(objetos[tam] + " ");

        }

    }

    private void date() {

        JDateChooser date = new JDateChooser();

        txtDate.setText(date.toString());

    }

    private void cauc_tempo() {
        java.util.Date zone = null;

        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);

        System.out.println(format);
        jdcData.setDate(Timestamp.from(Instant.from(zdtNow)));
        String dateStart = "25/01/2013 10:19:19";
        String dateStop = "26/02/2013 10:19:14";

//        dateStop =Integer.valueOf(temp);
        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        java.util.Date d1 = null;
        java.util.Date d2 = null;

        try {
            d1 = formate.parse(dateStart);
            d2 = formate.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
//            tempo = (int) (diffDays + diffHours + diffMinutes + diffSeconds) ;
//            lblCrono.setText(((diffDays <= 9 ? "0" : "") + diffDays) + (" - ") + ((diffHours <= 9 ? "0" : "") + diffHours) + (" : ") + ((diffMinutes <= 9 ? "0" : "") + diffMinutes) + (" : ") + ((diffSeconds <= 9 ? "0" : "") + diffSeconds));

            dia1 = ((diffDays <= 99 ? "0" : "") + diffDays);
            hora = ((diffHours <= 9 ? "0" : "") + diffHours);
            minu = ((diffMinutes <= 9 ? "0" : "") + diffMinutes);
            segu = ((diffSeconds <= 9 ? "0" : "") + diffSeconds);

            String temp = segu;
            String temp1 = minu;
            String temp2 = hora;
            String temp3 = dia1;
//
            seg = Integer.valueOf(temp);
            min = Integer.valueOf(temp1);
            hor = Integer.valueOf(temp2);
            dia = Integer.valueOf(temp3);

            cronometro();

//            System.out.print(diffDays + " days, ");
//            System.out.print(diffHours + " hours, ");
//            System.out.print(diffMinutes + " minutes, ");
//            System.out.print(diffSeconds + " seconds.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro ao calcular as Horas Trabalhadas!", "Atenção", JOptionPane.ERROR_MESSAGE);
//            txtTempo.setText("");
        }

    }

    private void atu() {
//        txtCalTem.getText(i);
        Thread cron = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    if (estado == true) {
                        try {

                            sleep(43200000);
//                            System.out.println("ola");
//                            if (seg >= 30) {
////                                seg = 0;
////                                min++;
//
//                            }
                            System.out.println("Ola");
//                            atualizacao();

                            sleep(3000);
//                            if (min >= 60) {
////                                seg = 0;
////                                min = 0;
////                                hor++;
//
//                            }
                            System.out.println("Ola2");
//                            txtCalTem.setText((i <= 9 ? "0" : "") + i);
//                            i++;
                        } catch (Exception e) {
                        }

                    } else {
                        break;
                    }

                }

            }

        };

        cron.start();
    }

    private void cronometro() {
//        String temp = segu;
//        String temp1 = minu;
//        String temp2 = hora;
//        String temp3 = dia1;
////
//        seg = Integer.valueOf(temp);
//        min = Integer.valueOf(temp1);
//        hor = Integer.valueOf(temp2);
//        dia = Integer.valueOf(temp3);
//        lblCroSeg.setText(""+seg);
        Thread cron = new Thread() {
            @Override
            public void run() {
// lblCronometro.getText().charAt(seg);
                for (;;) {
                    if (estado == true) {
                        try {
                            sleep(1000);
                            if (seg >= 60) {
                                seg = 0;
                                min++;
                            }
//                            atualizacao();

                            if (min >= 60) {
                                seg = 0;
                                min = 0;
                                hor++;
                            }
                            if (hor >= 24) {
                                min = 0;
                                hor = 0;
                                dia++;
                            }
                            if (dia >= 90000) {
                                hor = 0;
                                dia = 0;

                            }
                            lblCrono.setText((dia <= 9 ? "0" + "0" : "") + dia + " - " + (hor <= 9 ? "0" : "") + hor + " : " + (min <= 9 ? "0" : "") + min + " : " + (seg <= 9 ? "0" : "") + seg);

//                            lblCroDia.setText((dia <= 9 ? "0" : "") + dia);
//                            lblCroHor.setText((hor <= 9 ? "0" : "") + hor);
//                            lblCronMin.setText((min <= 9 ? "0" : "") + min);
//                            lblCroSeg.setText((seg <= 9 ? "0" : "") + seg);
                            seg++;

                        } catch (InterruptedException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    } else {
                        break;
                    }
                }

            }
        };

        cron.start();

    }

    private void tempo() {

        String sql = "insert into tempo (hor, min, seg) values (?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, lblCroHor.getText());
            pst.setString(2, lblCronMin.getText());
            pst.setString(3, lblCroDia.getText());
            int Adicionar = pst.executeUpdate();

            if (Adicionar > 0) {

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void selct_tempo() {

        String sql = "select hor, min, seg from tempo where id_tempo=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTempo.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                lblCroHor.setText(rs.getString(1));
                lblCronMin.setText(rs.getString(2));
                lblCroDia.setText(rs.getString(3));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void calculoTempo() {

//        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
//        ZonedDateTime zdtNow = ZonedDateTime.now();
//        String format = data_hora.format(zdtNow);
//        txtTempo.setText(format);
//DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
//        ZonedDateTime zdtNow = ZonedDateTime.now();
//        String format = data_hora.format(zdtNow);
//        Timer timer1 = new Timer(1000, new hora());
//        timer1.start();
//        String temp = "14:20:10";
//        String temp1 = "10:20:10";
//        horaInicia = Integer.valueOf(temp);
//        long tempoInicial = System.currentTimeMillis();
//  
// long tempoFinal = System.currentTimeMillis();
//
// System.out.printf("%.3f ms%n", (tempoFinal - tempoInicial) / 60000d);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//        try {
//
//            Date horaIni = simpleDateFormat.parse(temp1);
//            Date horaFim = simpleDateFormat.parse(temp);
//
//            double horaI = horaIni.getTime();
//            double horaF = horaFim.getTime();
//
//            double diferencaHoras = horaF - horaI;
//
//            txtTempo.setText(Double.toString(diferencaHoras));
//        String dateStop;
        String dateStart = "12/08/2022 09:29:58";
        String dateStop = txtTempo.getText();

//        dateStop =Integer.valueOf(temp);
        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            txtDate.setText(Long.toString(diffDays) + (" - ") + (diffHours) + (" : ") + (diffMinutes) + (" : ") + (diffSeconds));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro ao calcular as Horas Trabalhadas!", "Atenção", JOptionPane.ERROR_MESSAGE);
            txtTempo.setText("");
        }

    }

    private void equipamento_setor_add() {

        // intervalo ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        int len = 5;

        // cada iteração do loop escolhe aleatoriamente um caractere do dado
        // intervalo ASCII e o anexa à instância `StringBuilder`
        for (int i = 0; i < len; i++) {
//            int randomIndex = 0;
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
//            int len = 10;
//            return sb.toString();
        }
//        len=5;
        String randomIndex = null;
        System.out.println(sb);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserPanel1 = new com.toedter.calendar.demo.DateChooserPanel();
        jCalendarBeanInfo1 = new com.toedter.calendar.JCalendarBeanInfo();
        jDateChooserCellEditor1 = new com.toedter.calendar.JDateChooserCellEditor();
        jFrame1 = new javax.swing.JFrame();
        lblCroHor = new javax.swing.JLabel();
        lblCronMin = new javax.swing.JLabel();
        lblCroDia = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtTempo = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtDate = new javax.swing.JTextField();
        jdcData = new com.toedter.calendar.JDateChooser();
        jpaNet = new javax.swing.JPanel();
        lblNet = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        txtCalTem = new javax.swing.JTextField();
        lblCroSeg = new javax.swing.JLabel();
        lblCrono = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        lblCroHor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCroHor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCroHor.setText("00");
        getContentPane().add(lblCroHor);
        lblCroHor.setBounds(220, 20, 60, 30);

        lblCronMin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCronMin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCronMin.setText("00");
        getContentPane().add(lblCronMin);
        lblCronMin.setBounds(300, 20, 60, 30);

        lblCroDia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCroDia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCroDia.setText("00");
        getContentPane().add(lblCroDia);
        lblCroDia.setBounds(170, 20, 60, 30);

        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 20, 61, 23);

        jButton2.setText("Parar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(90, 20, 59, 23);

        jButton3.setText("Salvar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(10, 50, 63, 23);

        txtTempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempoActionPerformed(evt);
            }
        });
        getContentPane().add(txtTempo);
        txtTempo.setBounds(190, 50, 170, 30);

        jButton4.setText("Pesqui");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(80, 50, 63, 23);

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(130, 200, 73, 23);

        txtDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });
        getContentPane().add(txtDate);
        txtDate.setBounds(270, 90, 170, 30);

        jdcData.setDateFormatString("yyyy-MM-dd");
        jdcData.setIcon(null);
        getContentPane().add(jdcData);
        jdcData.setBounds(10, 100, 220, 30);

        jpaNet.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jpaNet.setLayout(null);
        jpaNet.add(lblNet);
        lblNet.setBounds(0, 0, 250, 140);

        getContentPane().add(jpaNet);
        jpaNet.setBounds(230, 150, 250, 140);

        jScrollPane1.setViewportView(jEditorPane1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(510, 20, 550, 140);
        getContentPane().add(txtCalTem);
        txtCalTem.setBounds(20, 280, 180, 30);

        lblCroSeg.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCroSeg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCroSeg.setText("00");
        getContentPane().add(lblCroSeg);
        lblCroSeg.setBounds(390, 20, 60, 30);

        lblCrono.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblCrono.setForeground(new java.awt.Color(0, 0, 51));
        lblCrono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCrono.setText("000 - 00 : 00 : 00");
        getContentPane().add(lblCrono);
        lblCrono.setBounds(240, 310, 220, 28);

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(670, 290, 73, 23);

        setSize(new java.awt.Dimension(1084, 548));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        estado = true;
        cronometro();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        estado = false;
//        cronometro();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        tempo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
//        selct_tempo();
        calculoTempo();
//        equipamento_setor_add();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cauc_tempo();
//        leitor_url();
//        teste_web();

//        date();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void txtTempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
//        atu();
        t();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(teste2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(teste2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(teste2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(teste2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new teste2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.demo.DateChooserPanel dateChooserPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JCalendarBeanInfo jCalendarBeanInfo1;
    private com.toedter.calendar.JDateChooserCellEditor jDateChooserCellEditor1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcData;
    private javax.swing.JPanel jpaNet;
    private javax.swing.JLabel lblCroDia;
    private javax.swing.JLabel lblCroHor;
    private javax.swing.JLabel lblCroSeg;
    private javax.swing.JLabel lblCronMin;
    private javax.swing.JLabel lblCrono;
    private javax.swing.JLabel lblNet;
    private javax.swing.JTextField txtCalTem;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtTempo;
    // End of variables declaration//GEN-END:variables
}
