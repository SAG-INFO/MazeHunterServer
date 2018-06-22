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
    
    public float BLIZZARD_SLOW_DURATION; 
    public float BLIZZARD_SLOW_AMOUNT;
    
    public float STANDARDHEAL_DURATION ;
    
    public int DEFAULT_SPEED;
    
    public float TRAP_ROOTDURATION;
    
    public PushConfig() {
    }
}
