/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.ActiveAbility;
import de.sag.mazehunter.game.player.ability.entities.projectiles.StunArrowEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.StunArrowResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class StunArrow extends ActiveAbility {

    public StunArrow(int playerId) {
        super(playerId, 4, 1);
    }

    @Override
    public void fire(Vector2 target) {
        Player player = Main.MAIN_SINGLETON.game.getPlayer(connectionID);

        for (int i = -2; i < 3; i++) {
            int projectileID = Main.MAIN_SINGLETON.game.world.entityManager.getNewEntityID();
            float angle = calcAngle(target)+i*20;
            Vector2 velocity = new Vector2(Config.FIREBALL_SPEED, 0).setAngle(angle);

            StunArrowEntity fireball = new StunArrowEntity(player.mc.position, connectionID, projectileID);
            fireball.shoot(velocity);
            Main.MAIN_SINGLETON.game.world.entityManager.entities.add(fireball);
            
            Main.MAIN_SINGLETON.server.sendToAllUDP(new StunArrowResponse(projectileID, connectionID, velocity));
        }
    }
}
