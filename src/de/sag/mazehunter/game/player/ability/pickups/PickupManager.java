package de.sag.mazehunter.game.player.ability.pickups;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.map.Block;
import de.sag.mazehunter.game.map.Centeropen;
import de.sag.mazehunter.game.map.Map;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.abilities.NoAbility;
import de.sag.mazehunter.server.networkData.abilities.pickups.DisposePickup;
import de.sag.mazehunter.server.networkData.abilities.pickups.EquipAbility;
import de.sag.mazehunter.server.networkData.abilities.pickups.SpawnPickup;
import de.sag.mazehunter.utils.MathUtils;
import de.sag.mazehunter.utils.Vector2;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author sreis
 */
public class PickupManager {

    public final ArrayList<AbilityPickup> pickups = new ArrayList<>();

    private final Vector2 tmpVec = new Vector2();

    private int lastPickupId = 0;

    private float timeSinceLastSpawn;
    private float spawnRate = 1;

    private final FACTORY abilityFactory;

    public PickupManager() {
        abilityFactory = new FACTORY();
    }

    /**
     * @param position the position in Pixels where the pickup is goining to be
     * placed
     * @param name the name of the Ability the Pickup represents. All lowercase,
     * dont mess with spelling
     */
    public void spawnPickup(Block block, String name) {
        AbilityPickup pickup = new AbilityPickup(block, lastPickupId++, name);
        pickup.position.set(block.tilelist[1][1].getPixelX() + Map.center / 2, block.tilelist[1][1].getPixelY() + Map.center / 2);
        pickups.add(pickup);
        ((Centeropen) block.tilelist[1][1]).pickup = pickup;
        SpawnPickup sp = new SpawnPickup();
        sp.abilityName = name;
        sp.id = pickup.id;
        sp.blockX = block.getX();
        sp.blockY = block.getY();

        Main.MAIN_SINGLETON.server.sendToAllTCP(sp);
    }

    public void update(float delta) {
        checkCollision();
        checkSpawn(delta);
    }

    private final String[] ailities = new String[]{"teleport", "fireball", "stunarrow", "speedboost"};

    public void checkSpawn(float delta) {
        timeSinceLastSpawn += delta;
        if (timeSinceLastSpawn < spawnRate || pickups.size() > 20) {
            return;
        }

        timeSinceLastSpawn = 0;

        String name = ailities[(int) MathUtils.random(0, ailities.length - 1)];
        spawnPickup(getRandomBlock(), name);
    }

    public void checkCollision() {
        for (Player player : Main.MAIN_SINGLETON.game.players) {
//            Optional<AbilityPickup> pickup = pickups.stream().filter((p) -> (tmpVec.set(player.mc.position).sub(p.position).len2() < Config.PICKUP_HITBOXRADIUS2)).findFirst();
//            if (pickup.isPresent()) {
//                equipAbility(player, pickup.get());
//            }
            AbilityPickup p = null;
            for (AbilityPickup pickup : pickups) {
                if (tmpVec.set(player.mc.position).sub(pickup.position).len2() < Config.PICKUP_HITBOXRADIUS2) {
                    p = pickup;
                }
            }
            if (p != null) {
                equipAbility(player, p);
            }
        }
    }

    private void equipAbility(Player player, AbilityPickup pickup) {
        if (!(player.activeAbility instanceof NoAbility)) {
            return;
        }

        System.out.println("equip:" + pickup.abilityName);
        disposePickup(pickup);
        abilityFactory.equipAbility(pickup.abilityName, player.connectionID);

        EquipAbility ea = new EquipAbility();
        ea.abilityName = pickup.abilityName;
        ea.id = player.connectionID;
        Main.MAIN_SINGLETON.server.sendToAllTCP(ea);
    }

    private void disposePickup(AbilityPickup pickup) {
        pickups.remove(pickup);

        DisposePickup dp = new DisposePickup();
        dp.id = pickup.id;
        Main.MAIN_SINGLETON.server.sendToAllTCP(dp);
    }

    private Block getRandomBlock() {
        while (true) {
            Block random = Main.MAIN_SINGLETON.game.world.map.blocklist[MathUtils.random(Map.blockWorldwidth - 1)][MathUtils.random(Map.blockWorldwidth - 1)];
            if (!random.tilelist[1][1].open) {
                continue;
            }
            return random;
        }
    }
}
