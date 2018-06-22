/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.game.player.abilities.Mobility.Dash;
import de.sag.mazehunter.game.player.abilities.SlideStuff.Slide;
import de.sag.mazehunter.server.networkData.HealthUpdate;
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
    float CollisionDistance;
    
    public Ability attackAbility;
    public Ability mobilityAbility;
    public Ability utilityAbility;
    public Ability slideAbility;
    
    private final Vector2 tmp = new Vector2();
    
    public Player(int id) {
        position = new Vector2();
        position.set(0f, 0f);
        velocity = new Vector2();
        velocity.set(0f, 0f);
        connectionID = id;
        speed = Config.DEFAULT_SPEED;
        movementSpeedFactor = 1.0f;
        maxHealth = 100;
        currentHealth = maxHealth;
        attackAbility = null;
        utilityAbility = null;
        mobilityAbility = new Dash(); // maybe the player will be able to choose one at some point ..
        slideAbility = new Slide();
    }

    public void move(int angle, boolean movement) {
        if (!movement) {
            velocity.set(0f, 0f);
        } else {
            //TODO: Collision
            updateVelocity(angle);
        }
    }
    
    /**
     * 
     * @param amount positive values for healing and negitve ones for damage
     */
    public void changeHealth(int amount) {
        if (amount + currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else if (amount + currentHealth < 0) {
            //TODO death
        } else {
            currentHealth += amount;
        }
        
        HealthUpdate hu = new HealthUpdate(amount, connectionID);
        Main.MAIN_SINGLETON.server.sendToAllTCP(hu);
    }
    
    public void updateVelocity(int angle) {
        velocity.set(speed, 0);
        velocity.setAngle((float) angle);
    }

    public void update(float delta) {
        this.position.add(tmp.set(velocity).scl(delta));
    }
    
    public void calcCD() {
        System.out.println("gftrgtrgotrgjotrjogtrjiogpftrhjiogfthio");
    }
}