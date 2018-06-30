/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.movement;

import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.InputListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.MovementSpeedRequest;

/**
 *
 * @author Karl Huber
 */
public class MovementSpeedListener extends InputListener {

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MovementSpeedRequest) {
            int id = connection.getID();
            
            Player player = Main.MAIN_SINGLETON.game.getPlayer(id);
            
            player.movementSpeedFactor += ((MovementSpeedRequest) object).change; 
            player.speed = player.movementSpeedFactor*Config.DEFAULT_SPEED;
            player.updateVelocity((int)player.velocity.angle());
            sendMovementResponse(player.position, player.velocity, id);
        }
    }
}