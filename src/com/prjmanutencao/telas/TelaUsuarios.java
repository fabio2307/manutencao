/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

/**
 *
 * @author Fábio
 */
import java.sql.*;
import com.prjmanutencao.dal.ModuloConexao;
import javax.swing.JOptionPane;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class TelaUsuarios extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String des = null;
    public static String ent2 = null;
    String ent3 = null;
    String nom_arq1 = null;
    String dir = null;
    String fe = "";
    String Imag = null;
    String id = null;

//    String acesso = ("files.000webhost.com");//("C:\\banco\\");
//    String log = "dbprojeto23"; 
//    String sen="Fs47526970";
    public static String acesso = ("127.0.0.1");//("C:\\banco\\");
    String log = "servidor";
    String sen = "12345";
    String dire = "fotos_usu";
    String data = null;

    /**
     * Creates new form TelaUsuarios
     */
    public TelaUsuarios() {
        initComponents();
        conexao = ModuloConexao.conector();
        cboUsuPer.setSelectedItem("User");
        cboUsotip.setSelectedItem("Auxiliar");
        cboSexUsu.setSelectedItem("Não Informado");
        btnUsuDelete.setEnabled(false);
        btnUsuCreate.setEnabled(false);
        btnUsuUpdate.setEnabled(false);
        btnAltFot.setEnabled(false);
        btnAltFot.setVisible(false);
        jdcDatUsu.setDateFormatString("yyyy-MM-dd");
        pesquisa_inic();

        ent3 = "C:\\Users\\fabio\\OneDrive\\Área de Trabalho\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\telas\\imagens_fundo\\sem_imagem.png";
        ImageIcon icon = new ImageIcon(ent3);
        icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
        lblFotUsu.setIcon(icon);

        ZonedDateTime zdtNow = ZonedDateTime.now();
        jdcDatUsu.setDate(Timestamp.from(Instant.from(zdtNow)));
        
        JTableHeader header = tblPeUsu.getTableHeader();
        DefaultTableCellRenderer centralizado = (DefaultTableCellRenderer) header.getDefaultRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

    }

    private void consultar() {

        txtNomDir.setText(null);

        String sql = "select * from usuarios where iduser like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id + "%");
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsoNom.setText(rs.getString(2));
                cboUsuPer.setSelectedItem(rs.getString(3));
                cboUsotip.setSelectedItem(rs.getString(4));
                txtUsoLog.setText(rs.getString(5));
                txtUsuSen.setText(rs.getString(6));
                txtNomDir.setText(rs.getString(7));
                nom_arq1 = (rs.getString(7));
                ent2 = (rs.getString(8));
                cboSexUsu.setSelectedItem(rs.getString(9));
                jdcDatUsu.setDate(rs.getDate(10));

                receber();
                btnUsuCreate.setEnabled(false);
                btnUsuDelete.setEnabled(true);
                btnUsuUpdate.setEnabled(true);
//                btnAltFot.setEnabled(true);
                btnAltFot.setVisible(true);

            } else {
                ImageIcon icon = new ImageIcon(ent3);
                icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
                lblFotUsu.setIcon(icon);
                btnUsuCreate.setEnabled(true);
                btnUsuDelete.setEnabled(false);
                btnUsuUpdate.setEnabled(false);
                btnAltFot.setVisible(false);
                pesquisar_usu();
                txtPesUsu.setText(null);
                txtUsoNom.setText(null);
                txtUsoLog.setText(null);
                txtUsuSen.setText(null);;
                txtUsoNom.setText(null);
                txtUsoLog.setText(null);
                txtUsuSen.setText(null);
                txtNomDir.setText(null);
                txtIdaUsu.setText(null);
                jdcDatUsu.setDate(null);
                cboUsuPer.setSelectedItem("User");
                cboUsotip.setSelectedItem("Auxiliar");
                cboSexUsu.setSelectedItem("Não Informado");

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void comparar() {

        String sql = "select iduser from usuarios where iduser like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText() + "%");
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsoNom.setEnabled(false);
                txtUsoLog.setEnabled(false);
                txtUsuSen.setEnabled(false);
                txtNomDir.setEnabled(false);
                txtIdaUsu.setEnabled(false);
                cboUsuPer.setEnabled(false);
                cboUsotip.setEnabled(false);
                cboSexUsu.setEnabled(false);
                jdcDatUsu.setEnabled(false);
                btnUsuFot.setEnabled(false);
                btnArqFotUsu.setEnabled(false);
                btnUsuUpdate.setEnabled(false);
                btnUsuDelete.setEnabled(false);
                btnUsuCreate.setEnabled(false);

                ent3 = "C:\\Users\\fabio\\OneDrive\\Área de Trabalho\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\telas\\imagens_fundo\\sem_imagem.png";
                ImageIcon icon = new ImageIcon(ent3);
                icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
                lblFotUsu.setIcon(icon);
            } else {
                pesquisar_usu();
                txtPesUsu.setText(null);
                txtUsoNom.setEnabled(true);
                txtUsoLog.setEnabled(true);
                txtUsuSen.setEnabled(true);
                txtNomDir.setEnabled(true);
                txtIdaUsu.setEnabled(true);
                cboUsuPer.setEnabled(true);
                cboUsotip.setEnabled(true);
                cboSexUsu.setEnabled(true);
                jdcDatUsu.setEnabled(true);
                btnUsuFot.setEnabled(true);
                btnArqFotUsu.setEnabled(true);
                btnUsuCreate.setEnabled(true);
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisa_inic() {

        String sql = "select iduser as 'R.E', nome_usuario as 'Nome', tipo_usuario as 'Tipo' from usuarios";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tblPeUsu.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar_usu() {

        String sql = "select iduser as 'R.E', nome_usuario as 'Nome', tipo_usuario as 'Tipo' from usuarios where iduser like ? or nome_usuario like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesUsu.getText() + "%");
            pst.setString(2, txtPesUsu.getText() + "%");
            rs = pst.executeQuery();
            tblPeUsu.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_usu() {
        int setar = tblPeUsu.getSelectedRow();
        txtUsuId.setText(tblPeUsu.getModel().getValueAt(setar, 0).toString());
        id = (tblPeUsu.getModel().getValueAt(setar, 0).toString());
        consultar();
        idade_usu();
        pesquisa_inic();

        txtPesUsu.setText(null);
        txtUsoNom.setEnabled(true);
        txtUsoLog.setEnabled(true);
        txtUsuSen.setEnabled(true);
        txtUsoNom.setEnabled(true);
        txtUsoLog.setEnabled(true);
        txtUsuSen.setEnabled(true);
        txtNomDir.setEnabled(true);
        txtIdaUsu.setEnabled(true);
        cboUsuPer.setEnabled(true);
        cboUsotip.setEnabled(true);
        cboSexUsu.setEnabled(true);
        jdcDatUsu.setEnabled(true);
        btnUsuFot.setEnabled(true);
        btnArqFotUsu.setEnabled(true);
    }

    private void idade_usu() {

        String sql = "select YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(nasc_usu))) from usuarios where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtIdaUsu.setText(rs.getString(1) + " Anos");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void search_file() {

        String nome_fot = txtNomDir.getText();
        String fileName = ent2;

        try {

            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagem em JPEG ", "jpg");
            file.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
            file.setFileFilter(filtro);
            file.setDialogTitle("Carregar Foto");
            file.showOpenDialog(this);
            ent2 = file.getSelectedFile().getPath();
            txtNomDir.setText(file.getSelectedFile().getName().replaceFirst("[.][^.]+$", ""));

            ImageIcon icon = new ImageIcon(ent2);
            icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 100));
            lblFotUsu.setIcon(icon);
            btnAltFot.setEnabled(true);

        } catch (HeadlessException e) {
            ImageIcon icon = new ImageIcon(ent3);
            icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
            lblFotUsu.setIcon(icon);
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void upload_file() {

        String dir = txtNomDir.getText();
//        dir = dir + ".jpg";
        FTPClient ftp = new FTPClient();

        try {

            ftp.connect(acesso);

            ftp.login(log, sen);

            boolean changeWorkingDirectory = ftp.changeWorkingDirectory(dire);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            try (FileInputStream arqEnviar = new FileInputStream(ent2)) {
                if (ftp.storeFile(dir, arqEnviar)) {
                    dir = txtNomDir.getText() + (".") + fe;
//                    JOptionPane.showMessageDialog(null, "Arquivo Armazenado com Sucesso!");
//                    System.out.println("Arquivo Armazenado com Sucesso!");
                } else {
//                    JOptionPane.showMessageDialog(null, "Erro ao Armazenar o Arquivo.");
                    ImageIcon icon = new ImageIcon(ent3);
                    icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
                    lblFotUsu.setIcon(icon);
                    System.out.println("Erro ao Armazenar o Arquivo.");
                    System.out.println(dir);
                }
                arqEnviar.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void receber() {

        FTPClient ftp = new FTPClient();

        try {

            ftp.connect(acesso);

            ftp.login(log, sen);

            boolean changeWorkingDirectory = ftp.changeWorkingDirectory(dire);
            String[] arq = ftp.listNames();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            FileOutputStream fos = new FileOutputStream(Imag + (".") + fe);
            ent2 = Imag + (".") + fe;
            if (ftp.retrieveFile(nom_arq1, fos)) {
                nom_arq1 = txtNomDir.getText() + (".") + fe;
                ImageIcon icon = new ImageIcon(Imag + (".") + fe);
                icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 100));
                lblFotUsu.setIcon(icon);
            } else {

                ImageIcon icon = new ImageIcon(ent3);
                icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
                lblFotUsu.setIcon(icon);

            }
            fos.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void deletar1() {
        String nom_arq2 = null;
        nom_arq2 = txtNomDir.getText();

        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(acesso);

            ftp.login(log, sen);

            boolean changeWorkingDirectory = ftp.changeWorkingDirectory(dire);
            String[] arq = ftp.listNames();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
//            nom_arq2 = txtNomDir.getText();
            ftp.deleteFile(nom_arq2);

            ftp.disconnect();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void servicos() {

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date pega;
            pega = jdcDatUsu.getDate();
            this.data = formato.format(pega);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
//        
    }

    private void adicionar() {

        if (ent2 != null) {
            Random aleatorio = new Random();
            int valor = aleatorio.nextInt(300000) + 1;
            txtNomDir.setText(valor + "");
            dir = txtNomDir.getText();
            dir = txtNomDir.getText();
            String fileName = ent2;
            int a = fileName.lastIndexOf('.');
            if (a >= 0) {
                fe = fileName.substring(a + 1);
            } else {
                txtNomDir.setText(null);
            }
        }
        servicos();
        String sql = "insert into usuarios(iduser, nome_usuario, perfil_usuario, tipo_usuario, login_usuario, senha_usuario, nome_foto, foto_usu, sexo_usu, nasc_usu) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsoNom.getText());
            pst.setString(3, cboUsuPer.getSelectedItem().toString());
            pst.setString(4, cboUsotip.getSelectedItem().toString());
            pst.setString(5, txtUsoLog.getText());
            pst.setString(6, txtUsuSen.getText());
            pst.setString(7, txtNomDir.getText() + (".") + fe);
            pst.setString(8, acesso + dir + (".") + fe);
            txtNomDir.setText(dir + (".") + fe);
            pst.setString(9, cboSexUsu.getSelectedItem().toString());
            pst.setString(10, data);
            if ((txtUsuSen.getText().isEmpty()) || (txtUsuId.getText().isEmpty()) || (txtUsoNom.getText().isEmpty()) || (txtUsoLog.getText().isEmpty())) {
                txtNomDir.setText(null);
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");

            } else {
                int Adicionar = pst.executeUpdate();
                if (Adicionar > 0) {
                    upload_file();
                    JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!");

                    ImageIcon icon = new ImageIcon(ent3);
                    icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
                    lblFotUsu.setIcon(icon);
                    limpar();
                    conexao.close();
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar Usuario!");
                    limpar();
                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {

        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Realizar Alterações neste Cadastro?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {

            java.util.Date pega = jdcDatUsu.getDate();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            this.data = formato.format(pega);

            String sql = "update usuarios set nome_usuario=?, perfil_usuario=?, tipo_usuario=?,login_usuario=?, senha_usuario=?,sexo_usu=?, nasc_usu=? where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsoNom.getText());
                pst.setString(2, cboUsuPer.getSelectedItem().toString());
                pst.setString(3, cboUsotip.getSelectedItem().toString());
                pst.setString(4, txtUsoLog.getText());
                pst.setString(5, txtUsuSen.getText());
                pst.setString(6, cboSexUsu.getSelectedItem().toString());
                pst.setString(7, data);
                pst.setString(8, txtUsuId.getText());

                if ((txtUsuSen.getText().isEmpty()) || (txtUsuId.getText().isEmpty()) || (txtUsoNom.getText().isEmpty()) || (txtUsoLog.getText().isEmpty())) {

                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {

                    int alterar1 = pst.executeUpdate();
                    if (alterar1 > 0) {
                        JOptionPane.showMessageDialog(null, "Dados do Usuário Alterados com Sucesso!");
                        ImageIcon icon = new ImageIcon(ent3);
                        icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
                        lblFotUsu.setIcon(icon);
                        limpar();
                        conexao.close();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao Alterar os Dados!");
                        ImageIcon icon = new ImageIcon(ent3);
                        icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
                        lblFotUsu.setIcon(icon);
                        conexao.close();
                        limpar();
                    }
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    private void altera_foto() {

        String fe1 = null;
        String fileName2 = ent2;

        dir = txtNomDir.getText();

        if (ent2 != null) {
            int a = fileName2.lastIndexOf('.');
            if (a >= 0) {
                fe1 = fileName2.substring(a + 1);
                System.out.println(ent2);

            } else {
            }

        }

        String sql = "update usuarios set nome_foto=?, foto_usu=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomDir.getText() + (".") + fe1);
            pst.setString(2, acesso + dir + (".") + fe1);
            System.out.println(acesso + dir + (".") + fe1);
            txtNomDir.setText(dir + (".") + fe1);
            System.out.println(dir + (".") + fe1);
            pst.setString(3, txtUsuId.getText());
            if ((txtUsuSen.getText().isEmpty()) || (txtUsuId.getText().isEmpty()) || (txtUsoNom.getText().isEmpty()) || (txtUsoLog.getText().isEmpty()) || (txtNomDir.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");

            } else {
                int Adicionar = pst.executeUpdate();
                if (Adicionar > 0) {
                    btnAltFot.setEnabled(false);
                    upload_file();
                    JOptionPane.showMessageDialog(null, "Foto Salva com Sucesso!");

                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar Usuario!");
                    limpar();
                }
            }

        } catch (HeadlessException | SQLException e) {
        }

    }

    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Remover esse Usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from usuarios where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());

                if ((txtUsuSen.getText().isEmpty()) || (txtUsuId.getText().isEmpty()) || (txtUsoNom.getText().isEmpty()) || (txtUsoLog.getText().isEmpty()) || (txtNomDir.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");

                } else {
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        deletar1();
                        JOptionPane.showMessageDialog(null, "Usuário Removido com Sucesso!");
                        ImageIcon icon = new ImageIcon(ent3);
                        icon.setImage(icon.getImage().getScaledInstance(lblFotUsu.getWidth(), lblFotUsu.getHeight(), 60));
                        lblFotUsu.setIcon(icon);
                        conexao.close();
                        limpar();
                        dispose();
                    }
                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void limpar() {
        txtUsoNom.setText(null);
        txtUsoLog.setText(null);
        txtUsuSen.setText(null);
        txtUsuId.setText(null);
        txtUsoNom.setText(null);
        txtUsoLog.setText(null);
        txtUsuSen.setText(null);
        txtNomDir.setText(null);
        txtIdaUsu.setText(null);
        cboUsuPer.setSelectedItem("User");
        cboUsotip.setSelectedItem("Auxiliar");
        cboSexUsu.setSelectedItem("Não Informado");
        ZonedDateTime zdtNow = ZonedDateTime.now();
        jdcDatUsu.setDate(Timestamp.from(Instant.from(zdtNow)));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuId = new javax.swing.JTextField();
        txtUsoNom = new javax.swing.JTextField();
        txtUsoLog = new javax.swing.JTextField();
        cboUsuPer = new javax.swing.JComboBox<>();
        txtUsuSen = new javax.swing.JTextField();
        cboUsotip = new javax.swing.JComboBox<>();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        lblFotUsu = new javax.swing.JLabel();
        btnUsuFot = new javax.swing.JButton();
        btnArqFotUsu = new javax.swing.JButton();
        txtNomDir = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboSexUsu = new javax.swing.JComboBox<>();
        txtIdaUsu = new javax.swing.JTextField();
        btnAltFot = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jdcDatUsu = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeUsu = new javax.swing.JTable();
        txtPesUsu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("sistema - Usuários");
        setPreferredSize(new java.awt.Dimension(1007, 672));
        setRequestFocusEnabled(false);
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

        txtUsuId.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUsuId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuIdKeyReleased(evt);
            }
        });
        getContentPane().add(txtUsuId);
        txtUsuId.setBounds(256, 185, 232, 28);

        txtUsoNom.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUsoNom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsoNom.setText("     ");
        txtUsoNom.setMinimumSize(new java.awt.Dimension(4, 21));
        txtUsoNom.setPreferredSize(new java.awt.Dimension(65, 15));
        txtUsoNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsoNomActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsoNom);
        txtUsoNom.setBounds(256, 267, 232, 28);

        txtUsoLog.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUsoLog.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtUsoLog);
        txtUsoLog.setBounds(256, 349, 232, 28);

        cboUsuPer.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cboUsuPer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Superadmin", "Admin", "User" }));
        getContentPane().add(cboUsuPer);
        cboUsuPer.setBounds(738, 188, 232, 28);

        txtUsuSen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUsuSen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuSen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuSenActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuSen);
        txtUsuSen.setBounds(738, 349, 232, 28);

        cboUsotip.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cboUsotip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gestor", "Operacional", "Técnico - Elética", "Técnico - Hidráulica", "Técnico - Civil", "Técnico - Refrigeração", "Auxiliar" }));
        cboUsotip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsotipActionPerformed(evt);
            }
        });
        getContentPane().add(cboUsotip);
        cboUsotip.setBounds(738, 267, 232, 28);

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48761_file_add_file_upload_add_upload.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuCreate);
        btnUsuCreate.setBounds(565, 491, 64, 64);

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48763_edit_edit_file_file.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuUpdate);
        btnUsuUpdate.setBounds(706, 491, 64, 64);

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48762_delete_delete file_delete_remove_delete_file_remove_remove_file.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Remover");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuDelete);
        btnUsuDelete.setBounds(853, 491, 64, 64);

        lblFotUsu.setBackground(new java.awt.Color(102, 102, 102));
        lblFotUsu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFotUsu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        lblFotUsu.setOpaque(true);
        getContentPane().add(lblFotUsu);
        lblFotUsu.setBounds(58, 416, 190, 190);

        btnUsuFot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48744_camera_camera.png"))); // NOI18N
        btnUsuFot.setToolTipText("Camera");
        btnUsuFot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuFot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuFotActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuFot);
        btnUsuFot.setBounds(280, 418, 64, 64);

        btnArqFotUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48769_folder_empty_folder_empty.png"))); // NOI18N
        btnArqFotUsu.setToolTipText("Arquivos");
        btnArqFotUsu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnArqFotUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArqFotUsuActionPerformed(evt);
            }
        });
        getContentPane().add(btnArqFotUsu);
        btnArqFotUsu.setBounds(380, 418, 64, 64);

        txtNomDir.setEditable(false);
        txtNomDir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNomDir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomDir.setEnabled(false);
        txtNomDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomDirActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomDir);
        txtNomDir.setBounds(270, 574, 180, 28);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nome");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(334, 600, 50, 15);

        cboSexUsu.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cboSexUsu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não Informado", "Masculino", "Feminino" }));
        getContentPane().add(cboSexUsu);
        cboSexUsu.setBounds(256, 226, 232, 28);

        txtIdaUsu.setEditable(false);
        txtIdaUsu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIdaUsu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtIdaUsu);
        txtIdaUsu.setBounds(408, 308, 80, 28);

        btnAltFot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48783_add_add_notification.png"))); // NOI18N
        btnAltFot.setToolTipText("Alterar Foto");
        btnAltFot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAltFot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltFotActionPerformed(evt);
            }
        });
        getContentPane().add(btnAltFot);
        btnAltFot.setBounds(330, 498, 64, 64);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/48783_add_add_notification.png"))); // NOI18N
        jLabel3.setToolTipText("Alterar Foto");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        jLabel3.setEnabled(false);
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(330, 498, 64, 64);

        jLabel4.setText("Fotos em Formato jpg");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(92, 606, 130, 14);

        ImageIcon icon = new ImageIcon("C:\\Users\\fabio\\OneDrive\\Área de Trabalho\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\data.png");
        jdcDatUsu.setIcon(icon);
        jdcDatUsu.setToolTipText("");
        jdcDatUsu.setDateFormatString("");
        jdcDatUsu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jdcDatUsu.setIcon(null);
        getContentPane().add(jdcDatUsu);
        jdcDatUsu.setBounds(256, 308, 140, 28);

        tblPeUsu = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdex, int colIndex){
                return false;
            }
        };
        tblPeUsu.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null}
            },
            new String [] {
                "R.E", "Nome", "Tipo"
            }
        ));
        tblPeUsu.getTableHeader().setReorderingAllowed(false);
        tblPeUsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPeUsuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeUsu);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(510, 390, 370, 80);

        txtPesUsu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesUsu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesUsuKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesUsu);
        txtPesUsu.setBounds(884, 418, 90, 28);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Pesq. Usuários");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(884, 396, 90, 20);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/Retângulo 538.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(500, 385, 480, 90);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/telas/imagens_fundo/usuario4.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(1010, 672));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1000, 650);

        setBounds(0, 0, 1007, 672);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsoNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsoNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsoNomActionPerformed

    private void cboUsotipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsotipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsotipActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // TODO add your handling code here:
        adicionar();
