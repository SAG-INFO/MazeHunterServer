/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;

/**
 *
 * @author Karl Huber
 */
public class Ability {
    
    public int getIndex (int id){
        int index = 0;
        for (int i = 0; i < 4; i++) {
            Player p = Main.MAIN_SINGLETON.game.player[i];
            if (p!=null && p.connectionID == id) {
                index = i;
            }
        }
        return index;
    }
    
    public void use(int id) {}
}
