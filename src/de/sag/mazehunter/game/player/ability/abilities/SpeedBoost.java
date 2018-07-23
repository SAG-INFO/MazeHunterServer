package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.ActiveAbility;
import de.sag.mazehunter.server.networkData.abilities.responses.SpeedBoostResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author arein
 */
public class SpeedBoost extends ActiveAbility{

    public SpeedBoost(int playerId) {
        super(playerId, 1);
    }

    @Override
    protected void fire(Vector2 targetPosition) {
        Player p = Main.MAIN_SINGLETON.game.getPlayer(playerId);
        p.mc.slow(1.6f, 10);
        p.mc.forceMovementUpdate();
        
        SpeedBoostResponse data = new SpeedBoostResponse();
        data.playerId = playerId;
        Main.MAIN_SINGLETON.server.sendToAllTCP(data);
    }
}
