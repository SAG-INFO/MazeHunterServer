/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Utility;

import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.InputListener;
import de.sag.mazehunter.server.networkData.abilities.DashRequest;
import de.sag.mazehunter.server.networkData.abilities.DashResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class UtilityListener extends InputListener {
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof DashRequest) {
        }
    }
        
    public void Send(int id) {
    }
}
