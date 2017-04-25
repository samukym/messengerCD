/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samuel.servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author samu
 */
public class Servidor {
    public static void main(String args[]) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String portNum, registryURL;
        try {
            startRegistry(4444);
            ServerImpl exportedObj = new ServerImpl();
            registryURL = "rmi://localhost:4444/messenger";
            Naming.rebind(registryURL, exportedObj);
            System.out.println("Callback Server ready.");
            //exportedObj.enviarNumeros();

        }// end try
        catch (Exception re) {
            System.out.println(
                    "Exception in HelloServer.main: " + re);
        } // end catch
    } // end main

    //This method starts a RMI registry on the local host, if
    //it does not already exists at the specified port number.
    private static void startRegistry(int RMIPortNum)
            throws RemoteException {
        try {
            Registry registry
                    = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();
            // This call will throw an exception
            // if the registry does not already exist
        } catch (RemoteException e) {
            // No valid registry at that port.
            Registry registry
                    = LocateRegistry.createRegistry(RMIPortNum);
        }
    } // end startRegistry

} // end class
