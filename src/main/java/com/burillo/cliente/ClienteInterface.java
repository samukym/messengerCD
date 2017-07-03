/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burillo.cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author iburillo
 */
public interface ClienteInterface extends Remote{
    
    public void mostrarMsg(String nickOrigen, String nickDest,String msg) throws RemoteException;

    public void mostrarNotificacion(String nombre) throws RemoteException;
    
    public void mostrarNotificacionPeticion() throws RemoteException;
    
    public void actualizarListAmigos() throws RemoteException;
    
    public void actualizarListAmigosDesc() throws RemoteException;
    
    public VChat getVentanaChat(String nick) throws RemoteException;
    
   // public void addVentanaChat(String nick, VChat ventanaC) throws RemoteException;

    public void anadirPeticion() throws RemoteException;
    
    
}
