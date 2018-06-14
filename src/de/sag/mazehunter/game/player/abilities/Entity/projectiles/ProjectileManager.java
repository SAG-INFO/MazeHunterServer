/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Entity.projectiles;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.entity.DisposeProjectile;
import de.sag.mazehunter.utils.Vector2;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Karl Huber
 */
public class ProjectileManager {
    
    /**ArrayList of all projectiles that only collide with players (they fly over walls etc.) */
    public ArrayList<Projectile> projectilesNoC;
    
    /** ArrayList of all projectiles that die when colliding with walls (but shoot normally when hitting a player)*/
    public ArrayList<Projectile> projectilesC;
    
    public void disposeProjectileNoC(Projectile p) {
        projectilesNoC.remove(p);
        System.out.println("disposethatshit");
        Main.MAIN_SINGLETON.server.sendToAllTCP(new DisposeProjectile(p.entityID));
    }
    
    /** Updates all projectiles */
    public void updateAll(float delta) {
        //updateC();
        updateNoC(delta);
        updateNonMoving();
    }
    
    final Vector2 tmpVec = new Vector2();
    public void updateNonMoving() {
        
        float minDistance = Config.FIREBALL_HITBOXRADIUS2 + Config.PLAYER_HITBOXRADIUS2;
        
        for (Player player : Main.MAIN_SINGLETON.game.player) {
            if(player == null)
                continue;
            
            Optional<Projectile> collidesWithPlayer = projectilesNoC.stream().filter((p) -> (tmpVec.set(player.position).sub(p.position).len2() < minDistance && player.connectionID != p.connectionID)).findFirst();
            if(collidesWithPlayer.isPresent()) {
                System.out.println("collision detected with player " + player.connectionID);
                collidesWithPlayer.get().shoot(player, collidesWithPlayer.get().entityID);
            }
        }
    }
    
    public void updateNoC(float delta) {
        float minDistance = Config.FIREBALL_HITBOXRADIUS2 + Config.PLAYER_HITBOXRADIUS2;
        
        projectilesNoC.forEach((p) -> {p.update(delta);});

        for (Player player : Main.MAIN_SINGLETON.game.player) {
            if(player == null)
                continue;
            
            Optional<Projectile> collidesWithPlayer = projectilesNoC.stream().filter((p) -> (tmpVec.set(player.position).sub(p.position).len2() < minDistance && player.connectionID != p.connectionID)).findFirst();
            if(collidesWithPlayer.isPresent()) {
                System.out.println("Projectil: collision detected with player " + player.connectionID);
                collidesWithPlayer.get().shoot(player, collidesWithPlayer.get().entityID);
            }
            
            Optional<Projectile> reachedMaxrange = projectilesNoC.stream().filter((p) -> (tmpVec.set(p.startPosition).sub(p.position).len2() > p.maxRange2)).findFirst();
            if(reachedMaxrange.isPresent()) {
                System.out.println("Projectile: maxRange reached: " + tmpVec.set(reachedMaxrange.get().startPosition).sub(reachedMaxrange.get().position).len2());
                disposeProjectileNoC(reachedMaxrange.get());
            }
        }
    }

    public void updateC() {
        //TODO 
    }
    
    public ProjectileManager() {
        projectilesNoC = new ArrayList<>();
        projectilesC = new ArrayList<>();
    }
}
