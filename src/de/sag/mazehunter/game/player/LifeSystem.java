/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author g.duennweber
 */


public class LifeSystem {



    
    public boolean checkDeath(Player[] player) {
        int a;
        for(a = 0; a<4; a++) {
        if(player[a] == null)
            break;
        }
        
        
        Vector2 tmp = new Vector2();
        for(int i = 0; i < a; i++) {
            if (player[i] instanceof Player) {
                for(int j = 0; j<a ; j++) {
                    tmp.set(player[j].mc.position);
                    if (i!=j && tmp.sub(player[i].mc.position).len() <= player[0].mc.size)
                        return true;
                }
            }
        }
    return false;
    }
    
}
