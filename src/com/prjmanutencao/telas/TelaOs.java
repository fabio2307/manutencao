/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import java.sql.*;
import com.prjmanutencao.dal.ModuloConexao;
import static com.prjmanutencao.telas.TelaPesquisaros.tblOsAberta;
import static com.prjmanutencao.telas.TelaPesquisaros.tblOsFechada;
//import static com.prjmanutencao.telas.TelaPesquisaros.temp;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import static java.lang.Thread.sleep;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import javax.swing.Timer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Fábio
 */
public class TelaOs extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public byte[] BYTES_IMAGEM = null;

    int seg = 0;
    int min = 0;
    int hor = 0;
    int dia = 0;
    boolean estado = true;

    String segu = null;
    String minu = null;
    String hora = null;
    String dia1 = null;

    String des = null;
    public static String ent = null;
    String nom_arq = null;
    String dir = null;
    String fe = "";

//    String acesso = ("files.000webhost.com");//("C:\\banco\\");
//    String log = "dbprojeto23"; 
//    String sen="Fs47526970";
    public static String acesso = ("127.0.0.1");//("C:\\banco\\");
    String log = "servidor";
    String sen = "12345";
    String dire = "arquivos_os";
    JFrame frame = null;

    /**
     * Creates new form TelaOs
     */
    public TelaOs() {
        initComponents();

        conexao = ModuloConexao.conector();
        btnCaptura.setEnabled(false);
        btnArqUpl.setEnabled(false);
        btnArqDow.setEnabled(false);
        btnFecOs.setEnabled(false);
        btnPesArq.setEnabled(false);
        btnAqui.setEnabled(false);
        btnArqDel.setEnabled(false);
        txtIdArq.setEnabled(false);
        txtNomArq.setEnabled(false);
        txtOsArq.setEnabled(false);
//        this.setTitle("Ordem de Serviço" + " " + txtIdOs.getText());
//        
        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);

        Timer timer1 = new Timer(1000, new hora());

        timer1.start();

        JTableHeader header, header1, header2;

        header = tblSetor.getTableHeader();
        DefaultTableCellRenderer centralizado = (DefaultTableCellRenderer) header.getDefaultRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        header1 = tblTecnico.getTableHeader();
        DefaultTableCellRenderer centralizado1 = (DefaultTableCellRenderer) header1.getDefaultRenderer();
        centralizado1.setHorizontalAlignment(SwingConstants.CENTER);

        header2 = tblArqOs.getTableHeader();
        DefaultTableCellRenderer centralizado2 = (DefaultTableCellRenderer) header2.getDefaultRenderer();
        centralizado2.setHorizontalAlignment(SwingConstants.CENTER);

    }
