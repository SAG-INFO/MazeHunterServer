/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

//import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;

/**
 *
 * @author julian.mittermeier
 */
public class Block {

    public Tile[][] tilelist;
    boolean up;
    boolean right;
    boolean down;
    boolean left;
    int BlockPositionX;
    int BlockPositionY;

    public Block(boolean u, boolean r, boolean d, boolean l, int blockx, int blocky) {

        up = u;
        right = r;
        down = d;
        left = l;

        tilelist = new Tile[3][3];
        tilelist[0][0] = new Corner(blockx, blocky, 0, 0);
        tilelist[1][0] = new PathUp(d, blockx, blocky, 1, 0);
        tilelist[2][0] = new Corner(blockx, blocky, 2, 0);
        tilelist[0][1] = new PathSide(l, blockx, blocky, 0, 1);
        tilelist[2][1] = new PathSide(r, blockx, blocky, 2, 1);
        tilelist[0][2] = new Corner(blockx, blocky, 0, 2);
        tilelist[1][2] = new PathUp(u, blockx, blocky, 1, 2);
        tilelist[2][2] = new Corner(blockx, blocky, 2, 2);

        if (u == false && r == false && d == false && l == false) {
            tilelist[1][1] = new Centerclosed(blockx, blocky, 1, 1);
        } else {
            tilelist[1][1] = new Centeropen(blockx, blocky, 1, 1);
        }

        Corner.width = World.ecke;
        Corner.height = World.ecke;
        Centeropen.width = World.center;
        Centeropen.height = World.center;
        PathUp.height = World.ecke;
        PathUp.width = World.center;
        PathSide.height = World.center;
        PathSide.width = World.ecke;

        BlockPositionX = blockx;
        BlockPositionY = blocky;

    }

    //position -1 means not found
    public int getPositionTileXinTile(Tile tile) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tilelist[j][i] == tile) {
                    return j;
                }

            }
        }
        return -1;
    }

    //position -1 means not found
    public int getPositionTileYintTile(Tile tile) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (tilelist[j][i] == tile) {
                    return i;
                }

            }
        }
        return -1;
    }

    //position -1 means not found
    public int getPostitionTileXinCoordinate(Tile tile) {
        int k = this.getPositionTileXinTile(tile);
        switch (k) {
            case 0:
                return 0;
            case 1:
                return World.ecke;
            case 2:
                return World.ecke + World.center;
            default:
                return -1;
        }
    }

    //position -1 means not found
    public int getPostitionTileYinCoordinate(Tile tile) {
        int k = this.getPositionTileYintTile(tile);
        switch (k) {
            case 0:
                return 0;
            case 1:
                return World.ecke;
            case 2:
                return World.ecke + World.center;
            default:
                return -1;
        }
    }

}
