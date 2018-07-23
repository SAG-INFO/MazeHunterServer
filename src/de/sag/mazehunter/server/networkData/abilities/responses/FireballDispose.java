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
public class FireballDispose {
    public int playerID;
    public int projectileID;

    public FireballDispose(int playerID, int projectileID) {
        this.playerID = playerID;
        this.projectileID = projectileID;
    }

    public FireballDispose() {
    }
}
