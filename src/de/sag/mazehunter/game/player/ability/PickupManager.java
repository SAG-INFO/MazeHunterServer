package de.sag.mazehunter.game.player.ability;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.map.Block;
import de.sag.mazehunter.game.map.Centeropen;
import de.sag.mazehunter.game.map.Map;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.pickups.DisposePickup;
import de.sag.mazehunter.server.networkData.abilities.pickups.EquipAbility;
import de.sag.mazehunter.server.networkData.abilities.pickups.SpawnPickup;
import de.sag.mazehunter.utils.MathUtils;
import de.sag.mazehunter.utils.Vector2;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author sreis
 */
public class PickupManager {

    public final ArrayList<AbilityPickup> pickups = new ArrayList<>();

    private final Vector2 tmpVec = new Vector2();

    private int idr=0;
    
    private boolean canCollectUtility = true;
    private boolean canCollectAttack = true;
    
    private float timeSinceLastSpawn;
    private float spawnRate = 1;
    
    public PickupManager() {
    }
    
    /**
     *@param position the position in Pixels where the pickup is goining to be placed
     *@param name the name of the Ability the Pickup represents. All lowercase, dont mess with spelling
     */
    public void spawnPickup(Block block, String name) {
        AbilityPickup pickup = new AbilityPickup(block, idr++, name);
        pickups.add(pickup);
        SpawnPickup sp = new SpawnPickup();
        sp.abilityName = name;
        sp.id = pickup.id;
        sp.blockX = block.getX();
        sp.blockY = block.getY();

        ((Centeropen)block.tilelist[1][1]).pickup = pickup;
        
        Main.MAIN_SINGLETON.server.sendToAllTCP(sp);
    }

    public void update(float delta) {
        
        for (Player player : Main.MAIN_SINGLETON.game.players) {
            if(player == null)
                continue;
            
            //Is it a boy or a girl? Its a Lambda-Expression! I <3 Java8
            Optional<AbilityPickup> pickup = pickups.stream().filter((p) -> (tmpVec.set(player.mc.position).sub(p.position).len2() < Config.PICKUP_HITBOXRADIUS2)).findFirst();
            if(pickup.isPresent())
                equipAbility(player, pickup.get());
        }
        
        timeSinceLastSpawn += delta;
        
        if(timeSinceLastSpawn < spawnRate || pickups.size()>=5)
            return;

        timeSinceLastSpawn = 0;
        String name = Math.random()<0.5f?"Trap":"Fireball";
        spawnPickup(calculateSpawnPosition(), name);
    }
    
    private void equipAbility(Player player, AbilityPickup pickup) {
        String type;
        
        if (equipAttackAbility(player, pickup)) {type = "attack";} 
        else if (equipUtilityAbility(player, pickup)) {type = "utility";}
        else {return;}
        
        disposePickup(pickup); 
        
        EquipAbility ea = new EquipAbility();
        ea.abilityName = pickup.abilityName;
        ea.type = type;
        ea.id = player.connectionID;
        Main.MAIN_SINGLETON.server.sendToAllTCP(ea);
    }
    private boolean equipAttackAbility(Player player, AbilityPickup pickup) {
        if (!canCollectAttack) {return false;}
        String name = pickup.abilityName;
        String oldName;
        boolean didthings;
        
        switch (name) {
            case "Fireball":    oldName = Main.MAIN_SINGLETON.game.abilityFACTORY.collectFireball(player.connectionID);
                                didthings = true;
                                break;
            default: return false;
        } 
        
        swapAttackCooldown();
        
        if (oldName != null) {spawnPickup(pickup.block, oldName);}
        return didthings;
    }
    private boolean equipUtilityAbility(Player player, AbilityPickup pickup) {
        if (!canCollectUtility) {return false;}
        String name = pickup.abilityName;
        String oldName;
        
        switch (name) {
            case "StunArrow":       oldName = Main.MAIN_SINGLETON.game.abilityFACTORY.collectStunArrow(player.connectionID);
                                    System.out.println("StunArrow detected.");
                                    break;
            case "Trap":            oldName = Main.MAIN_SINGLETON.game.abilityFACTORY.collectTrap(player.connectionID);
                                    System.out.println("Trap detected.");
                                    break;
            default: return false;
        }
        
        swapUtilityCooldown();
        
        if (oldName != null) {spawnPickup(pickup.block, oldName);}
        return true;
    }
    
    private void disposePickup(AbilityPickup pickup) {
        pickups.remove(pickup);

        DisposePickup dp = new DisposePickup();
        dp.id = pickup.id;
        Main.MAIN_SINGLETON.server.sendToAllTCP(dp);
    }

    private Block calculateSpawnPosition(){
        while (true) {
            Block random = Main.MAIN_SINGLETON.game.world.map.blocklist[MathUtils.random(Map.blockWorldwidth - 1)][MathUtils.random(Map.blockWorldwidth - 1)];
            if(!random.tilelist[1][1].open)
                continue;
            return random;
        }
    }

    private void swapAttackCooldown() {
        canCollectAttack = false;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                canCollectAttack = true;
            }}, 1000);
    }
    private void swapUtilityCooldown() {
        canCollectUtility = false;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                canCollectUtility = true;
            }}, 1000);
    }
}
