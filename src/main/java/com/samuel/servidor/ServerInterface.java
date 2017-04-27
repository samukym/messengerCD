/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samuel.servidor;

import com.burillo.cliente.ClienteImpl;
import com.burillo.cliente.ClienteInterface;
import java.awt.PopupMenu;
import java.rmi.Remote;
import java.util.ArrayList;

/**
 *
 * @author samu
 */
public interface ServerInterface extends Remote{
    // This remote method allows an object client to 
// register for callback
// @param callbackClientObject is a reference to the
//        object of the client; to be used by the server
//        to make its callbacks.

  public void registerForCallback(ClienteInterface callbackClientObject) throws java.rmi.RemoteException;

// This remote method allows an object client to 
// cancel its registration for callback

  public void unregisterForCallback(ClienteInterface callbackClientObject) throws java.rmi.RemoteException;
  
  //envio de mensajes
  public void enviarMsg(ClienteInterface destino, String txtMsg) throws java.rmi.RemoteException;

  public ArrayList<String> buscaAmigos(String nickname) throws java.rmi.RemoteException;
  
  public boolean login(String nombre,String pass,ClienteInterface user) throws java.rmi.RemoteException;

    public boolean enviarPeticionAmistad(String nickAmigo, String origen) throws java.rmi.RemoteException;

    public ClienteInterface getUsuario(String toString) throws java.rmi.RemoteException;

    public String getNick(ClienteInterface call) throws java.rmi.RemoteException;

    public void actualizarAmigos(ClienteImpl c) throws java.rmi.RemoteException;

    public ArrayList<String> getAmigosConectados(String nombre, ArrayList<String> usuarios, ClienteInterface c);
    
}
