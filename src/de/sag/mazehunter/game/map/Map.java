/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

/**
 *
 * @author goilster.typ.euw
 */
public class Map{

    public Block[][] blocklist;

    public static int BlockWorldwidth;
    public static int TileWorldwidth;
    public static int CoordinateWorldwidth;

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
            BlockWorldwidth = (int) Math.sqrt(b.length / 4);
            TileWorldwidth = BlockWorldwidth * 3;
            CoordinateWorldwidth = BlockWorldwidth * (2 * ecke + center);
            blocklist = new Block[BlockWorldwidth][BlockWorldwidth];
            int h = 0;
            for (int j = 0; j < BlockWorldwidth; j++) {
                for (int i = 0; i < BlockWorldwidth; i++) {
                    blocklist[i][j] = new Block(b[h], b[h + 1], b[h + 2], b[h + 3], i, j);
                    h = h + 4;
                }
            }
        }
    }
    
    public void makeMap(Block[][] b) {
        BlockWorldwidth = (int) Math.sqrt(b.length / 4);
        TileWorldwidth = BlockWorldwidth * 3;
        CoordinateWorldwidth = BlockWorldwidth * (2 * ecke + center);
        blocklist = b;
    }

    public void makeTestMap(int k) {
        BlockWorldwidth = k;
        TileWorldwidth = k * 3;
        CoordinateWorldwidth = BlockWorldwidth * (2 * ecke + center);
        blocklist = new Block[BlockWorldwidth][BlockWorldwidth];
        for (int j = 0; j < BlockWorldwidth; j++) {
            for (int i = 0; i < BlockWorldwidth; i++) {
                blocklist[i][j] = new Block(true, true, true, true, i, j);
            }
        }
    }

    public void update() {
        for (int i = 0; i < BlockWorldwidth; i++) {
            for (int j = 0; j < BlockWorldwidth; j++) {
                blocklist[i][j].update();
            }
        }
    }

    private int getIndex(float k) {
        while (k >= blockbreite) {
            k = k - blockbreite;
        }
        if (k < ecke) {
            return 0;
        } else if (k < ecke + center) {
            return 1;
        } else if (k < blockbreite) {
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
        if (bx <= BlockWorldwidth && by <= BlockWorldwidth) {
            return blocklist[bx][by].tilelist[tx][ty];
        } else {
            throw new RuntimeException("talktonumberdoesntwork:(");
        }
    }
}
