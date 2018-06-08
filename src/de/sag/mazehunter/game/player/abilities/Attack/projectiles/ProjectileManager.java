/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Attack.projectiles;

/**
 *
 * @author Karl Huber
 */
public class ProjectileManager {
    
    private int projectileID;
    
    public int getNewProjectileID() {
        projectileID++;
        return projectileID;
    }
    
    public void disposeProjectile(Projectile projectile) {
        
    }
}
