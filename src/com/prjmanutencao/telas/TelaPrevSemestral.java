/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import com.prjmanutencao.dal.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import static com.prjmanutencao.telas.TelaLogin.id;
import static com.prjmanutencao.telas.TelaLogin.tip;

import static com.prjmanutencao.telas.TelaDisPreventiva.tipo;
import static com.prjmanutencao.telas.TelaDisPreventiva.idprev;

import static com.prjmanutencao.telas.TelaEletricaSem.id_prev8;
import static com.prjmanutencao.telas.TelaHidraulicaSem.id_prev10;
import static com.prjmanutencao.telas.TelaCivilSem.id_prev9;
import static com.prjmanutencao.telas.TelaRefrigeracaoSem.id_prev11;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author Fábio
 */
public class TelaPrevSemestral extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

//    public static String id = null;
    public static String nome = null;
    String situacao = null;
    String situacao1 = " ";

//    int id2 = 0;
    /**
     * Creates new form TelaPrevMensal
     */
    public TelaPrevSemestral() {
        initComponents();
        conexao = ModuloConexao.conector();
        btnAltPrev.setEnabled(false);
        btnIniPrev.setEnabled(false);
        txtPesqEle.setEnabled(false);
        txtPesqHid.setEnabled(false);
        txtPesqRef.setEnabled(false);
        txtPesqCiv.setEnabled(false);
        ((DefaultTableModel) tblPreEle1.getModel()).setRowCount(0);
        ((DefaultTableModel) tblPreHid1.getModel()).setRowCount(0);
        ((DefaultTableModel) tblPreRef.getModel()).setRowCount(0);
        ((DefaultTableModel) tblPreCiv.getModel()).setRowCount(0);
        filtro_hid_sem();
        filtro_ele_sem();
        filtro_civ_sem();
        filtro_ref_sem();
        ativar();
        ativar1();
        ativar2();
        ativar3();
        eletrica_sem();
        civil_sem();
        hidraulica_sem();
        refrigeracao_sem();

    }

    private void ativar() {
        String sql = "select * from form_eletrica_semestral where iduser = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtPesqEle.setEnabled(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void ativar1() {
        String sql = "select * from form_hidraulica_semestral where iduser = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtPesqHid.setEnabled(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void ativar2() {
        String sql = "select * from form_civil_semestral where iduser = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtPesqCiv.setEnabled(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void ativar3() {
        String sql = "select * from form_refrigeracao_semestral where iduser = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtPesqRef.setEnabled(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void filtro_ele_sem() {
        situacao = "Aberta";
        situacao1 = " ";

        String sql = "select id_form_ele_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_semestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreEle1.setModel(DbUtils.resultSetToTableModel(rs));
//            txtPesqEle.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void filtro_hid_sem() {
        situacao = "Aberta";
        String sql = "select id_hidraulica_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_hidraulica_semestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreHid1.setModel(DbUtils.resultSetToTableModel(rs));
//            txtPesqHid.setEnabled(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void filtro_civ_sem() {
        situacao = "Aberta";
        String sql = "select id_civil_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_semestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreCiv.setModel(DbUtils.resultSetToTableModel(rs));
//            txtPesqCiv.setEnabled(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void filtro_ref_sem() {
        situacao = "Aberta";
        String sql = "select id_refrigeracao_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from form_refrigeracao_semestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
//            txtPesqRef.setEnabled(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void eletrica_sem() {

        String sql = "select count(*) from form_eletrica_semestral where iduser = ? and situacao_prev = ? or iduser = ? and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao1);
            pst.setString(3, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtPesqEle.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_ele_sem() {

        int setar = tblPreEle1.getSelectedRow();
        txtIdPrevAlt.setText(tblPreEle1.getModel().getValueAt(setar, 0).toString());
        idprev = (tblPreEle1.getModel().getValueAt(setar, 0).toString());
        txtIdPrevIni.setText(tblPreEle1.getModel().getValueAt(setar, 0).toString());
        txtTipPreAlt.setText(tblPreEle1.getModel().getValueAt(setar, 3).toString());
        tipo = (tblPreEle1.getModel().getValueAt(setar, 3).toString());
        txtTipPreIni.setText(tblPreEle1.getModel().getValueAt(setar, 3).toString());
        botão();
        btnIniPrev.setEnabled(true);

    }

    private void civil_sem() {

        String sql = "select count(*) from form_civil_semestral where iduser = ? and situacao_prev = ? or iduser = ? and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao1);
            pst.setString(3, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtPesqCiv.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_civil_sem() {

        int setar = tblPreCiv.getSelectedRow();
        txtIdPrevAlt.setText(tblPreCiv.getModel().getValueAt(setar, 0).toString());
        idprev = (tblPreCiv.getModel().getValueAt(setar, 0).toString());
        txtIdPrevIni.setText(tblPreCiv.getModel().getValueAt(setar, 0).toString());
        txtTipPreAlt.setText(tblPreCiv.getModel().getValueAt(setar, 3).toString());
        tipo = (tblPreCiv.getModel().getValueAt(setar, 3).toString());
        txtTipPreIni.setText(tblPreCiv.getModel().getValueAt(setar, 3).toString());
        botão();
        btnIniPrev.setEnabled(true);
    }

    private void hidraulica_sem() {

        String sql = "select count(*) from form_hidraulica_semestral where iduser = ? and situacao_prev = ? or iduser = ? and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao1);
            pst.setString(3, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtPesqHid.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_hid_sem() {

        int setar = tblPreHid1.getSelectedRow();
        txtIdPrevAlt.setText(tblPreHid1.getModel().getValueAt(setar, 0).toString());
        idprev = (tblPreHid1.getModel().getValueAt(setar, 0).toString());
        txtIdPrevIni.setText(tblPreHid1.getModel().getValueAt(setar, 0).toString());
        txtTipPreAlt.setText(tblPreHid1.getModel().getValueAt(setar, 3).toString());
        tipo = (tblPreHid1.getModel().getValueAt(setar, 3).toString());
        txtTipPreIni.setText(tblPreHid1.getModel().getValueAt(setar, 3).toString());
        botão();
        btnIniPrev.setEnabled(true);
    }

    private void refrigeracao_sem() {

        String sql = "select count(*) from form_refrigeracao_semestral where iduser = ? and situacao_prev = ? or iduser = ? and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao1);
            pst.setString(3, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtPesqRef.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }

    private void setar_ref_sem() {

        int setar = tblPreRef.getSelectedRow();
        txtIdPrevAlt.setText(tblPreRef.getModel().getValueAt(setar, 0).toString());
        idprev = (tblPreRef.getModel().getValueAt(setar, 0).toString());
        txtIdPrevIni.setText(tblPreRef.getModel().getValueAt(setar, 0).toString());
        txtTipPreAlt.setText(tblPreRef.getModel().getValueAt(setar, 3).toString());
        tipo = (tblPreRef.getModel().getValueAt(setar, 3).toString());
        txtTipPreIni.setText(tblPreRef.getModel().getValueAt(setar, 3).toString());
        botão();
        btnIniPrev.setEnabled(true);
    }

    private void botão() {

        if (tip.equals("Superadmin")) {
            btnAltPrev.setEnabled(true);
        }
        if (tip.equals("Admin")) {
            btnAltPrev.setEnabled(true);
        }
        if (tip.equals("User")) {
            txtIdPrevAlt.setText(null);
            txtTipPreAlt.setText(null);

        }
    }

    private void select_preventiva() {

        String preventiva = null;
        preventiva = txtTipPreIni.getText();

        if (preventiva.equals("Eletrica - Semestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev8 = txtIdPrevIni.getText();
                TelaEletricaSem prev_men = new TelaEletricaSem();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_eletr_sem();
                prev_men.txtDatIni8.setText(format);
                prev_men.prev_ini();

                dispose();
            }
        }
        if (preventiva.equals("Hidráulica - Semestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev10 = txtIdPrevIni.getText();
                TelaHidraulicaSem prev_men = new TelaHidraulicaSem();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_hidra_sem();
                prev_men.txtDatIni10.setText(format);
                prev_men.prev_ini();

                dispose();
            }
        }
        if (preventiva.equals("Civil - Semestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev9 = txtIdPrevIni.getText();
                TelaCivilSem prev_men = new TelaCivilSem();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_civil_sem();
                prev_men.txtDatIni9.setText(format);
                prev_men.prev_ini();

                dispose();
            }

        }
        if (preventiva.equals("Refrigeração - Semestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev11 = txtIdPrevIni.getText();
                TelaRefrigeracaoSem prev_men = new TelaRefrigeracaoSem();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_ref_sem();
                prev_men.txtDatIni11.setText(format);
                prev_men.prev_ini();

                dispose();
            }
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

        txtPesqEle = new javax.swing.JTextField();
        txtPesqHid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPesqRef = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPesqCiv = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPreCiv = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPreHid1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPreRef = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPreEle1 = new javax.swing.JTable();
        btnAltPrev = new javax.swing.JButton();
        btnIniPrev = new javax.swing.JButton();
        txtIdPrevAlt = new javax.swing.JTextField();
        txtIdPrevIni = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTipPreIni = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTipPreAlt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Lista de Preventivas Semestral");
        setPreferredSize(new java.awt.Dimension(1007, 672));
        getContentPane().setLayout(null);

        txtPesqEle.setEditable(false);
        txtPesqEle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtPesqEle);
        txtPesqEle.setBounds(40, 535, 180, 28);

        txtPesqHid.setEditable(false);
        txtPesqHid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtPesqHid);
        txtPesqHid.setBounds(40, 590, 180, 28);

        jLabel2.setBackground(new java.awt.Color(0, 0, 51));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("N° Preventivas Eletrica");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(52, 510, 160, 28);

        jLabel3.setBackground(new java.awt.Color(0, 0, 51));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("N° Preventivas Hidraulica");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(39, 565, 180, 28);

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("N° Preventivas Refrigeração");
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(293, 510, 210, 28);

        txtPesqRef.setEditable(false);
        txtPesqRef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtPesqRef);
        txtPesqRef.setBounds(310, 535, 180, 28);

        jLabel5.setBackground(new java.awt.Color(0, 0, 51));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("N° Preventivas  Civil");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(330, 565, 140, 28);

        txtPesqCiv.setEditable(false);
        txtPesqCiv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesqCiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesqCivActionPerformed(evt);
            }
        });
        getContentPane().add(txtPesqCiv);
        txtPesqCiv.setBounds(310, 590, 180, 28);

        tblPreCiv = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblPreCiv.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblPreCiv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Preventiva", "R.E", "Tècnico ", "Preventiva", "Setor", "Equipamento", "Cod. Equipamento", "Distribuição"
            }
        ));
        tblPreCiv.getTableHeader().setReorderingAllowed(false);
        tblPreCiv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPreCivMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPreCiv);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(128, 426, 840, 76);

        tblPreHid1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblPreHid1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblPreHid1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Preventiva", "R.E", "Tècnico ", "Preventiva", "Setor", "Equipamento", "Cod. Equipamento", "Distribuição"
            }
        ));
        tblPreHid1.getTableHeader().setReorderingAllowed(false);
        tblPreHid1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPreHid1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPreHid1);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(128, 266, 840, 76);

        tblPreRef = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblPreRef.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblPreRef.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Preventiva", "R.E", "Tècnico ", "Preventiva", "Setor", "Equipamento", "Cod. Equipamento", "Distribuição"
            }
        ));
        tblPreRef.getTableHeader().setReorderingAllowed(false);
        tblPreRef.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPreRefMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPreRef);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(128, 346, 840, 76);

        tblPreEle1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblPreEle1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblPreEle1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Preventiva", "R.E", "Tècnico ", "Preventiva", "Setor", "Equipamento", "Cod. Equipamento", "Distribuição"
            }
        ));
        tblPreEle1.getTableHeader().setReorderingAllowed(false);
        tblPreEle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPreEle1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPreEle1);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(128, 186, 840, 76);

        btnAltPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48757_document_edit_edit_document.png"))); // NOI18N
        btnAltPrev.setToolTipText("Alterar Preventiva");
        btnAltPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltPrevActionPerformed(evt);
            }
        });
        getContentPane().add(btnAltPrev);
        btnAltPrev.setBounds(596, 528, 64, 64);

        btnIniPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48755_add_document_add_document.png"))); // NOI18N
        btnIniPrev.setToolTipText("Iniciar Preventiva");
        btnIniPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniPrevActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniPrev);
        btnIniPrev.setBounds(786, 528, 64, 64);

        txtIdPrevAlt.setEditable(false);
        txtIdPrevAlt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdPrevAlt);
        txtIdPrevAlt.setBounds(684, 530, 70, 28);

        txtIdPrevIni.setEditable(false);
        txtIdPrevIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdPrevIni);
        txtIdPrevIni.setBounds(874, 530, 70, 28);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("N° Preventiva");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(868, 514, 80, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("N° Preventiva");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(680, 514, 78, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tipo Preventiva");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(864, 560, 90, 14);

        txtTipPreIni.setEditable(false);
        txtTipPreIni.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtTipPreIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtTipPreIni);
        txtTipPreIni.setBounds(854, 572, 110, 28);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tipo Preventiva");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(674, 560, 90, 14);

        txtTipPreAlt.setEditable(false);
        txtTipPreAlt.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtTipPreAlt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtTipPreAlt);
        txtTipPreAlt.setBounds(664, 572, 110, 28);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/prev sem.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, -10, 1008, 660);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tipo Preventiva");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(860, 560, 90, 14);

        jTextField3.setText("jTextField1");
        getContentPane().add(jTextField3);
        jTextField3.setBounds(860, 570, 100, 28);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Tipo Preventiva");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(860, 560, 90, 14);

        jTextField4.setText("jTextField1");
        getContentPane().add(jTextField4);
        jTextField4.setBounds(860, 570, 100, 28);

        setBounds(0, 0, 1007, 672);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesqCivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesqCivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesqCivActionPerformed

    private void tblPreEle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreEle1MouseClicked
        // TODO add your handling code here:
        setar_ele_sem();
    }//GEN-LAST:event_tblPreEle1MouseClicked

    private void tblPreCivMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreCivMouseClicked
        // TODO add your handling code here:
        setar_civil_sem();
    }//GEN-LAST:event_tblPreCivMouseClicked

    private void tblPreHid1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreHid1MouseClicked
        // TODO add your handling code here:
        setar_hid_sem();
    }//GEN-LAST:event_tblPreHid1MouseClicked

    private void tblPreRefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreRefMouseClicked
        // TODO add your handling code here:
        setar_ref_sem();
    }//GEN-LAST:event_tblPreRefMouseClicked

    private void btnAltPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltPrevActionPerformed
        // TODO add your handling code here:
        TelaDisPreventiva dis = new TelaDisPreventiva();
        dis.setVisible(true);
        getParent().add(dis);
        dis.pesquisar_pre();

        dispose();
    }//GEN-LAST:event_btnAltPrevActionPerformed

    private void btnIniPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniPrevActionPerformed
        // TODO add your handling code here:
        select_preventiva();
    }//GEN-LAST:event_btnIniPrevActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAltPrev;
    private javax.swing.JButton btnIniPrev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable tblPreCiv;
    public static javax.swing.JTable tblPreEle1;
    private javax.swing.JTable tblPreHid1;
    private javax.swing.JTable tblPreRef;
    private javax.swing.JTextField txtIdPrevAlt;
    private javax.swing.JTextField txtIdPrevIni;
    private javax.swing.JTextField txtPesqCiv;
    private javax.swing.JTextField txtPesqEle;
    private javax.swing.JTextField txtPesqHid;
    private javax.swing.JTextField txtPesqRef;
    private javax.swing.JTextField txtTipPreAlt;
    private javax.swing.JTextField txtTipPreIni;
    // End of variables declaration//GEN-END:variables
}
