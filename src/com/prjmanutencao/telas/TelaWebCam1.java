/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.telas;

import com.prjmanutencao.dal.ModuloConexao;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.prjmanutencao.telas.TelaEletricaMen.foto_ele;
import static com.prjmanutencao.telas.TelaHidraulicaMen1.foto_ele1;
import static com.prjmanutencao.telas.TelaCivilMen1.foto_ele2;
import static com.prjmanutencao.telas.TelaRefrigeracaoMen1.foto_ele3;

import static com.prjmanutencao.telas.TelaEletricaMen.lblFotEltMen;
import static com.prjmanutencao.telas.TelaEletricaMen.txtFotPre;
import static com.prjmanutencao.telas.TelaEletricaMen.btnSalFotPre;
import static com.prjmanutencao.telas.TelaEletricaMen.nome_foto0;

import static com.prjmanutencao.telas.TelaHidraulicaMen1.btnSalFotPre1;
import static com.prjmanutencao.telas.TelaHidraulicaMen1.lblFotEltMen1;
import static com.prjmanutencao.telas.TelaHidraulicaMen1.txtFotPre1;
import static com.prjmanutencao.telas.TelaHidraulicaMen1.nome_foto1;

import static com.prjmanutencao.telas.TelaCivilMen1.btnSalFotPre2;
import static com.prjmanutencao.telas.TelaCivilMen1.lblFotEltMen2;
import static com.prjmanutencao.telas.TelaCivilMen1.txtFotPre2;
import static com.prjmanutencao.telas.TelaCivilMen1.nome_foto2;

import static com.prjmanutencao.telas.TelaRefrigeracaoMen1.btnSalFotPre3;
import static com.prjmanutencao.telas.TelaRefrigeracaoMen1.lblFotEltMen3;
import static com.prjmanutencao.telas.TelaRefrigeracaoMen1.txtFotPre3;
import static com.prjmanutencao.telas.TelaRefrigeracaoMen1.nome_foto3;

import static com.prjmanutencao.telas.TelaEletricaTrimestral.foto_ele4;
import static com.prjmanutencao.telas.TelaHidraulicaTrimestral.foto_ele5;
import static com.prjmanutencao.telas.TelaCivilTrimestral.foto_ele6;
import static com.prjmanutencao.telas.TelaRefrigeracaoTrimestral.foto_ele7;

import static com.prjmanutencao.telas.TelaEletricaTrimestral.lblFotEltMen4;
import static com.prjmanutencao.telas.TelaEletricaTrimestral.txtFotPre4;
import static com.prjmanutencao.telas.TelaEletricaTrimestral.btnSalFotPre4;

import static com.prjmanutencao.telas.TelaHidraulicaTrimestral.btnSalFotPre5;
import static com.prjmanutencao.telas.TelaHidraulicaTrimestral.lblFotEltMen5;
import static com.prjmanutencao.telas.TelaHidraulicaTrimestral.txtFotPre5;

import static com.prjmanutencao.telas.TelaCivilTrimestral.btnSalFotPre6;
import static com.prjmanutencao.telas.TelaCivilTrimestral.lblFotEltMen6;
import static com.prjmanutencao.telas.TelaCivilTrimestral.txtFotPre6;

import static com.prjmanutencao.telas.TelaRefrigeracaoTrimestral.btnSalFotPre7;
import static com.prjmanutencao.telas.TelaRefrigeracaoTrimestral.lblFotEltMen7;
import static com.prjmanutencao.telas.TelaRefrigeracaoTrimestral.txtFotPre7;

import static com.prjmanutencao.telas.TelaEletricaSem.foto_ele8;
import static com.prjmanutencao.telas.TelaHidraulicaSem.foto_ele10;
import static com.prjmanutencao.telas.TelaCivilSem.foto_ele9;
import static com.prjmanutencao.telas.TelaRefrigeracaoSem.foto_ele11;

import static com.prjmanutencao.telas.TelaEletricaSem.lblFotEltMen8;
import static com.prjmanutencao.telas.TelaEletricaSem.txtFotPre8;
import static com.prjmanutencao.telas.TelaEletricaSem.btnSalFotPre8;

import static com.prjmanutencao.telas.TelaHidraulicaSem.btnSalFotPre10;
import static com.prjmanutencao.telas.TelaHidraulicaSem.lblFotEltMen10;
import static com.prjmanutencao.telas.TelaHidraulicaSem.txtFotPre10;

import static com.prjmanutencao.telas.TelaCivilSem.btnSalFotPre9;
import static com.prjmanutencao.telas.TelaCivilSem.lblFotEltMen9;
import static com.prjmanutencao.telas.TelaCivilSem.txtFotPre9;

import static com.prjmanutencao.telas.TelaRefrigeracaoSem.btnSalFotPre11;
import static com.prjmanutencao.telas.TelaRefrigeracaoSem.lblFotEltMen11;
import static com.prjmanutencao.telas.TelaRefrigeracaoSem.txtFotPre11;

/**
 *
 * @author Fábio
 */
public class TelaWebCam1 extends javax.swing.JFrame {

