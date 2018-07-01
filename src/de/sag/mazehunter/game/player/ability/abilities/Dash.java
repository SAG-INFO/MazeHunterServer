/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.Ability;
import de.sag.mazehunter.server.networkData.abilities.responses.DashResponse;
import de.sag.mazehunter.utils.Vector2;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Karl Huber
 */
public class Dash extends Ability {
    
    public boolean canUse = true;
    
    @Override
    public void use(int id) {
        if (canUse) {
            startCooldown();
        
            Player player = Main.MAIN_SINGLETON.game.getPlayer(id);
        
            Vector2 tempVelocity = new Vector2(player.mc.velocity);
            player.mc.position.add(tempVelocity.setLength(Config.DASH_RANGE));
        
            DashResponse dr = new DashResponse(player.mc.position, player.mc.velocity, id);
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
