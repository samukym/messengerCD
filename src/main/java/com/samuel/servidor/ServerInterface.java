/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samuel.servidor;

import com.burillo.cliente.ClienteImpl;
import com.burillo.cliente.ClienteInterface;
import java.rmi.Remote;
import java.util.ArrayList;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author samu
 */
public interface ServerInterface extends Remote {

    public void registerForCallback(ClienteInterface callbackClientObject) throws java.rmi.RemoteException;

    public void unregisterForCallback(ClienteInterface callbackClientObject) throws java.rmi.RemoteException;

    public void enviarMsg(ClienteInterface destino, String txtMsg) throws java.rmi.RemoteException;

    public ArrayList<String> buscaAmigos(String nickname) throws java.rmi.RemoteException;

    public boolean login(String nombre, String pass, ClienteInterface user) throws java.rmi.RemoteException;

    public void enviarPeticionAmistad(String nickAmigo, String origen) throws java.rmi.RemoteException;

    public ClienteInterface getUsuario(String toString) throws java.rmi.RemoteException;

    public String getNick(ClienteInterface call) throws java.rmi.RemoteException;

    public ArrayList<String> getAmigosConectados(boolean tipo, String nombre, ArrayList<String> usuarios, ClienteInterface c) throws java.rmi.RemoteException;

    public boolean nuevoUsuario(String nick, String c1) throws java.rmi.RemoteException;

    public ArrayList<String> getListaPeticiones(ClienteInterface callbackObj) throws java.rmi.RemoteException;

    public void aceptarAmigo(String toString, ClienteInterface callbackObj) throws java.rmi.RemoteException;

    public String getClaveUsuario(String nick) throws java.rmi.RemoteException;

    public void cambiarClaveUsuario(String nick, String text) throws java.rmi.RemoteException;

    public void darBajaUsuario(String text) throws java.rmi.RemoteException;

}
