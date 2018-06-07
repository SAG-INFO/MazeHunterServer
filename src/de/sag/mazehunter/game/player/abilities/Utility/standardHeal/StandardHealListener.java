/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Utility.standardHeal;

import de.sag.mazehunter.game.player.InputListener;
import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.server.networkData.abilities.StandardHealResponse;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Karl Huber
 */
public class StandardHealListener extends InputListener{
/*
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof StandardHealRequest) {
            SendStandardHealResponse(connection.getID());
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].changeHealth(Config.STANDARDHEAL_TOTALHEAL);
            }}, (long) (Config.STANDARDHEAL_DURATION));
        }
    }
    
    public void SendStandardHealResponse(int id) {
        Main.MAIN_SINGLETON.server.sendToAllUDP(new StandardHealResponse(id));
    }
*/
}
