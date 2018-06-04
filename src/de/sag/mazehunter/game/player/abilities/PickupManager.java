package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.PickupUpdate;
import de.sag.mazehunter.utils.Vector2;
import java.util.ArrayList;

/**
 *
 * @author sreis
 */
public class PickupManager {

    public static String ABILITY_DASH = "dash";
    public static String ABILITY_ARROW = "arrow";
    
    public final ArrayList<AbilityPickup> pickups = new ArrayList<>();
    private static final float PICKUP_RADIUS = 3;
    
    private final Vector2 tmpVec = new Vector2();
    
    public void spawnPickup(Vector2 position, String name){
        AbilityPickup pickup = new AbilityPickup(position);
        pickups.add(pickup);
        sendUpdate();
    }
    
    public void equipAbility(Player player, AbilityPickup pickup){
        int id = pickups.indexOf(pickup);
        pickups.remove(pickup);
        sendUpdate();
    }
    
    public void update(){
        for (AbilityPickup pickup : pickups) {
            for (Player player : Main.MAIN_SINGLETON.game.players) {
                if(tmpVec.set(player.position).sub(pickup.position).len2() < PICKUP_RADIUS){
                    equipAbility(player, pickup);
                }
            }
        }
    }
    
    public void sendUpdate(){
        PickupUpdate update = new PickupUpdate();
        for (AbilityPickup pickup : pickups) {
            PickupUpdate.PickupData data = new PickupUpdate.PickupData();
            data.name = pickup.name;
            data.position.set(pickup.position);
            update.datas.add(data);
        }
        Main.MAIN_SINGLETON.server.sendToAllTCP(update);
    }
}
