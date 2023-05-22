/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import com.prjmanutencao.dal.ModuloConexao;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Fábio
 */
public class TelaDisPreventiva extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
//    String perfil = null;
    public static String tipo = null;
    public static String idprev = null;
    String va, va1, va2 = null;
    int v = 0;

    /**
     * Creates new form TelaDisPreventiva
     */
    public TelaDisPreventiva() {
        initComponents();
        conexao = ModuloConexao.conector();
        ((DefaultTableModel) tblEquiSet.getModel()).setRowCount(0);
        ((DefaultTableModel) tblSetSet.getModel()).setRowCount(0);
        ((DefaultTableModel) tblTecSet.getModel()).setRowCount(0);
        txtPesEqui.setEnabled(false);
        btnApaPre.setEnabled(false);
        btnAlrPre.setEnabled(false);

        JTableHeader header, header1, header2;

        header = tblSetSet.getTableHeader();
        DefaultTableCellRenderer centralizado = (DefaultTableCellRenderer) header.getDefaultRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        header1 = tblEquiSet.getTableHeader();
        DefaultTableCellRenderer centralizado1 = (DefaultTableCellRenderer) header1.getDefaultRenderer();
        centralizado1.setHorizontalAlignment(SwingConstants.CENTER);

        header2 = tblTecSet.getTableHeader();
        DefaultTableCellRenderer centralizado2 = (DefaultTableCellRenderer) header2.getDefaultRenderer();
        centralizado2.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void pesquisar_prev() {
        String sql = "select id_setor as 'Cod. Setor', andar as 'Andar', setor as 'Setor' from setor where andar like ? or setor like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesSetPrev.getText() + "%");
            pst.setString(2, txtPesSetPrev.getText() + "%");
            rs = pst.executeQuery();
            tblSetSet.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_campos_prev() {
        int setar = tblSetSet.getSelectedRow();
        txtIdSetPrev.setText(tblSetSet.getModel().getValueAt(setar, 0).toString());
        txtSetPrev.setText(tblSetSet.getModel().getValueAt(setar, 1).toString() + " " + (tblSetSet.getModel().getValueAt(setar, 2).toString()));
        pesquisar_equipamento1();
        txtIdSetPrev.setBorder(null);
        txtSetPrev.setBorder(null);

        txtPesEqui.setEnabled(true);
        txtPesSetPrev.setText(null);
        ((DefaultTableModel) tblSetSet.getModel()).setRowCount(0);
    }

    private void pesquisar_tecnico_prev() {
        String sql = " select iduser as 'R.E', nome_usuario as 'Técnico', tipo_usuario as 'Especialidade' from usuarios where iduser like ? or nome_usuario like?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesTecPrev.getText() + "%");
            pst.setString(2, txtPesTecPrev.getText() + "%");
            rs = pst.executeQuery();
            tblTecSet.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_tecnico_prev() {
        int setar = tblTecSet.getSelectedRow();
        txtIdTecPrev.setText(tblTecSet.getModel().getValueAt(setar, 0).toString());
        txtTecPrev.setText(tblTecSet.getModel().getValueAt(setar, 1).toString());
        txtTipTec.setText(tblTecSet.getModel().getValueAt(setar, 2).toString());
        ((DefaultTableModel) tblTecSet.getModel()).setRowCount(0);
        txtPesTecPrev.setText(null);
        txtIdTecPrev.setBorder(null);
        txtTipTec.setBorder(null);
        txtTecPrev.setBorder(null);
    }

    private void pesquisar_equipamento() {

        String sql = "select id_equi_set as 'N° Equip.', nome_equi_set as 'Nome',  cod_equi_set as 'Cód. E.', tipo_equip as 'Equip.' from equipamento_setor where  id_equi_set like ? and id_setor = ? or cod_equi_set like ? and id_setor = ? or nome_equi_set like ? and id_setor = ?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtPesEqui.getText() + "%");
            pst.setString(2, txtIdSetPrev.getText());
            pst.setString(3, txtPesEqui.getText() + "%");
            pst.setString(4, txtIdSetPrev.getText());
            pst.setString(5, txtPesEqui.getText() + "%");
            pst.setString(6, txtIdSetPrev.getText());
            rs = pst.executeQuery();
            tblEquiSet.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void pesquisar_equipamento1() {

        String sql = "select id_equi_set as 'N° Equip.' ,nome_equi_set as 'Nome', cod_equi_set as 'Cód. E.', tipo_equip as 'Equip.' from equipamento_setor where id_setor=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdSetPrev.getText());
            rs = pst.executeQuery();
            tblEquiSet.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setar_equipamento() {
        int setar = tblEquiSet.getSelectedRow();
        txtIdEquiSet.setText(tblEquiSet.getModel().getValueAt(setar, 0).toString());
        txtNonEquiSet.setText(tblEquiSet.getModel().getValueAt(setar, 1).toString());
        txtCodEquiSet.setText(tblEquiSet.getModel().getValueAt(setar, 2).toString());
        txtTipEqui.setText(tblEquiSet.getModel().getValueAt(setar, 3).toString());
        ((DefaultTableModel) tblEquiSet.getModel()).setRowCount(0);
        txtPesEqui.setText(null);
        txtIdEquiSet.setBorder(null);
        txtCodEquiSet.setBorder(null);
        txtNonEquiSet.setBorder(null);
        txtTipEqui.setBorder(null);

    }

    private void vazio() {

        va = txtIdSetPrev.getText();
        va1 = txtIdEquiSet.getText();
        va2 = txtIdTecPrev.getText();

        try {
            if (va.equals("")) {
                txtIdSetPrev.setBorder(new LineBorder(Color.RED));
                txtSetPrev.setBorder(new LineBorder(Color.RED));
            }
            if (va1.equals("")) {
                txtIdEquiSet.setBorder(new LineBorder(Color.RED));
                txtCodEquiSet.setBorder(new LineBorder(Color.RED));
                txtNonEquiSet.setBorder(new LineBorder(Color.RED));
                txtTipEqui.setBorder(new LineBorder(Color.RED));
            }
            if (va2.equals("")) {
                txtIdTecPrev.setBorder(new LineBorder(Color.RED));
                txtTipTec.setBorder(new LineBorder(Color.RED));
                txtTecPrev.setBorder(new LineBorder(Color.RED));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void iniciar_prev() {
        Color c;
        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);
        txtDatPrev.setText(format);

        String perfil = cboTipPrev.getSelectedItem().toString();
        try {
            if (perfil.equals("-Selecione-")) {
                c = Color.RED;
                txtDatPrev.setText(null);
                JOptionPane.showMessageDialog(null, "Escolha um Tipo de Preventiva!");
                cboTipPrev.setBorder(new LineBorder(c));
            } else {
                cboTipPrev.setBorder(null);
            }
            if (perfil.equals("Eletrica - Mensal")) {
                cboTipPrev.setBorder(null);
                String sql = "insert into form_eletrica_mensal(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }
            if (perfil.equals("Eletrica - Trimestral")) {
                String sql = "insert into form_eletrica_trimestral(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }
            if (perfil.equals("Eletrica - Semestral")) {
                String sql = "insert into form_eletrica_semestral(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Civil - Mensal")) {
                String sql = "insert into form_civil_mensal(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Civil - Trimestral")) {
                String sql = "insert into form_civil_trimestral(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Civil - Semestral")) {
                String sql = "insert into form_civil_semestral(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Hidráulica - Mensal")) {
                String sql = "insert into form_hidraulica_mensal(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Hidráulica - Trimestral")) {
                String sql = "insert into form_hidraulica_trimestral(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Hidráulica - Semestral")) {
                String sql = "insert into form_hidraulica_semestral(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Refrigeração - Mensal")) {
                String sql = "insert into form_refrigeracao_mensal(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Refrigeração - Trimestral")) {
                String sql = "insert into form_refrigeracao_trimestral(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Refrigeração - Semestral")) {
                String sql = "insert into form_refrigeracao_semestral(id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    txtDatPrev.setText(null);
                    vazio();
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void pesquisar_pre() {

        String perfil = tipo;
        txtIdPre.setText(idprev);
        cboTipPrev.setEnabled(false);
        btnCadPre.setEnabled(false);
        btnApaPre.setEnabled(true);
        btnAlrPre.setEnabled(true);
        try {
            if (perfil.equals("Eletrica - Mensal")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_eletrica_mensal where id_form_ele_mensal = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));

                }
            }
            if (perfil.equals("Eletrica - Trimestral")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_eletrica_trimestral where id_form_ele_trimestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }
            }
            if (perfil.equals("Eletrica - Semestral")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_eletrica_semestral where id_form_ele_semestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));

                }
            }

            if (perfil.equals("Civil - Mensal")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_civil_mensal where id_civil_mensal = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }

            }

            if (perfil.equals("Civil - Trimestral")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_civil_trimestral where id_civil_trimestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }

            }
            if (perfil.equals("Civil - Semestral")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_civil_semestral where id_civil_semestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }

            }
            if (perfil.equals("Hidráulica - Mensal")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_hidraulica_mensal where id_hidraulica_mensal = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }

            }
            if (perfil.equals("Hidráulica - Trimestral")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_hidraulica_trimestral where id_hidraulica_trimestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }

            }

            if (perfil.equals("Hidráulica - Semestral")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_hidraulica_semestral where id_hidraulica_trimestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }

            }
            if (perfil.equals("Refrigeração - Mensal")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_refrigeracao_mensal where id_refrigeracao_mensal = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }

            }

            if (perfil.equals("Refrigeração - Trimestral")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_refrigeracao_trimestral where id_refrigeracao_trimestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }

            }

            if (perfil.equals("Refrigeração - Semestral")) {
                String sql = "select id_setor, setor_prev, id_equi_set, nome_equi_set, cod_equi_set, tipo_equi, iduser, nome_prev, tipo_usuario, tipo_prev, tempo_dis from form_refrigeracao_semestral where id_refrigeracao_semestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPre.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtIdSetPrev.setText(rs.getString(1));
                    txtSetPrev.setText(rs.getString(2));
                    txtIdEquiSet.setText(rs.getString(3));
                    txtNonEquiSet.setText(rs.getString(4));
                    txtCodEquiSet.setText(rs.getString(5));
                    txtTipEqui.setText(rs.getString(6));
                    txtIdTecPrev.setText(rs.getString(7));
                    txtTecPrev.setText(rs.getString(8));
                    txtTipTec.setText(rs.getString(9));
                    cboTipPrev.setSelectedItem(rs.getString(10));
                    txtDatPrev.setText(rs.getString(11));
                }

            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void alterar_prev() {

        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);
        txtDatPrev.setText(format);

        String perfil = cboTipPrev.getSelectedItem().toString();
        try {
            if (perfil.equals("Eletrica - Mensal")) {
                String sql = "update form_eletrica_mensal set  id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_form_ele_mensal = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }
            if (perfil.equals("Eletrica - Trimestral")) {
                String sql = "update form_eletrica_trimestral set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_form_ele_trimestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }
            if (perfil.equals("Eletrica - Semestral")) {
                String sql = "update form_eletrica_semestral set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_form_ele_semestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Civil - Mensal")) {
                String sql = "update form_civil_mensal set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_civil_mensal = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Civil - Trimestral")) {
                String sql = "update form_civil_trimestral set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_civil_trimestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Civil - Semestral")) {
                String sql = "update form_civil_semestral set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_civil_semestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Hidráulica - Mensal")) {
                String sql = "update form_hidraulica_mensal set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_hidraulica_mensal = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Hidráulica - Trimestral")) {
                String sql = "update form_hidraulica_trimestral set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_hidraulica_trimestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Hidráulica - Semestral")) {
                String sql = "update form_hidraulica_semestral set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_hidraulica_semestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Refrigeração - Mensal")) {
                String sql = "update form_refrigeracao_mensal set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_refrigeracao_mensal = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Refrigeração - Trimestral")) {
                String sql = "update form_refrigeracao_trimestral set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, iduser, nome_prev = ?, tipo_prev = ?, tempo_dis = ? where id_refrigeracao_trimestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

            if (perfil.equals("Refrigeração - Semestral")) {
                String sql = "update form_refrigeracao_semestral set id_setor = ?, setor_prev = ?, id_equi_set = ?, nome_equi_set = ?, cod_equi_set = ?, tipo_equi = ?, iduser = ?, nome_prev = ?, tipo_usuario = ?, tipo_prev = ?, tempo_dis = ? where id_refrigeracao_semestral = ?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSetPrev.getText());
                pst.setString(2, txtSetPrev.getText());
                pst.setString(3, txtIdEquiSet.getText());
                pst.setString(4, txtNonEquiSet.getText());
                pst.setString(5, txtCodEquiSet.getText());
                pst.setString(6, txtTipEqui.getText());
                pst.setString(7, txtIdTecPrev.getText());
                pst.setString(8, txtTecPrev.getText());
                pst.setString(9, txtTipTec.getText());
                pst.setString(10, cboTipPrev.getSelectedItem().toString());
                pst.setString(11, txtDatPrev.getText());
                pst.setString(12, txtIdPre.getText());
                if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {
                    int Adicionar = pst.executeUpdate();

                    if (Adicionar > 0) {
                        JOptionPane.showMessageDialog(null, "Preventiva Cadastrada com Sucesso!");
                        limpar();
                        conexao.close();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                        limpar();
                        conexao.close();

                    }
                }

            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void apagar_prev() {

        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Apagar está Preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String perfil = cboTipPrev.getSelectedItem().toString();
            try {
                if (perfil.equals("Eletrica - Mensal")) {
                    String sql = "delete from form_eletrica_mensal where id_form_ele_mensal = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();

                        }
                    }

                }
                if (perfil.equals("Eletrica - Trimestral")) {
                    String sql = "delete from form_eletrica_mensal where id_form_ele_trimestral = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();
                        }
                    }

                }
                if (perfil.equals("Eletrica - Semestral")) {
                    String sql = "delete from form_eletrica_mensal where id_form_ele_semestral = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();

                        }
                    }

                }

                if (perfil.equals("Civil - Mensal")) {
                    String sql = "delete from form_civil_mensal where id_civil_mensal = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();

                        }
                    }

                }

                if (perfil.equals("Civil - Trimestral")) {
                    String sql = "delete from form_civil_trimestral where id_civil_trimestral = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();

                        }
                    }

                }

                if (perfil.equals("Civil - Semestral")) {
                    String sql = "delete from form_civil_semestral where id_civil_semestral = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();

                        }
                    }

                }

                if (perfil.equals("Hidráulica - Mensal")) {
                    String sql = "delete from form_hidraulica_mensal where id_hidraulica_mensal = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();

                        }
                    }

                }

                if (perfil.equals("Hidráulica - Trimestral")) {
                    String sql = "delete from form_hidraulica_trimestral where id_hidraulica_trimestral = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();
                        }
                    }

                }

                if (perfil.equals("Hidráulica - Semestral")) {
                    String sql = "delete from form_hidraulica_semestral where id_hidraulica_semestral = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();

                        }
                    }

                }

                if (perfil.equals("Refrigeração - Mensal")) {
                    String sql = "delete from form_refrigeracao_mensal where id_refrigeracao_mensal = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();
                        }
                    }

                }

                if (perfil.equals("Refrigeração - Trimestral")) {
                    String sql = "delete from form_refrigeracao_trimestral where id_refrigeracao_trimestral = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();

                        }
                    }

                }

                if (perfil.equals("Refrigeração - Semestral")) {
                    String sql = "delete from form_refrigeracao_semestral where id_refrigeracao_semestral = ?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPre.getText());
                    if ((txtIdSetPrev.getText().isEmpty()) || (txtSetPrev.getText().isEmpty()) || (txtIdTecPrev.getText().isEmpty()) || (txtIdEquiSet.getText().isEmpty()) || (txtNonEquiSet.getText().isEmpty()) || (txtCodEquiSet.getText().isEmpty()) || (txtTecPrev.getText().isEmpty()) || (cboTipPrev.getSelectedItem().toString().isEmpty()) || (txtDatPrev.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    } else {
                        int Apagado = pst.executeUpdate();

                        if (Apagado > 0) {
                            JOptionPane.showMessageDialog(null, "Preventiva Apagada com Sucesso!");
                            limpar();
                            conexao.close();

                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Apagar Preventiva!");
                            limpar();
                            conexao.close();

                        }
                    }

                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    private void limpar() {

        ((DefaultTableModel) tblEquiSet.getModel()).setRowCount(0);
        ((DefaultTableModel) tblSetSet.getModel()).setRowCount(0);
        ((DefaultTableModel) tblTecSet.getModel()).setRowCount(0);
        txtPesEqui.setEnabled(false);
        txtPesTecPrev.setText(null);
        txtIdSetPrev.setText(null);
        txtSetPrev.setText(null);
        txtIdEquiSet.setText(null);
        txtNonEquiSet.setText(null);
        txtCodEquiSet.setText(null);
        txtIdTecPrev.setText(null);
        txtTecPrev.setText(null);
        txtTipEqui.setText(null);
        txtTipTec.setText(null);
        txtIdPre.setText(null);
        cboTipPrev.setSelectedItem("-Selecione-");
        txtDatPrev.setText(null);
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesSetPrev = new javax.swing.JTextField();
        txtPesEqui = new javax.swing.JTextField();
        txtIdSetPrev = new javax.swing.JTextField();
        txtSetPrev = new javax.swing.JTextField();
        txtIdTecPrev = new javax.swing.JTextField();
        txtTecPrev = new javax.swing.JTextField();
        txtDatPrev = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSetSet = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEquiSet = new javax.swing.JTable();
        btnCadPre = new javax.swing.JButton();
        btnAlrPre = new javax.swing.JButton();
        cboTipPrev = new javax.swing.JComboBox<>();
        txtPesTecPrev = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTecSet = new javax.swing.JTable();
        txtIdEquiSet = new javax.swing.JTextField();
        txtCodEquiSet = new javax.swing.JTextField();
        txtNonEquiSet = new javax.swing.JTextField();
        txtTipTec = new javax.swing.JTextField();
        txtTipEqui = new javax.swing.JTextField();
        btnApaPre = new javax.swing.JButton();
        txtIdPre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Distribuição de Preventivas");
        getContentPane().setLayout(null);

        txtPesSetPrev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesSetPrev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesSetPrevKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesSetPrev);
        txtPesSetPrev.setBounds(49, 178, 232, 28);

        txtPesEqui.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesEqui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesEquiKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesEqui);
        txtPesEqui.setBounds(359, 178, 232, 28);

        txtIdSetPrev.setEditable(false);
        txtIdSetPrev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdSetPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSetPrevActionPerformed(evt);
            }
        });
        txtIdSetPrev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSetPrevKeyReleased(evt);
            }
        });
        getContentPane().add(txtIdSetPrev);
        txtIdSetPrev.setBounds(237, 333, 106, 28);

        txtSetPrev.setEditable(false);
        txtSetPrev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtSetPrev);
        txtSetPrev.setBounds(237, 381, 248, 28);

        txtIdTecPrev.setEditable(false);
        txtIdTecPrev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdTecPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTecPrevActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdTecPrev);
        txtIdTecPrev.setBounds(710, 333, 106, 28);

        txtTecPrev.setEditable(false);
        txtTecPrev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTecPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTecPrevActionPerformed(evt);
            }
        });
        getContentPane().add(txtTecPrev);
        txtTecPrev.setBounds(710, 429, 248, 28);

        txtDatPrev.setEditable(false);
        txtDatPrev.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtDatPrev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtDatPrev);
        txtDatPrev.setBounds(710, 477, 106, 28);

        tblSetSet = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblSetSet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cod. Setor", "Andar", "Setor"
            }
        ));
        tblSetSet.getTableHeader().setReorderingAllowed(false);
        tblSetSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSetSetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSetSet);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 217, 278, 98);

        jScrollPane2.setEnabled(false);

        tblEquiSet = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblEquiSet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N° Equip.", "Nome ", "Cód. E.", "Equip."
            }
        ));
        tblEquiSet.getTableHeader().setReorderingAllowed(false);
        tblEquiSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEquiSetMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEquiSet);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(360, 217, 278, 98);

        btnCadPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48755_add_document_add_document.png"))); // NOI18N
        btnCadPre.setToolTipText("Cadastrar Preventiva");
        btnCadPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadPreActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadPre);
        btnCadPre.setBounds(550, 536, 64, 64);

        btnAlrPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48757_document_edit_edit_document.png"))); // NOI18N
        btnAlrPre.setToolTipText("Alterar Preventiva");
        btnAlrPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlrPreActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlrPre);
        btnAlrPre.setBounds(660, 536, 64, 64);

        cboTipPrev.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Selecione-", "Eletrica - Mensal", "Eletrica - Trimestral", "Eletrica - Semestral", "Civil - Mensal", "Civil - Trimestral", "Civil - Semestral", "Hidráulica - Mensal", "Hidráulica - Trimestral", "Hidráulica - Semestral", "Refrigeração - Mensal", "Refrigeração - Trimestral", "Refrigeração - Semestral" }));
        getContentPane().add(cboTipPrev);
        cboTipPrev.setBounds(228, 573, 184, 28);

        txtPesTecPrev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesTecPrev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesTecPrevKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesTecPrev);
        txtPesTecPrev.setBounds(669, 178, 232, 28);

        tblTecSet = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblTecSet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "R.E", "Técnico", "Especialidade"
            }
        ));
        tblTecSet.getTableHeader().setReorderingAllowed(false);
        tblTecSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTecSetMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTecSet);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(670, 217, 278, 98);

        txtIdEquiSet.setEditable(false);
        txtIdEquiSet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdEquiSet);
        txtIdEquiSet.setBounds(237, 429, 106, 28);

        txtCodEquiSet.setEditable(false);
        txtCodEquiSet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodEquiSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodEquiSetActionPerformed(evt);
            }
        });
        getContentPane().add(txtCodEquiSet);
        txtCodEquiSet.setBounds(380, 429, 106, 28);

        txtNonEquiSet.setEditable(false);
        txtNonEquiSet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtNonEquiSet);
        txtNonEquiSet.setBounds(237, 477, 248, 28);

        txtTipTec.setEditable(false);
        txtTipTec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtTipTec);
        txtTipTec.setBounds(710, 381, 162, 28);

        txtTipEqui.setEditable(false);
        txtTipEqui.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtTipEqui);
        txtTipEqui.setBounds(237, 525, 164, 28);

        btnApaPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48756_delete_delete_document_document.png"))); // NOI18N
        btnApaPre.setToolTipText("Excluir Preventiva");
        btnApaPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApaPreActionPerformed(evt);
            }
        });
        getContentPane().add(btnApaPre);
        btnApaPre.setBounds(772, 536, 64, 64);

        txtIdPre.setEditable(false);
        txtIdPre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdPre);
        txtIdPre.setBounds(852, 477, 106, 28);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/Dis3.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-6, -10, 1010, 660);

        setBounds(0, 0, 1007, 672);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdTecPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTecPrevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTecPrevActionPerformed

    private void txtTecPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTecPrevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTecPrevActionPerformed

    private void txtPesSetPrevKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesSetPrevKeyReleased
        // TODO add your handling code here:
        pesquisar_prev();
    }//GEN-LAST:event_txtPesSetPrevKeyReleased

    private void tblSetSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSetSetMouseClicked
        // TODO add your handling code here:
        setar_campos_prev();
    }//GEN-LAST:event_tblSetSetMouseClicked

    private void txtPesEquiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesEquiKeyReleased
        // TODO add your handling code here:
        pesquisar_equipamento();
