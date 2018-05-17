/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game;

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

    private final Vector2 tmp = new Vector2();

    public Player(int id) {
        position = new Vector2();
        position.set(0f, 0f);
        velocity = new Vector2();
        velocity.set(0f, 0f);
        connectionID = id;
        speed = 50;
    }

    public void move(int angle, boolean movement) {
        if (!movement) {
            velocity.set(0f, 0f);
        } else {
            //TODO: Collision
            velocity.set(speed, 0);
            velocity.setAngle((float) angle);
        }
    }

    public void update(float delta) {
        this.position.add(tmp.set(velocity).scl(delta));
    }
}
