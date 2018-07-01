/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.Ability;
import de.sag.mazehunter.game.player.ability.entities.projectiles.FrostBoltEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.FrostBoltResponse;
import de.sag.mazehunter.utils.Vector2;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Karl Huber
 *
 * SO NE GROSSE FEUERBALL JUNGE
 */
public class FrostBolt extends Ability {

    public int charge;
    public boolean canUse;

    @Override
    public void use(int connectionID, float angle) {

        if (!canUse) {
            return;
        }

        Player player = Main.MAIN_SINGLETON.game.getPlayer(connectionID);
        int projectileID = Main.MAIN_SINGLETON.game.world.entityManager.getNewEntityID();

        Vector2 fVelocity = new Vector2(Config.FROSTBOLT_SPEED, 0);
        fVelocity.setAngle(angle);

        Main.MAIN_SINGLETON.game.world.entityManager.entities.add(new FrostBoltEntity(fVelocity, player.mc.position.cpy(), Config.FROSTBOLT_HITBOXRADIUS2, projectileID, connectionID));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new FrostBoltResponse(projectileID, connectionID, fVelocity.cpy(), angle));

        startCooldown();

        charge--;
        if (charge == 0) {
            player.ability = new NoAbility();
        }
    }

    public void startCooldown(int index) {
        canUse = false;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                canUse = true;
            }
        }, (long) (Config.FROSTBOLT_CD_BETWEEN_USES));
    }

    public FrostBolt() {
        charge = Config.FROSTBOLT_CHARGES;
        canUse = true;
    }
}
