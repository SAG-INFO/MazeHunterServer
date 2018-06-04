/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Attack;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Attack.projectiles.FireballProjectile;
import de.sag.mazehunter.server.networkData.abilities.FireballResponse;
import de.sag.mazehunter.utils.Vector2;
import java.util.ArrayList;

/**
 *
 * @author Karl Huber 
 * 
 * SO NE GROSSE FEUERBALL JUNGE
 */
public class Fireball extends AttackPickup {
    
    public int charge;
    public boolean canUse;
    
    public ArrayList<FireballProjectile> fireballs;
    private static final Fireball FIREBALL_ABILITY = new Fireball();
    
    @Override
    public void use(int id, float angle) {
        
        int index = getIndex(id);
        
        charge--;
        if (charge == 0) {
            Main.MAIN_SINGLETON.game.player[index].attackAbility = null;
        }
        
        Vector2 fVelocity = new Vector2(Config.FIREBALL_SPEED, 0);
        fVelocity.setAngle(angle);
        
        fireballs.add(new FireballProjectile(fVelocity, Main.MAIN_SINGLETON.game.player[index].position, Config.FIREBALL_RADIUS));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new FireballResponse(id, Main.MAIN_SINGLETON.game.player[index].position, fVelocity));
    }
        
    @Override
    public boolean collect(int id) {
        if (Main.MAIN_SINGLETON.game.player[getIndex(id)].attackAbility != null) {
            return false;
        } else {
            Main.MAIN_SINGLETON.game.player[getIndex(id)].attackAbility = FIREBALL_ABILITY;
            charge = Config.FIREBALL_CHARGES;
        }
        return true; 
    }

    public Fireball() {
    }
}