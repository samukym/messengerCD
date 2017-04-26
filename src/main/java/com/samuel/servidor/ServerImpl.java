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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author samu
 */
class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    
    private ArrayList<ClienteInterface> clientList; //Lista "provisional"
    private HashMap<ClienteInterface,String> clients; //Lista definitiva con el nickname asociado
    
    public ServerImpl() throws RemoteException {
        super();
        clientList = new ArrayList<>();
        clients = new HashMap();
    }

    @Override
    public synchronized void registerForCallback(ClienteInterface callbackClientObject) throws java.rmi.RemoteException {
        // store the callback object into the vector
        if (!(clientList.contains(callbackClientObject))) {
            clientList.add(callbackClientObject);
            System.out.println("Registered new client ");
        } // end if
    }

// This remote method allows an object client to
// cancel its registration for callback
// @param id is an ID for the client; to be used by
// the server to uniquely identify the registered client.
    @Override
    public synchronized void unregisterForCallback(ClienteInterface callbackClientObject)
            throws java.rmi.RemoteException {
        if (clientList.remove(callbackClientObject)) {
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
            clients.put(user, nombre);     
            clientList.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> buscaAmigos(String nickname) throws RemoteException {
        Iterator clientes = clients.entrySet().iterator();
        ArrayList<String> resultado = new ArrayList();
        while (clientes.hasNext()){
        Map.Entry pair = (Map.Entry)clientes.next();
        resultado.add(pair.getValue().toString());
        }
        return resultado;
    }

    @Override
    public boolean enviarPeticionAmistad(String nickDestino, ClienteInterface origen) throws RemoteException {
        Iterator clientes = clients.entrySet().iterator();
        boolean estado = false;
        while (clientes.hasNext()){
        Map.Entry pair = (Map.Entry)clientes.next();
        if(pair.getValue().equals(nickDestino)){
            ClienteInterface c = (ClienteInterface) pair.getKey();
            estado = c.enviarPeticionAmistad(getNick(origen),origen,getUsuario(nickDestino));
        }
        }
        return estado;
    }

    @Override
    public ClienteInterface getUsuario(String nick) throws RemoteException {
        Iterator clientes = clients.entrySet().iterator();
        ClienteInterface c = null;
        while (clientes.hasNext()){
        Map.Entry pair = (Map.Entry)clientes.next();
        if(pair.getValue().equals(nick)){
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

}// end ServerImpl class

