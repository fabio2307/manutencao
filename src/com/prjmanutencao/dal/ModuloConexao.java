/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjmanutencao.dal;

import java.sql.*;

/**
 *
 * @author Fábio
 */
public class ModuloConexao {

    public static Connection conector() {
        java.sql.Connection conexao = null;
        // a linha a baixo chama o drive 
        String driver = "com.mysql.cj.jdbc.Driver";
        // armazenando informações referentes ao banco
        String url = "jdbc:mysql://localhost:3306/db_manutencao?characterEncoding=utf-8";
        String user = "dba";
        String password = "Dba123456";
        // estabelecendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        }
    }

}
