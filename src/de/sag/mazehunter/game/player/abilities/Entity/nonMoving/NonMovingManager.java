/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Entity.nonMoving;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.nonMoving.DisposeNonMoving;
import de.sag.mazehunter.utils.Vector2;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Karl Huber
 */
public class NonMovingManager {
    
    /** ArrayList of all NonMoving type entities*/
    public ArrayList<NonMoving> nonMoving;

    public void disposeNonMoving(NonMoving n) {
        nonMoving.remove(n);
        Main.MAIN_SINGLETON.server.sendToAllTCP(new DisposeNonMoving(n.entityID));
    }
            
    private final Vector2 tmpVec = new Vector2();
    public void updateNonMoving() {
        float minDistance = Config.TRAP_HITBOXRADIUS2 + Config.PLAYER_HITBOXRADIUS2;
        
        for (Player player : Main.MAIN_SINGLETON.game.player) {
            if(player == null)
                continue;
            
            Optional<NonMoving> collidesWithPlayer = nonMoving.stream().filter((p) -> (tmpVec.set(player.position).sub(p.position).len2() < minDistance && player.connectionID != p.connectionID)).findFirst();
            if(collidesWithPlayer.isPresent()) {
                System.out.println("NonMoving: collision detected with player " + player.connectionID);
                collidesWithPlayer.get().shoot(player, collidesWithPlayer.get().entityID);
            }
        }
    }
}
