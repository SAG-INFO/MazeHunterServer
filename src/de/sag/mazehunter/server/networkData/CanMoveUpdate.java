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
public class CanMoveUpdate {
    public boolean canMove;

    public CanMoveUpdate(boolean canMove) {
        this.canMove = canMove;
    }
    
    public CanMoveUpdate() {
    }
}
