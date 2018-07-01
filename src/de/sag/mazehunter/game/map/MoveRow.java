/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

import de.sag.mazehunter.Main;
import static de.sag.mazehunter.game.map.Map.BlockWorldwidth;
import static de.sag.mazehunter.game.map.Map.CoordinateWorldwidth;
import static de.sag.mazehunter.game.map.Map.blockbreite;
import de.sag.mazehunter.game.player.InputListener;
import de.sag.mazehunter.game.player.Player;

/**
 *
 * @author Karl Huber
 */
public class MoveRow{
    private Block[][] blocklist;
    
    public MoveRow() {
    }
    
    //moves row to the right; k is moved row
    private void moveRowRight(int k) {
        blocklist = Main.MAIN_SINGLETON.game.world.map.blocklist;
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
        blocklist = Main.MAIN_SINGLETON.game.world.map.blocklist;
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
        blocklist = Main.MAIN_SINGLETON.game.world.map.blocklist;
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
        blocklist = Main.MAIN_SINGLETON.game.world.map.blocklist;
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
        switch (direction) {
            case 1:
                moveRowUp(row);
                movePlayersY(row, direction);
                break;
            case 2:
                moveRowRight(row);
                movePlayersX(row, direction);
                break;
            case 3:
                moveRowDown(row);
                movePlayersY(row, direction);
                break;
            case 4:
                moveRowLeft(row);
                movePlayersX(row, direction);
                break;
        }
    }

    private void movePlayersX(int row, int direction) {
        for (Player p : Main.MAIN_SINGLETON.game.player) {
            if (p == null) 
                continue;
            
            if (Main.MAIN_SINGLETON.game.world.map.translateCoordinateToBlock(p.position.y) == row) {
                p.position.x = checkOutOfMap(p.position.x, direction);
            }
            InputListener.sendMovementResponse(p);
        }
    }
    
    private void movePlayersY(int row, int direction) {
        for (Player p : Main.MAIN_SINGLETON.game.player) {
            if (p == null) 
                continue;
            
            if (Main.MAIN_SINGLETON.game.world.map.translateCoordinateToBlock(p.position.x) == row) {
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
                return position - (CoordinateWorldwidth - blockbreite);
            }
        }
        if (dir == 3 || dir == 4) {
            if (position - blockbreite > 0) {
                return position - blockbreite;
            } else {
                return position + (CoordinateWorldwidth - blockbreite);
            }
        }
        throw new RuntimeException("direction is bullshit");
    }
}
