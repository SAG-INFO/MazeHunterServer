/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

import de.sag.mazehunter.game.player.abilities.AbilityPickup;

/**
 *
 * @author paul.kuschfeldt
 */
public class Centeropen extends Tile {

    public AbilityPickup pickup;
    
    public Centeropen(Block block, int x, int y) {
        super(block, x, y, true);
        open = true;
    }

    @Override
    public void setPosition() {
        super.setPosition();
        if(pickup != null)
            pickup.position.set(getPixelX()*Map.center/2, getPixelY()+Map.center/2);
    }
}
