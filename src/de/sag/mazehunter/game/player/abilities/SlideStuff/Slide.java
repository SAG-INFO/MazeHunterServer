/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.SlideStuff;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.server.networkData.abilities.responses.SlideResponse;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author karl.huber
 */
public class Slide extends Ability {
    
    boolean canUse;
    
    @Override
    public void use(int connectionID, char direction) {
        int row;
        //TODO server map
        if (direction == 'N' || direction == 'S') {row = (int)Main.MAIN_SINGLETON.game.player[getIndex(connectionID)].position.y;} 
                
        
        Main.MAIN_SINGLETON.server.sendToAllTCP(new SlideResponse());
    }

    @Override
    public void startCooldown() {
        
        canUse = false;
        
        Timer t = new Timer();
        t.schedule(new TimerTask() {@Override
            public void run() {
            canUse = true;
        }}, (long) Config.SLIDE_COOLDOWN);
    }
    
    public Slide() {
        canUse = true;
    }
}
