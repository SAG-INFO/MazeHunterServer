/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.server.networkData.MovementRequest;

/**
 *
 * @author g.duennweber
 */
public class InputListener extends Listener{

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MovementRequest) {
        
        
        
        
        }
    }
}
