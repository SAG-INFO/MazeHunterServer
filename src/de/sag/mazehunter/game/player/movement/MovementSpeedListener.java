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
        if (object instanceof MovementSpeedRequest) {
            System.out.println("movementspeedrequest");
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].mc.movementSpeedFactor += ((MovementSpeedRequest) object).change;
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].mc.speed = Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].mc.movementSpeedFactor * Config.DEFAULT_SPEED;
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].mc.setVelocity((int) Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].mc.requestedVelocity.angle(), true);
            sendMovementResponse(Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].mc.position, Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].mc.requestedVelocity, connection.getID());
        }
    }
}
