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
public class TrapResponse {
    public Vector2 position;

    public TrapResponse(Vector2 position) {
        this.position = position;
    }

    public TrapResponse() {
    }
    
}
