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
public class Corner extends Tile {

    public static int width;
    public static int height;

    public Corner(int xBlock, int yBlock, int xTile, int yTile) {
        open = false;
        blockPositionX = xBlock;
        blockPositionY = yBlock;
    }
}