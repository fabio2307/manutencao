/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.prjmanutencao.telas;
//
//import com.lti.civil.CaptureDeviceInfo;
//import com.lti.civil.CaptureException;
//import com.lti.civil.CaptureObserver;
//import com.lti.civil.CaptureStream;
//import com.lti.civil.CaptureSystem;
//import com.lti.civil.CaptureSystemFactory;
//import com.lti.civil.DefaultCaptureSystemFactorySingleton;
//import com.lti.civil.awt.AWTImageConverter;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.List;
//import javax.imageio.ImageIO;
//import javax.swing.JButton;
//
///**
// *
// * @author Fábio
// */
//public class TestWebCam implements CaptureObserver {
//
//    public byte bytes[] = null;
//    JButton start = null;
//    JButton shot = null;
//    JButton stop = null;
//    CaptureStream captureStream = null;
//    boolean takeShot = false;
//
//    public TestWebCam() {
//        CaptureSystemFactory factory = DefaultCaptureSystemFactorySingleton.instance();
//        CaptureSystem system;
//        try {
//            system = factory.createCaptureSystem();
//            system.init();
//            List list = system.getCaptureDeviceInfoList();
//            int i = 0;
//            if (i < list.size()) {
//                CaptureDeviceInfo info = (CaptureDeviceInfo) list.get(i);
//                System.out.println((new StringBuilder()).append("Device ID ").append(i).append(": ").append(info.getDeviceID()).toString());
//                System.out.println((new StringBuilder()).append("Description ").append(i).append(": ").append(info.getDescription()).toString());
//                captureStream = system.openCaptureDeviceStream(info.getDeviceID());
//                captureStream.setObserver(TestWebCam.this);
//            }
//        } catch (CaptureException ex) {
//            ex.printStackTrace();
//        }
////UI work of the program
////        JFrame frame = new JFrame();
////        frame.setSize(7000, 800);
////        JPanel panel = new JPanel();
////        frame.setContentPane(panel);
////        frame.setVisible(true);
////        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
////        start = new JButton("Start");
////        stop = new JButton("Stop");
////        shot = new JButton("Shot");
////        panel.add(start);
////        panel.add(stop);
////        panel.add(shot);
////        panel.revalidate();
////        start.addActionListener(new ActionListener() {
////
////            public void actionPerformed(ActionEvent e) {
////                try {
////                    captureStream.start();
////                } catch (CaptureException ex) {
////                    ex.printStackTrace();
////
////                }
////            }
////        });
////        stop.addActionListener(new ActionListener() {
////
////            public void actionPerformed(ActionEvent e) {
////                try {
////                    captureStream.stop();
////                } catch (CaptureException ex) {
////                    ex.printStackTrace();
////                }
////            }
////        });
////        shot.addActionListener(new ActionListener() {
////
////            public void actionPerformed(ActionEvent e) {
////                takeShot = true;
////            }
////        });
//    }
//
//    public void onNewImage(CaptureStream stream, Image image) {
//
//        if (!takeShot) {
//            return;
//        }
//        takeShot = false;
//        System.out.println("New Image Captured");
//
//        try {
//            if (image == null) {
//                bytes = null;
//                return;
//            }
//            try {
//                ByteArrayOutputStream os = new ByteArrayOutputStream();
//                ImageIO.write((BufferedImage)AWTImageConverter.toBufferedImage((com.lti.civil.Image) image), "jpg", os);
////                jpeg.encode(AWTImageConverter.toBufferedImage((com.lti.civil.Image) image));
//                os.flush();
//                bytes = os.toByteArray();
//            } catch (IOException e) {
//                e.printStackTrace();
//                bytes = null;
//            } catch (Throwable t) {
//                t.printStackTrace();
//                bytes = null;
//            }
//            if (bytes == null) {
//                return;
//            }
////            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
////            File file = new File("/img" + Calendar.getInstance().getTimeInMillis() + ".jpg");
////            FileOutputStream fos = new FileOutputStream(file);
////            fos.write(bytes);
////            fos.close();
////            BufferedImage myImage = ImageIO.read(file);
////            shot.setText("");
////            shot.setIcon(new ImageIcon(myImage));
////            shot.revalidate();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void onError(CaptureStream arg0, CaptureException arg1) {
//        throw new UnsupportedOperationException("Error is coming ");
//
//    }
//
//////    public static void main(String args[])
//////            throws Exception {
//////        TestWebCam test = new TestWebCam();
//////
//////    }
//    @Override
//    public void onNewImage(CaptureStream stream, com.lti.civil.Image image) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    void start() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
