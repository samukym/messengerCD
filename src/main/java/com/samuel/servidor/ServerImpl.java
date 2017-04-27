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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samu
 */
class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    
    private ArrayList<ClienteInterface> clientList; //Lista "provisional"
    private HashMap<ClienteInterface,String> clients; //Lista definitiva con el nickname asociado
    private ArrayList<String> usuariosOnline;
    private final Conexion conexion;
    private final Connection cn;
    
    public ServerImpl() throws RemoteException {
        super();
        clientList = new ArrayList();
        clients = new HashMap();
        conexion = new Conexion();
        cn = conexion.conexion();
        usuariosOnline = new ArrayList();
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
                    usuariosOnline.add(nombre);
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
        Iterator clientes = clients.entrySet().iterator();
        ArrayList<String> resultado = new ArrayList();
        while (clientes.hasNext()){
        Map.Entry pair = (Map.Entry)clientes.next();
        ClienteImpl aux = (ClienteImpl)pair.getValue();
        resultado.add(aux.getNick());
        }
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
    public void actualizarAmigos(ClienteImpl c) throws RemoteException {
        
    }

    @Override
    public ArrayList<String> getAmigosConectados(String nombre, ArrayList<String> usuarios, ClienteInterface c) throws RemoteException{
            return usuarios;
    }

}

