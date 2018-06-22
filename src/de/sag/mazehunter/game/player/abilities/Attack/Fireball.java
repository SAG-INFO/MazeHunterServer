/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Attack;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.game.player.abilities.Entity.projectiles.FireballEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.FireballResponse;
import de.sag.mazehunter.utils.Vector2;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Karl Huber 
 * 
 * SO NE GROSSE FEUERBALL JUNGE
 */
public class Fireball extends Ability {
    
    public int charge;
    public boolean canUse;
    
    @Override
    public void use(int connectionID, float angle) {
        
        if (!canUse) {
            return;
        }
        
        int index = getIndex(connectionID);
        int projectileID = Main.MAIN_SINGLETON.game.entityManager.getNewEntityID();
        
        Vector2 fVelocity = new Vector2(Config.FIREBALL_SPEED, 0);
        fVelocity.setAngle(angle);
        
        Main.MAIN_SINGLETON.game.entityManager.entities.add(new FireballEntity(fVelocity, Main.MAIN_SINGLETON.game.player[index].position.cpy(), Config.FIREBALL_HITBOXRADIUS2, projectileID, connectionID));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new FireballResponse(projectileID, connectionID, fVelocity.cpy(), angle));
        
        startCooldown(index);
        
        charge--;
        if (charge == 0) {
            Main.MAIN_SINGLETON.game.player[index].attackAbility = null;
        }
    }
        
    public void startCooldown(int index) {
        canUse = false;
        Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                canUse = true;
            }}, (long) (Config.FIREBALL_CD_BETWEEN_USES));
    }

    public Fireball() {
        charge = Config.FIREBALL_CHARGES;
        canUse = true;
    }
}