//        pesquisar_equipamento1();
    }//GEN-LAST:event_txtPesEquiKeyReleased

    private void tblEquiSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEquiSetMouseClicked
        // TODO add your handling code here:
        setar_equipamento();
    }//GEN-LAST:event_tblEquiSetMouseClicked

    private void btnCadPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadPreActionPerformed
        // TODO add your handling code here:
        iniciar_prev();
    }//GEN-LAST:event_btnCadPreActionPerformed

    private void txtPesTecPrevKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesTecPrevKeyReleased
        // TODO add your handling code here:
        pesquisar_tecnico_prev();
    }//GEN-LAST:event_txtPesTecPrevKeyReleased

    private void tblTecSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTecSetMouseClicked
        // TODO add your handling code here:
        setar_tecnico_prev();
    }//GEN-LAST:event_tblTecSetMouseClicked

    private void btnAlrPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlrPreActionPerformed
        // TODO add your handling code here:
        alterar_prev();
    }//GEN-LAST:event_btnAlrPreActionPerformed

    private void btnApaPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApaPreActionPerformed
        // TODO add your handling code here:
        apagar_prev();
    }//GEN-LAST:event_btnApaPreActionPerformed

    private void txtCodEquiSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodEquiSetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodEquiSetActionPerformed

    private void txtIdSetPrevKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSetPrevKeyReleased
        // TODO add your handling code here:
        String v;
        v = txtIdSetPrev.getText() + "%";
        if (v.equals("")) {
            vazio();
        } else {
            vazio();
        }
    }//GEN-LAST:event_txtIdSetPrevKeyReleased

    private void txtIdSetPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSetPrevActionPerformed
        // TODO add your handling code here:
