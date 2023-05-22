/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import java.sql.*;
import com.prjmanutencao.dal.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Fábio
 */
public class TelaPesquisaros extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaPesquisaros
     */
    public TelaPesquisaros() {
        initComponents();
        conexao = ModuloConexao.conector();
        os_aberta();
        os_fechada();
        os_abertas_quant();
        os_encerrada_quant();

        JTableHeader header, header1;
        header = tblOsAberta.getTableHeader();
        DefaultTableCellRenderer centralizado = (DefaultTableCellRenderer) header.getDefaultRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        header1 = tblOsFechada.getTableHeader();
        DefaultTableCellRenderer centralizado1 = (DefaultTableCellRenderer) header1.getDefaultRenderer();
        centralizado1.setHorizontalAlignment(SwingConstants.CENTER);

    }

    private void os_aberta() {

        String status = "Aberta";

        String sql = "select id_os as 'N° OS', solicitado_os as 'Solicitante', setor as 'Setor', nome_usuario as 'Técnico', tipo_os as 'O.S', desc_os as 'Descrisção', hora_inicial as 'Início' from os where situacao_os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, status);
            rs = pst.executeQuery();
            tblOsAberta.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void os_abertas_quant() {

        String status = "Aberta";

        String sql = "select count(*) from os  where situacao_os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, status);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOsAber.setText(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void pesquisar_os() {

        String status = "Aberta";

        String sql = "select id_os as 'N° OS', solicitado_os as 'Solicitante', setor as 'Setor', nome_usuario as 'Técnico', tipo_os as 'O.S', desc_os as 'Descrisção', hora_inicial as 'Início' from os where id_os like ?  and situacao_os = ? or nome_usuario like ?  and situacao_os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtOsAbr.getText() + "%");
            pst.setString(2, status);
            pst.setString(3, txtOsAbr.getText() + "%");
            pst.setString(4, status);
            rs = pst.executeQuery();
            tblOsAberta.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void os_fechada() {

        String status = "Encerrada";

        String sql = "select id_os as 'N° OS', solicitado_os as 'Solicitante', setor as 'Setor', nome_usuario as 'Técnico', tipo_os as 'O.S', desc_os as 'Descrisção', hora_final as 'Término', cro_os as 'Atend.' from os where situacao_os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, status);
            rs = pst.executeQuery();
            tblOsFechada.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void os_encerrada_quant() {

        String status = "Encerrada";

        String sql = "select count(*) from os  where situacao_os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, status);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOsEnce.setText(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void pesquisar_os1() {

        String status = "Encerrada";

        String sql = "select id_os as 'N° OS', solicitado_os as 'Solicitante', setor as 'Setor', nome_usuario as 'Técnico', tipo_os as 'O.S', desc_os as 'Descrisção', hora_inicial as 'Início',  hora_final as 'Término', cro_os as 'Atend.' from os where id_os like ?  and situacao_os='Encerrada'or nome_usuario like ?  and situacao_os='Encerrada' ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtOsFec.getText() + "%");
            pst.setString(2, txtOsFec.getText() + "%");
            rs = pst.executeQuery();
            tblOsFechada.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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

        txtOsAbr = new javax.swing.JTextField();
        txtOsFec = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOsAberta = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOsFechada = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtOsAber = new javax.swing.JTextField();
        txtOsEnce = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Lista O.S");
        setFocusCycleRoot(false);
        setPreferredSize(new java.awt.Dimension(1007, 672));
        getContentPane().setLayout(null);

        txtOsAbr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOsAbr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOsAbrKeyReleased(evt);
            }
        });
        getContentPane().add(txtOsAbr);
        txtOsAbr.setBounds(279, 145, 395, 26);

        txtOsFec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOsFec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOsFecKeyReleased(evt);
            }
        });
        getContentPane().add(txtOsFec);
        txtOsFec.setBounds(279, 373, 395, 26);

        tblOsAberta = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblOsAberta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblOsAberta.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblOsAberta.setModel(new javax.swing.table.DefaultTableModel(
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
                "N° O.S", "Solicitante ", "Setor", "Técnico ", "O.S", "Serviço", "Descrisção", "Início"
            }
        ));
        tblOsAberta.getTableHeader().setReorderingAllowed(false);
        tblOsAberta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOsAbertaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOsAberta);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 198, 937, 112);

        tblOsFechada = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblOsFechada.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblOsFechada.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblOsFechada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° O.S", "Solicitante", "Setor", "Técnico", "O.S", "Serviço", "Descrisção", "Inínicio", "Término", "Atend."
            }
        ));
        tblOsFechada.getTableHeader().setReorderingAllowed(false);
        tblOsFechada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOsFechadaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblOsFechada);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(30, 427, 937, 112);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/1216559_arrows_refresh_reload_repeat_rotate_icon.png"))); // NOI18N
        jButton1.setToolTipText("Atualizar Listas");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(585, 561, 64, 64);

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("O.S Abertas :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(668, 564, 110, 26);

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("O.S Encerradas :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(660, 594, 110, 26);

        txtOsAber.setEditable(false);
        txtOsAber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtOsAber);
        txtOsAber.setBounds(772, 562, 60, 28);

        txtOsEnce.setEditable(false);
        txtOsEnce.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtOsEnce);
        txtOsEnce.setBounds(772, 594, 60, 28);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/Lista Os.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(1007, 672));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-6, -20, 1007, 672);

        setBounds(0, 0, 1007, 672);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        os_aberta();
        os_fechada();
        os_abertas_quant();
        os_encerrada_quant();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtOsAbrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOsAbrKeyReleased
        // TODO add your handling code here:
        pesquisar_os();
    }//GEN-LAST:event_txtOsAbrKeyReleased

    private void tblOsAbertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOsAbertaMouseClicked
        // TODO add your handling code here:
        TelaOs os = new TelaOs();
        os.setVisible(true);
        getParent().add(os);
        os.crom();
        os.select_quant();
        os.select_time();
        os.os_encerrada_quant();
        dispose();
    }//GEN-LAST:event_tblOsAbertaMouseClicked

    private void txtOsFecKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOsFecKeyReleased
        // TODO add your handling code here:
        pesquisar_os1();
    }//GEN-LAST:event_txtOsFecKeyReleased

    private void tblOsFechadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOsFechadaMouseClicked
        // TODO add your handling code here:
        TelaOs os = new TelaOs();
        os.setVisible(true);
        getParent().add(os);
        os.crom1();
        dispose();
    }//GEN-LAST:event_tblOsFechadaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tblOsAberta;
    public static javax.swing.JTable tblOsFechada;
    private javax.swing.JTextField txtOsAber;
    private javax.swing.JTextField txtOsAbr;
    private javax.swing.JTextField txtOsEnce;
    private javax.swing.JTextField txtOsFec;
    // End of variables declaration//GEN-END:variables
}
