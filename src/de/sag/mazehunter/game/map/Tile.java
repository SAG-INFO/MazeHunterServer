/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

/**
 *
 * @author julian.mittermeier
 */
public abstract class Tile {

    public boolean open;
    public int IndexX;
    public int IndexY;
    int WorldIndexX;
    int WorldIndexY;
    public Block parent;

    public Tile() {

    }

    public Tile(Block block, int x, int y) {
        parent = block;
        IndexX = x;
        WorldIndexX = parent.IndexX * 3 + x;
        IndexY = y;
        WorldIndexY = parent.IndexY * 3 + y;
    }

    public Tile(boolean o) {
        open = o;
    }

    public void update() {
        //TODO: Effizienz steigern
        WorldIndexX = parent.IndexX * 3 + IndexX;
        WorldIndexY = parent.IndexY * 3 + IndexY;
    }

    public int getX() {
        switch (IndexX) {
            case 0:
                return parent.getX();
            case 1:
                return parent.getX() + Map.ecke;
            case 2:
                return parent.getX() + Map.ecke + Map.center;
            default:
                throw new RuntimeException("getXvonTile");
        }
    }

    public int getY() {
        switch (IndexY) {
            case 0:
                return parent.getY();
            case 1:
                return parent.getY() + Map.ecke;
            case 2:
                return parent.getY() + Map.ecke + Map.center;
            default:
                throw new RuntimeException("getYvonTile");
        }
    }
}