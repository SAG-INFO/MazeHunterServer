/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Utility;

import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.InputListener;
import de.sag.mazehunter.server.networkData.abilities.requests.UtilityRequest;

/**
 *
 * @author Karl Huber
 */
public class UtilityListener extends InputListener {

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof UtilityRequest) {
            if (Main.MAIN_SINGLETON.game.getPlayer(connection.getID()).utilityAbility != null) {
                Main.MAIN_SINGLETON.game.getPlayer(connection.getID()).utilityAbility.use(connection.getID(), ((UtilityRequest) object).angle);
            } else {
                System.out.println("no utility ability :(");
            }
        }
    }
}
