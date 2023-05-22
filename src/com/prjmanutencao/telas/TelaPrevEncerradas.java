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
import static com.prjmanutencao.telas.TelaLogin.id;
import static com.prjmanutencao.telas.TelaPrevEnc3.id12;
import static com.prjmanutencao.telas.TelaPrevEnc3.id11;
import static com.prjmanutencao.telas.TelaPrevEnc3.id10;
import static com.prjmanutencao.telas.TelaPrevEnc2.id9;
import static com.prjmanutencao.telas.TelaPrevEnc2.id8;
import static com.prjmanutencao.telas.TelaPrevEnc2.id7;
import static com.prjmanutencao.telas.TelaPrevEnc1.id6;
import static com.prjmanutencao.telas.TelaPrevEnc1.id5;
import static com.prjmanutencao.telas.TelaPrevEnc1.id4;
import static com.prjmanutencao.telas.TelaPrevEnc.id3;
import static com.prjmanutencao.telas.TelaPrevEnc.id2;
import static com.prjmanutencao.telas.TelaPrevEnc.id1;
import static com.prjmanutencao.telas.TelaPrevListas.tblPreCivil;
import static com.prjmanutencao.telas.TelaPrevListas.tblPreEle;
import static com.prjmanutencao.telas.TelaPrevListas.tblPreHid;
import static com.prjmanutencao.telas.TelaPrevListas.tblPreRef;
import java.awt.Color;
import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Fábio
 */
public class TelaPrevEncerradas extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

