package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.game.map.Block;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author sreis
 */
public class AbilityPickup {
    public final Vector2 position;
    public final int id;
    public final String abilityName;

    final Block block;
    
    public AbilityPickup(Block block, int id, String name) {
        this.block = block;
        this.position = new Vector2();
        this.id = id;
        this.abilityName = name;
    }
}
