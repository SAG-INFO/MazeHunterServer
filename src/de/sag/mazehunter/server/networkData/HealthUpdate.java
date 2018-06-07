/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData;

/**
 *
 * @author Karl Huber
 */
public class HealthUpdate {
    public int change;
    public int id;

    public HealthUpdate(int change, int id) {
        this.change = change;
        this.id = id;
    }

    public HealthUpdate() {
    }
}