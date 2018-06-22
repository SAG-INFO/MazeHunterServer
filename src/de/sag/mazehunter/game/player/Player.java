/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import de.sag.mazehunter.Main;
import static de.sag.mazehunter.Main.MAIN_SINGLETON;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.server.networkData.HealthUpdate;
import de.sag.mazehunter.utils.MathUtils;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author g.duennweber
 */
public class Player {

    private final float size = 6;
    public final Vector2 position = new Vector2(35, 35);
    public final Vector2 desiredVelocity = new Vector2(0, 0);
    public final Vector2 velocity = new Vector2(0, 0);
    public float speed;
    public float movementSpeedFactor;
    
    public int connectionID;
    
    public int maxHealth;
    public int currentHealth;

    private final Vector2 tmp = new Vector2();

    public Player(int id) {
        connectionID = id;
        speed = Config.DEFAULT_SPEED;
        movementSpeedFactor = 1.0f;
        maxHealth = 100;
        currentHealth = maxHealth;
    }

    public void move(int angle, boolean movement) {
        if (!movement) {
            velocity.set(0f, 0f);
        } else {
            updateVelocity(angle);
        }
    }

    public void updateVelocity(int angle) {
        velocity.set(speed, 0);
        velocity.setAngle((float) angle);
    }

    /**
     *
     * @param amount positive values for healing and negative ones for damage
     */
    public void changeHealth(int amount) {
        if (amount + currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else if (amount + currentHealth < 0) {
            //TODO death
        } else {
            currentHealth += amount;
        }

        HealthUpdate hu = new HealthUpdate(currentHealth, connectionID);
        Main.MAIN_SINGLETON.server.sendToAllUDP(hu);
    }

    public void update(float delta) {
        calcCD2();
        this.position.add(tmp.set(velocity).scl(delta));
    }
    
    public void calcCD2() {
        int signX = Integer.signum((int)velocity.x);
        int signY = Integer.signum((int)velocity.y);
        if(collides(tmp.set(position).add(size/2, size*signY)) || collides(tmp.set(position).add(-size/2, size*signY))){
            velocity.y = 0;
            InputListener.sendMovementResponse(this);
        }if(collides(tmp.set(position).add(size*signX, size/2)) || collides(tmp.set(position).add(size*signX, -size/2))){
            velocity.x = 0;
            InputListener.sendMovementResponse(this);
        }
    }
    
    private boolean collides(Vector2 position){
        return !MAIN_SINGLETON.game.world.talktoTile(position.x, position.y).open;
    }
    
//    public void calcCD() {
//        if (velocity.y > 0 && !MAIN_SINGLETON.game.world.talktoTile(tmp.set(position).add(0, 1)).open) {
//            velocity.y = 0;
//            InputListener.sendMovementResponse(this);
//        }else if (velocity.y < 0 && !MAIN_SINGLETON.game.world.talktoTile(tmp.set(position).add(0, -1)).open) {
//            velocity.y = 0;
//            InputListener.sendMovementResponse(this);
//        }if (velocity.x > 0 && !MAIN_SINGLETON.game.world.talktoTile(tmp.set(position).add(1, 0)).open) {
//            velocity.x = 0;
//            InputListener.sendMovementResponse(this);
//        }else if (velocity.x < 0 && !MAIN_SINGLETON.game.world.talktoTile(tmp.set(position).add(-1, 0)).open) {
//            velocity.x = 0;
//            InputListener.sendMovementResponse(this);
//        }
//    }
//    public void calcCD2() {
//        if (!MAIN_SINGLETON.game.world.talktoTile(tmp.set(position).add(0, Integer.signum((int) velocity.y))).open) {
//            velocity.y = 0;
//        } else if (!MAIN_SINGLETON.game.world.talktoTile(tmp.set(position).add(Integer.signum((int) velocity.x), 0)).open) {
//            velocity.x = 0;
//        }
//    }
}
