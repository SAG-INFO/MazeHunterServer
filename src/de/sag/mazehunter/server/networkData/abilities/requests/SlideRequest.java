/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData.abilities.requests;

/**
 *
 * @author karl.huber
 */
public class SlideRequest {
    public int direction;

    public SlideRequest(int direction) {
        this.direction = direction;
    }

    public SlideRequest() {
    }
}
