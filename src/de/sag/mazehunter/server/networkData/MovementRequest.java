/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData;

/**
 *
 * @author karl.huber
 */
public class MovementRequest {
    public int angle;
    public boolean moving;

    public MovementRequest(int angle, boolean movement) {
        this.angle = angle;
        this.moving = movement;
    }

    public MovementRequest() {
    }
}
