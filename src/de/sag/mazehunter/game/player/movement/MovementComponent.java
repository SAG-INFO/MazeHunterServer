/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.movement;

import static de.sag.mazehunter.Main.MAIN_SINGLETON;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.InputListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author g.duennweber
 */
public class MovementComponent {

    public final Vector2 position;
    public final Vector2 velocity = new Vector2();
    public final Vector2 requestedVelocity = new Vector2(0, 0);

    public float speed;
    public final float size = 5;
    public float movementSpeedFactor;
    public float dashLength;

    public final Vector2 tmp = new Vector2();
    public final Vector2 backupPosition = new Vector2();
    public final Vector2 backupVelocity = new Vector2();

    private Player player;

    public MovementComponent(Player p) {
        this.player = p;
        movementSpeedFactor = 1.0f;
        dashLength = 1.0f;
        speed = Config.DEFAULT_SPEED;
        System.out.println(player.connectionID);
        position = new Vector2((Math.abs(player.connectionID))*10, 35);

    }

    public void setVelocity(int angle, boolean movement) {
        if (!movement) {
            this.requestedVelocity.set(0f, 0f);
        } else {
            this.requestedVelocity.set(this.speed, 0);
            this.requestedVelocity.setAngle((float) angle);
        }
    }

    public void updateMovement(float delta) {
        velocity.set(requestedVelocity);
        backupPosition.set(this.position);
        backupVelocity.set(this.velocity);
        stopMoveAgainstWall();
        this.position.add(tmp.set(velocity).scl(delta));
        if (backupVelocity != this.velocity) {
            this.position.set(backupPosition);
            System.out.println("velocity: " + velocity.toString());
            this.position.add(tmp.set(velocity).scl(delta));
        }
    }

    private boolean playerInWall() {
        return !MAIN_SINGLETON.game.world.talktoTile(this.position).open;
    }

    private boolean collides(Vector2 p) {
        return !MAIN_SINGLETON.game.world.talktoTile(p).open;
    }

    public void stopMoveAgainstWall() {
        int signX = Integer.signum((int) this.velocity.x);
        int signY = Integer.signum((int) this.velocity.y);
        if (collides(tmp.set(this.position).add(this.size / 2, this.size * signY)) || collides(tmp.set(this.position).add(-this.size / 2, this.size * signY))) {
            this.velocity.y = 0;
            InputListener.sendMovementResponse(player);
        }
        if (collides(tmp.set(this.position).add(this.size * signX, this.size / 2)) || collides(tmp.set(this.position).add(this.size * signX, -this.size / 2))) {
            this.velocity.x = 0;
            InputListener.sendMovementResponse(player);
        }
    }

    public void dash() {
        Vector2 dir = new Vector2(0, 0);
        dir.set(requestedVelocity);
        dir.setLength(0.1f);
        for (int i = 0; i < 300; i++) {
            if (playerInWall()) {
                this.position.sub(dir.setLength(this.size));
                break;
            }
            this.position.add(dir);
        }
        //this.position.add(tmp.setAngle(dir.angle()).setLength(size/2)); 
        InputListener.sendMovementResponse(player);
    }

    public void setPosition(Vector2 p) {
        Vector2 dir = new Vector2(0, 0);
        dir.set(tmp.set(this.position).sub(p));
        this.position.set(p);
        dir.setLength(dashLength);
        while (playerInWall()) {
            this.position.add(tmp.set(dir));
        }
        this.position.add(tmp.setAngle(dir.angle()).setLength(this.size / 2));
        InputListener.sendMovementResponse(player);
    }
}
