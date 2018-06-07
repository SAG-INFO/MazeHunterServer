/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Attack.projectiles;

import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public abstract class Projectile {
    
    Vector2 velocity;
    Vector2 position;
    float radius;
    
    public void dispose() {}
    
    public void shoot() {}
    
    private final Vector2 tmp = new Vector2();
    public void update(float delta){
        this.position.add(tmp.set(velocity).scl(delta));
    }

    public Projectile(Vector2 velocity, Vector2 position, float radius) {
        this.velocity = velocity;
        this.position = position;
        this.radius = radius;
    }
}

