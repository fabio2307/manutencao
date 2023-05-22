/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import java.sql.*;
import com.prjmanutencao.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.security.SecureRandom;
import java.util.Random;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Fábio
 */
public class TelaSetor extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaSetor
     */
    public TelaSetor() {
        initComponents();
        conexao = ModuloConexao.conector();
        btnSetDelete.setEnabled(false);
//        ((DefaultTableModel) tblSetores.getModel()).setRowCount(0);
        btnSetUpdate.setEnabled(false);
        btnAdcSet.setEnabled(false);
        btnApaSet.setEnabled(false);
        btnAltCod.setEnabled(false);
        btnModEqui.setEnabled(false);
        cboTipoEquiSet.setEnabled(false);
        ((DefaultTableModel) tblCodEquiSet.getModel()).setRowCount(0);
        pesquisar1();
        
        JTableHeader header, header1;
        
        header = tblSetores.getTableHeader();
        DefaultTableCellRenderer centralizado = (DefaultTableCellRenderer) header.getDefaultRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
        header1 = tblCodEquiSet.getTableHeader();
        DefaultTableCellRenderer centralizado1 = (DefaultTableCellRenderer) header1.getDefaultRenderer();
        centralizado1.setHorizontalAlignment(SwingConstants.CENTER);

    }

    private void pesquisar() {
        String sql = "select id_setor as 'Id', andar as 'Andar', setor as 'Setor' from setor where id_setor like ? or andar like ? or setor like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            pst.setString(2, txtPesquisar.getText() + "%");
            pst.setString(3, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblSetores.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar1() {
        String sql = "select id_setor as 'Id', andar as 'Andar', setor as 'Setor' from setor ";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tblSetores.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_campos() {
        int setar = tblSetores.getSelectedRow();
        txtId.setText(tblSetores.getModel().getValueAt(setar, 0).toString());
        cboAndar.setSelectedItem(tblSetores.getModel().getValueAt(setar, 1).toString());
        txtSetor.setText(tblSetores.getModel().getValueAt(setar, 2).toString());

        btnSetCreate.setEnabled(false);
        btnSetDelete.setEnabled(true);
        btnSetUpdate.setEnabled(true);
        btnAdcSet.setEnabled(true);
        btnAltCod.setEnabled(false);
        btnModEqui.setEnabled(false);
        btnApaSet.setEnabled(false);
        cboTipoEquiSet.setEnabled(true);
        txtNomEqui.setText(null);
        txtIdEqu.setText(null);
        txtEquSet.setText(null);
        txtCodEquSet.setText(null);
        cboTipoEquiSet.setSelectedItem("Elétrica");
        pesquisar_equipamento1();
        qunt_equipamento();

    }

    private void adicionar() {
        String sql = "insert into setor ( andar, setor) values(?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboAndar.getSelectedItem().toString());
            pst.setString(2, txtSetor.getText());

            if ((cboAndar.getSelectedItem().toString().isEmpty()) || (txtSetor.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
            } else {
                int Adicionar = pst.executeUpdate();

                if (Adicionar > 0) {
                    JOptionPane.showMessageDialog(null, "Setor Cadastrado com Sucesso!");
//                    btnAdcSet.setEnabled(true);
                    txtEquSet.setText(null);
                    txtIdEqu.setText(null);
                    txtCodEquSet.setText(null);
                    cboTipoEquiSet.setSelectedItem("Elétrica");
                    cboTipoEquiSet.setEnabled(false);
                    ((DefaultTableModel) tblCodEquiSet.getModel()).setRowCount(0);
                    btnSetCreate.setEnabled(true);
                    btnAltCod.setEnabled(false);
                    btnApaSet.setEnabled(false);
                    btnAdcSet.setEnabled(false);
                    pesquisar1();
                    limpar();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Setor!");
                    txtEquSet.setText(null);
                    txtIdEqu.setText(null);
                    txtCodEquSet.setText(null);
                    cboTipoEquiSet.setSelectedItem("Elétrica");
                    cboTipoEquiSet.setEnabled(false);
                    ((DefaultTableModel) tblCodEquiSet.getModel()).setRowCount(0);
                    btnSetCreate.setEnabled(true);
                    btnAltCod.setEnabled(false);
                    btnApaSet.setEnabled(false);
                    btnAdcSet.setEnabled(false);
                    pesquisar1();
                    limpar();
                    dispose();
                    conexao.close();
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
        String sql = "update setor set andar=?, setor=? where id_setor=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboAndar.getSelectedItem().toString());
            pst.setString(2, txtSetor.getText());
            pst.setString(3, txtId.getText());

            if ((cboAndar.getSelectedItem().toString().isEmpty()) || (txtSetor.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
            } else {
                int alterar = pst.executeUpdate();

                if (alterar > 0) {
                    JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
//                    conexao.close();
                    limpar();
                    pesquisar1();
                    txtEquSet.setText(null);
                    txtIdEqu.setText(null);
                    txtCodEquSet.setText(null);
                    cboTipoEquiSet.setSelectedItem("Elétrica");
                    cboTipoEquiSet.setEnabled(false);
                    ((DefaultTableModel) tblCodEquiSet.getModel()).setRowCount(0);
                    btnSetCreate.setEnabled(true);
                    btnAltCod.setEnabled(false);
                    btnApaSet.setEnabled(false);
                    btnSetCreate.setEnabled(true);
                    btnSetUpdate.setEnabled(false);
                    btnAdcSet.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao Alterar Dados!");
                    conexao.close();
                    btnSetUpdate.setEnabled(false);
                    btnSetCreate.setEnabled(true);
                    txtEquSet.setText(null);
                    txtIdEqu.setText(null);
                    txtCodEquSet.setText(null);
                    cboTipoEquiSet.setSelectedItem("Elétrica");
                    cboTipoEquiSet.setEnabled(false);
                    ((DefaultTableModel) tblCodEquiSet.getModel()).setRowCount(0);
                    btnSetCreate.setEnabled(true);
                    btnAltCod.setEnabled(false);
                    btnApaSet.setEnabled(false);
                    btnAdcSet.setEnabled(false);
                    limpar();
                    pesquisar1();
                    conexao.close();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Remover esse Setor?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from setor where id_setor=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Setor Removido com Sucesso!");
                    limpar();
                    pesquisar1();
                    btnSetDelete.setEnabled(false);
                    btnSetCreate.setEnabled(true);
                    txtEquSet.setText(null);
                    txtIdEqu.setText(null);
                    txtCodEquSet.setText(null);
                    cboTipoEquiSet.setSelectedItem("Elétrica");
                    cboTipoEquiSet.setEnabled(false);
                    ((DefaultTableModel) tblCodEquiSet.getModel()).setRowCount(0);
                    btnSetCreate.setEnabled(true);
                    btnAltCod.setEnabled(false);
                    btnApaSet.setEnabled(false);
                    btnAdcSet.setEnabled(false);
                    conexao.close();
                    dispose();

                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void limpar() {
        txtPesquisar.setText(null);
        txtQuaSet.setText(null);
        txtId.setText(null);
        cboAndar.setSelectedItem("3° Subsolo");
        txtSetor.setText(null);
        txtEquSet.setText(null);
//        ((DefaultTableModel) tblSetores.getModel()).setRowCount(0);
        btnSetDelete.setEnabled(false);

    }

    private void equipamento_setor_add() {
        int len = 3;
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
            txtCodEquSet.setText(sb.toString() + " " + valor);
//            System.out.println(sb);

        }

//        return sb.toString();
    }

    private void cod_equipamento_set() {
        equipamento_setor_add();
        String sql = "insert into equipamento_setor( cod_equi_set, nome_equi_set, tipo_equip, id_setor, setor) values (?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCodEquSet.getText());
            pst.setString(2, txtNomEqui.getText());
            pst.setString(3, cboTipoEquiSet.getSelectedItem().toString());
            pst.setString(4, txtId.getText());
            pst.setString(5, cboAndar.getSelectedItem().toString() + " " + txtSetor.getText());
            if ((cboAndar.getSelectedItem().toString().isEmpty()) || (txtSetor.getText().isEmpty()) || (cboTipoEquiSet.getSelectedItem().toString().isEmpty() || (txtCodEquSet.getText().isEmpty()) || (txtNomEqui.getText()).isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
            } else {
                int Adicionar = pst.executeUpdate();

                if (Adicionar > 0) {
                    JOptionPane.showMessageDialog(null, "Equipamento Cadastrado com Sucesso!");
                    txtEquSet.setText(null);
                    txtIdEqu.setText(null);
                    txtQuaSet.setText(null);
                    txtNomEqui.setText(null);
                    cboTipoEquiSet.setSelectedItem("Elétrica");
                    cboTipoEquiSet.setEnabled(false);
                    ((DefaultTableModel) tblCodEquiSet.getModel()).setRowCount(0);
                    btnAdcSet.setEnabled(false);
                    btnSetCreate.setEnabled(true);
                    btnSetDelete.setEnabled(false);
                    btnSetUpdate.setEnabled(false);
                    txtCodEquSet.setText(null);
                    limpar();
                    pesquisar_equipamento1();
                    qunt_equipamento();

                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Equipamento");

                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void pesquisar_equipamento() {

        String sql = "select id_equi_set as 'N° Equip.', nome_equi_set as 'Equipamento', cod_equi_set as 'Cod. Equip.', tipo_equip as 'Tipo Equip' from equipamento_setor where id_equi_set like ? or id_setor like ? or cod_equi_set like ? or nome_equi_set like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEquSet.getText() + "%");
            pst.setString(2, txtEquSet.getText() + "%");
            pst.setString(3, txtEquSet.getText() + "%");
            pst.setString(4, txtEquSet.getText() + "%");
            rs = pst.executeQuery();
            tblCodEquiSet.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void pesquisar_equipamento1() {

        String sql = "select id_equi_set as 'N° Equip.',nome_equi_set as 'Equipamento', cod_equi_set as 'Cod. Equip.', tipo_equip as 'Tipo Equip' from equipamento_setor where id_setor=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            tblCodEquiSet.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setor_pesq() {

        String sql = "select id_setor, andar, setor from setor where id_setor = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                cboAndar.setSelectedItem(rs.getString(2));
                txtSetor.setText(rs.getString(3));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void qunt_equipamento() {

        String sql = "select count(*) from equipamento_setor  where id_setor = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaSet.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setar_equip() {
        int setar = tblCodEquiSet.getSelectedRow();
        txtIdEqu.setText(tblCodEquiSet.getModel().getValueAt(setar, 0).toString());
        txtNomEqui.setText(tblCodEquiSet.getModel().getValueAt(setar, 1).toString());
        txtCodEquSet.setText(tblCodEquiSet.getModel().getValueAt(setar, 2).toString());
        cboTipoEquiSet.setSelectedItem(tblCodEquiSet.getModel().getValueAt(setar, 3).toString());
        setor_pesq();
        btnAltCod.setEnabled(true);
        btnApaSet.setEnabled(true);
        btnModEqui.setEnabled(true);
        btnAdcSet.setEnabled(false);
        cboTipoEquiSet.setEnabled(true);
        btnSetCreate.setEnabled(false);
        btnSetDelete.setEnabled(false);
        btnSetUpdate.setEnabled(false);
        txtEquSet.setText(null);
        qunt_equipamento();
    }

    private void alterar_cod_equ() {

        String sql = "update equipamento_setor set nome_equi_set = ?, cod_equi_set = ?, tipo_equip = ?, Id_Setor = ?, setor = ? where id_equi_set = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomEqui.getText());
            pst.setString(2, txtCodEquSet.getText());
            pst.setString(3, cboTipoEquiSet.getSelectedItem().toString());
            pst.setString(4, txtId.getText());
            pst.setString(5, cboAndar.getSelectedItem().toString() + " " + txtSetor.getText());
            pst.setString(6, txtIdEqu.getText());

            if ((cboAndar.getSelectedItem().toString().isEmpty()) || (txtSetor.getText().isEmpty()) || (txtCodEquSet.getText().isEmpty()) || (txtIdEqu.getText().isEmpty() || (txtNomEqui.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
            } else {
                int alterar = pst.executeUpdate();

                if (alterar > 0) {
                    JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
                    txtEquSet.setText(null);
                    txtQuaSet.setText(null);
                    txtIdEqu.setText(null);
                    txtCodEquSet.setText(null);
                    txtNomEqui.setText(null);
                    cboTipoEquiSet.setSelectedItem("Elétrica");
                    btnAltCod.setEnabled(false);
                    btnApaSet.setEnabled(false);
                    btnModEqui.setEnabled(false);
//                    btnAdcSet.setEnabled(true);
                    btnSetCreate.setEnabled(true);
                    btnSetDelete.setEnabled(false);
                    btnSetUpdate.setEnabled(false);
                    ((DefaultTableModel) tblCodEquiSet.getModel()).setRowCount(0);
                    cboTipoEquiSet.setEnabled(false);
                    limpar();
                    pesquisar_equipamento1();
//                    qunt_equipamento();

                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao Alterar Dados!");

                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }

    private void apqgar_cod_equ() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Remover esse Setor?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from equipamento_setor where id_equi_set = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdEqu.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Equipamento Removido com Sucesso!");
                    txtEquSet.setText(null);
                    txtIdEqu.setText(null);
                    txtQuaSet.setText(null);
                    txtNomEqui.setText(null);
                    txtCodEquSet.setText(null);
                    cboTipoEquiSet.setSelectedItem("Elétrica");
                    cboTipoEquiSet.setEnabled(false);
                    ((DefaultTableModel) tblCodEquiSet.getModel()).setRowCount(0);
                    btnSetCreate.setEnabled(true);
                    btnAltCod.setEnabled(false);
                    btnApaSet.setEnabled(false);
                    btnSetCreate.setEnabled(true);
                    btnSetDelete.setEnabled(false);
                    btnSetUpdate.setEnabled(false);
                    pesquisar_equipamento1();
                    qunt_equipamento();
                    limpar();

                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
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

        txtPesquisar = new javax.swing.JTextField();
        txtSetor = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        btnSetCreate = new javax.swing.JButton();
        btnSetUpdate = new javax.swing.JButton();
        btnSetDelete = new javax.swing.JButton();
        cboAndar = new javax.swing.JComboBox<>();
        txtCodEquSet = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCodEquiSet = new javax.swing.JTable();
        txtEquSet = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAdcSet = new javax.swing.JButton();
        btnApaSet = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtIdEqu = new javax.swing.JTextField();
        cboTipoEquiSet = new javax.swing.JComboBox<>();
        btnModEqui = new javax.swing.JButton();
        btnAltCod = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtNomEqui = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtQuaSet = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSetores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Setor");
        setPreferredSize(new java.awt.Dimension(1009, 672));
        getContentPane().setLayout(null);

        txtPesquisar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesquisar);
        txtPesquisar.setBounds(260, 162, 371, 30);

        txtSetor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSetor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSetor.setMinimumSize(new java.awt.Dimension(6, 24));
        txtSetor.setPreferredSize(new java.awt.Dimension(6, 24));
        getContentPane().add(txtSetor);
        txtSetor.setBounds(186, 473, 280, 28);

        txtId.setEditable(false);
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        getContentPane().add(txtId);
        txtId.setBounds(186, 540, 159, 28);

        btnSetCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48761_file_add_file_upload_add_upload.png"))); // NOI18N
        btnSetCreate.setToolTipText("Adicionar");
        btnSetCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSetCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnSetCreate);
        btnSetCreate.setBounds(520, 551, 64, 64);

        btnSetUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48763_edit_edit_file_file.png"))); // NOI18N
        btnSetUpdate.setToolTipText("Alterar");
        btnSetUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSetUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnSetUpdate);
        btnSetUpdate.setBounds(600, 551, 64, 64);

        btnSetDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48762_delete_delete file_delete_remove_delete_file_remove_remove_file.png"))); // NOI18N
        btnSetDelete.setToolTipText("Remover");
        btnSetDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSetDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSetDelete);
        btnSetDelete.setBounds(680, 551, 64, 64);

        cboAndar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cboAndar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3° Subsolo", "2° Subsolo", "1° Subsolo", "Térreo", "1° Andar ", "2° Andar ", "3° Andar", "4° Andar", "5° Andar", "6° Andar", "7° Andar", "8° Andar", "9° Andar", "10° Andar", "11° Andar", "12° Andar", "13° Andar", "14° Andar", "15° Andar", "16° Andar ", "17° Andar ", "18° Andar", "19° Andar", "20° Andar", "21° Andar", "22° Andar", "23° Andar", "24° Andar", "25° Andar", "26° Andar", "27° Andar", "28° Andar", "29° Andar", "30° Anda" }));
        getContentPane().add(cboAndar);
        cboAndar.setBounds(186, 406, 280, 28);

        txtCodEquSet.setEditable(false);
        txtCodEquSet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtCodEquSet);
        txtCodEquSet.setBounds(661, 500, 88, 28);

        tblCodEquiSet = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblCodEquiSet.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblCodEquiSet.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "N° Equip.", "Equipamento ", "Cod Equip.", "Tipo Equip."
            }
        ));
        tblCodEquiSet.getTableHeader().setReorderingAllowed(false);
        tblCodEquiSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCodEquiSetMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCodEquiSet);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(510, 376, 408, 60);

        txtEquSet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEquSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEquSetActionPerformed(evt);
            }
        });
        txtEquSet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEquSetKeyReleased(evt);
            }
        });
        getContentPane().add(txtEquSet);
        txtEquSet.setBounds(510, 500, 152, 28);

        jLabel2.setBackground(new java.awt.Color(0, 0, 51));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pesq. Equipamento");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(530, 482, 120, 20);

        btnAdcSet.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnAdcSet.setText("Adicionar");
        btnAdcSet.setToolTipText("Adicionar Equipamento");
        btnAdcSet.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 0), new java.awt.Color(0, 204, 0), new java.awt.Color(0, 204, 0), new java.awt.Color(0, 204, 0)));
        btnAdcSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdcSetActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdcSet);
        btnAdcSet.setBounds(756, 550, 80, 30);

        btnApaSet.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnApaSet.setText("Excluir");
        btnApaSet.setToolTipText("Apagar Equipamento");
        btnApaSet.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 0, 0), new java.awt.Color(255, 0, 0), new java.awt.Color(255, 0, 0), new java.awt.Color(255, 0, 0)));
        btnApaSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApaSetActionPerformed(evt);
            }
        });
        getContentPane().add(btnApaSet);
        btnApaSet.setBounds(756, 586, 80, 30);

        jLabel3.setBackground(new java.awt.Color(0, 0, 51));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ID");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(802, 432, 30, 20);

        txtIdEqu.setEditable(false);
        txtIdEqu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdEqu);
        txtIdEqu.setBounds(778, 450, 82, 28);

        cboTipoEquiSet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elétrica", "Hidráulica ", "Civil", "Refrigeração" }));
        getContentPane().add(cboTipoEquiSet);
        cboTipoEquiSet.setBounds(748, 500, 112, 28);

        btnModEqui.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnModEqui.setMnemonic('M');
        btnModEqui.setText("Modificar");
        btnModEqui.setToolTipText("Modificar Equipamento");
        btnModEqui.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 0), new java.awt.Color(255, 255, 0), new java.awt.Color(255, 255, 0), new java.awt.Color(255, 255, 0)));
        btnModEqui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModEquiActionPerformed(evt);
            }
        });
        getContentPane().add(btnModEqui);
        btnModEqui.setBounds(840, 550, 80, 30);

        btnAltCod.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnAltCod.setText("Gerar Cod.");
        btnAltCod.setToolTipText("Gerar Codigo");
        btnAltCod.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 153), new java.awt.Color(0, 0, 153), new java.awt.Color(0, 0, 153), new java.awt.Color(0, 0, 153)));
        btnAltCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltCodActionPerformed(evt);
            }
        });
        getContentPane().add(btnAltCod);
        btnAltCod.setBounds(840, 586, 80, 30);

        jLabel5.setBackground(new java.awt.Color(0, 0, 51));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nome Equipamento");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(605, 432, 120, 20);

        txtNomEqui.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtNomEqui);
        txtNomEqui.setBounds(510, 450, 268, 28);

        jLabel6.setBackground(new java.awt.Color(0, 0, 51));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cod. Equip.");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(668, 482, 80, 20);

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tipo Equip.");
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(760, 484, 80, 15);

        txtQuaSet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtQuaSet);
        txtQuaSet.setBounds(860, 475, 60, 28);

        jLabel7.setBackground(new java.awt.Color(0, 0, 51));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Qd. Equip.");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(864, 460, 60, 14);

        tblSetores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblSetores.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Andar", "Setor"
            }
        ));
        tblSetores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSetoresMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSetores);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(110, 210, 780, 130);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/Setor5.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-1, 0, 1000, 672);

        setBounds(0, 0, 1007, 672);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetCreateActionPerformed
        // TODO add your handling code here:
        adicionar();

    }//GEN-LAST:event_btnSetCreateActionPerformed

    private void btnSetUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetUpdateActionPerformed
        // TODO add your handling code here:
        alterar();

    }//GEN-LAST:event_btnSetUpdateActionPerformed

    private void btnSetDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDeleteActionPerformed
        // TODO add your handling code here:
        remover();

    }//GEN-LAST:event_btnSetDeleteActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtEquSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEquSetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEquSetActionPerformed

    private void txtEquSetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEquSetKeyReleased
        // TODO add your handling code here:
        pesquisar_equipamento();
    }//GEN-LAST:event_txtEquSetKeyReleased

    private void tblCodEquiSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCodEquiSetMouseClicked
        // TODO add your handling code here:
        setar_equip();
    }//GEN-LAST:event_tblCodEquiSetMouseClicked

    private void btnAdcSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdcSetActionPerformed
        // TODO add your handling code here:
        cod_equipamento_set();
    }//GEN-LAST:event_btnAdcSetActionPerformed

    private void btnModEquiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModEquiActionPerformed
        // TODO add your handling code here:
        alterar_cod_equ();
    }//GEN-LAST:event_btnModEquiActionPerformed

    private void btnApaSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApaSetActionPerformed
        // TODO add your handling code here:
        apqgar_cod_equ();
    }//GEN-LAST:event_btnApaSetActionPerformed

    private void btnAltCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltCodActionPerformed
        // TODO add your handling code here:
        equipamento_setor_add();
    }//GEN-LAST:event_btnAltCodActionPerformed

    private void tblSetoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSetoresMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblSetoresMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdcSet;
    private javax.swing.JButton btnAltCod;
    private javax.swing.JButton btnApaSet;
    private javax.swing.JButton btnModEqui;
    private javax.swing.JButton btnSetCreate;
    private javax.swing.JButton btnSetDelete;
    private javax.swing.JButton btnSetUpdate;
    private javax.swing.JComboBox<String> cboAndar;
    private javax.swing.JComboBox<String> cboTipoEquiSet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblCodEquiSet;
    private javax.swing.JTable tblSetores;
    private javax.swing.JTextField txtCodEquSet;
    private javax.swing.JTextField txtEquSet;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdEqu;
    private javax.swing.JTextField txtNomEqui;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtQuaSet;
    private javax.swing.JTextField txtSetor;
    // End of variables declaration//GEN-END:variables
}
