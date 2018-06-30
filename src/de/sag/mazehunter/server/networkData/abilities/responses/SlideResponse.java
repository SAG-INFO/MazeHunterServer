/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData.abilities.responses;

/**
 *
 * @author karl.huber
 */
public class SlideResponse {
    public char direction;
    public int row;

    public SlideResponse(char direction, int row) {
        this.direction = direction;
        this.row = row;
    }

    public SlideResponse() {
    }
}
