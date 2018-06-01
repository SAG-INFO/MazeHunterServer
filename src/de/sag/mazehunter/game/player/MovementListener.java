/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.server.networkData.MovementRequest;

/**
 *
 * @author g.duennweber
 */
public class MovementListener extends InputListener{
    
    /*sending the configs from startGame() causes the client to disconnect.
    This is an alternative where the first movement input also requests the configs.
    */
    boolean first = true;

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MovementRequest) {
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].move(((MovementRequest) object).angle, ((MovementRequest) object).movement);
            sendMovementResponse(Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].position, Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].velocity,connection.getID());
            if (first) {
                Config.pushConfig();
                first = false;
            }
        }
    }
}
