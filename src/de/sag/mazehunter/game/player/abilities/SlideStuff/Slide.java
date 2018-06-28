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
    public void use(int connectionID, int direction) {
        int row = 0;
        
        switch (direction) {
            case 1:
            case 3:
                row = (int)Main.MAIN_SINGLETON.game.player[getIndex(connectionID)].position.x;
                break;
            case 2:
            case 4:
                row = (int)Main.MAIN_SINGLETON.game.player[getIndex(connectionID)].position.y;
                break;
            default:
                throw new RuntimeException("direction is bullshit");
        }
        
        row = Main.MAIN_SINGLETON.game.world.map.translateCoordinateToBlock(row);
        Main.MAIN_SINGLETON.game.world.map.moveRow(row, direction);
        Main.MAIN_SINGLETON.server.sendToAllTCP(new SlideResponse(direction, row));
        startCooldown();
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
