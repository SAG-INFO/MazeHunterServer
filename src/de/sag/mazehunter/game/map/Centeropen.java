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
public class Centeropen extends Tile {

    public static int width;
    public static int height;


    public Centeropen(int xBlock, int yBlock, int xTile, int yTile) {
        open = true;
        blockPositionX = xBlock;
        blockPositionY = yBlock;
    }
}
