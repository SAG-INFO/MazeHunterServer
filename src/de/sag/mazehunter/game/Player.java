/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game;

import com.esotericsoftware.kryonet.Connection;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author g.duennweber
 */
public class Player {
    public Vector2 position; 
    public Vector2 velocity;
    int connectionID;
    
    
    public Player(int id) {
    position = new Vector2();
    position.set(0f, 0f);
    velocity = new Vector2();
    velocity.set(0f, 0f);
    connectionID = id;
    }
    
    public void move(int angle, boolean movement) {
    
    
    
    }
}
