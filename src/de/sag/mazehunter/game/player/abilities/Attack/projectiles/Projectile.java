/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Attack.projectiles;

import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public abstract class Projectile {
    
    public final Vector2 velocity = new Vector2();
    public final Vector2 position = new Vector2();
    public final Vector2 startPosition = new Vector2();
    public float radius;
    public int projectileID;
    public int connectionID;
    public float maxRange;
    
    public void shoot(Player player) {}
    
    private final Vector2 tmp = new Vector2();
    public void update(float delta){
        this.position.add(tmp.set(velocity).scl(delta));
    }

    public Projectile(Vector2 velocity, Vector2 position, float radius, int id, Vector2 startPosition, int connectionID) {
        this.velocity.set(velocity);
        this.position.set(position);
        this.radius = radius;
        this.projectileID = id;
        this.startPosition.set(startPosition);
        this.maxRange = Config.FIREBALL_MAXRANGE2;
        this.connectionID = connectionID;
    }
}

