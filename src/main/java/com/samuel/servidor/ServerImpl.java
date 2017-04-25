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
import java.util.Vector;

/**
 *
 * @author samu
 */
class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    
    private ArrayList<ClienteInterface> clientList;
    
    public ServerImpl() throws RemoteException {
        super();
        clientList = new ArrayList<>();
    }

    @Override
    public synchronized void registerForCallback(ClienteInterface callbackClientObject)
            throws java.rmi.RemoteException {
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
    public void enviarMsg(ClienteInterface usuario, String txtMsg) throws RemoteException {
        usuario.mostrarMsg(txtMsg);
    }

}// end ServerImpl class

