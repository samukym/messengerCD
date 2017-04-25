/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samuel.servidor;

import com.burillo.cliente.ClienteInterface;
import com.burillo.cliente.Usuario;
import java.rmi.Remote;

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
  public void enviarMsg(ClienteInterface u, String txtMsg) throws java.rmi.RemoteException;
    
}
