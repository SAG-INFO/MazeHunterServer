/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Mobility;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.PermanentAbility;
import de.sag.mazehunter.server.networkData.abilities.responses.DashResponse;
import de.sag.mazehunter.utils.Vector2;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Karl Huber
 */
public class Dash extends PermanentAbility {
    
    public boolean canUse = true;
    
    @Override
    public void use(int id) {
        if (canUse) {
            startCooldown();
        
            int index = getIndex(id);
        
            Vector2 tempVelocity = Main.MAIN_SINGLETON.game.player[index].velocity;
            Main.MAIN_SINGLETON.game.player[index].position.add(tempVelocity.setLength(Config.DASH_RANGE));
        
            DashResponse dr = new DashResponse(Main.MAIN_SINGLETON.game.player[index].position, Main.MAIN_SINGLETON.game.player[index].velocity, id);
            Main.MAIN_SINGLETON.server.sendToAllUDP(dr);
        }
    }
    
    @Override
    public void startCooldown() {
        
        canUse = false;
        
        Timer t = new Timer();
        t.schedule(new TimerTask() {@Override
            public void run() {
            canUse = true;
        }}, (long) Config.DASH_COOLDOWN);
    }

    public Dash() {
    }
}
