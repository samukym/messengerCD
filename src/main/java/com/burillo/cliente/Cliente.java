/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burillo.cliente;

import java.rmi.Naming;
import com.samuel.servidor.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author samu
 */
public class Cliente {
     public static void main(String args[]) throws RemoteException, MalformedURLException, NotBoundException{
            String registryURL = "rmi://localhost:4444/messenger";        
            ServerInterface h = (ServerInterface) Naming.lookup(registryURL);
            ClienteInterface callbackObj = new ClienteImpl();
            h.registerForCallback(callbackObj);            
            h.enviarMsg(callbackObj, "aslkdfjasdlñf");
            h.unregisterForCallback(callbackObj);
     }       
}
