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
public class StunArrowResponse {
    public int projectileID;
    public int connectionID;
    public Vector2 velocity;

    public StunArrowResponse(int projectileID, int connectionID, Vector2 velocity) {
        this.projectileID = projectileID;
        this.connectionID = connectionID;
        this.velocity = velocity;
    }

    public StunArrowResponse() {
    }
    
}
