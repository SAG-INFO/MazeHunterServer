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
    public Vector2 position;
    public Vector2 velocity;
    public int id;

    public MovementResponse(Vector2 position, Vector2 velocity, int id) {
        this.position = position;
        this.velocity = velocity;
        this.id = id;
    }

    public MovementResponse() {
    }    
}
