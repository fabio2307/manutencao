/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import java.sql.*;
import com.prjmanutencao.dal.ModuloConexao;
import static com.prjmanutencao.telas.TelaCaptura.Imagem1;
import static com.prjmanutencao.telas.TelaCaptura.BYTES_IMAGEM1;
import static com.prjmanutencao.telas.TelaOs.btnArqDow;
import static com.prjmanutencao.telas.TelaOs.btnArqUpl;
import static com.prjmanutencao.telas.TelaOs.lblFotoOs;
import static com.prjmanutencao.telas.TelaOs.txtIdArq;
import static com.prjmanutencao.telas.TelaOs.txtNomArq;
import static com.prjmanutencao.telas.TelaOs.txtOsArq;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Fábio
 */
public class TelaArquivos extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String des = null;
    public static String ent1 = null;
    String nom_arq = null;
    String fe = "";
//    String acesso = ("files.000webhost.com");//("C:\\banco\\");
//    String log = "dbprojeto23"; 
//    String sen="Fs47526970";
    String acesso = ("127.0.0.1");//("C:\\banco\\");
    String log = "servidor";
    String sen = "12345";

//    String nom = (nom_arq + "." + fe);
//    public byte[] BYTES_IMAGEM2 = null;
    /**
     * Creates new form TelaArquivos
     */
    public TelaArquivos() {
        conexao = ModuloConexao.conector();
        initComponents();

    }

    private void pesquisar_arquivo() {

        String sql = ("select  nome_arq, tipo_arq from arquivos_os where id_arq=?");
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdArq.getText());
            rs = pst.executeQuery();
            if (txtIdArq.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o Campo Id para Realializar a Pesquisa!");
                lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));

            } else {
                rs = pst.executeQuery();
//            int Adicionar = pst.executeUpdate();
                byte[] imgBytes = ((byte[]) rs.getBytes("tipo_arq"));
                ImageIcon pic = null;
                pic = new ImageIcon(imgBytes);
                Image scaled = pic.getImage().getScaledInstance(lblArq.getWidth(), lblArq.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(scaled);
                lblArq.setIcon(icon);//Adapta o tamanho da img para ficar do tamanho da label
                //jl.setIcon(pic);

                //InputStream para ler algum dado de algum local (uma fonte)
                //FileInputStream nos permite ler um arquivo qualquer e retornar os dados em byte
                //FileOutputStream para gravar bytes em um arquivo e o FileInputStream para ler.
                //OutputStream para escrever um dado em algum local (destino)
                FileOutputStream fos;
                String nome_foto = "teste";
                nome_foto = JOptionPane.showInputDialog("Digite um nome para a foto ou imagem!");
                fos = new FileOutputStream("C:/Tabela/img/" + nome_foto + ".jpg");
                //fos = new FileOutputStream("C:/Tabela/img/teste.jpg");
                fos.write((byte[]) rs.getBytes("tipo_arq"));
                fos.write(rs.getBytes("tipo_arq"));
                fos.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro " + ex);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }

    private String leitor_pdf() {
        String nome_foto = (txtNomAqu.getText());
        String fileName = ent1;
        String Name = null;

        try {
//            String dir = txtDes.getText();
//            dir = (dir + txtNomAqu.getText());

            JFileChooser file = new JFileChooser();
            file.setDialogTitle("Salvar Arquivo");
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
//            FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Arquivo de texto (*.txt)", "txt");
//            FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("Documento word (*.docx)", "docx");
//            file.addChoosableFileFilter(filtro1);
//            file.addChoosableFileFilter(filtro2);
//            file.setAcceptAllFileFilterUsed(false);
//            file.setMultiSelectionEnabled(false);
//            String fileNameWithOutExt = nome_foto.replaceFirst("[.][^.]+$", "");
            file.setSelectedFile(new File(nome_foto.replaceFirst("[.][^.]+$", "")));
            int i = file.showSaveDialog(null);
            if (i == 1) {
//                des = null;

//                txtDes.setText("");
//                txtNomAqu.setText(file.getSelectedFile().getName());
//                nom_arq = (file.getSelectedFile().getName());
            } else {
                int a = fileName.lastIndexOf('.');
                if (a >= 0) {
                    fe = fileName.substring(a + 1);
//                    File arquivo = file.getSelectedFile();
//                    des = arquivo.getPath(); //+ (".") + fe;
//                    nome_foto = "." + fe;
//                    System.out.println(fe);
                } else {
//                    a = fileName.lastIndexOf('.');
//                    if (a > 0) {
//                    fe = null;//fileName.substring(a + 1);
//                    File arquivo = file.getSelectedFile();
//                    des = arquivo.getPath();
//                    System.out.println(fe);
//                }
                }

//                txtDes.setText(arquivo.getPath());
//                txtNomAqu.setText(file.getSelectedFile().getName()); 
                ent1 = (file.getSelectedFile().getName());
                File arquivo = file.getSelectedFile();

                des = arquivo.getPath() + (".") + fe;
                System.out.println(fe);
                System.out.println(nome_foto);
                receber();

//                txtNomAqu.setText(nom_arq + "." + fe);
//                txtArqui.setText(file.getSelectedFile().getPath());
//                JFileChooser file = new JFileChooser(FileSystemView.getFileSystemView());
//                BufferedWriter grava = new BufferedWriter(new FileWriter(nome_foto));
//                grava.write(dir);
//                grava.close();
//                String nome = (file.getSelectedFile().getName());
//                String destino = arquivo.getPath();
//
////
//                String sourcePath = ent;//txtArqui.getText();   // source file path
//                String destinationPath = (des + (".") + fe);  // destination file path
//                File sourceFile = new File(sourcePath);        // Creating A Source File
////                file.setSelectedFile(new File(sourcePath));
////                 txtNomAqu.setText(sourcePath);
//                File destinationFile = new File(destinationPath);   //Creating A Destination File. Name stays the same this way, referring to getName()
//                Files.copy(sourceFile.toPath(), destinationFile.toPath(), REPLACE_EXISTING);
//                txtNomDes.setText(nome);
//////                FileWriter writer = new FileWriter(file.getSelectedFile().getPath(), true);
//////                writer.write(arquivo.getPath());
//////                writer.close();
//////                File arqui = new File(dir);
//////                BufferedWriter writer = new BufferedWriter(new FileWriter(arqui));
//                arqui writer
//////                String contentToWrite = nome;
//////                String path = destino;
//////                Files.write(Paths.get(destino), nome.getBytes());
//                Files.copy(sourceFile.toPath(), destinationFile.toPath(), REPLACE_EXISTING);
                // Static Methods To Copy Copy source path to destination path
            }

        } catch (HeadlessException e) {
            System.out.println(e);  // printing in case of error.
        }

        return null;

    }

    private void enviar() {

        String dir = txtNomAqu.getText();

        FTPClient ftp = new FTPClient();

//        String caminho = lblArq.getIcon().toString();
//        FileInputStream fis = null;
//        File file = new File(caminho);
        try {

            ftp.connect(acesso);

            ftp.login(log, sen);

            boolean changeWorkingDirectory = ftp.changeWorkingDirectory("user");
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            try (FileInputStream arqEnviar = new FileInputStream(ent1)) {
                if (ftp.storeFile(dir, arqEnviar)) {
                    System.out.println("Arquivo armazenado com sucesso!");
                } else {
                    System.out.println("Erro ao armazenar o arquivo.");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void receber() {

        FTPClient ftp = new FTPClient();
//        String nome= (nome_foto+(".") + fe);

        try {

            ftp.connect(acesso);

            ftp.login(log, sen);

            boolean changeWorkingDirectory = ftp.changeWorkingDirectory("user");
            String[] arq = ftp.listNames();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            FileOutputStream fos = new FileOutputStream(des);
            if (ftp.retrieveFile(nom_arq, fos)) {
                System.out.println("Download efetuado com sucesso!");
            } else {
                System.out.println("Erro ao efetuar download do arquivo.");
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
            boolean changeWorkingDirectory = ftp.changeWorkingDirectory("user");
            String[] arq = ftp.listNames();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.deleteFile(nom_arq);
            ftp.disconnect();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void lOCALIZAR() {
        String nome_fot = txtNomAqu.getText();
        String fileName = ent1;

        try {

            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagem em JPEG e PNG", "jpg", "png");
            FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Arquivo em TXT, PDF e DOCX", "txt", "pdf", "docx");
            file.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
//            file.addChoosableFileFilter(filtro);
//            file.addChoosableFileFilter(filtro1);
            file.setFileFilter(filtro);
            file.setFileFilter(filtro1);
            file.setDialogTitle("Carregar Imagem da Pessoa");
//            file.setSelectedFile(new File(nome_fot.replaceFirst("[.][^.]+$", "")));
            file.showOpenDialog(this);

//            if (file.showOpenDialog(this) == 0) {
////            lblArq.setIcon(new ImageIcon(file.getSelectedFile().getPath()));
//            txtArqui.setText(file.getSelectedFile().getPath());
//            } else {
//                int a = fileName.lastIndexOf('.');
//                if (a >= 0) {
//                    fe = fileName.substring(a + 1);
//                    System.out.println(fe);
//                     des = arquivo.getPath() + (".") + fe;
//        } catch (HeadlessException e) {
//            lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
//        }
//            if (file.showOpenDialog(this) == 0) {
//
//                BufferedImage imagem = ImageIO.read(file.getSelectedFile());
////                Buffered arquivo = 
//                ByteArrayOutputStream buff = new ByteArrayOutputStream();
//                ImageIO.write(imagem, "JPG", buff);
//                BYTES_IMAGEM2 = buff.toByteArray(); // OBTENHO A IMAGEM NO TAMENHO ORIGINAL
//
//                ImageIcon icon = new ImageIcon("C:\\Nova pasta");
//                icon.setImage(icon.getImage().getScaledInstance(lblArq.getWidth(), lblArq.getHeight(), 100));
//                lblArq.setIcon(icon);
//                }
//            }
            ent1 = file.getSelectedFile().getPath();// + (".") + fe;
            txtNomAqu.setText(file.getSelectedFile().getName().replaceFirst("[.][^.]+$", ""));
            lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));
//
        } catch (HeadlessException e) {
            lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //    private void inserir_arquivo() {
    //
    //        String caminho = TxtArqui.getText();//getIcon().toString();
    //
    //        FileInputStream fis = null;
    //        File file = new File(caminho);
    //        try {
    //
    //            fis = new FileInputStream(file);
    //
    //        } catch (FileNotFoundException ex) {
    //            Logger.getLogger(TelaOs.class
    //                    .getName()).log(Level.SEVERE, null, ex);
    //        }
    //
    //        String sql = "insert into arquivos_os( nome_arq, tipo_arq) values(?,?)";
    //        try {
    //            pst = conexao.prepareStatement(sql);
    ////            pst.setString(1, txtIdArq.getText());
    //            pst.setString(1, txtNomAqu.getText());
    //            pst.setBinaryStream(2, fis, (int) file.length());
    //
    //            if (txtNomAqu.getText().isEmpty()) {
    //                JOptionPane.showMessageDialog(null, "Preencha o Campo Obrigatório!");
    //                lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
    //            } else {
    //                int Adicionar = pst.executeUpdate();
    //                if (Adicionar > 0) {
    //                    JOptionPane.showMessageDialog(null, "Arquivo Adiciono com Sucesso!");
    //                    lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));
    //                    txtIdArq.setText(null);
    //                    TxtArqui.setText(null);
    //                    txtNomAqu.setText(null);
    //                    lblArq.setIcon(null);
    //                }
    //            }
    //        } catch (HeadlessException | SQLException e) {
    //            JOptionPane.showMessageDialog(null, e);
    //            lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
    //            System.out.println(e);
    //        }
    //
    //    }

    private void alterar_foto() {

//        String caminho = TxtArqui.getText();
//        FileInputStream fis = null;
//        File file = new File(caminho);
//        try {
//
//            fis = new FileInputStream(file);
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TelaOs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String sql = "UPDATE arquivos_os  set nome_arq=?, tipo_arq=? where id_arq=?";
//        try {
//            pst = conexao.prepareStatement(sql);
////            pst.setString(1, txtIdArq.getText());
//            pst.setString(1, txtNomAqu.getText());
//            pst.setBinaryStream(2, fis, (int) file.length());
//            pst.setString(3, txtIdArq.getText());
//
//            if (txtNomAqu.getText().isEmpty() || txtIdArq.getText().isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Arquivo não Alterado!");
//                lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
//                txtIdArq.setText(null);
//                TxtArqui.setText(null);
//                txtNomAqu.setText(null);
//
//            } else {
//                int Alterar = pst.executeUpdate();
//                if (Alterar > 0) {
//                    JOptionPane.showMessageDialog(null, "Arquivo Alterado com Sucesso!");
//                    lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));
//                    txtIdArq.setText(null);
//                    TxtArqui.setText(null);
//                    txtNomAqu.setText(null);
//                    lblArq.setIcon(null);
//
//                }
//            }
//
//        } catch (HeadlessException | SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//            lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));
//            System.out.println(e);
//        }
    }

    private void remover_arquivos() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Remover esse Arquivo?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from arquivos_os where id_arq=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdArq.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Arquivo Removido com Sucesso!");
                    txtIdArq.setText(null);
                    txtArqui.setText(null);
                    txtNomAqu.setText(null);
                    lblArq.setIcon(null);
//                    limpar();
                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void select_file() {

//        String dir = txtArqui.getText();
//        dir = ("C:\\Nova pasta\\") + dir;
        String sql = "select  nome_arq, tipo_arq from arquivos_os where id_arq=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdArq.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNomAqu.setText(rs.getString(1));
                nom_arq = (rs.getString(1));
//                txtArqui.setText(rs.getString(2));
                ent1 = (rs.getString(2));
                lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));
//                lblArq.setIcon(new ImageIcon(dir));

            } else {
                if (txtIdArq.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o Campo Id para Realializar a Pesquisa!");
                    lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
                } else {
                    JOptionPane.showMessageDialog(null, "Não a Arquivo com esse Id!");
                    lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void insert_file() {
        String dir = txtNomAqu.getText();
//        Imagem1 = txtNomAqu.getText();
        System.out.println(dir);
        String fileName = ent1;
//        dir = ("C:\\banco\\") + dir;
        int a = fileName.lastIndexOf('.');
        if (a >= 0) {
            fe = fileName.substring(a + 1);
//            dir = acesso + dir + (".") + fe;
            System.out.println(ent1);
        }

        String sql = "insert into arquivos_os( nome_arq, tipo_arq) values(?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomAqu.getText() + (".") + fe);
            pst.setString(2, acesso + dir + (".") + fe);
            txtNomAqu.setText(dir + (".") + fe);
            if (txtNomAqu.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o Campo Obrigatório!");
                lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
            } else {
                int Adicionar = pst.executeUpdate();

//                dir = dir + (".") + fe;
//                System.out.println(dir);
                if (Adicionar > 0) {

                    enviar();
//                    mover();
                    JOptionPane.showMessageDialog(null, "Arquivo Adiciono com Sucesso!");
                    txtIdArq.setText(null);
                    txtArqui.setText(null);
                    txtNomAqu.setText(null);
                    lblArq.setIcon(null);

                } else {
                    lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));
                    txtIdArq.setText(null);
                    txtArqui.setText(null);
                    txtNomAqu.setText(null);
                    lblArq.setIcon(null);
                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void update_file() {
//        String dir = txtNomAqu.getText();
//        dir = ("C:\\banco\\") + dir;
        String sql = "UPDATE arquivos_os  set nome_arq=?, tipo_arq=? where id_arq=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomAqu.getText());
            pst.setString(2, txtArqui.getText());
//            pst.setString(2, dir);
            pst.setString(3, txtIdArq.getText());

            if (txtNomAqu.getText().isEmpty() || txtIdArq.getText().isEmpty()) {
                mover();
                JOptionPane.showMessageDialog(null, "Arquivo não Alterado!");
                lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\sem arquivo.png"));
                txtIdArq.setText(null);
                txtArqui.setText(null);
                txtNomAqu.setText(null);

            } else {
                int Alterar = pst.executeUpdate();
//                lblArq.setIcon(new ImageIcon(dir));
                if (Alterar > 0) {
                    JOptionPane.showMessageDialog(null, "Arquivo Alterado com Sucesso!");
                    lblArq.setIcon(new ImageIcon("C:\\Users\\Fábio\\OneDrive\\Documentos\\NetBeansProjects\\prjManutencao1\\src\\com\\prjmanutencao\\icones\\arquivo.png"));
                    txtIdArq.setText(null);
                    txtArqui.setText(null);
                    txtNomAqu.setText(null);
                    lblArq.setIcon(null);

                }
            }

        } catch (HeadlessException | SQLException e) {
        }

    }

    private void mover() {

//        String dir_arq = txtArqui.getText();
        String sourcePath = ent1;//txtArqui.getText();   // source file path
        String destinationPath = "C:\\banco\\";  // destination file path
        File sourceFile = new File(sourcePath);        // Creating A Source File
        File destinationFile = new File(destinationPath + sourceFile.getName());   //Creating A Destination File. Name stays the same this way, referring to getName()
        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), REPLACE_EXISTING);
            // Static Methods To Copy Copy source path to destination path
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);  // printing in case of error.
        }

//        // Arquivo a ser movido
//        File arquivo = new File(dir_arq);
//        
//
//        if (!arquivo.exists()) {
//            JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
////            System.out.println("Arquivo não encontrado");
//        } else {
//
//            // Diretorio de destino
//            File diretorioDestino = new File("C:\\Nova pasta");
//
//            // Move o arquivo para o novo diretorio
//            boolean sucesso = arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));
//            if (sucesso) {
//                JOptionPane.showMessageDialog(null, "Arquivo movido para '" + diretorioDestino.getAbsolutePath() + "'");
////                System.out.println("Arquivo movido para '" + diretorioDestino.getAbsolutePath() + "'");
//            } else {
//                JOptionPane.showMessageDialog(null, "Erro ao mover arquivo '" + arquivo.getAbsolutePath() + "' para '"
//                        + diretorioDestino.getAbsolutePath() + "'");
////                System.out.println("Erro ao mover arquivo '" + arquivo.getAbsolutePath() + "' para '"
////                        + diretorioDestino.getAbsolutePath() + "'");
//            }
//        }
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
                    JOptionPane.showMessageDialog(null, "Arquivo Deletado!");

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

//    private void alterar_fotoo() {
//
//        String caminho = lblFotoOs.getIcon().toString();
//        FileInputStream fis = null;
//        File file = new File(caminho);
//        try {
//
//            fis = new FileInputStream(file);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TelaOs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        String sql = "UPDATE foto_os  set id_os=?, nome_foto=?, imag_os=? where id_foto=?";
//        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setString(1, txtOsArq.getText());
//            pst.setString(2, txtNomArq.getText());
//            pst.setBinaryStream(3, fis, (int) file.length());
//            pst.setString(4, txtIdArq.getText());
//            int Alterar = pst.executeUpdate();
//            if (Alterar > 0) {
//                JOptionPane.showMessageDialog(null, "Arquivo Alterado com Sucesso!");
//                btnArqUpl.setEnabled(false);
//                btnArqDow.setEnabled(false);
////                limpar_imagem();
//
//            } else {
//                JOptionPane.showMessageDialog(null, "Arquivo não Alterado!");
//            }
//
//        } catch (HeadlessException | SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//            System.out.println(e);
//        }
//    }
//    private void inserir_fotoo() {
//
//        String caminho = lblFotoOs.getIcon().toString();
//        FileInputStream fis = null;
//        File file = new File(caminho);
//        try {
//
//            fis = new FileInputStream(file);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TelaOs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        String sql = "insert into foto_os( id_os, nome_foto, imag_os) values(?,?,?)";
//        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setString(1, txtOsArq.getText());
//            pst.setString(2, txtNomArq.getText());
//            pst.setBinaryStream(3, fis, (int) file.length());
//            int Adicionar = pst.executeUpdate();
//            if (Adicionar > 0) {
//                JOptionPane.showMessageDialog(null, "Foto Adiciona com Sucesso!");
//                btnArqUpl.setEnabled(false);
//                btnArqDow.setEnabled(false);
//                id_foto();
//                limpar_imagem();
//            } else {
//                JOptionPane.showMessageDialog(null, "Foto Naõ Adicionada!");
//            }
////            conexao.close();
//        } catch (HeadlessException | SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//            System.out.println(e);
//        }
//
//    }

//    private void pesquisar_fotoo() {
//
//        String sql = ("select id_os, nome_foto, imag_os from foto_os where id_foto=?");
//        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setString(1, txtIdArq.getText());
//            if (txtIdArq.getText().isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Preencha o Campo Id Foto para Realializar a Pesquisa!");
//
//            } else {
//                TelaCaptura captura = new TelaCaptura();
//                captura.setVisible(true);
//                rs = pst.executeQuery();
////                btnAltFoto.setEnabled(true);
//
//            }
//
////            int Adicionar = pst.executeUpdate();
//            while (rs.next()) {
//                byte[] imgBytes = ((byte[]) rs.getBytes("imag_os"));
//                ImageIcon pic = null;
//                pic = new ImageIcon(imgBytes);
//                Image scaled = pic.getImage().getScaledInstance(lblFotoOs.getWidth(), lblFotoOs.getHeight(), Image.SCALE_DEFAULT);
//                ImageIcon icon = new ImageIcon(scaled);
//                lblFotoOs.setIcon(icon);//Adapta o tamanho da img 
//                txtOsArq.setText(rs.getString(1));
//                txtNomArq.setText(rs.getString(2));
//
//            }
//
//        } catch (SQLException ex) {
//
//            JOptionPane.showMessageDialog(null, ex);
//            System.out.println(ex);
//        }
////        btnAltFoto.setEnabled(true);
//    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNomAqu = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnAltArq = new javax.swing.JButton();
        lblArq = new javax.swing.JLabel();
        txtIdArq = new javax.swing.JTextField();
        btnPesAqu = new javax.swing.JButton();
        btnSalArq = new javax.swing.JButton();
        txtArqui = new javax.swing.JTextField();
        btnDelArq = new javax.swing.JButton();
        txtDes = new javax.swing.JTextField();
        txtNomDes = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        txtNomAqu.setPreferredSize(new java.awt.Dimension(6, 26));
        txtNomAqu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomAquActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomAqu);
        txtNomAqu.setBounds(20, 20, 290, 26);

        jButton1.setText("Arquivos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 400, 75, 23);

        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(120, 400, 63, 23);

        btnAltArq.setText("foto");
        btnAltArq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltArqActionPerformed(evt);
            }
        });
        getContentPane().add(btnAltArq);
        btnAltArq.setBounds(210, 400, 53, 23);

        lblArq.setBackground(new java.awt.Color(102, 102, 102));
        lblArq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArq.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 102), new java.awt.Color(102, 102, 102), null, null));
        lblArq.setOpaque(true);
        getContentPane().add(lblArq);
        lblArq.setBounds(20, 190, 280, 190);
        getContentPane().add(txtIdArq);
        txtIdArq.setBounds(320, 20, 70, 26);

        btnPesAqu.setText("Pesquisar");
        btnPesAqu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesAquActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesAqu);
        btnPesAqu.setBounds(280, 400, 90, 23);

        btnSalArq.setText("Salva");
        btnSalArq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalArqActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalArq);
        btnSalArq.setBounds(330, 260, 59, 23);
        getContentPane().add(txtArqui);
        txtArqui.setBounds(20, 60, 290, 26);

        btnDelArq.setText("Deletar");
        btnDelArq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelArqActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelArq);
        btnDelArq.setBounds(320, 290, 67, 23);
        getContentPane().add(txtDes);
        txtDes.setBounds(20, 100, 290, 26);

        txtNomDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomDesActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomDes);
        txtNomDes.setBounds(20, 140, 290, 26);

        jButton3.setText("deletar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(310, 360, 67, 23);

        setSize(new java.awt.Dimension(416, 475));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        lOCALIZAR();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNomAquActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomAquActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomAquActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        insert_file();
//        enviar();
//        inserir_arquivo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnPesAquActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesAquActionPerformed
        // TODO add your handling code here:
        select_file();
//        pesquisar_arquivo();
//        leitor_pdf();

    }//GEN-LAST:event_btnPesAquActionPerformed

    private void btnSalArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalArqActionPerformed
        // TODO add your handling code here:
