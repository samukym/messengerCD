/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burillo.cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author iburillo
 */
public interface ClienteInterface extends Remote{
    
    public void mostrarMsg(String msg) throws RemoteException;
    public void notificarConectado() throws RemoteException;
    
}
