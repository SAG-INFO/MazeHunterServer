/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Utility;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.server.networkData.abilities.responses.StandardHealResponse;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Karl Huber
 */
public class StandardHeal extends Ability {
    
    @Override
    public void use(int id, float angle) {
        
        int index = getIndex(id);
        Main.MAIN_SINGLETON.game.player[index].status.root(Config.STANDARDHEAL_DURATION, id);
        
        Timer t = new Timer();
        t.schedule(new TimerTask() {@Override
            public void run() {
            Main.MAIN_SINGLETON.game.player[index].changeHealth(Config.STANDARDHEAL_TOTALHEAL);
        }}, (long) Config.STANDARDHEAL_DURATION*1000);
        
        Main.MAIN_SINGLETON.server.sendToAllTCP(new StandardHealResponse(id));
        
        Main.MAIN_SINGLETON.game.player[index].utilityAbility = null;
    }
}
