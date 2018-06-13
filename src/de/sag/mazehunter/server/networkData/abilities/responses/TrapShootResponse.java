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
public class TrapShootResponse {
    public int connectionID;

    public TrapShootResponse(int connectionID) {
        this.connectionID = connectionID;
    }

    public TrapShootResponse() {
    }
}
