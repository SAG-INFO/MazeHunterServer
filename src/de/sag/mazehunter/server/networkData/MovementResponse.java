/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData;

import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author g.duennweber
 */
public class MovementResponse {
    public final Vector2 position = new Vector2();
    public final Vector2 velocity = new Vector2();
    public int id;

    public MovementResponse(Vector2 position, Vector2 velocity, int id) {
        this.position.set(position);
        this.velocity.set(velocity);
        this.id = id;
    }

    public MovementResponse() {
    }    
}