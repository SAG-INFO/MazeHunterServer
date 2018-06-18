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
import de.sag.mazehunter.server.networkData.MovementSpeedRequest;

/**
 *
 * @author Karl Huber
 */
public class MovementSpeedListener extends InputListener {

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MovementSpeedRequest) {
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].movementSpeedFactor += ((MovementSpeedRequest) object).change; 
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].speed = Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].movementSpeedFactor*Config.DEFAULT_SPEED;
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].updateVelocity((int)Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].requestedVelocity.angle());
            sendMovementResponse(Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].position, Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].requestedVelocity,connection.getID());
        }
    }
}