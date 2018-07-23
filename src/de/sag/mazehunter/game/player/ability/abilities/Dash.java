/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.CooldownAbility;
import de.sag.mazehunter.server.networkData.abilities.responses.DashResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class Dash extends CooldownAbility{

    public final int playerId;

    public Dash(int playerId) {
        super(playerId, Config.DASH_COOLDOWN);
        this.playerId = playerId;
    }

    public void use() {
        if(!ready())
            return;
        
        startCooldown();
        
        Player player = Main.MAIN_SINGLETON.game.getPlayer(playerId);
        player.mc.dash();

        DashResponse dr = new DashResponse(player.mc.position, player.mc.velocity, playerId);
        Main.MAIN_SINGLETON.server.sendToAllUDP(dr);
    }
}
