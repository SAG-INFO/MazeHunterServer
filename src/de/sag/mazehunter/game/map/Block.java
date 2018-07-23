/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

import de.sag.mazehunter.game.player.ability.pickups.AbilityPickup;

/**
 *
 * @author heftigster.guy.na
 */
public class Block {

    public Tile[][] tilelist;
    boolean up;
    boolean right;
    boolean down;
    boolean left;
    private int indexX;
    private int indexY;

    public Block(boolean u, boolean r, boolean d, boolean l, int x, int y) {
        up = u;
        right = r;
        down = d;
        left = l;

        indexX = x;
        indexY = y;

        tilelist = new Tile[3][3];
        tilelist[0][0] = new Corner(this, 0, 0);
        tilelist[1][0] = new PathUp(this, 1, 0, d);
        tilelist[2][0] = new Corner(this, 2, 0);
        tilelist[0][1] = new PathSide(this, 0, 1, l);
        tilelist[2][1] = new PathSide(this, 2, 1, r);
        tilelist[0][2] = new Corner(this, 0, 2);
        tilelist[1][2] = new PathUp(this, 1, 2, u);
        tilelist[2][2] = new Corner(this, 2, 2);

        if (u == false && r == false && d == false && l == false) {
            tilelist[1][1] = new Centerclosed(this, 1, 1);
        } else {
            tilelist[1][1] = new Centeropen(this, 1, 1);
        }
    }

    public int getPixelX() {
        return indexX * Map.blockbreite;
    }

    public int getPixelY() {
        return indexY * Map.blockbreite;
    }

    public int getX() {
        return indexX;
    }

    public int getY() {
        return indexY;
    }

    public void setPosition(int x, int y) {
        this.indexX = x;
        this.indexY = y;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tilelist[i][j].setPosition();
            }
        }
        AbilityPickup p = ((Centeropen) tilelist[1][1]).pickup;
        if (p != null) {
            p.position.set(tilelist[1][1].getPixelX() + Map.center / 2, tilelist[1][1].getPixelY() + Map.center / 2);
        }
    }

    public Block clone() {
        Block b = new Block(this.up, this.right, this.down, this.left, this.indexX, this.indexY);
        return b;
    }
}
