/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

/**
 *
 * @author paul.kuschfeldt
 */
public class PathSide extends Tile {
    public PathSide(Block block, int x, int y, boolean b) {
        super(block, x, y, b);
        open = b;
    }
}
