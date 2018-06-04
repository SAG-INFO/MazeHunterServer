/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Mobility;

import de.sag.mazehunter.game.player.InputListener;
import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.server.networkData.abilities.DashRequest;
import de.sag.mazehunter.server.networkData.abilities.DashResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class MobilityListener extends InputListener{

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof DashRequest) {
            Vector2 tempVelocity = Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].velocity;
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].position.add(tempVelocity.setLength(Config.DASH_RANGE));
                SendDashResponse(connection.getID());
        }
    }
        
    public void SendDashResponse(int id) {
        DashResponse dr = new DashResponse(Main.MAIN_SINGLETON.game.player[getIndex(id)].position, Main.MAIN_SINGLETON.game.player[getIndex(id)].velocity, id);
        Main.MAIN_SINGLETON.server.sendToAllUDP(dr);
    }
}