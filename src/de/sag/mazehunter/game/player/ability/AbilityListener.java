/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.abilities.requests.AttackRequest;
import de.sag.mazehunter.server.networkData.abilities.requests.MobilityRequest;
import de.sag.mazehunter.server.networkData.abilities.requests.SlideRequest;

/**
 *
 * @author sreis
 */
public class AbilityListener extends Listener{
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof AttackRequest) {
            Main.MAIN_SINGLETON.game.getPlayer(connection.getID()).ability.use(connection.getID(), ((AttackRequest) object).angle);
        }else if(object instanceof MobilityRequest) {
            Main.MAIN_SINGLETON.game.getPlayer(connection.getID()).dash.use(connection.getID());
        }else if(object instanceof SlideRequest) {
            Main.MAIN_SINGLETON.game.getPlayer(connection.getID()).slide.use(connection.getID(), ((SlideRequest) object).direction);
        }
    }
}
