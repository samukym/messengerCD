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
public class Usuario {
    private String nick;
    private String pass;
    private ArrayList<Usuario> amigos;

    public Usuario(String nick, String pass, ArrayList<Usuario> amigos) {
        this.nick = nick;
        this.pass = pass;
        this.amigos = amigos;
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
        for(Usuario x : amigos){
            this.amigos.add(x);
        }
    }
    
    
    
}