//
//    private void titleAlign() {
//
//        String id = null;
////        id = txtIdOs.getText();
////        Font font = frame.getFont();
//
////        String currentTitle = frame.getTitle().trim();
////        FontMetrics fm = frame.getFontMetrics(font);
////        int frameWidth = frame.getWidth();
////        int titleWidth = fm.stringWidth(currentTitle + 2);
////        int spaceWidth = fm.stringWidth(" ");
////        int centerPos = (frameWidth / 2) - (titleWidth / 2);
////        int spaceCount = centerPos / spaceWidth;
////        String pad = "";
////        // for (int i=0; i!=w; i++) pad +=" ";
////        pad = String.format("%" + (spaceCount - 14) + "s", pad);
////        frame.setTitle(pad + currentTitle + 2);
//
//    }

    private void pesquisar_setor() {
        String sql = "select id_setor as Id, andar as Andar, setor as Setor from setor where setor like ? or id_setor like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesSetor.getText() + "%");
            pst.setString(2, txtPesSetor.getText() + "%");
            rs = pst.executeQuery();
            tblSetor.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_setor() {
        int setar = tblSetor.getSelectedRow();
        txtIdSetor.setText(tblSetor.getModel().getValueAt(setar, 0).toString());
        txtSetor.setText(tblSetor.getModel().getValueAt(setar, 1).toString() + " " + (tblSetor.getModel().getValueAt(setar, 2).toString()));

    }

    private void pesquisar_tecnico() {
        String sql = " select iduser as 'R.E', nome_usuario as 'Técnico', tipo_usuario as 'Especialidade' from usuarios where iduser like ? or nome_usuario like?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesTecnico.getText() + "%");
            pst.setString(2, txtPesTecnico.getText() + "%");
            rs = pst.executeQuery();
            tblTecnico.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_tecnico() {
        int setar = tblTecnico.getSelectedRow();
        txtIdTecnico.setText(tblTecnico.getModel().getValueAt(setar, 0).toString());
        txtTecnico.setText(tblTecnico.getModel().getValueAt(setar, 1).toString() + " - " + (tblTecnico.getModel().getValueAt(setar, 2).toString()));
    }

    private void id_os() {
        String sql = "select max(id_os) from os ";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtIdOs.setText(rs.getString(1));
                txtOsArq.setText(rs.getString(1));

            } else {
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void id_arquivo() {
        String sql = "select max(id_arq) from arquivos_os ";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtIdArq.setText(rs.getString(1));

            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Procurar Id do Arquivo!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    private void id_os_arq() {
//        String sql = "select max(id_os) from os ";
//        try {
//            pst = conexao.prepareStatement(sql);
//            rs = pst.executeQuery();
//            if (rs.next()) {
//                txtOsArq.setText(rs.getString(1));
//
//            } else {
//                JOptionPane.showMessageDialog(null, "Erro ao Procurar Id da O.S");
//            }
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    private void cauc_tempo() {

        String temp;
        String temp1;
        String temp2;
        String temp3;

        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);

        String dateStart = txtDataInicial.getText();
        String dateStop = format;

//        dateStop =Integer.valueOf(temp);
        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        java.util.Date d1;
//        d1 = null;
        java.util.Date d2;
//        d2 = null;

        try {
            d1 = formate.parse(dateStart);
            d2 = formate.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            dia1 = ((diffDays <= 9 ? "0" : "") + diffDays);
            hora = ((diffHours <= 9 ? "0" : "") + diffHours);
            minu = ((diffMinutes <= 9 ? "0" : "") + diffMinutes);
            segu = ((diffSeconds <= 9 ? "0" : "") + diffSeconds);

            temp = segu;
            temp1 = minu;
            temp2 = hora;
            temp3 = dia1;
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
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro ao calcular o Tempo de Ordem de Serviço!", "Atenção", JOptionPane.ERROR_MESSAGE);
//            txtTempo.setText("");
        }

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

        Thread cron = new Thread() {
            @Override
            public void run() {

                for (;;) {
                    if (estado == true) {
                        try {
                            sleep(1000);
                            if (seg >= 60) {
                                seg = 0;
                                min++;
                            }
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

                            lblOsCro.setText((dia <= 9 ? "0" : "") + dia + " - " + (hor <= 9 ? "0" : "") + hor + " : " + (min <= 9 ? "0" : "") + min + " : " + (seg <= 9 ? "0" : "") + seg);

//                            lblOsCro.setText((hor <= 9 ? "0" : "") + hor + "");
//                            lblCroMin.setText("" + (min <= 9 ? "0" : "") + min + "");
//                            lblCroSeg.setText("" + (seg <= 9 ? "0" : "") + seg);
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

    private void iniciar_os() {
        String sit_Os;
        sit_Os = "Aberta";

        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);
        txtDataInicial.setText(format);

        String sql = "insert into os (hora_inicial, hora_final, solicitado_os, id_setor, setor, iduser, nome_usuario, tipo_os, servico_os, desc_os,observ_os, situacao_os, cro_os)values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtDataInicial.getText());
            pst.setString(2, txtDataFinal.getText());
            pst.setString(3, txtSolicitado.getText());
            pst.setString(4, txtIdSetor.getText());
            pst.setString(5, txtSetor.getText());
            pst.setString(6, txtIdTecnico.getText());
            pst.setString(7, txtTecnico.getText());
            pst.setString(8, cboTipoOs.getSelectedItem().toString());
            pst.setString(9, cboServico.getSelectedItem().toString());
            pst.setString(10, txtDescr.getText());
            pst.setString(11, txtObserv.getText());
            pst.setString(12, sit_Os);
            pst.setString(13, lblOsCro.getText());

            if ((txtDataInicial.getText().isEmpty()) || (txtSolicitado.getText().isEmpty()) || (txtIdSetor.getText().isEmpty()) || (txtIdTecnico.getText().isEmpty()) || (txtDescr.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                estado = false;

            } else {

                int Adicionar = pst.executeUpdate();

                if (Adicionar > 0) {
                    id_os();
                    estado = true;
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço Aberta!");
                    btnFecOs.setEnabled(true);
                    btnCaptura.setEnabled(true);
                    btnIniOs.setEnabled(false);
                    tblSetor.setEnabled(false);
                    ((DefaultTableModel) tblSetor.getModel()).setRowCount(0);
                    tblTecnico.setEnabled(false);
                    ((DefaultTableModel) tblTecnico.getModel()).setRowCount(0);
                    txtPesSetor.setEnabled(false);
                    txtPesTecnico.setEnabled(false);
                    txtSolicitado.setEnabled(false);
                    txtDescr.setEnabled(false);
                    cboServico.setEnabled(false);
                    cboTipoOs.setEnabled(false);
                    btnAqui.setEnabled(true);
                    btnPesArq.setEnabled(true);
                    txtIdArq.setEnabled(true);
                    txtNomArq.setEnabled(true);
                    txtOsArq.setEnabled(true);
                    txtPesSetor.setText(null);
                    txtPesTecnico.setText(null);

                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao Abrir a Ordem de Serviço!");

                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            estado = false;

        }

    }

    public void crom() {

        java.util.Date data1 = new java.util.Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
        txtData.setText(formatador.format(data1));
        Calendar now = Calendar.getInstance();

        int setar = tblOsAberta.getSelectedRow();
        txtIdOs.setText(tblOsAberta.getModel().getValueAt(setar, 0).toString());
        txtOsArq.setText(tblOsAberta.getModel().getValueAt(setar, 0).toString());
        os_aberta();

        btnFecOs.setEnabled(true);
        btnCaptura.setEnabled(true);
        btnIniOs.setEnabled(false);
        tblSetor.setEnabled(false);
        ((DefaultTableModel) tblSetor.getModel()).setRowCount(0);
        tblTecnico.setEnabled(false);
        ((DefaultTableModel) tblTecnico.getModel()).setRowCount(0);
        txtPesSetor.setEnabled(false);
        txtPesTecnico.setEnabled(false);
        txtSolicitado.setEnabled(false);
        txtDescr.setEnabled(false);
        cboServico.setEnabled(false);
        cboTipoOs.setEnabled(false);
        btnAqui.setEnabled(true);
        btnPesArq.setEnabled(true);
        txtIdArq.setEnabled(true);
        txtNomArq.setEnabled(true);
        txtOsArq.setEnabled(true);
    }

    public void select_time() {

//        String sql = "select ate_hor_os, ate_min_os, ate_seg_os from os where id_os = ?";
//        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setString(1, txtIdOs.getText());
//            rs = pst.executeQuery();
//            if (rs.next()) {
//                lblOsCro.setText(rs.getString(1));
//                lblCroMin.setText(rs.getString(2));
//                lblCroSeg.setText(rs.getString(3));
//                cronometro();
//
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
    }

    public void crom1() {

        java.util.Date data1 = new java.util.Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
        txtData.setText(formatador.format(data1));
        Calendar now = Calendar.getInstance();

        int setar = tblOsFechada.getSelectedRow();
        txtIdOs.setText(tblOsFechada.getModel().getValueAt(setar, 0).toString());
        txtOsArq.setText(tblOsFechada.getModel().getValueAt(setar, 0).toString());
        os_Encerrada();

        btnFecOs.setEnabled(false);
        btnCaptura.setEnabled(false);
        btnIniOs.setEnabled(false);
        tblSetor.setEnabled(false);
        ((DefaultTableModel) tblSetor.getModel()).setRowCount(0);
        tblTecnico.setEnabled(false);
        ((DefaultTableModel) tblTecnico.getModel()).setRowCount(0);
        txtPesSetor.setEnabled(false);
        txtPesTecnico.setEnabled(false);
        txtSolicitado.setEnabled(false);
        txtDescr.setEnabled(false);
        cboServico.setEnabled(false);
        cboTipoOs.setEnabled(false);
        btnAqui.setEnabled(false);
        btnPesArq.setEnabled(true);
        btnArqDel.setVisible(false);
        txtIdArq.setEnabled(true);
        txtNomArq.setEnabled(false);
        txtOsArq.setEnabled(false);
        txtObserv.setEnabled(false);
    }

    private void os_aberta() {

        String sql = "select solicitado_os, id_setor, setor,  iduser, nome_usuario, tipo_os, servico_os, desc_os, observ_os, hora_inicial from os where id_os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdOs.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtSolicitado.setText(rs.getString(1));
                txtIdSetor.setText(rs.getString(2));
                txtSetor.setText(rs.getString(3));
                txtIdTecnico.setText(rs.getString(4));
                txtTecnico.setText(rs.getString(5));
                cboTipoOs.setSelectedItem(rs.getString(6));
                cboServico.setSelectedItem(rs.getString(7));
                txtDescr.setText(rs.getString(8));
                txtObserv.setText(rs.getString(9));
                txtDataInicial.setText(rs.getString(10));
                cauc_tempo();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void os_Encerrada() {

        String sql = "select solicitado_os, id_setor, setor,  iduser, nome_usuario, tipo_os, servico_os, desc_os, observ_os, hora_inicial, hora_final, cro_os from os where id_os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdOs.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtSolicitado.setText(rs.getString(1));
                txtIdSetor.setText(rs.getString(2));
                txtSetor.setText(rs.getString(3));
                txtIdTecnico.setText(rs.getString(4));
                txtTecnico.setText(rs.getString(5));
                cboTipoOs.setSelectedItem(rs.getString(6));
                cboServico.setSelectedItem(rs.getString(7));
                txtDescr.setText(rs.getString(8));
                txtObserv.setText(rs.getString(9));
                txtDataInicial.setText(rs.getString(10));
                txtDataFinal.setText(rs.getString(11));
                lblOsCro.setText(rs.getString(12));
                select_quant();
                os_encerrada_quant();

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atualizar() {
        String sql = "update os set observ_os =? where id_os=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtObserv.getText());
            pst.setString(2, txtIdOs.getText());
            int Adicionar = pst.executeUpdate();
            if (Adicionar > 0) {

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void fechar_os() {

        String sit = "Encerrada";

        DateTimeFormatter data_hora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        ZonedDateTime zdtNow = ZonedDateTime.now();
        String format = data_hora.format(zdtNow);
        txtDataFinal.setText(format);

        String sql = "update os set hora_final=?, cro_os = ?, observ_os=?, situacao_os = ? where id_os=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtDataFinal.getText());
            pst.setString(2, lblOsCro.getText());
            pst.setString(3, txtObserv.getText());
            pst.setString(4, sit);
            pst.setString(5, txtIdOs.getText());
            estado = false;

            if (txtDataFinal.getText().isEmpty() || lblOsCro.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");

            } else {
                int Adicionar = pst.executeUpdate();

                if (Adicionar > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço Ecerrada!");
                    conexao.close();
                    dispose();
                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            estado = false;
        }

    }

    private void limpar_imagem() {

        txtIdArq.setText(null);
        txtNomArq.setText(null);
        lblFotoOs.setIcon(null);

    }

    private void insert_file() {

        dir = txtNomArq.getText();
        String fileName = ent;
        int a = fileName.lastIndexOf('.');
        if (a >= 0) {
            fe = fileName.substring(a + 1);

        }

        String sql = "insert into arquivos_os( nome_arq, tipo_arq, id_os) values(?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomArq.getText() + (".") + fe);
            pst.setString(2, acesso + dir + (".") + fe);
            txtNomArq.setText(dir + (".") + fe);
            pst.setString(3, txtOsArq.getText());
            if (txtNomArq.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o Campo Obrigatório!");
                lblFotoOs.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
            } else {
                int Adicionar = pst.executeUpdate();
                if (Adicionar > 0) {
                    upload_file();
                    id_arquivo();
//                    JOptionPane.showMessageDialog(null, "Arquivo Adiciono com Sucesso!");
                    txtIdArq.setText(null);
                    txtNomArq.setText(null);
                    lblFotoOs.setIcon(null);

                } else {
                    lblFotoOs.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));

                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void select_file() {

        String sql = "select  nome_arq, tipo_arq, id_os from arquivos_os where id_arq=? and id_os=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdArq.getText());
            pst.setString(2, txtIdOs.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNomArq.setText(rs.getString(1));
                nom_arq = (rs.getString(1));
                ent = (rs.getString(2));
                txtOsArq.setText(rs.getString(3));
                btnArqDow.setEnabled(true);
                btnArqDel.setEnabled(true);
                lblFotoOs.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));

            } else {
                if (txtIdArq.getText().isEmpty()) {
                    btnArqDow.setEnabled(false);
                    btnArqDel.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Preencha o Campo Id para Realializar a Pesquisa!");
                    lblFotoOs.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
                } else {
                    btnArqDow.setEnabled(false);
                    btnArqDel.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Não a Arquivo com esse Id!");
                    txtNomArq.setText(null);

                    lblFotoOs.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void select_quant() {

        String sql = "select id_arq as 'N° Arquivo', nome_arq as 'Nome' from arquivos_os where id_arq like ? and  id_os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdArq.getText() + "%");
            pst.setString(2, txtIdOs.getText());
            rs = pst.executeQuery();
            tblArqOs.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void os_encerrada_quant() {

        String sql = "select count(*) from arquivos_os  where id_os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdOs.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtQuaArq.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setar_arquivos() {
        int setar = tblArqOs.getSelectedRow();
        txtIdArq.setText(tblArqOs.getModel().getValueAt(setar, 0).toString());
    }

    private void delete_file() {

        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Remover esse Arquivo?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from arquivos_os where id_arq=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdArq.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    deletar();
                    btnArqDel.setEnabled(false);
                    btnArqUpl.setEnabled(false);

                    JOptionPane.showMessageDialog(null, "Arquivo Deletado!");

                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void search_file() {

        String nome_fot = txtNomArq.getText();
        String fileName = ent;

        try {

            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagem em JPEG e PNG", "jpg", "png");
            FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Arquivo em TXT, PDF e DOCX", "txt", "pdf", "docx");
            file.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
            file.setFileFilter(filtro);
            file.setFileFilter(filtro1);
            file.setDialogTitle("Carregar Arquivos");
            file.showOpenDialog(this);
            ent = file.getSelectedFile().getPath();
            txtNomArq.setText(file.getSelectedFile().getName().replaceFirst("[.][^.]+$", ""));
//            id_os_arq();
            btnArqUpl.setEnabled(true);
            lblFotoOs.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));
//
        } catch (HeadlessException e) {
            btnArqUpl.setEnabled(false);

            lblFotoOs.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
            JOptionPane.showMessageDialog(null, e);
            btnArqUpl.setEnabled(false);

        }

    }

    private void salve_file() {

        String nome_foto = (txtNomArq.getText());
        String fileName = ent;

        try {
//            
            JFileChooser file = new JFileChooser();
            file.setDialogTitle("Salvar Arquivo");
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            file.setSelectedFile(new File(nome_foto.replaceFirst("[.][^.]+$", "")));
            int i = file.showSaveDialog(null);
            if (i == 1) {
                des = null;
            } else {
                int a = fileName.lastIndexOf('.');
                if (a >= 0) {
                    fe = fileName.substring(a + 1);
                }
                ent = (file.getSelectedFile().getName());
                File arquivo = file.getSelectedFile();
                des = arquivo.getPath() + (".") + fe;
                download_file();
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void upload_file() {

        String dir = txtNomArq.getText();
        FTPClient ftp = new FTPClient();

        try {

            ftp.connect(acesso);

            ftp.login(log, sen);

            boolean changeWorkingDirectory = ftp.changeWorkingDirectory(dire);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            try (FileInputStream arqEnviar = new FileInputStream(ent)) {
                if (ftp.storeFile(dir, arqEnviar)) {
                    btnArqUpl.setEnabled(false);
                    select_quant();
                    os_encerrada_quant();
                    JOptionPane.showMessageDialog(null, "Arquivo Armazenado com Sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao Armazenar o Arquivo.");
                    btnArqUpl.setEnabled(false);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
            btnArqUpl.setEnabled(false);

        }
    }

    private void download_file() {

        FTPClient ftp = new FTPClient();

        try {

            ftp.connect(acesso);

            ftp.login(log, sen);

            boolean changeWorkingDirectory = ftp.changeWorkingDirectory(dire);
            String[] arq = ftp.listNames();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            FileOutputStream fos = new FileOutputStream(des);
            if (ftp.retrieveFile(nom_arq, fos)) {
                JOptionPane.showMessageDialog(null, "Download Efetuado com Sucesso!");
                btnArqDow.setEnabled(false);
                btnArqDel.setEnabled(false);
                txtIdArq.setText(null);
                txtNomArq.setText(null);
                lblFotoOs.setIcon(null);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Efetuar Download do Arquivo.");
                btnArqUpl.setEnabled(false);

            }
            fos.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void deletar() {

        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(acesso);

            ftp.login(log, sen);

//            client.connect(host, port);
            boolean changeWorkingDirectory = ftp.changeWorkingDirectory(dire);
            String[] arq = ftp.listNames();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.deleteFile(nom_arq);
            select_quant();
            os_encerrada_quant();
            btnArqDel.setEnabled(false);
            btnArqDow.setEnabled(false);
            txtIdArq.setText(null);
            txtNomArq.setText(null);
            lblFotoOs.setIcon(null);

            ftp.disconnect();
        } catch (IOException e) {
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

        txtData = new javax.swing.JTextField();
        txtDataInicial = new javax.swing.JTextField();
        txtDataFinal = new javax.swing.JTextField();
        txtIdOs = new javax.swing.JTextField();
        txtSolicitado = new javax.swing.JTextField();
        txtTecnico = new javax.swing.JTextField();
        txtIdSetor = new javax.swing.JTextField();
        txtIdTecnico = new javax.swing.JTextField();
        txtSetor = new javax.swing.JTextField();
        txtDescr = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSetor = new javax.swing.JTable();
        txtPesSetor = new javax.swing.JTextField();
        txtPesTecnico = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTecnico = new javax.swing.JTable();
        cboServico = new javax.swing.JComboBox<>();
        cboTipoOs = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObserv = new javax.swing.JTextArea();
        txtHora = new javax.swing.JTextField();
        btnIniOs = new javax.swing.JButton();
        btnFecOs = new javax.swing.JButton();
        btnCaptura = new javax.swing.JButton();
        btnAqui = new javax.swing.JButton();
        btnArqUpl = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtIdArq = new javax.swing.JTextField();
        lblFotoOs = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNomArq = new javax.swing.JTextField();
        txtOsArq = new javax.swing.JTextField();
        btnPesArq = new javax.swing.JButton();
        btnArqDow = new javax.swing.JButton();
        btnArqDel = new javax.swing.JButton();
        lblOsCro = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblArqOs = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtQuaArq = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(1009, 672));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(null);

        txtData.setEditable(false);
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.setPreferredSize(new java.awt.Dimension(217, 26));
        getContentPane().add(txtData);
        txtData.setBounds(154, 124, 217, 26);

        txtDataInicial.setEditable(false);
        txtDataInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtDataInicial);
        txtDataInicial.setBounds(195, 209, 264, 26);

        txtDataFinal.setEditable(false);
        txtDataFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataFinalActionPerformed(evt);
            }
        });
        getContentPane().add(txtDataFinal);
        txtDataFinal.setBounds(195, 283, 264, 26);

        txtIdOs.setEditable(false);
        txtIdOs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdOs);
        txtIdOs.setBounds(36, 243, 74, 26);

        txtSolicitado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtSolicitado);
        txtSolicitado.setBounds(36, 367, 258, 26);

        txtTecnico.setEditable(false);
        txtTecnico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtTecnico);
        txtTecnico.setBounds(36, 429, 258, 26);

        txtIdSetor.setEditable(false);
        txtIdSetor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSetorActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdSetor);
        txtIdSetor.setBounds(884, 174, 74, 26);

        txtIdTecnico.setEditable(false);
        txtIdTecnico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdTecnico);
        txtIdTecnico.setBounds(884, 289, 74, 26);

        txtSetor.setEditable(false);
        txtSetor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtSetor);
        txtSetor.setBounds(322, 367, 252, 26);

        txtDescr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtDescr);
        txtDescr.setBounds(322, 429, 252, 26);

        jScrollPane1.setViewportBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setEnabled(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(448, 402));

        tblSetor = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblSetor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Andar", "Setor"
            }
        ));
        tblSetor.setFocusable(false);
        tblSetor.getTableHeader().setReorderingAllowed(false);
        tblSetor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSetorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSetor);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(510, 147, 298, 80);

        txtPesSetor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesSetorActionPerformed(evt);
            }
        });
        txtPesSetor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesSetorKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesSetor);
        txtPesSetor.setBounds(514, 120, 288, 24);

        txtPesTecnico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesTecnicoActionPerformed(evt);
            }
        });
        txtPesTecnico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesTecnicoKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesTecnico);
        txtPesTecnico.setBounds(514, 232, 288, 24);

        jScrollPane2.setEnabled(false);

        tblTecnico = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblTecnico.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblTecnico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "R.E", "Técnico ", "Especialidade"
            }
        ));
        tblTecnico.getTableHeader().setReorderingAllowed(false);
        tblTecnico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTecnicoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTecnico);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(510, 259, 298, 74);

        cboServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Civil", "Eletrica", "Hidráulico", "Refrigeração" }));
        getContentPane().add(cboServico);
        cboServico.setBounds(36, 488, 129, 26);

        cboTipoOs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Preventiva", "Corretiva", "Preditiva", "Proativa" }));
        getContentPane().add(cboTipoOs);
        cboTipoOs.setBounds(167, 488, 129, 26);

        txtObserv.setColumns(20);
        txtObserv.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtObserv.setLineWrap(true);
        txtObserv.setRows(5);
        jScrollPane3.setViewportView(txtObserv);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(36, 550, 260, 70);

        txtHora.setEditable(false);
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHora.setPreferredSize(new java.awt.Dimension(100, 26));
        txtHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraActionPerformed(evt);
            }
        });
        getContentPane().add(txtHora);
        txtHora.setBounds(370, 124, 100, 26);

        btnIniOs.setForeground(new java.awt.Color(255, 255, 255));
        btnIniOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48761_file_add_file_upload_add_upload.png"))); // NOI18N
        btnIniOs.setToolTipText("Iniciar O.S");
        btnIniOs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIniOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniOsActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniOs);
        btnIniOs.setBounds(342, 558, 65, 65);

        btnFecOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48753_diskette_diskette.png"))); // NOI18N
        btnFecOs.setToolTipText("Fechar O.S");
        btnFecOs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFecOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFecOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecOsActionPerformed(evt);
            }
        });
        getContentPane().add(btnFecOs);
        btnFecOs.setBounds(422, 558, 65, 65);

        btnCaptura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48744_camera_camera.png"))); // NOI18N
        btnCaptura.setToolTipText("Camera");
        btnCaptura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCaptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturaActionPerformed(evt);
            }
        });
        getContentPane().add(btnCaptura);
        btnCaptura.setBounds(502, 558, 65, 65);

        btnAqui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48769_folder_empty_folder_empty.png"))); // NOI18N
        btnAqui.setToolTipText("Arquivos");
        btnAqui.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAqui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAquiActionPerformed(evt);
            }
        });
        getContentPane().add(btnAqui);
        btnAqui.setBounds(582, 558, 65, 65);

        btnArqUpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48774_upload_upload_load_load.png"))); // NOI18N
        btnArqUpl.setToolTipText("Upload");
        btnArqUpl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnArqUpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArqUplActionPerformed(evt);
            }
        });
        getContentPane().add(btnArqUpl);
        btnArqUpl.setBounds(662, 558, 65, 65);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Arquivos");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(794, 370, 90, 26);

        txtIdArq.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdArq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdArqKeyReleased(evt);
            }
        });
        getContentPane().add(txtIdArq);
        txtIdArq.setBounds(800, 390, 80, 26);

        lblFotoOs.setBackground(new java.awt.Color(102, 102, 102));
        lblFotoOs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFotoOs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        lblFotoOs.setOpaque(true);
        getContentPane().add(lblFotoOs);
        lblFotoOs.setBounds(625, 367, 166, 166);

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nome");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(856, 410, 60, 26);

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("N°O.S");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(910, 370, 49, 26);

        txtNomArq.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomArq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomArqActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomArq);
        txtNomArq.setBounds(800, 430, 175, 26);

        txtOsArq.setEditable(false);
        txtOsArq.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOsArq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsArqActionPerformed(evt);
            }
        });
        getContentPane().add(txtOsArq);
        txtOsArq.setBounds(894, 390, 80, 26);

        btnPesArq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48758_file_search_file_document_search_document.png"))); // NOI18N
        btnPesArq.setToolTipText("Pesquisar");
        btnPesArq.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesArq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesArqActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesArq);
        btnPesArq.setBounds(742, 558, 64, 64);

        btnArqDow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48773_download_load_download_load.png"))); // NOI18N
        btnArqDow.setToolTipText("Download");
        btnArqDow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnArqDow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArqDowActionPerformed(evt);
            }
        });
        getContentPane().add(btnArqDow);
        btnArqDow.setBounds(822, 558, 64, 64);

        btnArqDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48756_delete_delete_document_document.png"))); // NOI18N
        btnArqDel.setToolTipText("Apagar");
        btnArqDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnArqDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArqDelActionPerformed(evt);
            }
        });
        getContentPane().add(btnArqDel);
        btnArqDel.setBounds(902, 558, 64, 64);

        lblOsCro.setFont(new java.awt.Font("Arial Black", 0, 22)); // NOI18N
        lblOsCro.setForeground(new java.awt.Color(0, 0, 80));
        lblOsCro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOsCro.setText("00 - 00 : 00 : 00");
        getContentPane().add(lblOsCro);
        lblOsCro.setBounds(320, 482, 256, 26);

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Id ");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(830, 360, 22, 26);

        tblArqOs = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblArqOs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "N° Arquivo", "Nome"
            }
        ));
        tblArqOs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArqOsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblArqOs);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(800, 480, 175, 54);

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("N° de Arquivos :");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(800, 458, 130, 18);

        txtQuaArq.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtQuaArq.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtQuaArq);
        txtQuaArq.setBounds(920, 458, 54, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Seg.");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(506, 506, 42, 14);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Dia(s)");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(348, 506, 50, 14);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Hora");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(403, 506, 42, 14);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Min.");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(454, 506, 42, 14);

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/tela os2.png"))); // NOI18N
        jLabel2.setText(" ");
        jLabel2.setToolTipText("");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setPreferredSize(new java.awt.Dimension(1007, 672));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(-8, 0, 1007, 672);

        setBounds(0, 0, 1007, 672);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesTecnicoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtPesTecnicoActionPerformed

    private void txtPesSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesSetorActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtPesSetorActionPerformed

    private void txtPesSetorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesSetorKeyReleased
        // TODO add your handling code here:
        pesquisar_setor();
    }//GEN-LAST:event_txtPesSetorKeyReleased

    private void tblSetorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSetorMouseClicked
        // TODO add your handling code here:
        setar_setor();
    }//GEN-LAST:event_tblSetorMouseClicked

    private void txtPesTecnicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesTecnicoKeyReleased
        // TODO add your handling code here:
        pesquisar_tecnico();


    }//GEN-LAST:event_txtPesTecnicoKeyReleased

    private void tblTecnicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTecnicoMouseClicked
        // TODO add your handling code here:
        setar_tecnico();
    }//GEN-LAST:event_tblTecnicoMouseClicked

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtHoraActionPerformed

    private void btnIniOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniOsActionPerformed
        // TODO add your handling code here: 
        iniciar_os();
        cronometro();

    }//GEN-LAST:event_btnIniOsActionPerformed

    private void btnFecOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecOsActionPerformed
        // TODO add your handling code here: 
        fechar_os();
        estado = false;
    }//GEN-LAST:event_btnFecOsActionPerformed

    private void txtDataFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataFinalActionPerformed

    private void btnCapturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturaActionPerformed
        // TODO add your handling code here:
        TelaWebCam captura = new TelaWebCam();
        captura.setVisible(true);
