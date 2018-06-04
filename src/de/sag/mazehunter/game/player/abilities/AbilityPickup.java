package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author sreis
 */
public class AbilityPickup {
    public final Vector2 position;
    public final int id;
    public final String abilityName;

    public AbilityPickup(Vector2 position, int id, String name) {
        this.position = new Vector2(position);
        this.id = id;
        this.abilityName = name;
    }
}
