/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Attack;

import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.InputListener;
import de.sag.mazehunter.server.networkData.abilities.requests.AttackRequest;

/**
 *
 * @author Karl Huber
 */
public class AttackListener extends InputListener {
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof AttackRequest) {
            System.out.println("Attack request received");
            if(Main.MAIN_SINGLETON.game.getPlayer(connection.getID()).attackAbility != null) {
            Main.MAIN_SINGLETON.game.getPlayer(connection.getID()).attackAbility.use(connection.getID(),((AttackRequest) object).angle);
            } else {
                System.out.println("no attack ability :(");
            }
        }
    }
}