/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.abilities;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Outputer;
import de.sag.mazehunter.game.Player;
import de.sag.mazehunter.server.networkData.DashRequest;

/**
 *
 * @author Karl Huber
 */
public class DashListener extends Listener{

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof DashRequest) {
            // Main.MAIN_SINGLETON.game.player[this.getIndex(connection.getID())].move(((DashRequest) object));
            SendDash(connection.getID());
        }
    }
    
    public void SendDash(int id) {
        //Outputer.sendDashResponse()
    }
    
    public int getIndex (int id){
        int index = 0;
        for (int i = 0; i < 4; i++) {
            Player p = Main.MAIN_SINGLETON.game.player[i];
            if (p!=null && p.connectionID == id) {
                index = i;
            }
        }
        return index;
    }
}