/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.configs.PushConfig;

/**
 *
 * @author Karl Huber
 * duration/cooldown always in seconds
 * 
 * damage and heal always int (eg. no 10.5 HP possible)
 * 
 * movementspeed factors always floats from 0.0 to 1.0 for slows and 1.0 to infinity to increase movementspeed
 */
public class Config {
    
    //Player Stuff
    public static int DEFAULT_SPEED = 50;
    public static int DEFAULT_HEALTHPOINTS = 100;
    
    //Ability Stuff
    public static final int BLIZZARD_RADIUS = 100;
    public static final float BLIZZARD_SLOW_DURATION = 3f; 
    public static final float BLIZZARD_SLOW_AMOUNT = 0.5f;
    public static final int BLIZZARD_DAMAGE = 15;
    public static final float BLIZZARD_COOLDOWN = 10f;
    
    public static final float DASH_COOLDOWN = 6f;
    public static final int DASH_RANGE = 30;
    
    public static final float STANDARDHEAL_DURATION = 2f;
    public static final float STANDARDHEAL_COOLDOWN = 30f;
    public static final int STANDARDHEAL_TOTALHEAL = 75;
    
    public static void pushConfig() {
        PushConfig pc = new PushConfig();
        
        //dont work for the client yet
        pc.DEFAULT_HEALTHPOINTS = DEFAULT_HEALTHPOINTS;
        pc.DEFAULT_SPEED = DEFAULT_SPEED;
        
        pc.BLIZZARD_RADIUS = BLIZZARD_RADIUS;
        pc.BLIZZARD_SLOW_DURATION = BLIZZARD_SLOW_DURATION;
        pc.BLIZZARD_SLOW_AMOUNT = BLIZZARD_SLOW_AMOUNT;
        pc.BLIZZARD_DAMAGE = BLIZZARD_DAMAGE;
        pc.BLIZZARD_COOLDOWN = BLIZZARD_COOLDOWN;
        
        pc.DASH_COOLDOWN = DASH_COOLDOWN;
        pc.DASH_RANGE = DASH_RANGE;
        
        pc.STANDARDHEAL_COOLDOWN = STANDARDHEAL_COOLDOWN;
        pc.STANDARDHEAL_DURATION = STANDARDHEAL_DURATION;
        pc.STANDARDHEAL_TOTALHEAL = STANDARDHEAL_TOTALHEAL;
        
        Main.MAIN_SINGLETON.server.sendToAllUDP(pc);
    }
}