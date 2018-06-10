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
    
    Vector2 velocity;
    Vector2 position;
    Vector2 startPosition;
    float radius;
    int id;
    float maxRange;
    
    public void shoot(Player player) {}
    
    private final Vector2 tmp = new Vector2();
    public void update(float delta){
        this.position.add(tmp.set(velocity).scl(delta));
        System.out.println("Position Update: " + position);
    }

    public Projectile(Vector2 velocity, Vector2 position, float radius, int id, Vector2 startPosition) {
        this.velocity = velocity;
        this.position = position;
        this.radius = radius;
        this.id = id;
        this.startPosition = startPosition;
        this.maxRange = Config.FIREBALL_MAXRANGE2;
    }
}

