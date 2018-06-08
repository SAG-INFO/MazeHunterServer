/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData.abilities.responses;

import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class FireballResponse {
    public int id;
    public Vector2 velocity;

    public FireballResponse(int id, Vector2 velocity) {
        this.id = id;
        this.velocity = velocity;
    }

    public FireballResponse() {
    }
}
