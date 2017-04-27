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
    private HashMap<ClienteInterface,ClienteImpl> clients; //Lista definitiva con el nickname asociado
    
    public ServerImpl() throws RemoteException {
        super();
        clientList = new ArrayList<>();
        clients = new HashMap<>();
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
            ClienteImpl cli = new ClienteImpl(nombre,pass,null);
            clients.put(user,cli);     
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
        ClienteImpl aux = (ClienteImpl)pair.getValue();
        if(aux.getNick().equals(nick)){
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
            ClienteImpl aux = (ClienteImpl)pair.getValue();
            c = aux.getNick();
        }
        }
        return c;
    }

    @Override
    public void actualizarAmigos(ClienteImpl c) throws RemoteException {
        
    }

    @Override
    public ArrayList<String> getAmigosConectados(String nombre, ArrayList<String> usuarios, ClienteInterface c) {
            return usuarios;
    }

}

