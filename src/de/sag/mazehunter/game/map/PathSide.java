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

    public static int width;
    public static int height;
    
    public PathSide(boolean b, int xBlock, int yBlock, int xTile, int yTile) {
        open = b;
        blockPositionX = xBlock;
        blockPositionY = yBlock;
    }
}
