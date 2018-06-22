/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

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
    int IndexX;
    int IndexY;

    public Block(boolean u, boolean r, boolean d, boolean l, int x, int y) {
        up = u;
        right = r;
        down = d;
        left = l;

        IndexX = x;
        IndexY = y;

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

        Corner.width = World.ecke;
        Corner.height = World.ecke;
        Centeropen.width = World.center;
        Centeropen.height = World.center;
        PathUp.height = World.ecke;
        PathUp.width = World.center;
        PathSide.height = World.center;
        PathSide.width = World.ecke;

    }

    public void update() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                        tilelist[i][j].update();
            }
        }
    }

    public int getX() {
        return IndexX * World.blockbreite;
    }

    public int getY() {
        return IndexY * World.blockbreite;
    }
}
