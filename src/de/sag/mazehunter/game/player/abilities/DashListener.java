/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.DashRequest;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class DashListener extends Listener{

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof DashRequest) {
            Vector2 tempVelocity = Main.MAIN_SINGLETON.game.players[getIndex(connection.getID())].velocity;
            Main.MAIN_SINGLETON.game.players[getIndex(connection.getID())].position.add(tempVelocity.setLength(Config.DASH_RANGE));
                SendDashResponse(connection.getID());
        }
    }
    
    public void SendDashResponse(int id) {
        Main.MAIN_SINGLETON.game.outputer.sendDashResponse(Main.MAIN_SINGLETON.game.players[getIndex(id)].position, Main.MAIN_SINGLETON.game.players[getIndex(id)].velocity, id);
    }
    
    public int getIndex (int id){
        int index = 0;
        for (int i = 0; i < 4; i++) {
            Player p = Main.MAIN_SINGLETON.game.players[i];
            if (p!=null && p.connectionID == id) {
                index = i;
            }
        }
        return index;
    }
}