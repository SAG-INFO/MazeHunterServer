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

    //moves row to the right; k is moved row
    private void moveRowRight(int k) {
        Block b = blocklist[BlockWorldwidth - 1][k];
        for (int i = BlockWorldwidth - 2; i >= 0; i--) {
            blocklist[i][k].IndexX = blocklist[i][k].IndexX + 1;
            blocklist[i + 1][k] = blocklist[i][k];
        }
        blocklist[0][k] = b;
        blocklist[0][k].IndexX = 0;
        for (int m = 0; m < BlockWorldwidth; m++) {
        }
    }

    //moves row to the left; k is moved row
    private void moveRowLeft(int k) {
        Block b = blocklist[0][k];
        for (int i = 1; i < BlockWorldwidth; i++) {
            blocklist[i][k].IndexX = blocklist[i][k].IndexX - 1;
            blocklist[i - 1][k] = blocklist[i][k];
        }
        blocklist[BlockWorldwidth - 1][k] = b;
        blocklist[BlockWorldwidth - 1][k].IndexX = BlockWorldwidth - 1;
        for (int m = 0; m < BlockWorldwidth; m++) {
        }
    }

    //moves row up; k is moved row
    private void moveRowUp(int k) {
        
        Block b = blocklist[k][BlockWorldwidth - 1];
        for (int i = BlockWorldwidth - 2; i >= 0; i--) {
            blocklist[k][i].IndexY = blocklist[k][i].IndexY + 1;
            blocklist[k][i + 1] = blocklist[k][i];
        }
        blocklist[k][0] = b;
        blocklist[k][0].IndexY = 0;
        for (int m = 0; m < BlockWorldwidth; m++) {
        }
    }

    //moves row down; k is moved row
    private void moveRowDown(int k) {
        Block b = blocklist[k][0];
        for (int i = 1; i < BlockWorldwidth; i++) {
            blocklist[k][i].IndexY = blocklist[k][i].IndexY - 1;
            blocklist[k][i - 1] = blocklist[k][i];
        }
        blocklist[k][BlockWorldwidth - 1] = b;
        blocklist[k][BlockWorldwidth - 1].IndexY = BlockWorldwidth - 1;
        for (int m = 0; m < BlockWorldwidth; m++) {
        }
    }

    //direction: 1 moves row up, 2 moves row right, 3 moves row down, 4 moves row
    //row: what row to move; X coordinate when up or down, Y coordinate when left or right
    public void moveRow(int row, int direction) {
        movePlayers(row, direction);
        switch (direction) {
            case 1:
                moveRowUp(row);
                break;
            case 2:
                moveRowRight(row);
                break;
            case 3:
                moveRowDown(row);
                break;
            case 4:
                moveRowLeft(row);
                break;
        }
    }

    public void movePlayers(int row, int direction) {
        if (direction == 1 || direction == 3) {
            doRowY(row, direction);
        }
        if (direction == 2 || direction == 4) {
            doRowX(row, direction);
        }
    }
    
    private void doRowX(int row, int direction) {
        for (Player p : Main.MAIN_SINGLETON.game.player) {
            if (p == null) 
                continue;
            
            if (translateCoordinateToBlock(p.position.y) == row) {
                p.position.x = checkOutOfMap(p.position.x, direction);
            }
            InputListener.sendMovementResponse(p);
        }
    }
    
    private void doRowY(int row, int direction) {
        for (Player p : Main.MAIN_SINGLETON.game.player) {
            if (p == null) 
                continue;
            
            if (translateCoordinateToBlock(p.position.x) == row) {
                    p.position.y = checkOutOfMap(p.position.y, direction);
            }
            InputListener.sendMovementResponse(p);
        }
    }
    
    private float checkOutOfMap(float position, int dir) {
        if (dir == 1 || dir == 2) {
            if (position + blockbreite < CoordinateWorldwidth) {
                return position + blockbreite;
            } else {
                return CoordinateWorldwidth - position;
            }
        } 
        if (dir == 3 || dir == 4) {
            if (position - blockbreite > 0) {
                return position - blockbreite;
            } else {
                return CoordinateWorldwidth - position;
            }
        } 
        throw new RuntimeException("direction is bullshit");
    }
}