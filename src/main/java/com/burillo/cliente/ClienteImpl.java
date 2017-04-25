/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burillo.cliente;

import java.util.ArrayList;

/**
 *
 * @author iburillo
 */
public class ClienteImpl implements ClienteInterface{
    private String nick;
    private String pass;
    private ArrayList<Usuario> amigos;
    
     public ClienteImpl(String nick, String pass, ArrayList<Usuario> amigos) {
        super();
        this.nick = nick;
        this.pass = pass;
        this.amigos = new ArrayList();
        for(Usuario u : amigos){
            amigos.add(u);
        }
    }
    public ClienteImpl(){
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

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Usuario> amigos) {
        this.amigos = amigos;
    }

    @Override
    public void mostrarMsg(String msg) {
        System.out.println(msg);
    }
        
    

}
