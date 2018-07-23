package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.game.player.ability.ActiveAbility;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author sreis
 */
public class NoAbility extends ActiveAbility{
    
    public NoAbility(int playerId) {
        super(playerId, -1, -1);
    }

    @Override
    protected void fire(Vector2 targetPosition) {
    }
    
}
