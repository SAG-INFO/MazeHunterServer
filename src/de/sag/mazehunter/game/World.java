package de.sag.mazehunter.game;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.map.Block;
import de.sag.mazehunter.game.map.Map;
import de.sag.mazehunter.game.map.MapGenerator;
import de.sag.mazehunter.game.map.MoveRow;
import de.sag.mazehunter.game.player.abilities.Entity.EntityManager;
import de.sag.mazehunter.game.player.abilities.PickupManager;
import de.sag.mazehunter.server.networkData.PushMap;

/**
 *
 * @author sreis
 */
public class World {
    
    public final Map map;
    public final PickupManager pickupManager;
    public final MoveRow moveRow;
    public final EntityManager entityManager;
    private final MapGenerator mapGenerator;

    public World() {
        map = new Map(25, 50);
        mapGenerator = new MapGenerator();
        Block[][] b = mapGenerator.generateBlocklist(10);
        map.makeMap(b);
        PushMap p = new PushMap(mapGenerator.convertBlocksToBoolean(b));
        Main.MAIN_SINGLETON.server.sendToAllTCP(p);
        //map.makeMap(true, false, false, true, true, true, false, true, true, false, true, true, true, true, true, true, true, true, false, false, true, true, false, true, true, true, true, false, false, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, false, true, false, true, true, false, true, false, true, false, false, true, false, true, false, true, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, true, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, false, false, true, true, true, false, true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, false, true, false, false, true, true, true, false, false, true, true, true, false, true, true, false, true, false, false, true, true, true, true, true, true, false, true, true, false, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, true, false, true, false, true, false, true, true, false, true, true, true, false, true, true, false, true, true, false, true, true, false, true, false, true, true, false, true, true, true, false, true, false, false, true, true, true, true, false, true, true, true, true, true, false, false, true, true);
        
        pickupManager = new PickupManager();
        entityManager = new EntityManager();
        moveRow = new MoveRow();
    }

    public void update(float delta) {
        entityManager.update(delta);
        pickupManager.update();
        map.update();
    }
}
