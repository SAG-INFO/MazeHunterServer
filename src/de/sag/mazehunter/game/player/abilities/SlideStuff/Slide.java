/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.SlideStuff;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.map.Block;
import de.sag.mazehunter.game.map.Map;
import static de.sag.mazehunter.game.map.Map.blockWorldwidth;
import de.sag.mazehunter.game.player.InputListener;
import de.sag.mazehunter.game.player.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.server.networkData.abilities.responses.SlideResponse;
import de.sag.mazehunter.utils.Vector2;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author karl.huber
 */
public class Slide extends Ability {

    protected boolean canUse;
    private Map map;

    public Slide() {
        canUse = true;
        map = Main.MAIN_SINGLETON.game.world.map;
    }

    @Override
    public void use(int connectionID, int direction) {
        int row = 0;

        Vector2 playerPosition = Main.MAIN_SINGLETON.game.getPlayer(connectionID).position;

        switch (direction) {
            case 1:
                row = map.translateCoordinateToBlock(playerPosition.x);
                moveRowUp(row);
                doRowY(row, direction);
                break;
            case 3:
                row = map.translateCoordinateToBlock(playerPosition.x);
                moveRowDown(row);
                doRowY(row, direction);
                break;
            case 2:
                row = map.translateCoordinateToBlock(playerPosition.y);
                moveRowLeft(row);
                doRowX(row, direction);
                break;
            case 4:
                row = map.translateCoordinateToBlock(playerPosition.y);
                moveRowRight(row);
                doRowX(row, direction);
                break;
            default:
                throw new RuntimeException("direction is bullshit");
        }

        Main.MAIN_SINGLETON.server.sendToAllTCP(new SlideResponse(direction, row));
        startCooldown();
    }

    @Override
    public void startCooldown() {

        canUse = false;

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                canUse = true;
            }
        }, (long) Config.SLIDE_COOLDOWN);
    }
    
        private void moveRowRight(int k) {
        Block in = map.blocklist[blockWorldwidth - 1][k];
        Block out = in.clone();

        in.setPosition(-1, in.getY());
        in.setPosition(0, in.getY());

        out.setPosition(blockWorldwidth - 1, out.getY());
        out.setPosition(blockWorldwidth, out.getY());

        for (int i = blockWorldwidth - 2; i >= 0; i--) {
            Block block = map.blocklist[i][k];
            block.setPosition(block.getX() + 1, block.getY());
            map.blocklist[i + 1][k] = map.blocklist[i][k];
        }

        map.blocklist[0][k] = in;
    }

    private void moveRowLeft(int k) {
        Block in = map.blocklist[0][k];
        Block out = in.clone();
        
        in.setPosition(blockWorldwidth, k);
        in.setPosition(blockWorldwidth-1, in.getY());

        out.setPosition(0, out.getY());
        out.setPosition(- 1, out.getY());

        for (int i = 1; i < blockWorldwidth; i++) {
            Block block = map.blocklist[i][k];
            block.setPosition(block.getX() - 1, block.getY());
            map.blocklist[i - 1][k] = map.blocklist[i][k];
        }
        map.blocklist[blockWorldwidth - 1][k] = in;
    }

    private void moveRowUp(int k) {
        Block in = map.blocklist[k][blockWorldwidth - 1];
        Block out = in.clone();
        
        in.setPosition(k, -1);
        in.setPosition(in.getX(), 0);

        out.setPosition(out.getX(), blockWorldwidth-1);
        out.setPosition(out.getX(), blockWorldwidth);

        for (int i = blockWorldwidth - 2; i >= 0; i--) {
            Block block = map.blocklist[k][i];
            block.setPosition(block.getX(), block.getY() + 1);
            map.blocklist[k][i + 1] = block;
        }
        map.blocklist[k][0] = in;
    }

    private void moveRowDown(int k) {
        Block in = map.blocklist[k][0];
        Block out = in.clone();
        
        in.setPosition(k, blockWorldwidth);
        in.setPosition(in.getX(), blockWorldwidth-1);

        out.setPosition(out.getX(), 0);
        out.setPosition(out.getX(), - 1);

        for (int i = 1; i < blockWorldwidth; i++) {
            Block block = map.blocklist[k][i];
            block.setPosition(block.getX(), block.getY() - 1);
            map.blocklist[k][i - 1] = block;
        }
        map.blocklist[k][blockWorldwidth - 1] = in;
    }

    private void doRowX(int row, int direction) {
        for (Player p : Main.MAIN_SINGLETON.game.players) {
            if (map.translateCoordinateToBlock(p.position.y) == row) {
                stopMovement(p);
                p.position.x = map.boundPosition(p.position.x+(Map.blockbreite*(direction==2?1:-1)));
            }
        }
    }

    private void doRowY(int row, int direction) {
        for (Player p : Main.MAIN_SINGLETON.game.players) {
            if (map.translateCoordinateToBlock(p.position.x) == row) {
                stopMovement(p);
                p.position.y = map.boundPosition(p.position.y+(Map.blockbreite*(direction==1?1:-1)));
            }
        }
    }
    
    private final Vector2 tmpVec = new Vector2();
    /** TODO: Replace by Stun, when we have an actual CC-System*/
    private void stopMovement(Player player){
        tmpVec.set(player.velocity);
        
        player.velocity.set(Vector2.Zero);
        MovementListener.sendMovementResponse(player);
        
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                player.velocity.set(tmpVec);
                MovementListener.sendMovementResponse(player);
            }
        }, 1000);
    }
}