//        mover();
        leitor_pdf();
//        try {
//            int confirma = JOptionPane.showConfirmDialog(null, "Deseja Salvar este Arquivo?", "Atenção", JOptionPane.YES_NO_OPTION);
//            if (confirma == JOptionPane.YES_OPTION) {
//            String dir_arq = TxtArqui.getText();
//
//            try {
//                File arquivo = new File(dir_arq);

//                if (arquivo.renameTo(new File("C:\\Nova pasta" + arquivo.getName()))) {
//                    JOptionPane.showMessageDialog(null, "Arquivo movido com sucesso!");
////                    System.out.println("Arquivo movido com sucesso!");
//                } else {
//                    JOptionPane.showMessageDialog(null, "Falha ao mover arquivo!");
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Falha ao mover arquivo!");
////                System.out.println("Falha ao mover arquivo!");
//            }
//            String dir_arq = txtArqui.getText();
//            FileOutputStream fos;
//            try {
//                fos = new FileOutputStream(dir_arq);
//                 txtArqui.setText(fos.getSelectedFile().getPath());
//                String nome_foto = txtNomAqu.getText();
//            } catch (FileNotFoundException ex) {
//                
//            }
//                nome_foto = JOptionPane.showInputDialog("Digite um nome para a foto ou imagem!");
////                fos.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
//        fos = new FileOutputStream("C:/Tabela/img/teste.jpg");
//            fos.write((byte[]) rs.getBytes(dir_arq));
//            fos.write(rs.getBytes(dir_arq));
//            fos.close();
//            JOptionPane.showMessageDialog(null, "Arquivo Salvo no Desktop!");
////            } else {
////            }
//        } catch (HeadlessException e) {
//        }   catch (FileNotFoundException ex) {
//                Logger.getLogger(TelaArquivos.class.getName()).log(Level.SEVERE, null, ex);
//            }
    }//GEN-LAST:event_btnSalArqActionPerformed

    private void btnAltArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltArqActionPerformed
        // TODO add your handling code here:
        TelaCaptura captura = new TelaCaptura();
        captura.setVisible(true);
    }//GEN-LAST:event_btnAltArqActionPerformed

    private void btnDelArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelArqActionPerformed
        // TODO add your handling code here:
        remover_arquivos();
    }//GEN-LAST:event_btnDelArqActionPerformed

    private void txtNomDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomDesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomDesActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        delete_file();
        deletar();
//        receber();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaArquivos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaArquivos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaArquivos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaArquivos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaArquivos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAltArq;
    private javax.swing.JButton btnDelArq;
    private javax.swing.JButton btnPesAqu;
    private javax.swing.JButton btnSalArq;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public static javax.swing.JLabel lblArq;
    private javax.swing.JTextField txtArqui;
    private javax.swing.JTextField txtDes;
    private javax.swing.JTextField txtIdArq;
    public static javax.swing.JTextField txtNomAqu;
    private javax.swing.JTextField txtNomDes;
    // End of variables declaration//GEN-END:variables
}
