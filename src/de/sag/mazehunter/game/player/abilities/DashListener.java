/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

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
public class DashListener extends InputListener {

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof DashRequest) {
            Vector2 tempVelocity = Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].mc.requestedVelocity;
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].mc.position.add(tempVelocity.setLength(Config.DASH_RANGE));
            SendDashResponse(connection.getID());
        }
    }

    public void SendDashResponse(int id) {
        DashResponse dr = new DashResponse(Main.MAIN_SINGLETON.game.player[getIndex(id)].mc.position, Main.MAIN_SINGLETON.game.player[getIndex(id)].mc.requestedVelocity, id);
        Main.MAIN_SINGLETON.server.sendToAllUDP(dr);
    }
}
