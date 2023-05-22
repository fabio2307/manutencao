/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import com.prjmanutencao.dal.ModuloConexao;
import static com.prjmanutencao.telas.TelaLogin.id;
import static com.prjmanutencao.telas.TelaOs.txtData;
import static com.prjmanutencao.telas.TelaOs.txtHora;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Fábio
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String id_e_men = null;
    String id_e_tri = null;
    String id_e_sem = null;

    String id_h_men = null;
    String id_h_tri = null;
    String id_h_sem = null;

    String id_r_men = null;
    String id_r_tri = null;
    String id_r_sem = null;

    String id_c_men = null;
    String id_c_tri = null;
    String id_c_sem = null;

    String sta_e_men = null;
    String sta_e_trim = null;
    String sta_e_sem = null;

    String sta_h_men = null;
    String sta_h_trim = null;
    String sta_h_sem = null;

    String sta_r_men = null;
    String sta_r_trim = null;
    String sta_r_sem = null;

    String sta_c_men = null;
    String sta_c_trim = null;
    String sta_c_sem = null;

    String a = null;
    String n = null;
    String set = null;
    String tem = null;

    String ipDaMaquina = null;
    String nomeDaMaquina = null;
    String ip = null;

    boolean estado = true;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        conexao = ModuloConexao.conector();
        ipAcesso();
        a = "Aberta";
        n = "''";
        set = "Irregular";
        tem = "0";
        encerremento();
    }

    private void select_eletr_men() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_form_ele_mensal, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_eletrica_mensal where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_e_men = (rs.getString(1));
                sta_e_men = (rs.getString(4));
                System.out.println(sta_e_men);
                atu_ele_men();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void atu_ele_men() {

        String status;
        status = sta_e_men;

        try {
            if (sta_e_men.equals(set)) {

                String sql = "update form_eletrica_mensal set  situacao_prev = ?  where id_form_ele_mensal = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_e_men);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_eletr_tri() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_form_ele_trimestral, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_eletrica_trimestral where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_e_tri = (rs.getString(1));
                sta_e_trim = (rs.getString(4));
                atu_ele_tri();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_ele_tri() {

        String status;
        status = sta_e_trim;
        try {
            if (sta_e_trim.equals(set)) {

                String sql = "update form_eletrica_trimestral set  situacao_prev = ?  where id_form_ele_trimestral = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_e_tri);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_eletr_sem() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_form_ele_semestral, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_eletrica_semestral where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_e_sem = (rs.getString(1));
                sta_e_sem = (rs.getString(4));
                atu_ele_sem();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_ele_sem() {

        String status;
        status = sta_e_sem;
        try {
            if (sta_e_sem.equals(set)) {

                String sql = "update form_eletrica_semestral set  situacao_prev = ?  where id_form_ele_semestral = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_e_sem);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_hid_men() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_hidraulica_mensal, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_hidraulica_mensal where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_h_men = (rs.getString(1));
                sta_h_men = (rs.getString(4));
                System.out.println(sta_h_men);
                atu_hid_men();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_hid_men() {

        String status;
        status = sta_h_men;

        try {
            if (sta_h_men.equals(set)) {

                String sql = "update form_hidraulica_mensal set  situacao_prev = ?  where id_hidraulica_mensal = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_h_men);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_hid_tri() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_hidraulica_trimestral, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_hidraulica_trimestral where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_h_tri = (rs.getString(1));
                sta_h_trim = (rs.getString(4));
                atu_hid_tri();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_hid_tri() {

        String status;
        status = sta_h_trim;

        try {
            if (sta_h_trim.equals(set)) {

                String sql = "update form_hidraulica_trimestral set  situacao_prev = ?  where id_hidraulica_trimestral = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_h_tri);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_hid_sem() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_hidraulica_semestral, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_hidraulica_semestral where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_h_sem = (rs.getString(1));
                sta_h_sem = (rs.getString(4));
                atu_hid_sem();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_hid_sem() {

        String status;
        status = sta_h_sem;

        try {
            if (sta_h_sem.equals(set)) {

                String sql = "update form_hidraulica_semestral set  situacao_prev = ?  where id_hidraulica_semestral = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_e_sem);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_ref_men() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_refrigeracao_mensal, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_refrigeracao_mensal where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_r_men = (rs.getString(1));
                sta_r_men = (rs.getString(4));
                atu_ref_men();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_ref_men() {

        String status;
        status = sta_r_men;

        try {
            if (sta_r_men.equals(set)) {

                String sql = "update form_refrigeracao_mensal set  situacao_prev = ?  where id_refrigeracao_mensal = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_r_men);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_ref_tri() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_refrigeracao_trimestral, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_refrigeracao_trimestral where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_r_tri = (rs.getString(1));
                sta_r_trim = (rs.getString(4));
                atu_ref_tri();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_ref_tri() {

        String status;
        status = sta_r_trim;

        try {
            if (sta_r_trim.equals(set)) {

                String sql = "update form_refrigeracao_trimestral set  situacao_prev = ?  where id_refrigeracao_trimestral = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_r_tri);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_ref_sem() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_refrigeracao_semestral, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_refrigeracao_semestral where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_r_sem = (rs.getString(1));
                sta_r_sem = (rs.getString(4));
                atu_ref_sem();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_ref_sem() {

        String status;
        status = sta_r_sem;

        try {
            if (sta_r_sem.equals(set)) {

                String sql = "update form_refrigeracao_semestral set  situacao_prev = ?  where id_refrigeracao_semestral = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_r_sem);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_civ_men() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_civil_mensal, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_civil_mensal where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_c_men = (rs.getString(1));
                sta_c_men = (rs.getString(4));
                atu_civ_men();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_civ_men() {

        String status;
        status = sta_c_men;

        try {
            if (sta_c_men.equals(set)) {

                String sql = "update form_civil_mensal set  situacao_prev = ?  where id_civil_mensal = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_c_men);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_civ_tri() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_civil_trimestral, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_civil_trimestral where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_c_tri = (rs.getString(1));
                sta_c_trim = (rs.getString(4));
                atu_civ_tri();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_civ_tri() {

        String status;
        status = sta_c_trim;

        try {
            if (sta_c_trim.equals(set)) {

                String sql = "update form_civil_trimestral set  situacao_prev = ?  where id_civil_trimestral = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_c_tri);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_civ_sem() {
        String sit = a;
        String nul = n;
        String z = tem;

        String sql = "select id_civil_semestral, situacao_prev, DATE_FORMAT(data_prev,'%d / %m / %Y'), if(month(data_prev) - month(CURDATE()) <(?) ,'Irregular','') as tipo from form_civil_semestral where situacao_prev is null or situacao_prev = ? or situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, z);
            pst.setString(2, nul);
            pst.setString(3, sit);
            rs = pst.executeQuery();
            while (rs.next()) {
                id_c_sem = (rs.getString(1));
                sta_c_sem = (rs.getString(4));
                atu_civ_sem();
            }
//                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atu_civ_sem() {

        String status;
        status = sta_c_sem;

        try {
            if (sta_c_sem.equals(set)) {

                String sql = "update form_civil_semestral set  situacao_prev = ?  where id_civil_semestral = ?";

                pst = conexao.prepareStatement(sql);
                pst.setString(1, status);
                pst.setString(2, id_c_sem);
                int alterar = pst.executeUpdate();
                while (alterar > 1) {
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void encerremento() {

        select_eletr_men();
        select_hid_men();
        select_civ_men();
        select_ref_men();
//        System.out.println("men");

        select_eletr_tri();
        select_hid_tri();
        select_civ_tri();
        select_ref_tri();
//        System.out.println("tri");

        select_eletr_sem();
        select_hid_sem();
        select_civ_sem();
        select_ref_sem();
//        System.out.println("sem");

        Thread cron = new Thread() {
            @Override
            public void run() {
//                System.out.println("Metodo iniciado");
                for (;;) {
                    if (estado == true) {
                        try {
                            sleep(43200000);
                            select_eletr_men();
                            select_hid_men();
                            select_civ_men();
                            select_ref_men();
                            sleep(1000);
                            select_eletr_tri();
                            select_hid_tri();
                            select_civ_tri();
                            select_ref_tri();
                            sleep(1000);
                            select_eletr_sem();
                            select_hid_sem();
                            select_civ_sem();
                            select_ref_sem();
                        } catch (InterruptedException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }

                    } else {
//                        break;
                    }

                }

            }

        };

        cron.start();
    }

//    private void atuAcesso() {
//
//        try {
//            //pegamos o ip da maquina.
//            ipDaMaquina = InetAddress.getLocalHost().getHostAddress();
//            //nome da maquina.
//            nomeDaMaquina = InetAddress.getLocalHost().getHostName();
//            acesso();
//            ipAcesso();
//
//        } catch (UnknownHostException e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//
//    }

    private void ipAcesso() {
        String sql = "select max(id_ace) from acesso";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                ip = (rs.getString(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void acesso() {

        String data;

        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);
        data = format;

        String sql = "update acesso set data_said = ? where id_ace = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, data);
            pst.setString(2, ip);
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

        desktop = new javax.swing.JDesktopPane();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        men = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        menCadUsu = new javax.swing.JMenuItem();
        menCadSet = new javax.swing.JMenuItem();
        menOs = new javax.swing.JMenu();
        menOsAbr = new javax.swing.JMenuItem();
        menOsAbeFec = new javax.swing.JMenuItem();
        menPreSemEnc = new javax.swing.JMenu();
        menPreAbr = new javax.swing.JMenuItem();
        menPreMen = new javax.swing.JMenuItem();
        menPreSemEnce = new javax.swing.JMenuItem();
        menRel = new javax.swing.JMenu();
        menRelOs = new javax.swing.JMenuItem();
        menRelPre = new javax.swing.JMenuItem();
        menAju = new javax.swing.JMenu();
        memAjuSob = new javax.swing.JMenuItem();
        menOpc = new javax.swing.JMenu();
        menOpcSai = new javax.swing.JMenuItem();
        menOpcAce = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sistema - Tela Inicial");
        setMinimumSize(new java.awt.Dimension(600, 400));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        desktop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));
        desktop.setMaximumSize(new java.awt.Dimension(1000, 720));
        desktop.setMinimumSize(new java.awt.Dimension(1000, 720));
        desktop.setOpaque(false);
        desktop.setPreferredSize(new java.awt.Dimension(1010, 720));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1006, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
        );

        getContentPane().add(desktop);
        desktop.setBounds(0, 0, 1010, 672);

        lblUsuario.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setText("Usuário");
        lblUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblUsuario);
        lblUsuario.setBounds(1060, 590, 260, 20);

        lblData.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblData.setText("Data");
        getContentPane().add(lblData);
        lblData.setBounds(1042, 610, 296, 20);

        lblHora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblHora.setForeground(new java.awt.Color(0, 0, 153));
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHora.setText("-- : -- : --");
        getContentPane().add(lblHora);
        lblHora.setBounds(1140, 630, 100, 17);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1040, 190, 73, 23);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/Tela Principal2.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1280, 720));
        jLabel1.setMinimumSize(new java.awt.Dimension(1280, 730));
        jLabel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -23, 1390, 720);

        men.setBorder(new javax.swing.border.MatteBorder(null));
        men.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        men.setRequestFocusEnabled(false);

        menCad.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        menCad.setText("Cadastro");
        menCad.setEnabled(false);
        menCad.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        menCadUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        menCadUsu.setText("Usuáro");
        menCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuActionPerformed(evt);
            }
        });
        menCad.add(menCadUsu);

        menCadSet.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menCadSet.setText("Setor");
        menCadSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadSetActionPerformed(evt);
            }
        });
        menCad.add(menCadSet);

        men.add(menCad);

        menOs.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        menOs.setText("Ordem de Serviço");
        menOs.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        menOsAbr.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menOsAbr.setText("Abrir O.S");
        menOsAbr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOsAbrActionPerformed(evt);
            }
        });
        menOs.add(menOsAbr);

        menOsAbeFec.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        menOsAbeFec.setText("O.S Abertas/Fechada");
        menOsAbeFec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOsAbeFecActionPerformed(evt);
            }
        });
        menOs.add(menOsAbeFec);

        men.add(menOs);

        menPreSemEnc.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        menPreSemEnc.setText("Preventiva");
        menPreSemEnc.setEnabled(false);
        menPreSemEnc.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        menPreAbr.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        menPreAbr.setText("Abrir Preventiva");
        menPreAbr.setEnabled(false);
        menPreAbr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPreAbrActionPerformed(evt);
            }
        });
        menPreSemEnc.add(menPreAbr);

        menPreMen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        menPreMen.setText("Iniciar/Abertas");
        menPreMen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPreMenActionPerformed(evt);
            }
        });
        menPreSemEnc.add(menPreMen);

        menPreSemEnce.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        menPreSemEnce.setText("Encerradas/Irregulares");
        menPreSemEnce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPreSemEnceActionPerformed(evt);
            }
        });
        menPreSemEnc.add(menPreSemEnce);

        men.add(menPreSemEnc);

        menRel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        menRel.setText("Relatório");
        menRel.setEnabled(false);
        menRel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        menRelOs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menRelOs.setText("O.S");
        menRel.add(menRelOs);

        menRelPre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menRelPre.setText("Preventiva");
        menRel.add(menRelPre);

        men.add(menRel);

        menAju.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        menAju.setText("Ajuda");
        menAju.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        memAjuSob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_MASK));
        memAjuSob.setText("Sobre");
        memAjuSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memAjuSobActionPerformed(evt);
            }
        });
        menAju.add(memAjuSob);

        men.add(menAju);

        menOpc.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        menOpc.setText("Opções");
        menOpc.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        menOpcSai.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menOpcSai.setText("Sair");
        menOpcSai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOpcSaiActionPerformed(evt);
            }
        });
        menOpc.add(menOpcSai);

        menOpcAce.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menOpcAce.setText("Acessos");
        menOpcAce.setEnabled(false);
        menOpcAce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOpcAceActionPerformed(evt);
            }
        });
        menOpc.add(menOpcAce);

        men.add(menOpc);

        setJMenuBar(men);

        setSize(new java.awt.Dimension(1370, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuActionPerformed
        // As linhas a baixo chamam a tela usuarios
        TelaUsuarios usuarios = new TelaUsuarios();
        usuarios.setVisible(true);
        desktop.add(usuarios);
    }//GEN-LAST:event_menCadUsuActionPerformed

    private void menCadSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadSetActionPerformed
        // TODO add your handling code here:
        TelaSetor setor = new TelaSetor();
        setor.setVisible(true);
        desktop.add(setor);
    }//GEN-LAST:event_menCadSetActionPerformed

    private void menOsAbrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOsAbrActionPerformed
        // TODO add your handling code here:
        TelaOs os = new TelaOs();
        os.setVisible(true);
        desktop.add(os);

        Date data1 = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
        txtData.setText(formatador.format(data1));
        Calendar now = Calendar.getInstance();

        Timer timer = new Timer(1000, new hora());
        timer.start();


    }//GEN-LAST:event_menOsAbrActionPerformed

    private void memAjuSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memAjuSobActionPerformed
        // Chamando tela sobre
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_memAjuSobActionPerformed

    private void menOpcSaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOpcSaiActionPerformed
        // TODO add your handling code here:
        try {

            select_eletr_men();
            select_hid_men();
            select_civ_men();
            select_ref_men();
            sleep(1000);
            select_eletr_tri();
            select_hid_tri();
            select_civ_tri();
            select_ref_tri();
            sleep(1000);
            select_eletr_sem();
            select_hid_sem();
            select_civ_sem();
            select_ref_sem();
            sleep(1000);
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Realmente Fechar este Programa?\n Você Podera estar Perdendo Dados!", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                acesso();
                System.exit(0);
            }
        } catch (HeadlessException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_menOpcSaiActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // As linhas a baixo substituem a label lblData pela data atual
        //ao inicializar o forme
//        Date data = new Date();
//        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
//        lblData.setText(formatador.format(data));
//
//        Timer timer = new Timer(1000, new hora());
//        timer.start();


    }//GEN-LAST:event_formWindowActivated

    private void menOsAbeFecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOsAbeFecActionPerformed
        // TODO add your handling code here:
        TelaPesquisaros pesquisar = new TelaPesquisaros();
        pesquisar.setVisible(true);
        desktop.add(pesquisar);

    }//GEN-LAST:event_menOsAbeFecActionPerformed

    private void menPreAbrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPreAbrActionPerformed
        // TODO add your handling code here:
        TelaDisPreventiva dispreventivas = new TelaDisPreventiva();
        dispreventivas.setVisible(true);
        desktop.add(dispreventivas);
    }//GEN-LAST:event_menPreAbrActionPerformed

    private void menPreMenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPreMenActionPerformed
        // TODO add your handling code here:
        TelaPrevListas abe = new TelaPrevListas();
        abe.setVisible(true);
        desktop.add(abe);
    }//GEN-LAST:event_menPreMenActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Realmente Fechar este Programa?\n Você Podera estar Perdendo Dados!", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            acesso();
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menPreSemEnceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPreSemEnceActionPerformed
        // TODO add your handling code here:
        TelaPrevEncerradas ence = new TelaPrevEncerradas();
        ence.setVisible(true);
        desktop.add(ence);
    }//GEN-LAST:event_menPreSemEnceActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
        lblData.setText(formatador.format(data));

        Timer timer = new Timer(1000, new hora());
        timer.start();
        encerremento();
    }//GEN-LAST:event_formWindowOpened

    private void menOpcAceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOpcAceActionPerformed
        // TODO add your handling code here:
        TelaAcesso acesso = new TelaAcesso();
        acesso.setVisible(true);
        desktop.add(acesso);
    }//GEN-LAST:event_menOpcAceActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuItem memAjuSob;
    private javax.swing.JMenuBar men;
    private javax.swing.JMenu menAju;
    public static javax.swing.JMenu menCad;
    private javax.swing.JMenuItem menCadSet;
    private javax.swing.JMenuItem menCadUsu;
    private javax.swing.JMenu menOpc;
    public static javax.swing.JMenuItem menOpcAce;
    private javax.swing.JMenuItem menOpcSai;
    private javax.swing.JMenu menOs;
    private javax.swing.JMenuItem menOsAbeFec;
    private javax.swing.JMenuItem menOsAbr;
    public static javax.swing.JMenuItem menPreAbr;
    private javax.swing.JMenuItem menPreMen;
    public static javax.swing.JMenu menPreSemEnc;
    private javax.swing.JMenuItem menPreSemEnce;
    public static javax.swing.JMenu menRel;
    private javax.swing.JMenuItem menRelOs;
    private javax.swing.JMenuItem menRelPre;
    // End of variables declaration//GEN-END:variables

    class hora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            lblHora.setText(String.format("%1$tH:%1$tM:%1tS", now));
        }

        public void actionPerformed1(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            Timer timer = new Timer(1000, new hora());
            txtHora.setText(String.format("%1$tH:%1$tM:%1tS", now));
            timer.start();

        }

    }

}
