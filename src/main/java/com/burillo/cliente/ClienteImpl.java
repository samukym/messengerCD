/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burillo.cliente;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author iburillo
 */
public class ClienteImpl extends UnicastRemoteObject implements ClienteInterface{
     
     public ClienteImpl(ArrayList<ClienteInterface> amigos) throws RemoteException {
        super();
        if(amigos!=null){
        for(ClienteInterface u : amigos){
            amigos.add(u);
        }
        }
    }
    public ClienteImpl() throws RemoteException{
        super();
    }

    @Override
    public void mostrarMsg(String msg) {
        
        VChat chat = new VChat(msg);
        chat.setVisible(true);
        chat.setLocationRelativeTo(null);
        
    }    

    @Override
    public void mostrarNotificacion(String nombre,String nombre2) throws RemoteException {
        VAvisoConexion v = new VAvisoConexion(nombre,nombre2);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - v.getWidth();
        int y = (int) rect.getMaxY() - v.getHeight();
        v.setLocation(x, y);
        v.setVisible(true);
    }
    
}
