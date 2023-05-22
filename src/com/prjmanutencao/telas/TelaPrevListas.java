/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import com.prjmanutencao.dal.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

import static com.prjmanutencao.telas.TelaDisPreventiva.idprev;
import static com.prjmanutencao.telas.TelaDisPreventiva.tipo;
import static com.prjmanutencao.telas.TelaLogin.tip;

import static com.prjmanutencao.telas.TelaEletricaMen.id_prev;
import static com.prjmanutencao.telas.TelaHidraulicaMen1.id_prev1;
import static com.prjmanutencao.telas.TelaCivilMen1.id_prev2;
import static com.prjmanutencao.telas.TelaCivilSem.id_prev9;
import static com.prjmanutencao.telas.TelaCivilTrimestral.id_prev6;
import static com.prjmanutencao.telas.TelaEletricaSem.id_prev8;
import static com.prjmanutencao.telas.TelaEletricaTrimestral.id_prev4;
import static com.prjmanutencao.telas.TelaHidraulicaSem.id_prev10;
import static com.prjmanutencao.telas.TelaHidraulicaTrimestral.id_prev5;
import static com.prjmanutencao.telas.TelaRefrigeracaoMen1.id_prev3;

import static com.prjmanutencao.telas.TelaLogin.id;
import static com.prjmanutencao.telas.TelaRefrigeracaoSem.id_prev11;
import static com.prjmanutencao.telas.TelaRefrigeracaoTrimestral.id_prev7;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Fábio
 */
public class TelaPrevListas extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String usu = null;

//    public static String id = null;
    public static String nome = null;
    String si = null;
    String set = null;
    String situacao = null;
    String situacao1 = null;
    String t = null;
    String tempo = null;

