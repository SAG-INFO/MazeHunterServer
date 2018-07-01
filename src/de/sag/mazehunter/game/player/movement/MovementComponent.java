/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.movement;

import de.sag.mazehunter.Main;
import static de.sag.mazehunter.Main.MAIN_SINGLETON;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.MovementResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author g.duennweber
 */
public class MovementComponent {

    public final Vector2 position = new Vector2(35, 35);
    
    public final Vector2 velocity = new Vector2();
    private final Vector2 lastVelocity = new Vector2();
    private final Vector2 backupVelocity = new Vector2();
    private final Vector2 requestedVelocity = new Vector2(0, 0);

    public float speed;
    public final float size = 5;
    public float movementSpeedFactor;
    public float dashLength;

    public final Vector2 tmp = new Vector2();
    public final Vector2 backupPosition = new Vector2();

    private final Player player;

    public MovementComponent(Player p) {
        this.player = p;
        movementSpeedFactor = 1.0f;
        dashLength = 1.0f;
        speed = Config.DEFAULT_SPEED;

    }

    public void update(float delta) {
        velocity.set(requestedVelocity).scl(movementSpeedFactor);
        backupPosition.set(this.position);
        backupVelocity.set(this.velocity);
        
        checkCollision();
        this.position.add(tmp.set(velocity).scl(delta));
        
        if (backupVelocity != this.velocity) {
            this.position.set(backupPosition);
            this.position.add(tmp.set(velocity).scl(delta));
        }
        
        //send MovementUpdate id velocity has changed
        if(!lastVelocity.equals(velocity)){
            lastVelocity.set(velocity);
            sendMovementUpdate();
        }
    }

    public void setVelocity(int angle, boolean moving) {
        if (!moving) {
            this.requestedVelocity.set(0f, 0f);
        } else {
            this.requestedVelocity.set(this.speed, 0);
            this.requestedVelocity.setAngle(angle);
        }
        this.velocity.set(requestedVelocity);
    }

    private boolean collides(Vector2 p) {
        return !MAIN_SINGLETON.game.world.map.talktoTile(p.x, p.y).open;
    }

    public void checkCollision() {
        int signX = Integer.signum((int) this.velocity.x);
        int signY = Integer.signum((int) this.velocity.y);
        if (collides(tmp.set(this.position).add(this.size / 2, this.size * signY)) || collides(tmp.set(this.position).add(-this.size / 2, this.size * signY))) {
            this.velocity.y = 0;
        }
        if (collides(tmp.set(this.position).add(this.size * signX, this.size / 2)) || collides(tmp.set(this.position).add(this.size * signX, -this.size / 2))) {
            this.velocity.x = 0;
        }
    }

    public void dash() {
        Vector2 dir = new Vector2(0, 0);
        dir.set(requestedVelocity);
        dir.setLength(this.size);
        for (int i = 0; i < 300; i++) {
            if (collides(position)) {
                this.position.sub(dir);
                break;
            }
            this.position.add(dir);
        }
        sendMovementUpdate();
    }
    
    public void setPosition(Vector2 p) {
        Vector2 dir = new Vector2(0, 0);
        dir.set(tmp.set(this.position).sub(p));
        this.position.set(p);
        dir.setLength(dashLength);
        while (collides(position)) {
            this.position.add(tmp.set(dir));
        }
        this.position.add(tmp.setAngle(dir.angle()).setLength(this.size / 2));
        sendMovementUpdate();
    }
    
    private void sendMovementUpdate(){
        MovementResponse mr = new MovementResponse(position, velocity, player.connectionID);
        Main.MAIN_SINGLETON.server.sendToAllUDP(mr);
    }
}
