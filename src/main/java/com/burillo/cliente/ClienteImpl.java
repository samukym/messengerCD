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

    public ClienteImpl(ArrayList<ClienteInterface> amigos, ServerInterface h) throws RemoteException {
        super();
        this.h = h;
        if (amigos != null) {
            for (ClienteInterface u : amigos) {
                amigos.add(u);
            }
            ventanasChat = new HashMap<>();
        }
    }

    public ClienteImpl(ServerInterface h) throws RemoteException {
        super();
        this.h = h;
        ventanasChat = new HashMap<>();
    }

    public void setVprincipal(VPrincipal vprincipal) {
        this.vprincipal = vprincipal;
    }
    
    public void addVentanaChat(String nick, VChat ventanaC) {
        this.ventanasChat.put(nick, ventanaC);
        Iterator c = ventanasChat.entrySet().iterator();
        System.out.println("NICKS");
        while (c.hasNext()) {
            Map.Entry pair = (Map.Entry) c.next();
            System.out.println(pair.getKey().toString());
        }
    }

    @Override
    public VChat getVentanaChat(String nick) {
        return this.ventanasChat.get(nick);
    }

    @Override
    public void mostrarMsg(String nickOrigen, String nickDest, String msg) {
        
        VChat chat = this.ventanasChat.get(nickDest);     
        if (chat == null) {
            chat = new VChat(h, nickOrigen, nickDest);
            this.ventanasChat.put(nickDest, chat);   
        }       
        chat.setVisible(true);
        chat.añadirLinea(nickOrigen, msg);
        Iterator c = ventanasChat.entrySet().iterator();
        System.out.println("NICS");
        while (c.hasNext()) {
            Map.Entry pair = (Map.Entry) c.next();
            System.out.println(pair.getKey().toString());
        }
    }

    @Override
    public void mostrarNotificacion(String nombre){
        VAvisoConexion v = new VAvisoConexion(nombre,false);
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
        VAvisoConexion v = new VAvisoConexion("",true);
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
