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
 * duration/cooldown in seconds/milliseconds
 * 
 * damage and heal always int (eg. no 10.5 HP possible)
 * 
 * movementspeed factors always floats from 0.0 to 1.0 for slows and 1.0 to infinity to increase movementspeed
 */
public class Config {
    
    //Game stuff
    public static float PICKUP_HITBOXRADIUS2 = 1000;
    
    //Player Stuff
    public static int DEFAULT_SPEED = 150;
    public static float PLAYER_HITBOXRADIUS2 = 200;
    
    //permanent ability Stuff
    public static final float DASH_COOLDOWN = 2;
    public static final int DASH_RANGE = 90;
    
    public static final float SLIDE_COOLDOWN = 3;
    
    //pickups
    public static final int BLIZZARD_RADIUS = 100;
    public static final float BLIZZARD_SLOW_DURATION = 3f; 
    public static final float BLIZZARD_SLOW_AMOUNT = 0.5f;
    public static final int BLIZZARD_DAMAGE = 15;
    public static final float BLIZZARD_SPAWNRATE = 1.0f;
    
    public static final float STANDARDHEAL_DURATION = 2f;
    public static final float STANDARDHEAL_SPAWNRATE = 1.0f;
    public static final int STANDARDHEAL_TOTALHEAL = 100;
    
    public static final int FIREBALL_DAMAGE = 25;
    public static final int FIREBALL_SPEED = 300;
    public static final float FIREBALL_HITBOXRADIUS2 = 60;
    public static final int FIREBALL_CHARGES = 1;
    public static final float FIREBALL_SPAWNRATE = 1.0f;
    public static final float FIREBALL_CD_BETWEEN_USES = 1;
    public static final int FIREBALL_MAXRANGE2 = 100000;
    
    public static final float STUNARROW_BASE_STUN_DURATION = 0.3f;
    public static final float STUNARROW_GAIN_PER_PIXEL = 0.003f;
    public static final int STUNARROW_SPEED = 200;
    public static final int STUNARROW_HITBOXRADIUS2 = 50;
    public static final float STUNARROW_SPAWNRATE = 1.0f;
    public static final int STUNARROW_MAXRANGE2 = 100000;
    public static final int STUNARROW_CHARGES = 1;
    public static final float STUNARROW_CD_BETWEEN_USES = 5000;
    
    public static final int TRAP_HITBOXRADIUS2 = 200;
    public static final float TRAP_ROOTDURATION = 4;
    public static final int TRAP_CHARGES = 1;
    public static final int TRAP_MAXTRAPCOUNT_PER_PLAYER = 2;
    
    public static void pushConfig() {
        PushConfig pc = new PushConfig();
        
        pc.DEFAULT_SPEED = DEFAULT_SPEED;
        
        pc.BLIZZARD_SLOW_DURATION = BLIZZARD_SLOW_DURATION;
        pc.BLIZZARD_SLOW_AMOUNT = BLIZZARD_SLOW_AMOUNT;
        
        pc.STANDARDHEAL_DURATION = STANDARDHEAL_DURATION;
        
        pc.TRAP_ROOTDURATION = TRAP_ROOTDURATION;
        
        Main.MAIN_SINGLETON.server.sendToAllUDP(pc);
    }
}