//    int id2 = 0;
    /**
     * Creates new form TelaPrevMensal
     */
    public TelaPrevListas() {
        initComponents();
        conexao = ModuloConexao.conector();
        ((DefaultTableModel) tblPreEle.getModel()).setRowCount(0);
        ((DefaultTableModel) tblPreHid.getModel()).setRowCount(0);
        ((DefaultTableModel) tblPreRef.getModel()).setRowCount(0);
        ((DefaultTableModel) tblPreCivil.getModel()).setRowCount(0);

        JTableHeader header, header1, header2, header3;

        header = tblPreEle.getTableHeader();
        DefaultTableCellRenderer centralizado = (DefaultTableCellRenderer) header.getDefaultRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        header1 = tblPreHid.getTableHeader();
        DefaultTableCellRenderer centralizado1 = (DefaultTableCellRenderer) header1.getDefaultRenderer();
        centralizado1.setHorizontalAlignment(SwingConstants.CENTER);

        header2 = tblPreCivil.getTableHeader();
        DefaultTableCellRenderer centralizado2 = (DefaultTableCellRenderer) header2.getDefaultRenderer();
        centralizado2.setHorizontalAlignment(SwingConstants.CENTER);

        header3 = tblPreRef.getTableHeader();
        DefaultTableCellRenderer centralizado3 = (DefaultTableCellRenderer) header3.getDefaultRenderer();
        centralizado3.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void inicializacao() {

        try {

            if (t.equals("Listas")) {
                ImageIcon ico = new ImageIcon("C:\\Users\\fabio\\OneDrive\\Área de Trabalho\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\telas\\imagens_fundo\\lista de preventivas.png");
                lblPreEnc.setIcon(ico);
                selec_encerrad();
            }
            if (t.equals("Iniciadas")) {
                ImageIcon ico = new ImageIcon("C:\\Users\\fabio\\OneDrive\\Área de Trabalho\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\telas\\imagens_fundo\\prev abertas.png");
                lblPreEnc.setIcon(ico);
                selec_aberta();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void selec_encerrad() {
        filtroUsu();
        limpar();
        try {

            if (si.equals("Mensal")) {
                ele_men();
                ele_men_quantida();
                hid_men();
                hid_men_quantida();
                civ_men();
                civ_men_quantida();
                ref_men();
                ref_men_quantida();
            }

            if (si.equals("Trimestral")) {
                ele_tri();
                ele_tri_quantida();
                hid_tri();
                hid_tri_quantida();
                civ_tri();
                civ_tri_quantida();
                ref_tri();
                ref_tri_quantida();

            }
            if (si.equals("Semestral")) {
                ele_sem();
                ele_sem_quantida();
                hid_sem();
                hid_sem_quantida();
                civ_sem();
                civ_sem_quantida();
                ref_sem();
                ref_sem_quantida();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void selec_aberta() {
        filtroUsu();
        limpar();
        try {

            if (si.equals("Mensal")) {
                ele_men_ini();
                ele_men_quantida1();
                hid_men_ini();
                hid_men_quantida1();
                civ_men_ini();
                civ_men_quantida1();
                ref_men_ini();
                ref_men_quantida1();
            }

            if (si.equals("Trimestral")) {
                ele_tri_ini();
                ele_tri_quantida1();
                hid_tri_ini();
                hid_tri_quantida1();
                civ_tri_ini();
                civ_tri_quantida1();
                ref_tri_ini();
                ref_tri_quantida1();

            }
            if (si.equals("Semestral")) {
                ele_sem_ini();
                ele_sem_quantida1();
                hid_sem_ini();
                hid_sem_quantida1();
                civ_sem_ini();
                civ_sem_quantida1();
                ref_sem_ini();
                ref_sem_quantida1();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void filtroUsu() {

        String sql = "select tipo_usuario from usuarios where iduser = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                usu = (rs.getString(1));
                fitro();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void fitro() {
        try {
            switch (usu) {

                case "Técnico - Elética":
//                    tblPreEle.setForeground(java.awt.Color.red);

                    tblPreRef.setForeground(java.awt.Color.red);
                    tblPreHid.setForeground(java.awt.Color.red);
                    tblPreCivil.setForeground(java.awt.Color.red);
                    break;
                case "Técnico - Hidráulica":
//                    tblPreHid.setForeground(java.awt.Color.red);

                    tblPreEle.setForeground(java.awt.Color.red);
                    tblPreCivil.setForeground(java.awt.Color.red);
                    tblPreRef.setForeground(java.awt.Color.red);
                    break;
                case "Técnico - Civil":
//                    tblPreCivil.setForeground(java.awt.Color.red);

                    tblPreRef.setForeground(java.awt.Color.red);
                    tblPreHid.setForeground(java.awt.Color.red);
                    tblPreEle.setForeground(java.awt.Color.red);
                    break;
                case "Técnico - Refrigeração":
//                    tblPreRef.setForeground(java.awt.Color.red);

                    tblPreEle.setForeground(java.awt.Color.red);
                    tblPreHid.setForeground(java.awt.Color.red);
                    tblPreCivil.setForeground(java.awt.Color.red);
                    break;
                default:
                    break;

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_men() {
        situacao1 = " ";

        String sql = "select id_form_ele_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_eletrica_mensal where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_men_pes() {

        String sql = "select id_form_ele_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_eletrica_mensal where iduser = ? and situacao_prev is null and id_form_ele_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_men_quantida() {

        String sql = "select count(*) from form_eletrica_mensal where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaEle.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_tri() {
        situacao1 = " ";

        String sql = "select id_form_ele_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_eletrica_trimestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_tri_pes() {

        String sql = "select id_form_ele_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_eletrica_trimestral where iduser = ? and situacao_prev is null and id_form_ele_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_tri_quantida() {

        String sql = "select count(*) from form_eletrica_trimestral where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaEle.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_sem() {
        situacao1 = " ";

        String sql = "select id_form_ele_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_eletrica_semestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_sem_pes() {

        String sql = "select id_form_ele_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_eletrica_semestral where iduser = ? and situacao_prev is null and id_form_ele_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_sem_quantida() {

        String sql = "select count(*) from form_eletrica_semestral where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaEle.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_ele() {
        set = "Eletrica";
        int setar = tblPreEle.getSelectedRow();
        txtIdPrevAlt.setText(tblPreEle.getModel().getValueAt(setar, 0).toString());
        idprev = (tblPreEle.getModel().getValueAt(setar, 0).toString());
        txtIdPrevIni.setText(tblPreEle.getModel().getValueAt(setar, 0).toString());
        txtTipPreAlt.setText(tblPreEle.getModel().getValueAt(setar, 3).toString());
        tipo = (tblPreEle.getModel().getValueAt(setar, 3).toString());
        txtTipPreIni.setText(tblPreEle.getModel().getValueAt(setar, 3).toString());
        botão();
        btnAltPrev.setEnabled(true);
        btnIniPrev.setEnabled(true);

        txtTipPreIni.setText(set + " - " + si);
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
    }

    /*
    public void tempo() {
        String CLASS = "Aberta";

        tblPreEle.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Color c;
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c = Color.BLACK;
                Object texto = table.getValueAt(row, 7);
                if (texto != null && CLASS.equals(texto.toString())) //                    c = Color.RED;
                {
                    l.setForeground(java.awt.Color.red);
                }
                {
//                    l.setForeground(java.awt.Color.red);
                }

                return l;
//                return null;
            }

        });
    }
     */
    private void hid_men() {
        situacao1 = " ";

        String sql = "select id_hidraulica_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_hidraulica_mensal where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_men_pes() {

        situacao1 = " ";

        String sql = "select id_hidraulica_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_hidraulica_mensal where iduser = ?  and situacao_prev is null and id_hidraulica_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqHid.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_men_quantida() {

        String sql = "select count(*) from form_hidraulica_mensal where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaHid.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_tri() {
        situacao1 = " ";

        String sql = "select id_hidraulica_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_hidraulica_trimestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_tri_pes() {

        situacao1 = " ";

        String sql = "select id_hidraulica_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_hidraulica_trimestral where iduser = ?  and situacao_prev is null and id_hidraulica_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqHid.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_tri_quantida() {

        String sql = "select count(*) from form_hidraulica_trimestral where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaHid.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_sem() {
        situacao1 = " ";;

        String sql = "select id_hidraulica_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_hidraulica_semestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_sem_pes() {

        situacao1 = " ";

        String sql = "select id_hidraulica_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_hidraulica_semestral where iduser = ?  and situacao_prev is null and id_hidraulica_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqHid.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_sem_quantida() {

        String sql = "select count(*) from form_hidraulica_semestral where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaHid.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_hid() {
        set = "Hidráulica";
        int setar = tblPreHid.getSelectedRow();
        txtIdPrevAlt.setText(tblPreHid.getModel().getValueAt(setar, 0).toString());
        idprev = (tblPreHid.getModel().getValueAt(setar, 0).toString());
        txtIdPrevIni.setText(tblPreHid.getModel().getValueAt(setar, 0).toString());
        txtTipPreAlt.setText(tblPreHid.getModel().getValueAt(setar, 3).toString());
        tipo = (tblPreHid.getModel().getValueAt(setar, 3).toString());
        txtTipPreIni.setText(tblPreHid.getModel().getValueAt(setar, 3).toString());
        botão();
        btnAltPrev.setEnabled(true);
        btnIniPrev.setEnabled(true);

        txtTipPreIni.setText(set + " - " + si);
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
    }

    private void civ_men() {
        situacao1 = " ";

        String sql = "select id_civil_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_civil_mensal where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_men_pes() {

        situacao1 = " ";

        String sql = "select id_civil_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_civil_mensal where iduser = ? and situacao_prev is null and id_civil_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqCiv.getText() + "%");
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_men_quantida() {

        String sql = "select count(*) from form_civil_mensal where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaCiv.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_tri() {
        situacao1 = " ";

        String sql = "select id_civil_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_civil_trimestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_tri_pes() {

        String sql = "select id_civil_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from form_civil_trimestral where iduser = ? and situacao_prev is null and id_civil_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqCiv.getText() + "%");
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_sem() {
        situacao1 = " ";

        String sql = "select id_civil_semestral as 'N° Preventiva',iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_civil_semestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_tri_quantida() {

        String sql = "select count(*) from form_civil_trimestral where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaCiv.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_sem_pes() {

        String sql = "select id_civil_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_civil_semestral where iduser = ? and situacao_prev is null and id_civil_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqCiv.getText() + "%");
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_sem_quantida() {

        String sql = "select count(*) from form_civil_semestral where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaCiv.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_civ() {
        set = "Civil";
        int setar = tblPreCivil.getSelectedRow();
        txtIdPrevAlt.setText(tblPreCivil.getModel().getValueAt(setar, 0).toString());
        idprev = (tblPreCivil.getModel().getValueAt(setar, 0).toString());
        txtIdPrevIni.setText(tblPreCivil.getModel().getValueAt(setar, 0).toString());
        txtTipPreAlt.setText(tblPreCivil.getModel().getValueAt(setar, 3).toString());
        tipo = (tblPreCivil.getModel().getValueAt(setar, 3).toString());
        txtTipPreIni.setText(tblPreCivil.getModel().getValueAt(setar, 3).toString());
        botão();
        btnAltPrev.setEnabled(true);
        btnIniPrev.setEnabled(true);

        txtTipPreIni.setText(set + " - " + si);
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
    }

    private void ref_men() {
        situacao1 = " ";

        String sql = "select id_refrigeracao_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from form_refrigeracao_mensal where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_men_pes() {

        String sql = "select id_refrigeracao_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_refrigeracao_mensal where iduser = ? and situacao_prev is null and id_refrigeracao_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqRef.getText() + "%");
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_men_quantida() {

        String sql = "select count(*) from form_refrigeracao_mensal where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaRef.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_tri() {
        situacao1 = " ";

        String sql = "select id_refrigeracao_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from form_refrigeracao_trimestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_tri_pes() {

        String sql = "select id_refrigeracao_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_refrigeracao_trimestral where iduser = ? and situacao_prev is null and id_refrigeracao_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqRef.getText() + "%");
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_tri_quantida() {

        String sql = "select count(*) from form_refrigeracao_trimestral where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaRef.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_sem() {
        situacao1 = " ";

        String sql = "select id_refrigeracao_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_refrigeracao_semestral where iduser = ? and situacao_prev is null or iduser = ? and situacao_prev = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, situacao1);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_sem_pes() {

        String sql = "select id_refrigeracao_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', tempo_dis as Distribuição from  form_refrigeracao_semestral where iduser = ? and situacao_prev is null and id_refrigeracao_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, txtPesqRef.getText() + "%");
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_sem_quantida() {

        String sql = "select count(*) from form_refrigeracao_semestral where iduser = ?  and situacao_prev is null ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaRef.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_ref() {
        set = "Refrigeração";
        int setar = tblPreRef.getSelectedRow();
        txtIdPrevAlt.setText(tblPreRef.getModel().getValueAt(setar, 0).toString());
        idprev = (tblPreRef.getModel().getValueAt(setar, 0).toString());
        txtIdPrevIni.setText(tblPreRef.getModel().getValueAt(setar, 0).toString());
        txtTipPreAlt.setText(tblPreRef.getModel().getValueAt(setar, 3).toString());
        tipo = (tblPreRef.getModel().getValueAt(setar, 3).toString());
        txtTipPreIni.setText(tblPreRef.getModel().getValueAt(setar, 3).toString());
        botão();
        btnAltPrev.setEnabled(true);
        btnIniPrev.setEnabled(true);

        txtTipPreIni.setText(set + " - " + si);
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
    }

    private void select_preventiva() {

        String preventiva;
        preventiva = txtTipPreIni.getText();

        if (preventiva.equals("Eletrica - Mensal")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev = txtIdPrevIni.getText();
                TelaEletricaMen prev_men = new TelaEletricaMen();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_eletr_mensal();
                TelaEletricaMen.txtDatIni.setText(format);
                prev_men.prev_ini();

                dispose();
            }
        }
        if (preventiva.equals("Hidráulica - Mensal")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev1 = txtIdPrevIni.getText();
                TelaHidraulicaMen1 prev_men = new TelaHidraulicaMen1();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_hidraulica_mensal();
                TelaHidraulicaMen1.txtDatIni1.setText(format);
                prev_men.prev_ini();

                dispose();
            }
        }
        if (preventiva.equals("Civil - Mensal")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev2 = txtIdPrevIni.getText();
                TelaCivilMen1 prev_men = new TelaCivilMen1();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_civil_mensal();
                TelaCivilMen1.txtDatIni2.setText(format);
                prev_men.prev_ini();

                dispose();
            }

        }
        if (preventiva.equals("Refrigeração - Mensal")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev3 = txtIdPrevIni.getText();
                TelaRefrigeracaoMen1 prev_men = new TelaRefrigeracaoMen1();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_refrigeracao_mensal();
                TelaRefrigeracaoMen1.txtDatIni3.setText(format);
                prev_men.prev_ini();

                dispose();
            }
        }
        if (preventiva.equals("Eletrica - Trimestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev4 = txtIdPrevIni.getText();
                TelaEletricaTrimestral prev_men = new TelaEletricaTrimestral();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_eletr_trim();
                TelaEletricaTrimestral.txtDatIni4.setText(format);
                prev_men.prev_ini();

                dispose();
            }
        }
        if (preventiva.equals("Hidráulica - Trimestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev5 = txtIdPrevIni.getText();
                TelaHidraulicaTrimestral prev_men = new TelaHidraulicaTrimestral();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_hidra_trim();
                TelaHidraulicaTrimestral.txtDatIni5.setText(format);
                prev_men.prev_ini();

                dispose();
            }
        }
        if (preventiva.equals("Civil - Trimestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev6 = txtIdPrevIni.getText();
                TelaCivilTrimestral prev_men = new TelaCivilTrimestral();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_civ_tri();
                TelaCivilTrimestral.txtDatIni6.setText(format);
                prev_men.prev_ini();

                dispose();
            }

        }
        if (preventiva.equals("Refrigeração - Trimestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                ZonedDateTime zdtNow = ZonedDateTime.now();
                String format = data_hora.format(zdtNow);

                id_prev7 = txtIdPrevIni.getText();
                TelaRefrigeracaoTrimestral prev_men = new TelaRefrigeracaoTrimestral();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_refri_trimestral();
                TelaRefrigeracaoTrimestral.txtDatIni7.setText(format);
                prev_men.prev_ini();

                dispose();
            }
        }
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
                TelaEletricaSem.txtDatIni8.setText(format);
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
                TelaHidraulicaSem.txtDatIni10.setText(format);
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
                TelaCivilSem.txtDatIni9.setText(format);
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
                TelaRefrigeracaoSem.txtDatIni11.setText(format);
                prev_men.prev_ini();

                dispose();
            }
        }

    }

    private void ele_men_ini() {
        situacao = "Aberta";

        String sql = "select id_form_ele_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_eletrica_mensal where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_men_pes1() {

        situacao = "Aberta";

        String sql = "select id_form_ele_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_eletrica_mensal where iduser = ? and situacao_prev = ? and id_form_ele_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_men_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_eletrica_mensal where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaEle.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_tri_ini() {
        situacao = "Aberta";

        String sql = "select id_form_ele_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_eletrica_trimestral where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_tri_pes1() {

        situacao = "Aberta";

        String sql = "select id_form_ele_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_eletrica_trimestral where iduser = ? and situacao_prev = ? and id_form_ele_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_tri_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_eletrica_trimestral where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaEle.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_sem_ini() {
        situacao = "Aberta";

        String sql = "select id_form_ele_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_eletrica_semestral where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_sem_pes1() {

        situacao = "Aberta";

        String sql = "select id_form_ele_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_eletrica_semestral where iduser = ? and situacao_prev = ? and id_form_ele_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_sem_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_eletrica_semestral where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaEle.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_men_ini() {
        situacao = "Aberta";

        String sql = "select id_hidraulica_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_hidraulica_mensal where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_men_pes1() {

        situacao = "Aberta";

        String sql = "select id_hidraulica_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_hidraulica_mensal where iduser = ? and situacao_prev = ? and id_hidraulica_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqHid.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_men_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_hidraulica_mensal where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaHid.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_tri_ini() {
        situacao = "Aberta";

        String sql = "select id_hidraulica_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_hidraulica_trimestral where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_tri_pes1() {

        situacao = "Aberta";

        String sql = "select id_hidraulica_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_hidraulica_trimestral where iduser = ? and situacao_prev = ? and id_hidraulica_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqHid.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_tri_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_hidraulica_trimestral where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaHid.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_sem_ini() {
        situacao = "Aberta";

        String sql = "select id_hidraulica_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_hidraulica_semestral where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_sem_pes1() {

        situacao = "Aberta";

        String sql = "select id_hidraulica_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_hidraulica_semestral where iduser = ? and situacao_prev = ? and id_hidraulica_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqHid.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_sem_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_hidraulica_semestral where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaHid.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_men_ini() {
        situacao = "Aberta";

        String sql = "select id_refrigeracao_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_refrigeracao_mensal where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_men_pes1() {

        situacao = "Aberta";

        String sql = "select id_refrigeracao_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_refrigeracao_mensal where iduser = ? and situacao_prev = ? and id_refrigeracao_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqRef.getText() + "%");
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_men_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_refrigeracao_mensal where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaRef.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_tri_ini() {
        situacao = "Aberta";

        String sql = "select id_refrigeracao_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_refrigeracao_trimestral where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_tri_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_refrigeracao_trimestral where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaRef.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_tri_pes1() {

        situacao = "Aberta";

        String sql = "select id_refrigeracao_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_refrigeracao_trimestral where iduser = ? and situacao_prev = ? and id_refrigeracao_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqRef.getText() + "%");
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_sem_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_refrigeracao_semestral where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaRef.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_sem_ini() {
        situacao = "Aberta";

        String sql = "select id_refrigeracao_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_refrigeracao_semestral where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_sem_pes1() {

        situacao = "Aberta";

        String sql = "select id_refrigeracao_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_refrigeracao_semestral where iduser = ? and situacao_prev = ? and id_refrigeracao_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqRef.getText() + "%");
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_men_ini() {
        situacao = "Aberta";

        String sql = "select id_civil_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_civil_mensal where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_men_pes1() {

        situacao = "Aberta";

        String sql = "select id_civil_mensal as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_civil_mensal where iduser = ? and situacao_prev = ? and id_civil_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqCiv.getText() + "%");
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_men_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_civil_mensal where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaCiv.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_tri_ini() {
        situacao = "Aberta";

        String sql = "select id_civil_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_civil_trimestral where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_tri_pes1() {

        situacao = "Aberta";

        String sql = "select id_civil_trimestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_civil_trimestral where iduser = ? and situacao_prev = ? and id_civil_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqCiv.getText() + "%");
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_tri_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_civil_trimestral where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaCiv.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_sem_ini() {
        situacao = "Aberta";

        String sql = "select id_civil_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from  form_civil_semestral where iduser = ? and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_sem_pes1() {

        situacao = "Aberta";

        String sql = "select id_civil_semestral as 'N° Preventiva', iduser as 'R.E', nome_prev as Técnico, tipo_prev as 'Preventiva', setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equip.', situacao_prev as 'Situação', tempo_dis as Distribuição from form_civil_semestral where iduser = ? and situacao_prev = ? and id_civil_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            pst.setString(3, txtPesqCiv.getText() + "%");
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_sem_quantida1() {
        situacao = "Aberta";

        String sql = "select count(*) from form_civil_semestral where iduser = ?  and situacao_prev = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, situacao);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaCiv.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void select_preventiva1() {

        String preventiva = null;
        preventiva = txtTipPreIni.getText();

        if (preventiva.equals("Eletrica - Mensal")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev = txtIdPrevIni.getText();
                TelaEletricaMen prev_men = new TelaEletricaMen();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_eletr_mensal1();

                dispose();
            }
        }
        if (preventiva.equals("Hidráulica - Mensal")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev1 = txtIdPrevIni.getText();
                TelaHidraulicaMen1 prev_men = new TelaHidraulicaMen1();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_hidra_mensal1();

                dispose();
            }
        }
        if (preventiva.equals("Civil - Mensal")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev2 = txtIdPrevIni.getText();
                TelaCivilMen1 prev_men = new TelaCivilMen1();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_civil_mensal1();

                dispose();
            }

        }
        if (preventiva.equals("Refrigeração - Mensal")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev3 = txtIdPrevIni.getText();
                TelaRefrigeracaoMen1 prev_men = new TelaRefrigeracaoMen1();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_refri_mensal1();

                dispose();
            }
        }
        if (preventiva.equals("Eletrica - Trimestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev4 = txtIdPrevIni.getText();
                TelaEletricaTrimestral prev_men = new TelaEletricaTrimestral();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_eletr_tri1();

                dispose();
            }
        }
        if (preventiva.equals("Hidráulica - Trimestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev5 = txtIdPrevIni.getText();
                TelaHidraulicaTrimestral prev_men = new TelaHidraulicaTrimestral();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_hidr_trim1();

                dispose();
            }
        }
        if (preventiva.equals("Civil - Trimestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev6 = txtIdPrevIni.getText();
                TelaCivilTrimestral prev_men = new TelaCivilTrimestral();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_civil_tri1();

                dispose();
            }
        }
        if (preventiva.equals("Eletrica - Semestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev8 = txtIdPrevIni.getText();
                TelaEletricaSem prev_men = new TelaEletricaSem();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_eletr_sem1();

                dispose();
            }
        }
        if (preventiva.equals("Hidráulica - Semestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev10 = txtIdPrevIni.getText();
                TelaHidraulicaSem prev_men = new TelaHidraulicaSem();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_hidra_sem2();

                dispose();
            }
        }
        if (preventiva.equals("Civil - Semestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev9 = txtIdPrevIni.getText();
                TelaCivilSem prev_men = new TelaCivilSem();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_civ_sem1();

                dispose();
            }

        }
        if (preventiva.equals("Refrigeração - Semestral")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Iniciar a Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                id_prev11 = txtIdPrevIni.getText();
                TelaRefrigeracaoSem prev_men = new TelaRefrigeracaoSem();
                prev_men.setVisible(true);
                getParent().add(prev_men);
                prev_men.select_ref_sem1();

                dispose();
            }
        }

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

    private void limpar() {
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
        txtIdPrevIni.setText(null);
        txtIdPrevAlt.setText(null);
        txtTipPreIni.setText(null);
        txtIdPrevAlt.setText(null);
        txtTipPreAlt.setText(null);
        btnAltPrev.setEnabled(false);
        btnIniPrev.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        txtPesqEle = new javax.swing.JTextField();
        txtPesqHid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPesqRef = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPesqCiv = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPreRef = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblPreEle = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblPreCivil = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblPreHid = new javax.swing.JTable();
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
        rbnPreMen = new javax.swing.JRadioButton();
        rbnPreTri = new javax.swing.JRadioButton();
        rbnPreSem = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rbnPreLis = new javax.swing.JRadioButton();
        rbnPreAbe = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtQuaRef = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtQuaEle = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtQuaCiv = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtQuaHid = new javax.swing.JTextField();
        lblPreEnc = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Preventivas");
        setPreferredSize(new java.awt.Dimension(1007, 672));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        txtPesqEle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesqEle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqEleKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesqEle);
        txtPesqEle.setBounds(60, 218, 80, 24);

        txtPesqHid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesqHid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqHidKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesqHid);
        txtPesqHid.setBounds(60, 301, 80, 24);

        jLabel3.setBackground(new java.awt.Color(0, 0, 51));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/9104277_search_file_folder_document_data_icon.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(139, 298, 30, 30);

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/9104277_search_file_folder_document_data_icon.png"))); // NOI18N
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(139, 381, 30, 30);

        txtPesqRef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesqRef.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqRefKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesqRef);
        txtPesqRef.setBounds(60, 385, 80, 24);

        jLabel5.setBackground(new java.awt.Color(0, 0, 51));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/9104277_search_file_folder_document_data_icon.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(139, 463, 30, 30);

        txtPesqCiv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesqCiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesqCivActionPerformed(evt);
            }
        });
        txtPesqCiv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqCivKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesqCiv);
        txtPesqCiv.setBounds(60, 468, 80, 24);

        tblPreRef = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblPreRef.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblPreRef.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N° Preventiva", "Tècnico ", "Setor", "Equipamento", "Cod. Equipamento", "Distribuição"
            }
        ));
        tblPreRef.getTableHeader().setReorderingAllowed(false);
        tblPreRef.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPreRefMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPreRef);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(178, 332, 766, 76);

        tblPreEle = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblPreEle.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblPreEle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N° Preventiva", "Tècnico ", "Setor", "Equipamento", "Cod. Equipamento", "Distribuição"
            }
        ));
        tblPreEle.getTableHeader().setReorderingAllowed(false);
        tblPreEle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPreEleMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblPreEle);

        getContentPane().add(jScrollPane15);
        jScrollPane15.setBounds(178, 164, 766, 76);

        tblPreCivil = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblPreCivil.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblPreCivil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N° Preventiva", "Tècnico ", "Setor", "Equipamento", "Cod. Equipamento", "Distribuição"
            }
        ));
        tblPreCivil.getTableHeader().setReorderingAllowed(false);
        tblPreCivil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPreCivilMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tblPreCivil);

        getContentPane().add(jScrollPane16);
        jScrollPane16.setBounds(178, 416, 766, 76);

        tblPreHid = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblPreHid.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblPreHid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N° Preventiva", "Tècnico ", "Setor", "Equipamento", "Cod. Equipamento", "Distribuição"
            }
        ));
        tblPreHid.getTableHeader().setReorderingAllowed(false);
        tblPreHid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPreHidMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tblPreHid);

        getContentPane().add(jScrollPane17);
        jScrollPane17.setBounds(178, 248, 766, 76);

        btnAltPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48757_document_edit_edit_document.png"))); // NOI18N
        btnAltPrev.setToolTipText("Alterar Preventiva");
        btnAltPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltPrevActionPerformed(evt);
            }
        });
        getContentPane().add(btnAltPrev);
        btnAltPrev.setBounds(550, 530, 64, 64);

        btnIniPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48755_add_document_add_document.png"))); // NOI18N
        btnIniPrev.setToolTipText("Iniciar Preventiva");
        btnIniPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniPrevActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniPrev);
        btnIniPrev.setBounds(740, 530, 64, 64);

        txtIdPrevAlt.setEditable(false);
        txtIdPrevAlt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdPrevAlt);
        txtIdPrevAlt.setBounds(640, 530, 70, 28);

        txtIdPrevIni.setEditable(false);
        txtIdPrevIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdPrevIni);
        txtIdPrevIni.setBounds(830, 530, 70, 28);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("N° Preventiva");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(826, 514, 80, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("N°");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(46, 468, 16, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tipo Preventiva");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(820, 560, 90, 14);

        txtTipPreIni.setEditable(false);
        txtTipPreIni.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtTipPreIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtTipPreIni);
        txtTipPreIni.setBounds(808, 574, 120, 28);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tipo Preventiva");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(630, 560, 90, 14);

        txtTipPreAlt.setEditable(false);
        txtTipPreAlt.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtTipPreAlt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtTipPreAlt);
        txtTipPreAlt.setBounds(616, 574, 120, 28);

        buttonGroup1.add(rbnPreMen);
        rbnPreMen.setOpaque(false);
        rbnPreMen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPreMenActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPreMen);
        rbnPreMen.setBounds(194, 121, 21, 21);

        buttonGroup1.add(rbnPreTri);
        rbnPreTri.setOpaque(false);
        rbnPreTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPreTriActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPreTri);
        rbnPreTri.setBounds(452, 121, 21, 21);

        buttonGroup1.add(rbnPreSem);
        rbnPreSem.setOpaque(false);
        rbnPreSem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPreSemActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPreSem);
        rbnPreSem.setBounds(712, 121, 21, 21);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/9104277_search_file_folder_document_data_icon.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(138, 214, 32, 30);

        buttonGroup2.add(rbnPreLis);
        rbnPreLis.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rbnPreLis.setText("Lista de Preventivas");
        rbnPreLis.setOpaque(false);
        rbnPreLis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPreLisActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPreLis);
        rbnPreLis.setBounds(52, 108, 140, 21);

        buttonGroup2.add(rbnPreAbe);
        rbnPreAbe.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rbnPreAbe.setText("Preventivas em Aberto");
        rbnPreAbe.setOpaque(false);
        rbnPreAbe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPreAbeActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPreAbe);
        rbnPreAbe.setBounds(52, 132, 142, 21);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 51));
        jLabel11.setText("N° Preventiva");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(634, 514, 78, 20);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 51));
        jLabel13.setText("N°");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(46, 301, 16, 20);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 51));
        jLabel14.setText("N°");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(46, 385, 16, 20);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 51));
        jLabel15.setText("N°");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(46, 220, 16, 20);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Refrigeração");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(120, 564, 120, 22);

        txtQuaRef.setEditable(false);
        txtQuaRef.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtQuaRef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtQuaRef);
        txtQuaRef.setBounds(190, 590, 60, 26);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 51));
        jLabel17.setText("Quantidade");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(108, 590, 82, 26);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Eletrica");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(140, 506, 80, 22);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 51));
        jLabel19.setText("Quantidade");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(108, 532, 82, 26);

        txtQuaEle.setEditable(false);
        txtQuaEle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtQuaEle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtQuaEle);
        txtQuaEle.setBounds(190, 532, 60, 26);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Civil");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(330, 564, 100, 22);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 51));
        jLabel21.setText("Quantidade");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(310, 590, 82, 26);

        txtQuaCiv.setEditable(false);
        txtQuaCiv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtQuaCiv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtQuaCiv);
        txtQuaCiv.setBounds(390, 590, 60, 26);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 51));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Hidráulica");
        getContentPane().add(jLabel22);
        jLabel22.setBounds(330, 506, 100, 22);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 51));
        jLabel23.setText("Quantidade");
        getContentPane().add(jLabel23);
        jLabel23.setBounds(308, 532, 82, 26);

        txtQuaHid.setEditable(false);
        txtQuaHid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtQuaHid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuaHid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuaHidActionPerformed(evt);
            }
        });
        getContentPane().add(txtQuaHid);
        txtQuaHid.setBounds(390, 532, 60, 26);

        lblPreEnc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/lista de preventivas.png"))); // NOI18N
        getContentPane().add(lblPreEnc);
        lblPreEnc.setBounds(-10, -8, 1008, 672);

        setBounds(0, 0, 1007, 672);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesqCivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesqCivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesqCivActionPerformed

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
        if (t.equals("Listas")) {
            select_preventiva();
        }
        if (t.equals("Iniciadas")) {
            select_preventiva1();
        }
    }//GEN-LAST:event_btnIniPrevActionPerformed

    private void tblPreRefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreRefMouseClicked
        // TODO add your handling code here:
        setar_ref();
    }//GEN-LAST:event_tblPreRefMouseClicked

    private void tblPreEleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreEleMouseClicked
        // TODO add your handling code here:
        setar_ele();
    }//GEN-LAST:event_tblPreEleMouseClicked

    private void tblPreCivilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreCivilMouseClicked
        // TODO add your handling code here:
        setar_civ();
    }//GEN-LAST:event_tblPreCivilMouseClicked

    private void tblPreHidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPreHidMouseClicked
        // TODO add your handling code here:
        setar_hid();
    }//GEN-LAST:event_tblPreHidMouseClicked

    private void rbnPreMenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPreMenActionPerformed
        // TODO add your handling code here:
        si = "Mensal";
        if (t.equals("Listas")) {
            selec_encerrad();
        }
        if (t.equals("Iniciadas")) {
            selec_aberta();
        }

    }//GEN-LAST:event_rbnPreMenActionPerformed

    private void rbnPreTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPreTriActionPerformed
        // TODO add your handling code here:
        si = "Trimestral";
        if (t.equals("Listas")) {
            selec_encerrad();
        }
        if (t.equals("Iniciadas")) {
            selec_aberta();
        }
    }//GEN-LAST:event_rbnPreTriActionPerformed

    private void rbnPreSemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPreSemActionPerformed
        // TODO add your handling code here:
        si = "Semestral";
        if (t.equals("Listas")) {
            selec_encerrad();
        }
        if (t.equals("Iniciadas")) {
            selec_aberta();
        }
    }//GEN-LAST:event_rbnPreSemActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        rbnPreMen.setSelected(true);
        rbnPreLis.setSelected(true);
        si = "Mensal";
        t = "Listas";
        selec_encerrad();
        btnAltPrev.setEnabled(false);
        btnIniPrev.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:

    }//GEN-LAST:event_formInternalFrameActivated

    private void txtPesqEleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqEleKeyReleased
        // TODO add your handling code here:
        String v;
        v = txtPesqEle.getText();
        try {
            if (t.equals("Listas")) {
                if (si.equals("Mensal")) {
                    if (v.equals("")) {
                        ele_men();
                    } else {
                        ele_men_pes();
                    }
                }
                if (si.equals("Trimestral")) {
                    if (v.equals("")) {
                        ele_tri();
                    } else {
                        ele_tri_pes();
                    }
                }
                if (si.equals("Semestral")) {
                    if (v.equals("")) {
                        ele_sem();
                    } else {
                        ele_sem_pes();
                    }
                }
            }
            if (t.equals("Iniciadas")) {
                if (si.equals("Mensal")) {
                    if (v.equals("")) {
                        ele_men_ini();
                    } else {
                        ele_men_pes1();
                    }
                }
                if (si.equals("Trimestral")) {
                    if (v.equals("")) {
                        ele_tri_ini();
                    } else {
                        ele_tri_pes1();
                    }
                }
                if (si.equals("Semestral")) {
                    if (v.equals("")) {
                        ele_sem_ini();
                    } else {
                        ele_sem_pes1();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPesqEleKeyReleased

    private void txtPesqHidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqHidKeyReleased
        // TODO add your handling code here:
        String v;
        v = txtPesqHid.getText();

        try {
            if (t.equals("Listas")) {
                if (si.equals("Mensal")) {
                    if (v.equals("")) {
                        hid_men();
                    } else {
                        hid_men_pes();
                    }
                }
                if (si.equals("Trimestral")) {
                    if (v.equals("")) {
                        hid_tri();
                    } else {
                        hid_tri_pes();
                    }
                }
                if (si.equals("Semestral")) {
                    if (v.equals("")) {
                        hid_sem();
                    } else {
                        hid_sem_pes();
                    }
                }
            }
            if (t.equals("Iniciadas")) {
                if (si.equals("Mensal")) {
                    if (v.equals("")) {
                        hid_men_ini();
                    } else {
                        hid_men_pes1();
                    }
                }
                if (si.equals("Trimestral")) {
                    if (v.equals("")) {
                        hid_tri_ini();
                    } else {
                        hid_tri_pes1();
                    }
                }
                if (si.equals("Semestral")) {
                    if (v.equals("")) {
                        hid_sem_ini();
                    } else {
                        hid_sem_pes1();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPesqHidKeyReleased

    private void txtPesqRefKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqRefKeyReleased
        // TODO add your handling code here:
        String v;
        v = txtPesqRef.getText();
        try {
            if (t.equals("Listas")) {
                if (si.equals("Mensal")) {
                    if (v.equals("")) {
                        ref_men();
                    } else {
                        ref_men_pes();
                    }
                }
                if (si.equals("Trimestral")) {
                    if (v.equals("")) {
                        ref_tri();
                    } else {
                        ref_tri_pes();
                    }
                }
                if (si.equals("Semestral")) {
                    if (v.equals("")) {
                        ref_sem();
                    } else {
                        ref_sem_pes();
                    }
                }
            }
            if (t.equals("Iniciadas")) {
                if (si.equals("Mensal")) {
                    if (v.equals("")) {
                        ref_men_ini();
                    } else {
                        ref_men_pes1();
                    }
                }
                if (si.equals("Trimestral")) {
                    if (v.equals("")) {
                        ref_tri_ini();
                    } else {
                        ref_tri_pes1();
                    }
                }
                if (si.equals("Semestral")) {
                    if (v.equals("")) {
                        ref_sem_ini();
                    } else {
                        ref_sem_pes1();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPesqRefKeyReleased

    private void txtPesqCivKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqCivKeyReleased
        // TODO add your handling code here:
        String v;
        v = txtPesqCiv.getText();
        try {
            if (t.equals("Listas")) {
                if (si.equals("Mensal")) {
                    if (v.equals("")) {
                        civ_men();
                    } else {
                        civ_men_pes();
                    }
                }
                if (si.equals("Trimestral")) {
                    if (v.equals("")) {
                        civ_tri();
                    } else {
                        civ_tri_pes();
                    }
                }
                if (si.equals("Semestral")) {
                    if (v.equals("")) {
                        civ_sem();
                    } else {
                        civ_sem_pes();
                    }
                }
            }
            if (t.equals("Iniciadas")) {
                if (si.equals("Mensal")) {
                    if (v.equals("")) {
                        civ_men_ini();
                    } else {
                        civ_men_pes1();
                    }
                }
                if (si.equals("Trimestral")) {
                    if (v.equals("")) {
                        civ_tri_ini();
                    } else {
                        civ_tri_pes1();
                    }
                }
                if (si.equals("Semestral")) {
                    if (v.equals("")) {
                        civ_sem_ini();
                    } else {
                        civ_sem_pes1();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPesqCivKeyReleased

    private void rbnPreLisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPreLisActionPerformed
        // TODO add your handling code here:
        si = "Mensal";
        t = "Listas";
        rbnPreMen.setSelected(true);
        inicializacao();
    }//GEN-LAST:event_rbnPreLisActionPerformed

    private void rbnPreAbeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPreAbeActionPerformed
        // TODO add your handling code here:
        si = "Mensal";
        t = "Iniciadas";
        rbnPreMen.setSelected(true);
        inicializacao();
    }//GEN-LAST:event_rbnPreAbeActionPerformed

    private void txtQuaHidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuaHidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuaHidActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAltPrev;
    private javax.swing.JButton btnIniPrev;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblPreEnc;
    private javax.swing.JRadioButton rbnPreAbe;
    private javax.swing.JRadioButton rbnPreLis;
    private javax.swing.JRadioButton rbnPreMen;
    private javax.swing.JRadioButton rbnPreSem;
    private javax.swing.JRadioButton rbnPreTri;
    public static javax.swing.JTable tblPreCivil;
    public static javax.swing.JTable tblPreEle;
    public static javax.swing.JTable tblPreHid;
    public static javax.swing.JTable tblPreRef;
    private javax.swing.JTextField txtIdPrevAlt;
    private javax.swing.JTextField txtIdPrevIni;
    private javax.swing.JTextField txtPesqCiv;
    private javax.swing.JTextField txtPesqEle;
    private javax.swing.JTextField txtPesqHid;
    private javax.swing.JTextField txtPesqRef;
    private javax.swing.JTextField txtQuaCiv;
    private javax.swing.JTextField txtQuaEle;
    private javax.swing.JTextField txtQuaHid;
    private javax.swing.JTextField txtQuaRef;
    private javax.swing.JTextField txtTipPreAlt;
    private javax.swing.JTextField txtTipPreIni;
    // End of variables declaration//GEN-END:variables
}
