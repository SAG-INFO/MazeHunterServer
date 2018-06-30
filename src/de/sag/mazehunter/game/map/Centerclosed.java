/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;


/**
 *
 * @author Admin
 */
public class Centerclosed extends Tile {

    public Centerclosed(Block block, int x, int y) {
        super(block, x, y, false);
        open = false;
    }
}
