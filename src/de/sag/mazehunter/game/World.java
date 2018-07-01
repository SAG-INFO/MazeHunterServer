package de.sag.mazehunter.game;

import de.sag.mazehunter.game.map.Map;
import de.sag.mazehunter.game.player.ability.entities.EntityManager;
import de.sag.mazehunter.game.player.ability.PickupManager;

/**
 *
 * @author sreis
 */
public class World {
    
    public final Map map;
    public final PickupManager pickupManager;
    public EntityManager entityManager;

    public World() {
        map = new Map(25, 50);
        map.makeMap(true, false, false, true, true, true, false, true, true, false, true, true, true, true, true, true, true, true, false, false, true, true, false, true, true, true, true, false, false, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, false, true, false, true, true, false, true, false, true, false, false, true, false, true, false, true, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, false, false, true, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, true, true, false, false, true, true);
        
        pickupManager = new PickupManager();
        entityManager = new EntityManager();
    }

    public void update(float delta) {
        entityManager.update(delta);
        pickupManager.update(delta);
    }
}
