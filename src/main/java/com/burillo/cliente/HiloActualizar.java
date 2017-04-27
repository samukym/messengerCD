/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burillo.cliente;

import com.samuel.servidor.ServerInterface;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author iburillo
 */
public class HiloActualizar extends Thread{
    JList pantalla;
    ServerInterface h;
    String nombre;
    ArrayList <String> usuarios;
    ClienteInterface c;

    public HiloActualizar(JList pantalla, ServerInterface h, String nombre, ArrayList <String> usuarios,ClienteInterface c) {
        this.pantalla = pantalla;
        this.h = h;
        this.nombre = nombre;
        this.usuarios = usuarios;
        this.c=c;
    }
     @Override
    public void run() {

        while (true) {
            try {
                usuarios = h.getAmigosConectados(nombre, usuarios,c);
                if (usuarios != null) {
                    DefaultListModel modelo = new DefaultListModel();
                    for (int i = 0; i < usuarios.size(); i++) {
                        
                        modelo.addElement(usuarios.get(i));
                    }
                    pantalla.setModel(modelo);
                    
                }
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloActualizar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (RemoteException ex) {
                  Logger.getLogger(HiloActualizar.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
    }
    
}
