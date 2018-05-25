/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.server.networkData.MovementRequest;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author g.duennweber
 */
public class Player {

    public final Vector2 position;
    public final Vector2 velocity;
    public int connectionID;
    float speed;
    float movementSpeedFactor;
    int maxHealth;
    int currentHealth;
    
    private final Vector2 tmp = new Vector2();

    public Player(int id) {
        position = new Vector2();
        position.set(0f, 0f);
        velocity = new Vector2();
        velocity.set(0f, 0f);
        connectionID = id;
        speed = PlayerConfig.DEFAULT_SPEED;
        movementSpeedFactor = 1.0f;
        maxHealth = PlayerConfig.DEFAULT_HEALTHPOINTS;
        currentHealth = maxHealth;
    }

    public void move(int angle, boolean movement) {
        if (!movement) {
            velocity.set(0f, 0f);
        } else {
            //TODO: Collision
            updateVelocity(angle);
        }
    }
    
    public void heal(int amount) {
        if (amount + currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth += amount;
        }
    }
    
    public void updateVelocity(int angle) {
        velocity.set(speed, 0); 
        velocity.setAngle((float) angle);
    }

    public void update(float delta) {
        this.position.add(tmp.set(velocity).scl(delta));
    }
}