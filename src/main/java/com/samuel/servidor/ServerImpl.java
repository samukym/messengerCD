/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samuel.servidor;

import com.burillo.cliente.ClienteInterface;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author samu
 */
class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    
    private Vector clientList;
    
    public ServerImpl() throws RemoteException {
        super();
        clientList = new Vector();;
    }

    @Override
    public synchronized void registerForCallback(ClienteInterface callbackClientObject)
            throws java.rmi.RemoteException {
        // store the callback object into the vector
        if (!(clientList.contains(callbackClientObject))) {
            clientList.addElement(callbackClientObject);
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
        if (clientList.removeElement(callbackClientObject)) {
            System.out.println("Unregistered client ");
        } else {
            System.out.println(
                    "unregister: clientwasn't registered.");
        }
    }

    public synchronized void doCallbacks() throws java.rmi.RemoteException {
        
    } // doCallbacks

}// end ServerImpl class