//        id_arquivo();
        limpar_imagem();
        btnArqUpl.setEnabled(true);
        btnArqDow.setEnabled(false);
        btnArqDel.setEnabled(false);

    }//GEN-LAST:event_btnCapturaActionPerformed

    private void txtNomArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomArqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomArqActionPerformed

    private void txtIdSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSetorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSetorActionPerformed

    private void btnAquiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAquiActionPerformed
        // TODO add your handling code here:
        search_file();
    }//GEN-LAST:event_btnAquiActionPerformed

    private void btnArqUplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArqUplActionPerformed
        // TODO add your handling code here:
        insert_file();
    }//GEN-LAST:event_btnArqUplActionPerformed

    private void btnPesArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesArqActionPerformed
        // TODO add your handling code here:
        select_file();
    }//GEN-LAST:event_btnPesArqActionPerformed

    private void btnArqDowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArqDowActionPerformed
        // TODO add your handling code here:
        salve_file();
    }//GEN-LAST:event_btnArqDowActionPerformed

    private void btnArqDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArqDelActionPerformed
        // TODO add your handling code here:
        delete_file();
    }//GEN-LAST:event_btnArqDelActionPerformed

    private void txtIdArqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdArqKeyReleased
        // TODO add your handling code here:
        select_quant();
    }//GEN-LAST:event_txtIdArqKeyReleased

    private void tblArqOsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArqOsMouseClicked
        // TODO add your handling code here:
        setar_arquivos();
    }//GEN-LAST:event_tblArqOsMouseClicked

    private void txtOsArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsArqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsArqActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        atualizar();
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAqui;
    private javax.swing.JButton btnArqDel;
    public static javax.swing.JButton btnArqDow;
    public static javax.swing.JButton btnArqUpl;
    private javax.swing.JButton btnCaptura;
    private javax.swing.JButton btnFecOs;
    private javax.swing.JButton btnIniOs;
    private javax.swing.JButton btnPesArq;
    private javax.swing.JComboBox<String> cboServico;
    private javax.swing.JComboBox<String> cboTipoOs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JLabel lblFotoOs;
    private javax.swing.JLabel lblOsCro;
    private javax.swing.JTable tblArqOs;
    private javax.swing.JTable tblSetor;
    private javax.swing.JTable tblTecnico;
    public static javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDataFinal;
    private javax.swing.JTextField txtDataInicial;
    private javax.swing.JTextField txtDescr;
    public static javax.swing.JTextField txtHora;
    public static javax.swing.JTextField txtIdArq;
    private javax.swing.JTextField txtIdOs;
    private javax.swing.JTextField txtIdSetor;
    private javax.swing.JTextField txtIdTecnico;
    public static javax.swing.JTextField txtNomArq;
    private javax.swing.JTextArea txtObserv;
    public static javax.swing.JTextField txtOsArq;
    private javax.swing.JTextField txtPesSetor;
    private javax.swing.JTextField txtPesTecnico;
    private javax.swing.JTextField txtQuaArq;
    private javax.swing.JTextField txtSetor;
    private javax.swing.JTextField txtSolicitado;
    private javax.swing.JTextField txtTecnico;
    // End of variables declaration//GEN-END:variables

    class hora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            txtHora.setText(String.format("%1$tH:%1$tM:%1tS", now));

        }
    }

}
