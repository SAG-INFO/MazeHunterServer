/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import de.sag.mazehunter.utils.Vector2;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author g.duennweber
 */
public class LifeSystem {

    public boolean checkDeath(CopyOnWriteArrayList<Player> players) {
        Vector2 tmp = new Vector2();
        for (Player hunter : players){
            if (hunter instanceof Hunter) {
                for (Player player : players) {
                    tmp.set(player.mc.position);
                    if (player != hunter && tmp.sub(hunter.mc.position).len() <= hunter.mc.size) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
