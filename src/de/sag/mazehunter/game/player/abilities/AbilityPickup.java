package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author sreis
 */
public class AbilityPickup {
    public final Vector2 position;
    public String name;

    public AbilityPickup(Vector2 position) {
        this.position = new Vector2(position);
    }
}
