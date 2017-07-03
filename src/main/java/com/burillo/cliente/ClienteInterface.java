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
    
    //Chat
    public void enviarMensaje(String nickOrigen, String nickDest,String msg) throws RemoteException;
    //Envía un mensaje a un amigo conectado 
    
    public void addVentanaChat(String nick, VChat ventanaC) throws RemoteException;
    //Añade un chat con un amigo abierto a la lista 
        
    public void removeVentanaChat(String nick) throws RemoteException;
    //Elimina de la lista un chat con un amigo 
    
    //Notificaciones
    public void mostrarNotificacion(String nombre) throws RemoteException;
    //Muestra la notificación de que un amigo se ha conectado
    
    public void mostrarNotificacionPeticion() throws RemoteException;
    //Muestra la notificación de que hay una nueva petición de amistad
    
    //Listas
    public void actualizarListAmigos() throws RemoteException;
    //Actualiza la lista de amigos conectados en la ventana principal
    
    public void actualizarListAmigosDesc() throws RemoteException;
    //Actualiza la lista de amigos desconectados en la ventana principal

    public void anadirPeticion() throws RemoteException;
    //Se añade una petición a la lista de peticiones en la ventana principal
    
}
