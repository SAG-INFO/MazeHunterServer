/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.server.networkData.HealthUpdate;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author g.duennweber
 */
public class Player {

    public final Vector2 position;
    public final Vector2 velocity;
    public int connectionID;
    float speed;
    float movementSpeedFactor;
    int maxHealth;
    int currentHealth;
    float collisionDistanceX;
    float collisionDistanceY;
    
    private final Vector2 tmp = new Vector2();

    public Player(int id) {
        position = new Vector2();
        position.set(0f, 0f);
        velocity = new Vector2();
        velocity.set(0f, 0f);
        connectionID = id;
        speed = Config.DEFAULT_SPEED;
        movementSpeedFactor = 1.0f;
        maxHealth = 100;
        currentHealth = maxHealth;
        collisionDistanceX = 0f;
        collisionDistanceY = 0f;
    }

    public void move(int angle, boolean movement) {
        if (!movement) {
            velocity.set(0f, 0f);
        } else {
            //TODO: Collision
            updateVelocity(angle);
        }
    }
    
    /**
     * 
     * @param amount positive values for healing and negative ones for damage
     */
    public void changeHealth(int amount) {
        if (amount + currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else if (amount + currentHealth < 0) {
            //TODO death
        } else {
            currentHealth += amount;
        }
        
        HealthUpdate hu = new HealthUpdate(currentHealth, connectionID);
        Main.MAIN_SINGLETON.server.sendToAllUDP(hu);
    }
    
    public void updateVelocity(int angle) {
        velocity.set(speed, 0); 
        velocity.setAngle((float) angle);
    }

    public void update(float delta) {
        this.position.add(tmp.set(velocity).scl(delta));
    }
    
    /*public void calcCD() {
        int myBlockX = 1 + (int)(position.x/3/*world.blocklength/);
        float InsidemyTileX = (position.x) - (myBlockX * 3/*world.blocklength/);
        int myTileX = 0;
        if(InsidemyTileX < 1 /*wolrd.eckelength/)
            myTileX = 1;
                    else if(InsidemyTileX < 1 /*wolrd.eckelength/ + 1 /*world.centerlength/)
                        myTileX = 2;
                                else
                                    myTileX = 3;
        
        
        int myBlockY = 1 + (int)(position.y/3/*world.blocklength/);
        float InsidemyTileX = (position.y) - (myBlockX * 3/*world.blocklength/);
        int myTileX = 0;
        if(InsidemyTileX < 1 /*wolrd.eckelength/)
            myTileX = 1;
                    else if(InsidemyTileX < 1 /*wolrd.eckelength/ + 1 /*world.centerlength/)
                        myTileX = 2;
                                else
                                    myTileX = 3;
        
        
        collisionDistanceX = 1;
        collisionDistanceY =1;
    }*/
    
    public void calcCD() {
    int myBlockX = world.myBlock;
    int myTileX = world.myBlock;
    int myBlockY = world.myBlock;
    int myTileY = world.myBlock;
    int[][] myNeighbours = new int[8][4];
    int[][] myNeighbours = world.getMyNeighbours.clone();
    collisionDistanceX = 10000.0f;
    collisionDistanceY = 10000.0f;
    switch(this.velocity.angle()) {
        case 0: 
            if(!world.IsTileOpen(myNeighbours[0][0], myNeighbours[0][1], myNeighbours[0][2], myNeighbours[0][3]))
                collisionDistanceX = getTilePosition(myNeighbours[0][0], myNeighbours[0][2], 'r') - this.position.x;
            break;
        case 45:
            if(!world.IsTileOpen(myNeighbours[0][0], myNeighbours[0][1], myNeighbours[0][2], myNeighbours[0][3]))
                collisionDistanceX = getTilePosition(myNeighbours[0][0], myNeighbours[0][2], 'r') - this.position.x;  
            if(!world.IsTileOpen(myNeighbours[1][0], myNeighbours[1][1], myNeighbours[1][2], myNeighbours[1][3]))
                collisionDistanceX = getTilePosition(myNeighbours[1][0], myNeighbours[1][2], 'r') - this.position.x;
                collisionDistanceY = getTilePosition(myNeighbours[1][1], myNeighbours[1][3], 'o') - this.position.y;
            if(!world.IsTileOpen(myNeighbours[2][0], myNeighbours[2][1], myNeighbours[2][2], myNeighbours[2][3]))
                collisionDistanceY = getTilePosition(myNeighbours[2][1], myNeighbours[2][3], 'o') - this.position.y;
            break;
        case 90:
            if(!world.IsTileOpen(myNeighbours[2][0], myNeighbours[2][1], myNeighbours[2][2], myNeighbours[2][3]))
                collisionDistanceY = getTilePosition(myNeighbours[2][1], myNeighbours[2][3], 'o') - this.position.y;
            break;
        case 135:
            if(!world.IsTileOpen(myNeighbours[2][0], myNeighbours[2][1], myNeighbours[2][2], myNeighbours[2][3]))
                collisionDistanceY = getTilePosition(myNeighbours[2][1], myNeighbours[2][3], 'o') - this.position.y;  
            if(!world.IsTileOpen(myNeighbours[3][0], myNeighbours[3][1], myNeighbours[3][2], myNeighbours[3][3]))
                collisionDistanceY = getTilePosition(myNeighbours[3][1], myNeighbours[3][3], 'o') - this.position.y;
                collisionDistanceX = getTilePosition(myNeighbours[3][0], myNeighbours[3][2], 'l') - this.position.x;
            if(!world.IsTileOpen(myNeighbours[4][0], myNeighbours[4][1], myNeighbours[4][2], myNeighbours[4][3]))
                collisionDistanceX = getTilePosition(myNeighbours[4][0], myNeighbours[4][2], 'l') - this.position.x;
            break;
        case 180:
            if(!world.IsTileOpen(myNeighbours[4][0], myNeighbours[4][1], myNeighbours[4][2], myNeighbours[4][3]))
                collisionDistanceX = getTilePosition(myNeighbours[4][0], myNeighbours[4][2], 'l') - this.position.x;
            break;
        case 225:
            if(!world.IsTileOpen(myNeighbours[4][0], myNeighbours[4][1], myNeighbours[4][2], myNeighbours[4][3]))
                collisionDistanceX = getTilePosition(myNeighbours[4][0], myNeighbours[4][2], 'l') - this.position.x;  
            if(!world.IsTileOpen(myNeighbours[5][0], myNeighbours[5][1], myNeighbours[5][2], myNeighbours[5][3]))
                collisionDistanceX = getTilePosition(myNeighbours[5][0], myNeighbours[5][2], 'l') - this.position.x;
                collisionDistanceY = getTilePosition(myNeighbours[5][1], myNeighbours[5][3], 'u') - this.position.y;
            if(!world.IsTileOpen(myNeighbours[6][0], myNeighbours[6][1], myNeighbours[6][2], myNeighbours[6][3]))
                collisionDistanceY = getTilePosition(myNeighbours[6][1], myNeighbours[6][3], 'u') - this.position.y;
            break;
        case 270:
            if(!world.IsTileOpen(myNeighbours[6][0], myNeighbours[6][1], myNeighbours[6][2], myNeighbours[6][3]))
                collisionDistanceY = getTilePosition(myNeighbours[6][1], myNeighbours[6][3], 'u') - this.position.y;
            break;
        case 315:
            if(!world.IsTileOpen(myNeighbours[6][0], myNeighbours[6][1], myNeighbours[6][2], myNeighbours[6][3]))
                collisionDistanceY = getTilePosition(myNeighbours[6][1], myNeighbours[6][3], 'u') - this.position.y;  
            if(!world.IsTileOpen(myNeighbours[7][0], myNeighbours[7][1], myNeighbours[7][2], myNeighbours[7][3]))
                collisionDistanceY = getTilePosition(myNeighbours[7][1], myNeighbours[7][3], 'u') - this.position.y;
                collisionDistanceX = getTilePosition(myNeighbours[7][0], myNeighbours[7][2], 'r') - this.position.x;
            if(!world.IsTileOpen(myNeighbours[0][0], myNeighbours[0][1], myNeighbours[0][2], myNeighbours[0][3]))
                collisionDistanceX = getTilePosition(myNeighbours[0][0], myNeighbours[0][2], 'r') - this.position.x;
            break;
    }
    }
    
    public float getTilePosition(int block, int tile, char side){ //char "schauen"
        float tp = -1;
        switch(side){
            case 'r':  
                /*if(tile == 0)
                    tp = 0;
                else if(tile == 1)
                    tp = world.ecke;
                else if (tile == 2)
                    tp = world.ecke + world.center;
                tp += block * world.blockbreite;
                break;*/
            case 'o':
                if(tile == 0)
                    tp = 0;
                else if(tile == 1)
                    tp = world.ecke;
                else if (tile == 2)
                    tp = world.ecke + world.center;
                tp += block * world.blockbreite;
                break;
            case 'l':
                /*if(tile == 0)
                    tp = world.ecke;
                else if(tile == 1)
                    tp = world.ecke + world.center;
                else if (tile == 2)
                    tp = world.ecke + world.center + world.ecke;
                tp += block * world.blockbreite;
                break;*/
            case 'u':
                if(tile == 0)
                    tp = world.ecke;
                else if(tile == 1)
                    tp = world.ecke + world.center;
                else if (tile == 2)
                    tp = world.ecke + world.center + world.ecke;
                tp += block * world.blockbreite;
                break;
        }
        return tp;
    }
}