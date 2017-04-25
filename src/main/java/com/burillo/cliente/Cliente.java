/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burillo.cliente;

import java.rmi.Naming;
import com.samuel.servidor.*;

/**
 *
 * @author samu
 */
public class Cliente {
            /*String registryURL = "rmi://localhost:4444/messenger";        
            ServerImpl h = (CallbackServerInterface) Naming.lookup(registryURL);
            ClienteInterface callbackObj = new ClienteImpl();
            h.registerForCallback(callbackObj);*/
            VLogin login = new VLogin();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
}
