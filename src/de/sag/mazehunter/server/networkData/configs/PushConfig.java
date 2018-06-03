/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData.configs;

/**
 *
 * @author Karl Huber
 * 
 * duration/cooldown always in seconds
 * 
 * damage and heal always int (eg. no 10.5 HP possible)
 * 
 * movementspeed factors always floats from 0.0 to 1.0 for slows and 1.0 to infinity to increase movementspeed
 */
public class PushConfig {
    
    public int BLIZZARD_RADIUS;
    public float BLIZZARD_SLOW_DURATION; 
    public float BLIZZARD_SLOW_AMOUNT;
    public int BLIZZARD_DAMAGE;
    
    public float DASH_COOLDOWN;
    public int DASH_RANGE;
    
    public float STANDARDHEAL_DURATION ;
    public int STANDARDHEAL_TOTALHEAL;
    
    public int DEFAULT_SPEED;
    
    public int FIREBALL_DAMAGE;
    public int FIREBALL_SPEED;
    public int FIREBALL_SIZE;
    public int FIREBALL_CHARGES;
    public float FIREBALL_CD_BETWEEN_USES;

    public PushConfig() {
    }
}
