package de.sag.mazehunter.game;

import de.sag.mazehunter.game.map.Map;
import de.sag.mazehunter.game.map.SlideManager;
import de.sag.mazehunter.game.player.ability.entities.EntityManager;
import de.sag.mazehunter.game.player.ability.pickups.PickupManager;

/**
 *
 * @author sreis
 */
public class World {
    
    public final Map map;
    public final PickupManager pickupManager;
    public EntityManager entityManager;
    public final SlideManager slideManager;

    public World() {
        map = new Map(46, 46);
        map.makeMap(true, false, false, true, true, true, false, true, true, false, true, true, true, true, true, true, true, true, false, false, true, true, false, true, true, true, true, false, false, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, false, true, false, true, true, false, true, false, true, false, false, true, false, true, false, true, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, false, false, true, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, true, true, false, false, true, true);
        
        pickupManager = new PickupManager();
        entityManager = new EntityManager();
        slideManager = new SlideManager(map);
    }

    public void update(float delta) {
        entityManager.update(delta);
        pickupManager.update(delta);
    }
}
