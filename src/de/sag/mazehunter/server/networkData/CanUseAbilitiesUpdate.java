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
public class CanUseAbilitiesUpdate {
    public boolean canUseAbilities;

    public CanUseAbilitiesUpdate(boolean canUseAbilities) {
        this.canUseAbilities = canUseAbilities;
    }
    
    public CanUseAbilitiesUpdate() {
    }
}
