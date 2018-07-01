/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.movement;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.MovementRequest;

/**
 *
 * @author g.duennweber
 */
public class MovementListener extends Listener{
    
    /*sending the configs from startGame() causes the client to disconnect.
    This is an alternative where the first movement input triggers the server 
    to send the configs to everyone.
    */
    boolean first = true;

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MovementRequest) {
            
            Player player = Main.MAIN_SINGLETON.game.getPlayer(connection.getID());
            MovementRequest request = (MovementRequest)object;
            
            player.mc.setVelocity(request.angle, request.moving);

            //TODO: GET THIS OT OF HERE!!!!!!!!!! GODDAMIT !!!
            if (first) {
                Config.pushConfig();
                first = false;
            }
        }
    }
}