//    public static String id = null;
    public static String nome = null;
    String si = null;
    String set = null;
    String data = null;
    String setor = null;
    String tempo = null;
    String tempo1 = null;
    String tempo2 = null;
    String t = null;
    String prev = null;

    /**
     * Creates new form TelaPrevMensal
     */
    public TelaPrevEncerradas() {
        initComponents();
        conexao = ModuloConexao.conector();
        t = "Encerrada";
        tempo = "60";
        tempo1 = "60";
        tempo2 = "60";
//        rbnPesEle.setEnabled(false);
//        ZonedDateTime zdtNow = ZonedDateTime.now();
//        cadDataPre.setDate(Timestamp.from(Instant.from(zdtNow)));

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

//        jDateChooser1.set
//        jDateChooser1.setBounds(560, 530, 110, 28);
//        jDateChooser1.getJCalendar().setPreferredSize(new Dimension(100,420));
//        jDateChooser1.getJCalendar().setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        cadDataPre.getJCalendar().setBounds(25, 25, 25, 25);
//        cadDataPre.getJCalendar().set
//        JCalendar.setDefaultLocale(Locale.UK);
//        java.util.Date pega = cadDataPre.getDate();
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        this.data = formato.format(pega);
//        cadDataPre.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
//            public void propertyChange(PropertyChangeEvent e) {
//                
//            }
//        });
    }

    private void inicializacao() {

        try {

            if (t.equals("Encerrada")) {
                prev = "Encerrada";
                ImageIcon ico = new ImageIcon("C:\\Users\\fabio\\OneDrive\\Área de Trabalho\\prjManutencao1\\src\\com\\prjmanutencao\\telas\\imagens_fundo\\encerradas_1.png");
                lblPreEnc.setIcon(ico);
                selec_encerrad();
                pesq_Bot();
                rbnPreMen.setSelected(true);
                rbnPreMen.setSelected(true);
                tblPreEle.setForeground(Color.blue);
                tblPreHid.setForeground(Color.blue);
                tblPreCivil.setForeground(Color.blue);
                tblPreRef.setForeground(Color.blue);
            }
            if (t.equals("Irregular")) {
                prev = "Irregular";
                ImageIcon ico = new ImageIcon("C:\\Users\\fabio\\OneDrive\\Área de Trabalho\\prjManutencao1\\src\\com\\prjmanutencao\\telas\\imagens_fundo\\irregular_1.png");
                lblPreEnc.setIcon(ico);
                selec_encerrad();
                pesq_Bot();
                rbnPreMen.setSelected(true);
                tblPreEle.setForeground(Color.red);
                tblPreHid.setForeground(Color.red);
                tblPreCivil.setForeground(Color.red);
                tblPreRef.setForeground(Color.red);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void selec_encerrad() {
        limpar();
        try {

            if (si.equals("Mensal")) {
                ele_men_ini();
                hid_men_ini();
                civ_men_ini();
                ref_men_ini();
            }

            if (si.equals("Trimestral")) {
                ele_tri_ini();
                hid_tri_ini();
                civ_tri_ini();
                ref_tri_ini();

            }
            if (si.equals("Semestral")) {
                ele_sem_ini();
                hid_sem_ini();
                civ_sem_ini();
                ref_sem_ini();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void ele_men_ini() {

        String s = prev;

        String sql = "select id_form_ele_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_mensal where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo);
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_men() {

        String s = prev;

        String sql = "select id_form_ele_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_mensal where iduser = ? and situacao_prev = ? and id_form_ele_mensal like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_tri_ini() {

        String s = prev;

        String sql = "select id_form_ele_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_trimestral where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo1);
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_tri() {
        String s = prev;

        String sql = "select id_form_ele_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_trimestral where iduser = ? and situacao_prev = ? and id_form_ele_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_sem() {
        String s = prev;

        String sql = "select id_form_ele_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_semestral where  iduser = ? and situacao_prev = ?  and id_form_ele_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ele_sem_ini() {

        String s = prev;

        String sql = "select id_form_ele_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_semestral where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo2);
            rs = pst.executeQuery();
            tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_ele() {
        set = "Eletrica";
        int setar = tblPreEle.getSelectedRow();
        txtIdPrevIni.setText(tblPreEle.getModel().getValueAt(setar, 0).toString());
        txtTipPreIni.setText(set + " - " + si);
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
        btnIniPrev.setEnabled(true);
    }

    private void hid_men_ini() {

        String s = prev;

        String sql = "select id_hidraulica_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_hidraulica_mensal where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo);
//            pst.setString(4, txtPesqEle.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
//            txtPesqEle.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_men() {
        String s = prev;

        String sql = "select id_hidraulica_mensal as 'N° Preventiva', nome_prev as 'Técnico', setor_prev as 'Setor', nome_equi_set as 'Equipamento', cod_equi_set as 'Cod. Equipamento', tempo_dis as 'Distribuição' from  form_hidraulica_mensal where iduser = ? and situacao_prev = ? and id_hidraulica_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqHid.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
//            txtPesqEle.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_tri() {
        String s = prev;

        String sql = "select id_hidraulica_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_hidraulica_trimestral where iduser = ? and situacao_prev = ? and id_hidraulica_trimestral like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqHid.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
//            txtPesqEle.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_tri_ini() {

        String s = prev;

        String sql = "select id_hidraulica_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_hidraulica_trimestral where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo1);
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_sem() {
        String s = prev;

        String sql = "select id_hidraulica_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_hidraulica_semestral where iduser = ? and situacao_prev = ? and id_hidraulica_semestral like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqHid.getText() + "%");
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
//            txtPesqEle.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void hid_sem_ini() {

        String s = prev;

        String sql = "select id_hidraulica_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_hidraulica_semestral where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo2);
            rs = pst.executeQuery();
            tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_hid() {
        set = "Hidraulica";
        int setar = tblPreHid.getSelectedRow();
        txtIdPrevIni.setText(tblPreHid.getModel().getValueAt(setar, 0).toString());
        txtTipPreIni.setText(set + " - " + si);
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
        btnIniPrev.setEnabled(true);
    }

    private void civ_men() {
        String s = prev;

        String sql = "select id_civil_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_mensal where iduser = ? and situacao_prev = ? and id_civil_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqCiv.getText() + "%");
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_men_ini() {

        String s = prev;

        String sql = "select id_civil_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_mensal where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo);
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_tri() {
        String s = prev;

        String sql = "select id_civil_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_trimestral where iduser = ? and situacao_prev = ? and id_civil_trimestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqCiv.getText() + "%");
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_tri_ini() {

        String s = prev;

        String sql = "select id_civil_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_trimestral where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo1);
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_sem() {
        String s = prev;

        String sql = "select id_civil_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_semestral where iduser = ? and situacao_prev = ? and id_civil_semestral like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqCiv.getText() + "%");
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void civ_sem_ini() {

        String s = prev;

        String sql = "select id_civil_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_semestral where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo2);
            rs = pst.executeQuery();
            tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_civ() {
        set = "Civil";
        int setar = tblPreCivil.getSelectedRow();
        txtIdPrevIni.setText(tblPreCivil.getModel().getValueAt(setar, 0).toString());
        txtTipPreIni.setText(set + " - " + si);
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
        btnIniPrev.setEnabled(true);
    }

    private void ref_men() {
        String s = prev;

        String sql = "select id_refrigeracao_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_refrigeracao_mensal where iduser = ? and situacao_prev = ? and id_refrigeracao_mensal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqRef.getText() + "%");
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_men_ini() {

        String s = prev;

        String sql = "select id_refrigeracao_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_refrigeracao_mensal where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_tri() {
        String s = prev;

        String sql = "select id_refrigeracao_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_refrigeracao_trimestral where iduser = ? and situacao_prev = ? and id_refrigeracao_trimestral like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqRef.getText() + "%");
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_tri_ini() {

        String s = prev;

        String sql = "select id_refrigeracao_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_refrigeracao_trimestral where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo1);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_sem() {
        String s = prev;

        String sql = "select id_refrigeracao_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_refrigeracao_semestral where iduser = ? and situacao_prev = ? and id_refrigeracao_semestral like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, txtPesqRef.getText() + "%");
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ref_sem_ini() {

        String s = prev;

        String sql = "select id_refrigeracao_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_refrigeracao_semestral where iduser = ? and situacao_prev = ? and data_prev  > (NOW() - INTERVAL ? DAY) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, s);
            pst.setString(3, tempo2);
            rs = pst.executeQuery();
            tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_ref() {
        set = "Refrigeração";
        int setar = tblPreRef.getSelectedRow();
        txtIdPrevIni.setText(tblPreRef.getModel().getValueAt(setar, 0).toString());
        txtTipPreIni.setText(set + " - " + si);
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
        btnIniPrev.setEnabled(true);
    }

    private void pev_pesq() {
        try {
            if (setor.equals("Eletrica - Mensal")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_form_ele_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_mensal where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Eletrica - Trimestral")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_form_ele_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_trimestral where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Eletrica - Semestral")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_form_ele_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_eletrica_semestral where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreEle.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Hidráulica - Mensal")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_hidraulica_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_hidraulica_mensal where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Hidráulica - Trimestral")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_hidraulica_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_hidraulica_trimestral where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Hidráulica - Semestral")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_hidraulica_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_hidraulica_semestral where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreHid.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Civil - Mensal")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_civil_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_mensal where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Civil - Trimestral")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_civil_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_trimestral where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Civil - Semestral")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_civil_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_civil_semestral where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreCivil.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Refrigeração - Mensal")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_refrigeracao_mensal as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_refrigeracao_mensal where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Refrigeração - Trimestral")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_refrigeracao_trimestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_refrigeracao_trimestral where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (setor.equals("Refrigeração - Semestral")) {
                String s = prev;
                java.util.Date pega = cadDataPre.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                this.data = formato.format(pega);

                String sql = "select id_refrigeracao_semestral as 'N° Preventiva', nome_prev as Técnico, setor_prev as Setor, nome_equi_set as Equipamento, cod_equi_set as 'Cod. Equipamento', tempo_dis as Distribuição from  form_refrigeracao_semestral where iduser = ? and situacao_prev = ? and tempo_dis like ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, s);
                    pst.setString(3, data + "%");
                    rs = pst.executeQuery();
                    tblPreRef.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void prev_encee() {
        String atu;
        atu = txtTipPreIni.getText();
        try {

            if (atu.equals("Eletrica - Mensal")) {
                id1 = txtIdPrevIni.getText();
                TelaPrevEnc enc = new TelaPrevEnc();
                enc.setVisible(true);
                getParent().add(enc);
                TelaPrevEnc.jtpAbas.setSelectedIndex(0);
                TelaPrevEnc.jtpAbas.setEnabledAt(0, true);
                enc.prev_encerrada2();
                dispose();
            }
            if (atu.equals("Eletrica - Trimestral")) {
                id2 = txtIdPrevIni.getText();
                TelaPrevEnc enc = new TelaPrevEnc();
                enc.setVisible(true);
                getParent().add(enc);
                TelaPrevEnc.jtpAbas.setSelectedIndex(1);
                TelaPrevEnc.jtpAbas.setEnabledAt(1, true);
                enc.prev_encerrada1();
                dispose();
            }
            if (atu.equals("Eletrica - Semestral")) {
                id3 = txtIdPrevIni.getText();
                TelaPrevEnc enc = new TelaPrevEnc();
                enc.setVisible(true);
                getParent().add(enc);
                TelaPrevEnc.jtpAbas.setSelectedIndex(2);
                TelaPrevEnc.jtpAbas.setEnabledAt(2, true);
                enc.prev_encerrada();
                dispose();
            }
            if (atu.equals("Hidraulica - Mensal")) {
                id4 = txtIdPrevIni.getText();
                TelaPrevEnc1 enc1 = new TelaPrevEnc1();
                enc1.setVisible(true);
                getParent().add(enc1);
                TelaPrevEnc1.jtpAbas1.setSelectedIndex(0);
                TelaPrevEnc1.jtpAbas1.setEnabledAt(0, true);
                enc1.prev_encerrada3();
                dispose();
            }
            if (atu.equals("Hidraulica - Trimestral")) {
                id5 = txtIdPrevIni.getText();
                TelaPrevEnc1 enc1 = new TelaPrevEnc1();
                enc1.setVisible(true);
                getParent().add(enc1);
                TelaPrevEnc1.jtpAbas1.setSelectedIndex(1);
                TelaPrevEnc1.jtpAbas1.setEnabledAt(1, true);
                enc1.prev_encerrada4();
                dispose();
            }
            if (atu.equals("Hidraulica - Semestral")) {
                id6 = txtIdPrevIni.getText();
                TelaPrevEnc1 enc1 = new TelaPrevEnc1();
                enc1.setVisible(true);
                getParent().add(enc1);
                TelaPrevEnc1.jtpAbas1.setSelectedIndex(2);
                TelaPrevEnc1.jtpAbas1.setEnabledAt(2, true);
                enc1.prev_encerrada5();
                dispose();
            }
            if (atu.equals("Civil - Mensal")) {
                id9 = txtIdPrevIni.getText();
                TelaPrevEnc2 enc2 = new TelaPrevEnc2();
                enc2.setVisible(true);
                getParent().add(enc2);
                TelaPrevEnc2.jtpAbas3.setSelectedIndex(0);
                TelaPrevEnc2.jtpAbas3.setEnabledAt(0, true);
                enc2.prev_encerrada8();
                dispose();
            }
            if (atu.equals("Civil - Trimestral")) {
                id8 = txtIdPrevIni.getText();
                TelaPrevEnc2 enc2 = new TelaPrevEnc2();
                enc2.setVisible(true);
                getParent().add(enc2);
                TelaPrevEnc2.jtpAbas3.setSelectedIndex(1);
                TelaPrevEnc2.jtpAbas3.setEnabledAt(1, true);
                enc2.prev_encerrada7();
                dispose();
            }
            if (atu.equals("Civil - Semestral")) {
                id7 = txtIdPrevIni.getText();
                TelaPrevEnc2 enc2 = new TelaPrevEnc2();
                enc2.setVisible(true);
                getParent().add(enc2);
                TelaPrevEnc2.jtpAbas3.setSelectedIndex(2);
                TelaPrevEnc2.jtpAbas3.setEnabledAt(2, true);
                enc2.prev_encerrada6();
                dispose();
            }
            if (atu.equals("Refrigeração - Mensal")) {
                id10 = txtIdPrevIni.getText();
                TelaPrevEnc3 enc3 = new TelaPrevEnc3();
                enc3.setVisible(true);
                getParent().add(enc3);
                TelaPrevEnc3.jtpAbas3.setSelectedIndex(0);
                TelaPrevEnc3.jtpAbas3.setEnabledAt(0, true);
                enc3.prev_encerrada12();
                dispose();
            }
            if (atu.equals("Refrigeração - Trimestral")) {
                id11 = txtIdPrevIni.getText();
                TelaPrevEnc3 enc3 = new TelaPrevEnc3();
                enc3.setVisible(true);
                getParent().add(enc3);
                TelaPrevEnc3.jtpAbas3.setSelectedIndex(1);
                TelaPrevEnc3.jtpAbas3.setEnabledAt(1, true);
                enc3.prev_encerrada11();
                dispose();
            }
            if (atu.equals("Refrigeração - Semestral")) {
                id12 = txtIdPrevIni.getText();
                TelaPrevEnc3 enc3 = new TelaPrevEnc3();
                enc3.setVisible(true);
                getParent().add(enc3);
                TelaPrevEnc3.jtpAbas3.setSelectedIndex(2);
                TelaPrevEnc3.jtpAbas3.setEnabledAt(2, true);
                enc3.prev_encerrada10();
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void pesq_Bot() {

        String te;

        rbnPesEle.setEnabled(true);
        rbnPesHid.setEnabled(true);
        rbnPesCiv.setEnabled(true);
        rbnPesRef.setEnabled(true);

        try {

            if (si.equals("Mensal")) {
                String sql = "select count(*) from form_eletrica_mensal where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            System.out.println(te);
                            rbnPesEle.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Mensal")) {
                String sql = "select count(*) from form_hidraulica_mensal where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesHid.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Mensal")) {
                String sql = "select count(*) from form_civil_mensal where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesCiv.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Mensal")) {
                String sql = "select count(*) from form_refrigeracao_mensal where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesRef.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Trimestral")) {
                String sql = "select count(*) from form_eletrica_trimestral where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesEle.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Trimestral")) {
                String sql = "select count(*) from form_hidraulica_trimestral where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesHid.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Trimestral")) {
                String sql = "select count(*) from form_civil_trimestral where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesCiv.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Trimestral")) {
                String sql = "select count(*) from form_refrigeracao_trimestral where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesRef.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Semestral")) {
                String sql = "select count(*) from form_eletrica_semestral where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesEle.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Semestral")) {
                String sql = "select count(*) from form_hidraulica_semestral where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesHid.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Semestral")) {
                String sql = "select count(*) from form_civil_semestral where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesCiv.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (si.equals("Semestral")) {
                String sql = "select count(*) from form_refrigeracao_semestral where iduser = ? and situacao_prev = ?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, prev);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        te = (rs.getString(1));
                        if ("0".equals(te)) {
                            rbnPesRef.setEnabled(false);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void limpar() {
        txtPesqEle.setText(null);
        txtPesqHid.setText(null);
        txtPesqCiv.setText(null);
        txtPesqRef.setText(null);
        txtIdPrevIni.setText(null);
        txtTipPreIni.setText(null);
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        cadDataPre = new com.toedter.calendar.JDateChooser();
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
        btnIniPrev = new javax.swing.JButton();
        txtIdPrevIni = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTipPreIni = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rbnPreMen = new javax.swing.JRadioButton();
        rbnPreTri = new javax.swing.JRadioButton();
        rbnPreSem = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rbnPesEle = new javax.swing.JRadioButton();
        rbnPesRef = new javax.swing.JRadioButton();
        rbnPesHid = new javax.swing.JRadioButton();
        rbnPesCiv = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rbnPreEnc = new javax.swing.JRadioButton();
        rbnPreIrr = new javax.swing.JRadioButton();
        lblPreEnc = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Encerradas/Irregulares");
        setMaximumSize(new java.awt.Dimension(1008, 672));
        setPreferredSize(new java.awt.Dimension(1007, 672));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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

        cadDataPre.setAutoscrolls(true);
        cadDataPre.setEnabled(false);
        cadDataPre.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    pev_pesq();
                }
            }
        });
        getContentPane().add(cadDataPre);
        cadDataPre.setBounds(56, 124, 126, 28);

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

        btnIniPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48754_document_document.png"))); // NOI18N
        btnIniPrev.setToolTipText("Visualizar Preventiva");
        btnIniPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniPrevActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniPrev);
        btnIniPrev.setBounds(740, 530, 64, 64);

        txtIdPrevIni.setEditable(false);
        txtIdPrevIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdPrevIni);
        txtIdPrevIni.setBounds(830, 530, 70, 28);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("N°");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(46, 468, 16, 24);

        txtTipPreIni.setEditable(false);
        txtTipPreIni.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtTipPreIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtTipPreIni);
        txtTipPreIni.setBounds(810, 574, 110, 28);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Pesquisa por Data:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(570, 520, 120, 14);

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

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tipo Preventiva");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(820, 560, 90, 14);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("N° Preventiva");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(826, 514, 80, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Pesquisa por Data:");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(62, 106, 116, 16);

        buttonGroup2.add(rbnPesEle);
        rbnPesEle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbnPesEle.setForeground(new java.awt.Color(0, 0, 51));
        rbnPesEle.setText("Eletrica");
        rbnPesEle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPesEleActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPesEle);
        rbnPesEle.setBounds(526, 540, 100, 23);

        buttonGroup2.add(rbnPesRef);
        rbnPesRef.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbnPesRef.setForeground(new java.awt.Color(0, 0, 51));
        rbnPesRef.setText("Refrigeração");
        rbnPesRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPesRefActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPesRef);
        rbnPesRef.setBounds(526, 570, 100, 23);

        buttonGroup2.add(rbnPesHid);
        rbnPesHid.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbnPesHid.setForeground(new java.awt.Color(0, 0, 51));
        rbnPesHid.setText("Hidráulica");
        rbnPesHid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPesHidActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPesHid);
        rbnPesHid.setBounds(630, 540, 100, 23);

        buttonGroup2.add(rbnPesCiv);
        rbnPesCiv.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbnPesCiv.setForeground(new java.awt.Color(0, 0, 51));
        rbnPesCiv.setText("Civil");
        rbnPesCiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPesCivActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPesCiv);
        rbnPesCiv.setBounds(630, 570, 100, 23);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("N°");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(46, 218, 16, 24);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("N°");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(46, 301, 16, 24);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("N°");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(46, 385, 16, 24);

        buttonGroup3.add(rbnPreEnc);
        rbnPreEnc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbnPreEnc.setForeground(new java.awt.Color(0, 0, 51));
        rbnPreEnc.setText("Encerrada");
        rbnPreEnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPreEncActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPreEnc);
        rbnPreEnc.setBounds(60, 520, 100, 23);

        buttonGroup3.add(rbnPreIrr);
        rbnPreIrr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbnPreIrr.setForeground(new java.awt.Color(0, 0, 51));
        rbnPreIrr.setText("Irregular");
        rbnPreIrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnPreIrrActionPerformed(evt);
            }
        });
        getContentPane().add(rbnPreIrr);
        rbnPreIrr.setBounds(210, 520, 100, 23);

        lblPreEnc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/encerradas_1.png"))); // NOI18N
        getContentPane().add(lblPreEnc);
        lblPreEnc.setBounds(-10, -8, 1008, 672);

        setBounds(0, 0, 1007, 672);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesqCivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesqCivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesqCivActionPerformed

    private void btnIniPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniPrevActionPerformed
        // TODO add your handling code here:
        prev_encee();
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
        data = null;
        si = "Mensal";
        t = "Encerrada";
        pesq_Bot();
        btnIniPrev.setEnabled(false);
        selec_encerrad();
        buttonGroup2.clearSelection();
        cadDataPre.setEnabled(false);

    }//GEN-LAST:event_rbnPreMenActionPerformed

    private void rbnPreTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPreTriActionPerformed
        // TODO add your handling code here:
        si = "Trimestral";
        btnIniPrev.setEnabled(false);
        selec_encerrad();
        buttonGroup2.clearSelection();
        cadDataPre.setEnabled(false);
        pesq_Bot();
    }//GEN-LAST:event_rbnPreTriActionPerformed

    private void rbnPreSemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPreSemActionPerformed
        // TODO add your handling code here:
        si = "Semestral";
        btnIniPrev.setEnabled(false);
        selec_encerrad();
        buttonGroup2.clearSelection();
        cadDataPre.setEnabled(false);
        pesq_Bot();
    }//GEN-LAST:event_rbnPreSemActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        prev = "Encerrada";
        rbnPreMen.setSelected(true);
        rbnPreEnc.setSelected(true);
        si = "Mensal";
        selec_encerrad();
        btnIniPrev.setEnabled(false);
        pesq_Bot();

        ImageIcon icon = new ImageIcon("C:\\Users\\fabio\\OneDrive\\Área de Trabalho\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\data.png");
        cadDataPre.setIcon(icon);

        tblPreEle.setForeground(Color.blue);
        tblPreHid.setForeground(Color.blue);
        tblPreCivil.setForeground(Color.blue);
        tblPreRef.setForeground(Color.blue);

    }//GEN-LAST:event_formInternalFrameOpened

    private void txtPesqEleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqEleKeyReleased
        // TODO add your handling code here:
        String v;
        v = txtPesqEle.getText();
        try {
            if (si.equals("Mensal")) {
                if (v.equals("")) {
                    ele_men_ini();
                } else {
                    ele_men();
                }
            }
            if (si.equals("Trimestral")) {
                if (v.equals("")) {
                    ele_tri_ini();
                } else {
                    ele_tri();
                }
            }
            if (si.equals("Semestral")) {
                if (v.equals("")) {
                    ele_sem_ini();
                } else {
                    ele_sem();
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
            if (si.equals("Mensal")) {
                if (v.equals("")) {
                    hid_men_ini();
                } else {
                    hid_men();
                }
            }
            if (si.equals("Trimestral")) {
                if (v.equals("")) {
                    hid_tri_ini();
                } else {
                    hid_tri();
                }
            }
            if (si.equals("Semestral")) {
                if (v.equals("")) {
                    hid_sem_ini();
                } else {
                    hid_sem();
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
            if (si.equals("Mensal")) {
                if (v.equals("")) {
                    ref_men_ini();
                } else {
                    ref_men();
                }
            }
            if (si.equals("Trimestral")) {
                if (v.equals("")) {
                    ref_tri_ini();
                } else {
                    ref_tri();
                }
            }
            if (si.equals("Semestral")) {
                if (v.equals("")) {
                    ref_sem_ini();
                } else {
                    ref_sem();
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
            if (si.equals("Mensal")) {
                if (v.equals("")) {
                    civ_men_ini();
                } else {
                    civ_men();
                }
            }
            if (si.equals("Trimestral")) {
                if (v.equals("")) {
                    civ_tri_ini();
                } else {
                    civ_tri();
                }
            }
            if (si.equals("Semestral")) {
                if (v.equals("")) {
                    civ_sem_ini();
                } else {
                    civ_sem();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPesqCivKeyReleased

    private void rbnPesEleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPesEleActionPerformed
        // TODO add your handling code here:
        data = null;
        setor = null;
        setor = "Eletrica - " + si;
        cadDataPre.setEnabled(true);
        selec_encerrad();
//        
    }//GEN-LAST:event_rbnPesEleActionPerformed

    private void rbnPesHidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPesHidActionPerformed
        // TODO add your handling code here:
        data = null;
        setor = null;
        setor = "Hidráulica - " + si;
        cadDataPre.setEnabled(true);
        selec_encerrad();
//        
    }//GEN-LAST:event_rbnPesHidActionPerformed

    private void rbnPesRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPesRefActionPerformed
        // TODO add your handling code here:
        data = null;
        setor = null;
        setor = "Refrigeração - " + si;
        cadDataPre.setEnabled(true);
        selec_encerrad();
//        
    }//GEN-LAST:event_rbnPesRefActionPerformed

    private void rbnPesCivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPesCivActionPerformed
        // TODO add your handling code here:
        data = null;
        setor = null;
        setor = "Civil - " + si;
        cadDataPre.setEnabled(true);
        selec_encerrad();
//       
    }//GEN-LAST:event_rbnPesCivActionPerformed

    private void rbnPreEncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPreEncActionPerformed
        // TODO add your handling code here:
        si = "Mensal";
        t = "Encerrada";
        btnIniPrev.setEnabled(false);
        inicializacao();
        buttonGroup2.clearSelection();
        cadDataPre.setEnabled(false);
//        pesq_Bot();
    }//GEN-LAST:event_rbnPreEncActionPerformed

    private void rbnPreIrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnPreIrrActionPerformed
        // TODO add your handling code here:
        si = "Mensal";
        t = "Irregular";
        btnIniPrev.setEnabled(false);
        inicializacao();
        buttonGroup2.clearSelection();
        cadDataPre.setEnabled(false);
//        pesq_Bot();
    }//GEN-LAST:event_rbnPreIrrActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniPrev;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.toedter.calendar.JDateChooser cadDataPre;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JRadioButton rbnPesCiv;
    private javax.swing.JRadioButton rbnPesEle;
    private javax.swing.JRadioButton rbnPesHid;
    private javax.swing.JRadioButton rbnPesRef;
    private javax.swing.JRadioButton rbnPreEnc;
    private javax.swing.JRadioButton rbnPreIrr;
    private javax.swing.JRadioButton rbnPreMen;
    private javax.swing.JRadioButton rbnPreSem;
    private javax.swing.JRadioButton rbnPreTri;
    public static javax.swing.JTable tblPreCivil;
    public static javax.swing.JTable tblPreEle;
    public static javax.swing.JTable tblPreHid;
    public static javax.swing.JTable tblPreRef;
    private javax.swing.JTextField txtIdPrevIni;
    private javax.swing.JTextField txtPesqCiv;
    private javax.swing.JTextField txtPesqEle;
    private javax.swing.JTextField txtPesqHid;
    private javax.swing.JTextField txtPesqRef;
    private javax.swing.JTextField txtTipPreIni;
    // End of variables declaration//GEN-END:variables
}
