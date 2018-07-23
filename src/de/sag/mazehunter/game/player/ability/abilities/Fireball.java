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
import de.sag.mazehunter.game.player.ability.entities.projectiles.FireballEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.FireballResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 *
 * SO NE GROSSE FEUERBALL JUNGE
 */
public class Fireball extends ActiveAbility {

    public Fireball(int playerID) {
        super(playerID, 3, 2);
    }
    
    @Override
    public void fire(Vector2 target) {
        Player player = Main.MAIN_SINGLETON.game.getPlayer(playerId);

        int projectileID = Main.MAIN_SINGLETON.game.world.entityManager.getNewEntityID();
        float angle = calcAngle(target);
        Vector2 velocity = new Vector2(Config.FIREBALL_SPEED, 0).setAngle(angle);

        FireballEntity fireball = new FireballEntity(player.mc.position, playerId, projectileID);
        fireball.shoot(velocity);
        Main.MAIN_SINGLETON.game.world.entityManager.entities.add(fireball);

        Main.MAIN_SINGLETON.server.sendToAllUDP(new FireballResponse(projectileID, playerId, velocity));
    }
}
