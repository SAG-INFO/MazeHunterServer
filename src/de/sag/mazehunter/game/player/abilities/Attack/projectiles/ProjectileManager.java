/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Attack.projectiles;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.abilities.AbilityPickup;
import de.sag.mazehunter.server.networkData.abilities.projectiles.DisposeProjectile;
import de.sag.mazehunter.utils.Vector2;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Karl Huber
 */
public class ProjectileManager {
    
    private int projectileID;
    
    /**ArrayList of all projectiles that only collide with players (they fly over walls etc.) */
    public ArrayList<Projectile> projectilesNoC;
    
    /** ArrayList of all projectiles that die when colliding with walls (but shoot normally when hitting a player)*/
    public ArrayList<Projectile> projectilesC;
    
    public int getNewProjectileID() {
        projectileID++;
        return projectileID;
    }
    
    public void disposeProjectileNoC(Projectile p) {
        projectilesNoC.remove(p);
        Main.MAIN_SINGLETON.server.sendToAllTCP(new DisposeProjectile(p.id));
    }
    
    /** Updates all projectiles */
    public void updateAll() {
        updateC();
        updateNoC();
    }
    
    public void updateNoC() {
        final Vector2 tmpVec = new Vector2();
        for (Player player : Main.MAIN_SINGLETON.game.player) {
            if(player == null)
                continue;
            
            Optional<Projectile> projectile = projectilesNoC.stream().filter((p) -> (tmpVec.set(player.position).sub(p.position).len2() < Config.FIREBALL_HITBOXRADIUS2 + Config.PLAYER_HITBOXRADIUS2)).findFirst();
            if(projectile.isPresent())
                projectile.get().shoot(player);
                disposeProjectileNoC(projectile.get());
                
            Optional<Projectile> pimmel = projectilesNoC.stream().filter((p) -> (tmpVec.set(p.startPosition).sub(p.position).len2() < Config.FIREBALL_MAXRANGE)).findFirst();
            if(projectile.isPresent())
                disposeProjectileNoC(pimmel.get());
        }
    }

    public void updateC() {
        //TODO collision
    }
    
    public ProjectileManager() {
        projectilesNoC = new ArrayList<>();
        projectilesC = new ArrayList<>();
    }
}
