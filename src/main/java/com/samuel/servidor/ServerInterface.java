/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samuel.servidor;

import com.burillo.cliente.ClienteInterface;
import java.rmi.Remote;
import java.util.ArrayList;

/**
 *
 * @author samu
 */
public interface ServerInterface extends Remote {

    public void registerForCallback(ClienteInterface callbackClientObject) throws java.rmi.RemoteException;

    public void unregisterForCallback(ClienteInterface callbackClientObject) throws java.rmi.RemoteException;

    public ClienteInterface getUsuario(String toString) throws java.rmi.RemoteException;

    //Devuelve la instancia del cliente con ese nick
    public String getNick(ClienteInterface call) throws java.rmi.RemoteException;
    //Devuelve el nick del cliente con esa instancia

    //Gestión de usuarios
    public boolean login(String nombre, String pass, ClienteInterface user) throws java.rmi.RemoteException;
    //Comprueba en la BBDD si existe el usuario con esa contraseña

    public boolean nuevoUsuario(String nick, String c1) throws java.rmi.RemoteException;
    //Añade un usuario con su nick y contraseña 

    public boolean cambiarClaveUsuario(String nick, String pass1, String pass2) throws java.rmi.RemoteException;
    //Cambia la contraseña de un usuario (si coincide)

    public void darBajaUsuario(String text) throws java.rmi.RemoteException;
    //Da de baja a un usuario del sistema, eliminando su lista de amigos

    //Gestión de amigos
    public ArrayList<String> buscaAmigos(String nickname, String nick2) throws java.rmi.RemoteException;
    //Devuelve la lista de usuarios que no son amigos y que pueden serlo (para peticiones) 

    public void enviarPeticionAmistad(String nickAmigo, String origen) throws java.rmi.RemoteException;
    //Añade una peticion a la BD y avisa al usuario al que se le pide amistad de la peticion

    public ArrayList<String> getAmigosConectados(boolean tipo, String nombre, ArrayList<String> usuarios, ClienteInterface c) throws java.rmi.RemoteException;
    //Devuelve una lista de los amigos conectados

    public ArrayList<String> getAmigosDesconectados(String nombre) throws java.rmi.RemoteException;
    //Devuelve una lista de los amigos desconectados

    public ArrayList<String> getListaPeticiones(ClienteInterface callbackObj) throws java.rmi.RemoteException;
    //Devuelve una lista de las peticiones pendientes

    public void aceptarAmigo(String toString, ClienteInterface callbackObj) throws java.rmi.RemoteException;
    //Acepta una petición de amistad

    public void rechazarPeticion(String selectedValue, ClienteInterface callbackObj) throws java.rmi.RemoteException;
    //Rechaza una petición de amistad
    
    public void actualizarAmigos() throws java.rmi.RemoteException;
    //Actualiza los amigos de un usuario
    
    public void eliminarAmigo(String nombre, String selectedValue) throws java.rmi.RemoteException;
    //Elimina un amigo
}