//        int v = 0;
        v = txtIdSetPrev.getText().length();
        if (v < 0) {
            vazio();
        }
    }//GEN-LAST:event_txtIdSetPrevActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlrPre;
    private javax.swing.JButton btnApaPre;
    private javax.swing.JButton btnCadPre;
    private javax.swing.JComboBox<String> cboTipPrev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblEquiSet;
    private javax.swing.JTable tblSetSet;
    private javax.swing.JTable tblTecSet;
    private javax.swing.JTextField txtCodEquiSet;
    private javax.swing.JTextField txtDatPrev;
    private javax.swing.JTextField txtIdEquiSet;
    private javax.swing.JTextField txtIdPre;
    private javax.swing.JTextField txtIdSetPrev;
    private javax.swing.JTextField txtIdTecPrev;
    private javax.swing.JTextField txtNonEquiSet;
    private javax.swing.JTextField txtPesEqui;
    private javax.swing.JTextField txtPesSetPrev;
    private javax.swing.JTextField txtPesTecPrev;
    private javax.swing.JTextField txtSetPrev;
    private javax.swing.JTextField txtTecPrev;
    private javax.swing.JTextField txtTipEqui;
    private javax.swing.JTextField txtTipTec;
    // End of variables declaration//GEN-END:variables
}
