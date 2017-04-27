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
    private ArrayList<String> amigos;
    private ArrayList<ClienteInterface> chats;
    
     public ClienteImpl(ArrayList<ClienteInterface> amigos) throws RemoteException {
        super();
        this.amigos = new ArrayList();
        this.chats = new ArrayList();
        if(amigos!=null){
        for(ClienteInterface u : amigos){
            amigos.add(u);
        }
        }
    }
    public ClienteImpl() throws RemoteException{
        super();
        this.amigos = new ArrayList();
        this.chats = new ArrayList();
    }

    public ArrayList<String> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<String> amigos) {
       for(String x : amigos){
           this.amigos.add(x);
       }
    }

    @Override
    public void mostrarMsg(String msg) {
        
        VChat chat = new VChat(msg);
        chat.setVisible(true);
        chat.setLocationRelativeTo(null);
        
    }    

    @Override
    public boolean enviarPeticionAmistad(String nick) throws RemoteException {
        return true;
    }

    @Override
    public void setAmigo(String amigo) {
        this.amigos.add(amigo);
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