    private Dimension dimensao_defaul;
    private Webcam WEBCAM;
    boolean executando = true;
    String Imagem;
    public static String nome_foto = null;
    public static String tipo_prev = null;
//    public String dir;

    public static byte[] BYTES_IMAGEM1 = null;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaWebCam
     */
    public TelaWebCam1() {
        initComponents();
        conexao = ModuloConexao.conector();
        btnCapCam.setEnabled(false);
        btnParCam.setEnabled(false);
        Iniciar();

    }

    private void iniciar_camera() {

        new Thread() {
            public void run() {
                executando = true;
                btnIniCam.setEnabled(false);
                lblImaCam.setText("Iniciando...");
                WEBCAM.open();
                lblImaCam.setText("");
                Inicializa_video();
            }
        }.start();
        btnCapCam.setEnabled(true);
        btnParCam.setEnabled(true);
    }

    private void capturar_imagem() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre.setText(nome_foto0 + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen.getWidth(), lblFotEltMen.getHeight(), 100));
            lblFotEltMen.setIcon(icon);

            btnSalFotPre.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem1() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre1.setText(nome_foto1 + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele1 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen1.getWidth(), lblFotEltMen1.getHeight(), 100));
            lblFotEltMen1.setIcon(icon);

            btnSalFotPre1.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem2() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre2.setText(nome_foto2 + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele2 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen2.getWidth(), lblFotEltMen2.getHeight(), 100));
            lblFotEltMen2.setIcon(icon);

            btnSalFotPre2.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem3() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre3.setText(nome_foto3 + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele3 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen3.getWidth(), lblFotEltMen3.getHeight(), 100));
            lblFotEltMen3.setIcon(icon);

            btnSalFotPre3.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem4() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre4.setText(nome_foto + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele4 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen4.getWidth(), lblFotEltMen4.getHeight(), 100));
            lblFotEltMen4.setIcon(icon);

            btnSalFotPre4.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem5() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre5.setText(nome_foto + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele5 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen5.getWidth(), lblFotEltMen5.getHeight(), 100));
            lblFotEltMen5.setIcon(icon);

            btnSalFotPre5.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem6() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre6.setText(nome_foto + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele6 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen6.getWidth(), lblFotEltMen6.getHeight(), 100));
            lblFotEltMen6.setIcon(icon);

            btnSalFotPre6.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem7() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre7.setText(nome_foto + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele7 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen7.getWidth(), lblFotEltMen7.getHeight(), 100));
            lblFotEltMen7.setIcon(icon);

            btnSalFotPre7.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem8() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre8.setText(nome_foto + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele8 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen8.getWidth(), lblFotEltMen8.getHeight(), 100));
            lblFotEltMen8.setIcon(icon);

            btnSalFotPre8.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem10() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre10.setText(nome_foto + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele10 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen10.getWidth(), lblFotEltMen10.getHeight(), 100));
            lblFotEltMen10.setIcon(icon);

            btnSalFotPre10.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem9() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre9.setText(nome_foto + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele9 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen9.getWidth(), lblFotEltMen9.getHeight(), 100));
            lblFotEltMen9.setIcon(icon);

            btnSalFotPre9.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void capturar_imagem11() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(300000) + 1;

        try {
            executando = false;
            ByteArrayOutputStream buff = new ByteArrayOutputStream();
            ImageIO.write(WEBCAM.getImage(), "JPG", buff);
            byte[] bytes = buff.toByteArray();

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            BufferedImage imagem = ImageIO.read(is);

            int Nova_Largura = 1080, Nova_Altura = 720; //aqui se de a largura e altura da imagem pix
            BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
            txtFotPre11.setText(nome_foto + Integer.toString(valor) + ".jpg");
//            txtFotPre1.setText(nome_foto + Integer.toString(valor) + ".jpg");
            ImageIO.write(new_img, "JPG", new File(Imagem + valor + ".jpg"));
            foto_ele11 = Imagem + valor + ".jpg";

            ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
            ImageIO.write(new_img, "JPG", buff2);
            BYTES_IMAGEM1 = buff2.toByteArray(); // AQUI A IMAGEM ESTÁ CONVERTIDA EM BYTES NO TAMANHO PERSONALIZADO
////
            ImageIcon icon = new ImageIcon(BYTES_IMAGEM1);
            icon.setImage(icon.getImage().getScaledInstance(lblFotEltMen11.getWidth(), lblFotEltMen11.getHeight(), 100));
            lblFotEltMen11.setIcon(icon);

            btnSalFotPre11.setEnabled(true);

            WEBCAM.close();
            parar_camera();
            dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
//            System.out.println();

        }

    }

    private void parar_camera() {

        new Thread() {
            public void run() {
                WEBCAM.close();
                executando = false;
                lblImaCam.setIcon(null);
                btnIniCam.setEnabled(true);
                btnCapCam.setEnabled(false);
                btnParCam.setEnabled(false);
            }
        }.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        lblImaCam = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        btnIniCam = new javax.swing.JButton();
        btnParCam = new javax.swing.JButton();
        btnCapCam = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Webcam System O.S");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));

        lblImaCam.setBackground(new java.awt.Color(102, 102, 102));
        lblImaCam.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lblImaCam.setForeground(new java.awt.Color(153, 255, 0));
        lblImaCam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImaCam.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        lblImaCam.setOpaque(true);
        jLayeredPane1.add(lblImaCam);
        lblImaCam.setBounds(10, 10, 280, 280);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(100, 0, 300, 300);

        jLayeredPane2.setBackground(new java.awt.Color(153, 153, 153));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jLayeredPane2.setOpaque(true);

        btnIniCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/Start.png"))); // NOI18N
        btnIniCam.setToolTipText("Iniciar WebCam");
        btnIniCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniCamActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btnIniCam);
        btnIniCam.setBounds(18, 10, 64, 64);

        btnParCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/Stop.png"))); // NOI18N
        btnParCam.setToolTipText("Parar WebCam");
        btnParCam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnParCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParCamActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btnParCam);
        btnParCam.setBounds(18, 90, 64, 64);

        btnCapCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/Captura.png"))); // NOI18N
        btnCapCam.setToolTipText("Captura");
        btnCapCam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapCamActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btnCapCam);
        btnCapCam.setBounds(18, 170, 64, 64);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/prjmanutencao/icones/webcam.png"))); // NOI18N
        jLabel1.setToolTipText("Webcam System");
        jLayeredPane2.add(jLabel1);
        jLabel1.setBounds(26, 240, 50, 50);

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(0, 0, 100, 300);

        setSize(new java.awt.Dimension(416, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniCamActionPerformed
        // TODO add your handling code here:
        iniciar_camera();
    }//GEN-LAST:event_btnIniCamActionPerformed

    private void btnCapCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapCamActionPerformed
        // TODO add your handling code here:

        if (tipo_prev.equals("Eletrica - Mensal")) {
            capturar_imagem();
        }
        if (tipo_prev.equals("Hidráulica - Mensal")) {
            capturar_imagem1();
        }
        if (tipo_prev.equals("Civil - Mensal")) {
            capturar_imagem2();
        }
        if (tipo_prev.equals("Refrigeração - Mensal")) {
            capturar_imagem3();
        }
        if (tipo_prev.equals("Eletrica - Trimestral")) {
            capturar_imagem4();
        }
        if (tipo_prev.equals("Hidráulica - Trimestral")) {
            capturar_imagem5();
        }
        if (tipo_prev.equals("Civil - Trimestral")) {
            capturar_imagem6();
        }
        if (tipo_prev.equals("Refrigeração - Trimestral")) {
            capturar_imagem7();
        }
        if (tipo_prev.equals("Eletrica - Semestral")) {
            capturar_imagem8();
        }
        if (tipo_prev.equals("Hidráulica - Semestral")) {
            capturar_imagem10();
        }
        if (tipo_prev.equals("Civil - Semestral")) {
            capturar_imagem9();
        }
        if (tipo_prev.equals("Refrigeração - Semestral")) {
            capturar_imagem11();
        }
//        capturar_imagem();


    }//GEN-LAST:event_btnCapCamActionPerformed

    private void btnParCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParCamActionPerformed
        // TODO add your handling code here:
        parar_camera();
    }//GEN-LAST:event_btnParCamActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        parar_camera();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(TelaWebCam1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaWebCam1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaWebCam1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaWebCam1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaWebCam1().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapCam;
    private javax.swing.JButton btnIniCam;
    private javax.swing.JButton btnParCam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLabel lblImaCam;
    // End of variables declaration//GEN-END:variables

    private void Iniciar() {
        try {
//            dimensao_defaul = new Dimension(176, 144); //UTILIZO A DIMENSÃO DE MINHA PREFERÊNCIA
            dimensao_defaul = WebcamResolution.VGA.getSize();//UTILIZA A DIMENSÃO PADRÃO DA WEBCAM
//            WEBCAM = Webcam.getDefault(); //UTILIZA A WEBCAM PADRÃO DO COMPUTADOR            
            List list = Webcam.getWebcams();
            for (int i = 0; i < list.size(); i++) {
                WEBCAM = (Webcam) list.get(i); //UTILIZA A ÚLTIMA WEBCAM ENCONTRADA, SE EXISTIR MAIS DE UMA PEGA A ÚLTIMA
            }
            WEBCAM.setViewSize(dimensao_defaul);

            for (Dimension dimensao : WEBCAM.getViewSizes()) { // AQUI EU CONSIGO SABER QUAIS SÃO RESOLUÇÕES SUPORTADA PELA WEBCAM
                System.out.println("Largura: " + dimensao.getWidth() + "  Altura: " + dimensao.getHeight());
            }
//         
        } catch (WebcamException e) {
//            e.printStackTrace();            
        }
    }

    private void Inicializa_video() {
        new Thread() {
            @Override
            public void run() {
                while (true && executando) {
                    try {
                        Image imagem = WEBCAM.getImage();
                        ImageIcon icon = new ImageIcon(imagem);
                        icon.setImage(icon.getImage().getScaledInstance(lblImaCam.getWidth(), lblImaCam.getHeight(), 500));
                        lblImaCam.setIcon(icon);
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }

}