//deletar1();

    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void txtUsuSenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuSenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuSenActionPerformed

    private void btnUsuFotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuFotActionPerformed
        // TODO add your handling code here:
        TelaWebCamUsu captura = new TelaWebCamUsu();
        captura.setVisible(true);
    }//GEN-LAST:event_btnUsuFotActionPerformed

    private void btnArqFotUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArqFotUsuActionPerformed
        // TODO add your handling code here:
        search_file();
    }//GEN-LAST:event_btnArqFotUsuActionPerformed

    private void txtNomDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomDirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomDirActionPerformed

    private void btnAltFotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltFotActionPerformed
        // TODO add your handling code here:
        altera_foto();
    }//GEN-LAST:event_btnAltFotActionPerformed

    private void txtPesUsuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesUsuKeyReleased
        // TODO add your handling code here:
        pesquisar_usu();
    }//GEN-LAST:event_txtPesUsuKeyReleased

    private void tblPeUsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeUsuMouseClicked
        // TODO add your handling code here:
        setar_usu();
    }//GEN-LAST:event_tblPeUsuMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("C:\\Users\\fabio\\OneDrive\\Área de Trabalho\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\data.png");
        jdcDatUsu.setIcon(icon);

        txtUsoNom.setEnabled(false);
        txtUsoLog.setEnabled(false);
        txtUsuSen.setEnabled(false);
        txtUsoNom.setEnabled(false);
        txtUsoLog.setEnabled(false);
        txtUsuSen.setEnabled(false);
        txtNomDir.setEnabled(false);
        txtIdaUsu.setEnabled(false);
        cboUsuPer.setEnabled(false);
        cboUsotip.setEnabled(false);
        cboSexUsu.setEnabled(false);
        jdcDatUsu.setEnabled(false);
        btnUsuFot.setEnabled(false);
        btnArqFotUsu.setEnabled(false);

    }//GEN-LAST:event_formInternalFrameOpened

    private void txtUsuIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuIdKeyReleased
        // TODO add your handling code here:
        String v;
        v = txtUsuId.getText();
        comparar();
        if (v.equals("")) {

            txtUsoNom.setEnabled(false);
            txtUsoLog.setEnabled(false);
            txtUsuSen.setEnabled(false);
            txtNomDir.setEnabled(false);
            txtIdaUsu.setEnabled(false);
            cboUsuPer.setEnabled(false);
            cboUsotip.setEnabled(false);
            cboSexUsu.setEnabled(false);
            jdcDatUsu.setEnabled(false);
            btnUsuFot.setEnabled(false);
            btnArqFotUsu.setEnabled(false);
            btnUsuUpdate.setEnabled(false);
            btnUsuDelete.setEnabled(false);
            btnUsuCreate.setEnabled(false);
            limpar();
        }

    }//GEN-LAST:event_txtUsuIdKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAltFot;
    private javax.swing.JButton btnArqFotUsu;
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuFot;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboSexUsu;
    private javax.swing.JComboBox<String> cboUsotip;
    private javax.swing.JComboBox<String> cboUsuPer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcDatUsu;
    public static javax.swing.JLabel lblFotUsu;
    private javax.swing.JTable tblPeUsu;
    private javax.swing.JTextField txtIdaUsu;
    public static javax.swing.JTextField txtNomDir;
    private javax.swing.JTextField txtPesUsu;
    private javax.swing.JTextField txtUsoLog;
    public static javax.swing.JTextField txtUsoNom;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuSen;
    // End of variables declaration//GEN-END:variables
}
