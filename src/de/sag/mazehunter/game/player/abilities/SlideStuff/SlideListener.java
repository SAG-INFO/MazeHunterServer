/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.SlideStuff;

import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.InputListener;
import de.sag.mazehunter.server.networkData.abilities.requests.SlideRequest;

/**
 *
 * @author karl.huber
 */
public class SlideListener extends InputListener {
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof SlideRequest) {
            System.out.println(((SlideRequest) object).direction);
            Main.MAIN_SINGLETON.game.getPlayer(connection.getID()).slideAbility.use(connection.getID(), ((SlideRequest) object).direction);
        }
    }

    public SlideListener() {
    }
}
