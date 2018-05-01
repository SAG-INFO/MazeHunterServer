/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;

/**
 *
 * @author sreis
 */
public class DisconnectListener extends Listener{
    @Override
    public void disconnected(Connection connection) {
        if(Main.MAIN_SINGLETON.server.getConnections().length == 0)
            Main.MAIN_SINGLETON.exitGame();
    }
}
