package de.sag.mazehunter.game.map;

import de.sag.mazehunter.Main;
import static de.sag.mazehunter.game.map.Map.blockWorldwidth;
import de.sag.mazehunter.game.player.Player;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author arein
 */
public class SlideManager {

    private final Map map;
    
    private boolean slideInProgress;

    public SlideManager(Map map) {
        this.map = map;
    }

    public void moveRowRight(int k) {
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
        
        movePlayersHorizontal(k, 2);
        blockSliding();
    }
    public void moveRowLeft(int k) {
        Block in = map.blocklist[0][k];
        Block out = in.clone();

        in.setPosition(blockWorldwidth, k);
        in.setPosition(blockWorldwidth - 1, in.getY());

        out.setPosition(0, out.getY());
        out.setPosition(- 1, out.getY());

        for (int i = 1; i < blockWorldwidth; i++) {
            Block block = map.blocklist[i][k];
            block.setPosition(block.getX() - 1, block.getY());
            map.blocklist[i - 1][k] = map.blocklist[i][k];
        }
        map.blocklist[blockWorldwidth - 1][k] = in;
        
        movePlayersHorizontal(k, 4);
        blockSliding();
    }
    public void moveRowUp(int k) {
        Block in = map.blocklist[k][blockWorldwidth - 1];
        Block out = in.clone();

        in.setPosition(k, -1);
        in.setPosition(in.getX(), 0);

        out.setPosition(out.getX(), blockWorldwidth - 1);
        out.setPosition(out.getX(), blockWorldwidth);

        for (int i = blockWorldwidth - 2; i >= 0; i--) {
            Block block = map.blocklist[k][i];
            block.setPosition(block.getX(), block.getY() + 1);
            map.blocklist[k][i + 1] = block;
        }
        map.blocklist[k][0] = in;
        
        movePlayersVertical(k, 1);
        blockSliding();
    }
    public void moveRowDown(int k) {
        Block in = map.blocklist[k][0];
        Block out = in.clone();

        in.setPosition(k, blockWorldwidth);
        in.setPosition(in.getX(), blockWorldwidth - 1);

        out.setPosition(out.getX(), 0);
        out.setPosition(out.getX(), - 1);

        for (int i = 1; i < blockWorldwidth; i++) {
            Block block = map.blocklist[k][i];
            block.setPosition(block.getX(), block.getY() - 1);
            map.blocklist[k][i - 1] = block;
        }
        map.blocklist[k][blockWorldwidth - 1] = in;
        
        movePlayersVertical(k, 3);
        blockSliding();
    }

    private void blockSliding(){
        slideInProgress = true;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                slideInProgress = false;
            }
        }, 1000);
    }
    
    private void movePlayersHorizontal(int row, int direction) {
        for (Player p : Main.MAIN_SINGLETON.game.players) {
            if (map.translateCoordinateToBlock(p.mc.position.y) == row) {
                stopMovement(p);
                p.mc.position.x = map.boundPosition(p.mc.position.x + (Map.blockbreite * (direction == 2 ? 1 : -1)));
                p.mc.forceMovementUpdate();
            }
        }
    }
    private void movePlayersVertical(int row, int direction) {
        for (Player p : Main.MAIN_SINGLETON.game.players) {
            if (map.translateCoordinateToBlock(p.mc.position.x) == row) {
                stopMovement(p);
                p.mc.position.y = map.boundPosition(p.mc.position.y + (Map.blockbreite * (direction == 1 ? 1 : -1)));
                p.mc.forceMovementUpdate();
            }
        }
    }

    public boolean canSlide(){
        return !slideInProgress;
    }
    
    private void stopMovement(Player player) {
        player.mc.slow(0, 1);
    }
}
