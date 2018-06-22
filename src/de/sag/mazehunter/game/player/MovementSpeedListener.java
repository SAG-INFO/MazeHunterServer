/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
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
            int index = getIndex(id);
            
            Main.MAIN_SINGLETON.game.player[index].movementSpeedFactor += ((MovementSpeedRequest) object).change; 
            Main.MAIN_SINGLETON.game.player[index].speed = Main.MAIN_SINGLETON.game.player[index].movementSpeedFactor*Config.DEFAULT_SPEED;
            Main.MAIN_SINGLETON.game.player[index].updateVelocity((int)Main.MAIN_SINGLETON.game.player[index].velocity.angle());
            sendMovementResponse(Main.MAIN_SINGLETON.game.player[index].position, Main.MAIN_SINGLETON.game.player[index].velocity, id);
        }
    }
}


