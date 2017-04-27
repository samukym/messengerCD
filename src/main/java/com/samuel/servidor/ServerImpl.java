/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samuel.servidor;

import com.burillo.cliente.ClienteImpl;
import com.burillo.cliente.ClienteInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author samu
 */
class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    
    private ArrayList<ClienteInterface> clientList; //Lista "provisional"
    private HashMap<ClienteInterface,String> clients; //Lista definitiva con el nickname asociado
    private final Conexion conexion;
    private final Connection cn;
    
    public ServerImpl() throws RemoteException {
        super();
        clientList = new ArrayList();
        clients = new HashMap();
        conexion = new Conexion();
        cn = conexion.conexion();
    }

    @Override
    public synchronized void registerForCallback(ClienteInterface callbackClientObject) throws java.rmi.RemoteException {
        // store the callback object into the vector
        if (!(clientList.contains(callbackClientObject))) {
            clientList.add(callbackClientObject);
            System.out.println("Registered new client ");
        } // end if
    }

    @Override
    public synchronized void unregisterForCallback(ClienteInterface callbackClientObject)
            throws java.rmi.RemoteException {
        if (clients.containsKey(callbackClientObject)) {
            clients.remove(callbackClientObject);
            System.out.println("Unregistered client ");
        } else {
            System.out.println(
                    "unregister: clientwasn't registered.");
        }
    }

    @Override
    public void enviarMsg(ClienteInterface destino, String txtMsg) throws RemoteException {
        destino.mostrarMsg(txtMsg);
    }

    @Override
    public boolean login(String nombre, String pass, ClienteInterface user) throws RemoteException {
        if(clientList.contains(user)){
            try {                
                Statement ps = cn.createStatement();
                ResultSet rs = ps.executeQuery("Select nick from usuarios where nick like '" + nombre + "'"
                        + "and pass like '" + pass + "';");
                while (rs.next()) {
                    clients.put(user, nombre);
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> buscaAmigos(String nickname) throws RemoteException {
        ArrayList<String> resultado = new ArrayList();
       
        return resultado;
    }

    @Override
    public boolean enviarPeticionAmistad(String nickAmigo, String origen) throws RemoteException {
        return true;
    }

    @Override
    public ClienteInterface getUsuario(String nick) throws RemoteException {
        Iterator clientes = clients.entrySet().iterator();
        ClienteInterface c = null;
        while (clientes.hasNext()){
        Map.Entry pair = (Map.Entry)clientes.next();
        if(pair.getValue().toString().equals(nick)){
            c = (ClienteInterface) pair.getKey();
        }
        }
        return c;
    }

    @Override
    public String getNick(ClienteInterface call) throws RemoteException {
        Iterator clientes = clients.entrySet().iterator();
        String c = null;
        while (clientes.hasNext()){
        Map.Entry pair = (Map.Entry)clientes.next();
        if(pair.getKey().equals(call)){
            c = pair.getValue().toString();
        }
        }
        return c;
    }

    @Override
    public ArrayList<String> getAmigosConectados(boolean tipo,String nombre, ArrayList<String> usuarios, ClienteInterface c) throws RemoteException{
        ArrayList<String> amigos = new ArrayList();
        int tinicial = usuarios.size();
        try {        
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery("Select amigo1,amigo2 from amigos where amigo1 like '" + nombre + "'"
                    + "or amigo2 like '" + nombre + "';");
            while (rs.next()) {
                if(!rs.getString(1).equals(nombre) && clients.containsValue(rs.getString(1)))
                    amigos.add(rs.getString(1));
                if(!rs.getString(2).equals(nombre) && clients.containsValue(rs.getString(2)))
                    amigos.add(rs.getString(2));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(tipo){
        if(tinicial < amigos.size()){
            for (int i = tinicial; i < amigos.size(); i++) {
                    c.mostrarNotificacion(amigos.get(i),nombre);
                }
        }
        }
        return amigos;
    }

    @Override
    public boolean nuevoUsuario(String nick, String pass) throws RemoteException {
        try {                
                Statement ps = cn.createStatement();
                ResultSet rs = ps.executeQuery("Select nick from usuarios where nick like '" + nick + "'"
                        + "and pass like '" + pass + "';");
                while (rs.next()) {                  
                    return false;
                }
                if(ps.executeUpdate("insert into usuarios values('"+nick+"','"+pass+"');")==0){
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }

}

