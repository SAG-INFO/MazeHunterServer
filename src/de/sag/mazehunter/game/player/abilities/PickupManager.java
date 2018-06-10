package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.pickups.DisposePickup;
import de.sag.mazehunter.server.networkData.abilities.pickups.EquipAbility;
import de.sag.mazehunter.server.networkData.abilities.pickups.SpawnPickup;
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
    
    public PickupManager() {
        this.spawnRandomPickupsOnRandomPositionsAtAlmostRandomTimesBoii();
    }
    
    /**
     *@param position the position in Pixels where the pickup is goining to be placed
     *@param name the name of the Ability the Pickup represents. All lowercase, dont mess with spelling
     */
    public void spawnPickup(Vector2 position, String name) {
        AbilityPickup pickup = new AbilityPickup(position, idr++, name);
        pickups.add(pickup);
        SpawnPickup sp = new SpawnPickup();
        sp.abilityName = name;
        sp.id = pickup.id;
        sp.position.set(position);

        Main.MAIN_SINGLETON.server.sendToAllTCP(sp);
    }

    public void update() {
        for (Player player : Main.MAIN_SINGLETON.game.player) {
            if(player == null)
                continue;
            
            //Is it a boy or a girl? Its a Lambda-Expression! I <3 Java8
            Optional<AbilityPickup> pickup = pickups.stream().filter((p) -> (tmpVec.set(player.position).sub(p.position).len2() < Config.PICKUP_HITBOXRADIUS2)).findFirst();
            if(pickup.isPresent())
                equipAbility(player, pickup.get());
        }
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
        
        if (oldName != null) {spawnPickup(pickup.position, oldName);}
        return didthings;
    }
    
    private boolean equipUtilityAbility(Player player, AbilityPickup pickup) {
        if (!canCollectUtility) {return false;}
        String name = pickup.abilityName;
        String oldName;
        boolean didthings ;
        
        switch (name) {
            case "StandardHeal":    oldName = Main.MAIN_SINGLETON.game.abilityFACTORY.collectStandardHeal(player.connectionID);
                                    didthings = true;
                                    break;
            default: return false;
        }
        
        swapUtilityCooldown();
        
        if (oldName != null) {spawnPickup(pickup.position, oldName);}
        return didthings;
    }
    
    private void disposePickup(AbilityPickup pickup) {
        pickups.remove(pickup);

        DisposePickup dp = new DisposePickup();
        dp.id = pickup.id;
        Main.MAIN_SINGLETON.server.sendToAllTCP(dp);
    }
    
    /** This is just a placehoder. Will be later replaced by a propper System that spawns stuff where it belongs*/
    private void spawnRandomPickupsOnRandomPositionsAtAlmostRandomTimesBoii(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (pickups.size() <= 5) {
                    String name = Math.random()<0.5f?"Fireball":"StandardHeal";
                    spawnPickup(new Vector2((float) Math.random() * 400, (float) Math.random() * 400), name);
                }
            }
        }, 0, 5000);
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
