/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samuel.servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author herni
 */
public class Conexion {
    Connection conectar = null;

    public Connection conexion(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        conectar = DriverManager.getConnection("jdbc:mysql://localhost/messenger","root","1234");
            System.out.println("Conectado a la base de datos");
        }catch(ClassNotFoundException | SQLException e){
            
        }
        return conectar;
    }
    
    
    
    
}
