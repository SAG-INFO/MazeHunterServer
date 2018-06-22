/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData.abilities.responses;

/**
 *
 * @author Karl Huber
 */
public class StunArrowShootResponse {
    public int playerID;
    public int projectileID;
    public float stunDuration;

    public StunArrowShootResponse(int playerID, int projectileID, float stunDuration) {
        this.playerID = playerID;
        this.projectileID = projectileID;
        this.stunDuration = stunDuration;
    }

    public StunArrowShootResponse() {
    }
}
