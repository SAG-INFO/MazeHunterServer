/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.server.networkData.abilities.standardHeal;

import de.sag.mazehunter.game.player.abilities.abilityConfigs.StandardHealConfig;


/**
 *
 * @author Karl Huber
 */
public class StandardHealConfigResponse {
    public StandardHealConfig healConfig;

    public StandardHealConfigResponse(StandardHealConfig healConfig) {
        this.healConfig = healConfig;
    }

    public StandardHealConfigResponse() {
    }
}
