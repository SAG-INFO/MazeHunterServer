/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.CanMoveUpdate;
import de.sag.mazehunter.server.networkData.CanUseAbilitiesUpdate;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Karl Huber
 */
public class Status extends Listener {
    
    // "mobility" type Abilities are also affected by canMove
    private int canMove = 0; // 0 == true
    private int canUseAbilities = 0; // 0 == true
    
    private void addCanMove(int connectionID) {
        canMove++;
        if (canMove == 1) {Main.MAIN_SINGLETON.server.sendToTCP(connectionID, new CanMoveUpdate(false));}
    }
    
    private void lowerCanMove(int connectionID) {
        canMove--;
        if (canMove == 0) {Main.MAIN_SINGLETON.server.sendToTCP(connectionID, new CanMoveUpdate(true));}
    }
    
    private void addCanUseAbilities(int connectionID) {
        canUseAbilities++;
        if (canUseAbilities == 1) {Main.MAIN_SINGLETON.server.sendToTCP(connectionID, new CanUseAbilitiesUpdate(false));}
    }
    
    private void lowerCanUseAbilities(int connectionID) {
        canUseAbilities--;
        if (canUseAbilities == 0) {Main.MAIN_SINGLETON.server.sendToTCP(connectionID, new CanUseAbilitiesUpdate(true));}
    }
    
    /**
     *
     * @param duration duration in milliseconds.
     * @param connectionID connectionID of the affected player.
     */
    public void stun(float duration, int connectionID) {
        stopMovement(connectionID);
        addCanMove(connectionID);
        addCanUseAbilities(connectionID);
        Timer t = new java.util.Timer();
        t.schedule(new TimerTask() {@Override
            public void run() {
            lowerCanMove(canMove);
            lowerCanUseAbilities(canMove);
        }}, (long) duration);
    }
    
    /**
     *
     * @param duration duration in milliseconds.
     * @param connectionID connectionID of the affected player.
     */
    public void root(float duration, int connectionID) {
        stopMovement(connectionID);
        addCanMove(connectionID);
        Timer t = new java.util.Timer();
        t.schedule(new TimerTask() {@Override
            public void run() {
            lowerCanMove(canMove);
        }}, (long) duration);
    }
    
    /**
     *
     * @param duration duration in milliseconds.
     * @param connectionID connectionID of the affected player.
     */
    public void silence(float duration, int connectionID) {
        addCanUseAbilities(connectionID);
        Timer t = new java.util.Timer();
        t.schedule(new TimerTask() {@Override
            public void run() {
            lowerCanUseAbilities(canMove);
        }}, (long) duration);
    }
    
    /**
     * 
     * @param duration duration of the effect in seconds.
     * @param change +0.25 would be 25% faster movement for the duration.
     * @param connectionID connectionID of the affected player.
     */
    public void changeMovementSpeed(float duration, float change, int connectionID) {
        Player player = Main.MAIN_SINGLETON.game.getPlayer(connectionID);
        
        updateMovementSpeed(player, change);
        
        Timer t = new java.util.Timer();
        t.schedule(new TimerTask() {@Override
            public void run() {
               updateMovementSpeed(player, 1/change);
            }
        }, (long) duration);
    }
    
    private void updateMovementSpeed(Player player, float change) {
        player.mc.movementSpeedFactor *= change; 
    }
    
    public void stopMovement(int connectionID){
        Main.MAIN_SINGLETON.game.getPlayer(connectionID).mc.setVelocity(0, false);
    }
}