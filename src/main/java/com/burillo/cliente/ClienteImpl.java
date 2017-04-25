/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burillo.cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author iburillo
 */
public class ClienteImpl extends UnicastRemoteObject implements ClienteInterface{
    private String nick;
    private String pass;
    private ArrayList<ClienteInterface> amigos;
    
     public ClienteImpl(String nick, String pass, ArrayList<ClienteInterface> amigos) throws RemoteException {
        super();
        this.nick = nick;
        this.pass = pass;
        this.amigos = new ArrayList();
        for(ClienteInterface u : amigos){
            amigos.add(u);
        }
    }
    public ClienteImpl() throws RemoteException{
        super();
        this.amigos = new ArrayList();
    }
   
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ArrayList<ClienteInterface> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<ClienteInterface> amigos) {
        this.amigos = amigos;
    }

    @Override
    public void mostrarMsg(String msg) {
        System.out.println(msg);
        VChat chat = new VChat(msg);
        chat.setVisible(true);
        chat.setLocationRelativeTo(null);
    }

    @Override
    public void notificarConectado(ArrayList<ClienteImpl> amigos) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    

}
