/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Entity.nonMoving;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.Game;
import de.sag.mazehunter.game.player.abilities.Utility.Trap;
import de.sag.mazehunter.server.networkData.abilities.entity.DisposeNonMoving;
import de.sag.mazehunter.utils.Vector2;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Karl Huber
 */
public class NonMovingManager {
    
    /**ArrayList to keep track of the amount of {@link Trap} a player layed down. Index for a player is the same as {@link Game#player}. The int in there is the EntityID of the Trap.*/
    public ArrayList<ArrayList<IntWrapper>> trapCount;
    
    /** ArrayList of all NonMoving type entities*/
    public ArrayList<NonMoving> nonMoving;

    public void disposeNonMoving(NonMoving n) {
        nonMoving.remove(n);
        System.out.println("disposethatshit");
        Main.MAIN_SINGLETON.server.sendToAllTCP(new DisposeNonMoving(n.entityID));
    }
    
    public void disposeNonMoving(int entityID) {
        for (NonMoving n : nonMoving) {
            if (n.entityID == entityID) {
                disposeNonMoving(n);
                return;
            }
        }
    }
    
    private final Vector2 tmpVec = new Vector2();
    public void updateNonMoving() {
        
        for (Player player : Main.MAIN_SINGLETON.game.player) {
            if(player == null)
                continue;
            
            Optional<NonMoving> collidesWithPlayer = nonMoving.stream().filter((p) -> (tmpVec.set(player.position).sub(p.position).len2() < p.radius2 + Config.PLAYER_HITBOXRADIUS2 && player.connectionID != p.connectionID)).findFirst();
            if(collidesWithPlayer.isPresent()) {
                System.out.println("NonMoving: collision detected with player " + player.connectionID);
                collidesWithPlayer.get().shoot(player, collidesWithPlayer.get().entityID);
            }
        }
    }
    
    public NonMovingManager() {
        nonMoving = new ArrayList<>();
        trapCount = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            trapCount.add(new ArrayList<>());
        }
    }
}
