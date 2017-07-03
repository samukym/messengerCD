/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burillo.cliente;

import com.samuel.servidor.ServerInterface;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author iburillo
 */
public class ClienteImpl extends UnicastRemoteObject implements ClienteInterface {

    private HashMap<String, VChat> ventanasChat;
    private ServerInterface h;
    private VPrincipal vprincipal;
    private String nombre;

    public ClienteImpl(ServerInterface h) throws RemoteException {
        super();
        this.h = h;
        this.nombre = "null";
        ventanasChat = new HashMap<>();
    }

    public void setNombre(String nombre){
           this.nombre = nombre; 
    }
    
    public void setVprincipal(VPrincipal vprincipal) {
        this.vprincipal = vprincipal;
    }
    
    @Override
    public void enviarMensaje(String nickOrigen, String nickDest, String msg) {
        
        VChat chat = this.ventanasChat.get(nickDest);     
        if (chat == null) {
            chat = new VChat(h, nickOrigen, nickDest);
            this.ventanasChat.put(nickDest, chat);   
        }       
        chat.setVisible(true);
        chat.añadirLinea(nickOrigen, msg);
    }
    
    @Override
    public void addVentanaChat(String nick, VChat ventanaC) {
        this.ventanasChat.put(nick, ventanaC);
    }
    
    @Override
    public void removeVentanaChat(String nick){
        this.ventanasChat.remove(nick);
    }

  @Override
    public void mostrarNotificacion(String nombre){
        VAviso v = new VAviso(this.nombre,nombre,false);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - v.getWidth();
        int y = (int) rect.getMaxY() - v.getHeight();
        v.setLocation(x, y);
        v.setVisible(true);
    }
    
    @Override
     public void mostrarNotificacionPeticion(){
        VAviso v = new VAviso(this.nombre,"",true);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - v.getWidth();
        int y = (int) rect.getMaxY() - v.getHeight();
        v.setLocation(x, y);
        v.setVisible(true);
    }

    @Override
    public void actualizarListAmigos() throws RemoteException {
        vprincipal.actualizarAmigos();
    }
    
    @Override
    public void actualizarListAmigosDesc() throws RemoteException {
        vprincipal.actualizarAmigosDesconectados();
    }

    @Override
    public void anadirPeticion() throws RemoteException {
        this.vprincipal.anadirPeticion();
    }

}
