/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.movement.MovementComponent;
import de.sag.mazehunter.server.networkData.HealthUpdate;

/**
 *
 * @author g.duennweber
 */
public class Player {

    public int connectionID;
    public int maxHealth;
    public int currentHealth;
    
    public final MovementComponent mc;

    public Player(int id) {
        connectionID = id;
        maxHealth = 100;
        currentHealth = maxHealth;
        mc = new MovementComponent(this);
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

    public void update(float delta) {
        System.out.println("update: " + mc.position.x + "   " + mc.position.y);
        mc.updateMovement(delta);
    }
}
