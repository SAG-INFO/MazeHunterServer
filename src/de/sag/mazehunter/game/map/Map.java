/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.InputListener;
import de.sag.mazehunter.game.player.Player;

/**
 *
 * @author goilster.typ.euw
 */
public class Map {

    public Block[][] blocklist;

    public static int blockWorldwidth;
    public static int TileWorldwidth;
    public static int coordinateWorldwidth;

    public static int ecke;
    public static int center;
    public static int blockbreite;

    public Map(int e, int c) {
        ecke = e;
        center = c;
        blockbreite = c + 2 * e;
    }

    /**
     * World generiert die Map. Die Booleanwerte werden im Uhrzeigersinn,
     * beginnend oben eingetragen. Anschließend werden zuerst die Zeilen und
     * dann die Spalten generiert, beginnend beim Ursprung (0|0).
     *
     * @param b = größe der welt
     */
    public void makeMap(boolean... b) {
        if (b.length / 4 == 1 || b.length / 4 == 4 || b.length / 4 == 9 || b.length / 4 == 16 || b.length / 4 == 25 || b.length / 4 == 36 || b.length / 4 == 49 || b.length / 4 == 64 || b.length / 4 == 81 || b.length / 4 == 100) {
            blockWorldwidth = (int) Math.sqrt(b.length / 4);
            TileWorldwidth = blockWorldwidth * 3;
            coordinateWorldwidth = blockWorldwidth * (2 * ecke + center);
            blocklist = new Block[blockWorldwidth][blockWorldwidth];
            int h = 0;
            for (int j = 0; j < blockWorldwidth; j++) {
                for (int i = 0; i < blockWorldwidth; i++) {
                    blocklist[i][j] = new Block(b[h], b[h + 1], b[h + 2], b[h + 3], i, j);
                    h = h + 4;
                }
            }
        }

        for (Block[] blocks : blocklist) {
            for (Block block : blocks) {
                block.setPosition(block.getX(), block.getY());
            }
        }
    }

    public void makeTestMap(int k) {
        blockWorldwidth = k;
        TileWorldwidth = k * 3;
        coordinateWorldwidth = blockWorldwidth * (2 * ecke + center);
        blocklist = new Block[blockWorldwidth][blockWorldwidth];
        for (int j = 0; j < blockWorldwidth; j++) {
            for (int i = 0; i < blockWorldwidth; i++) {
                blocklist[i][j] = new Block(true, true, true, true, i, j);
            }
        }
    }

    private int getIndex(float pixelCoordiante) {
        while (pixelCoordiante >= blockbreite) {
            pixelCoordiante = pixelCoordiante - blockbreite;
        }
        if (pixelCoordiante < ecke) {
            return 0;
        } else if (pixelCoordiante < ecke + center) {
            return 1;
        } else if (pixelCoordiante < blockbreite) {
            return 2;
        } else {
            throw new RuntimeException("translateCoordinateToTile funktioniert mit diesem Wert nicht!");
        }
    }

    public Tile talktoTile(float x, float y) {
        return blocklist[translateCoordinateToBlock(x)][translateCoordinateToBlock(y)].tilelist[getIndex(x)][getIndex(y)];
    }

    public Block talktoBlock(float x, float y) {
        return blocklist[translateCoordinateToBlock(x)][translateCoordinateToBlock(y)];
    }

    public int translateCoordinateToBlock(float k) {
        return (int) k / blockbreite;
    }

    public Tile talktoNumber(int x, int y) {
        int bx = (int) x / 3;
        int tx = x - bx * 3;
        int by = (int) y / 3;
        int ty = y - by * 3;
        if (bx <= blockWorldwidth && by <= blockWorldwidth) {
            return blocklist[bx][by].tilelist[tx][ty];
        } else {
            throw new RuntimeException("talktonumberdoesntwork:(");
        }
    }

    public float boundPosition(float position) {
        if(position<0)
            return coordinateWorldwidth+position;
        else
            return position%coordinateWorldwidth;
    }
}
