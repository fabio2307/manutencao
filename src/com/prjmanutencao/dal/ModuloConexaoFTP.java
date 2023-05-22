/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.dal;

import java.io.IOException;
import java.net.InetAddress;

import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Fábio
 */
public class ModuloConexaoFTP {

    public static void main(String[] args) throws SocketException,
            IOException {

        FTPClient ftp = new FTPClient();

        ftp.connect("files.000webhost.com");
        InetAddress host = null;

//        ftp.port(host, 21);

        ftp.login("dbprojeto23", "Fs47526970");

        ftp.changeWorkingDirectory("meuDir");

        String[] arq = ftp.listNames();

        System.out.println("Listando arquivos: \n");

        for (String f : arq) {

            System.out.println(f);

        }

    }

}
