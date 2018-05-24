/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData;

import de.sag.mazehunter.game.abilities.AbilityConfig;

/**
 *
 * @author Karl Huber
 */
public class AbilityConfigResponse {
    
    public AbilityConfig abilityConfig;

    public AbilityConfigResponse(AbilityConfig abilityConfig) {
        this.abilityConfig = abilityConfig;
    }

    public AbilityConfigResponse() {
    }
}
