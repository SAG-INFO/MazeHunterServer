package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.DisposePickup;
import de.sag.mazehunter.server.networkData.abilities.EquipAbility;
import de.sag.mazehunter.server.networkData.abilities.SpawnPickup;
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

    private static final float PICKUP_RADIUS = 60;

    private final Vector2 tmpVec = new Vector2();

    private int idr=0;
    
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
        for (Player player : Main.MAIN_SINGLETON.game.players) {
            if(player == null)
                continue;
            
            //Is it a boy or a girl? Its a Lambda-Expression! I <3 Java8
            Optional<AbilityPickup> pickup = pickups.stream().filter((p) -> (tmpVec.set(player.position).sub(p.position).len2() < PICKUP_RADIUS)).findFirst();
            if(pickup.isPresent())
                equipAbility(player, pickup.get());
        }
    }
    
    private void equipAbility(Player player, AbilityPickup pickup) {
        disposePickup(pickup);
        EquipAbility ea = new EquipAbility();
        ea.abilityName = pickup.abilityName;
        Main.MAIN_SINGLETON.server.sendToAllTCP(ea);
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
                    String name = Math.random()<0.5f?"teleport":"arrow";
                    spawnPickup(new Vector2((float) Math.random() * 400, (float) Math.random() * 400), name);
                }
            }
        }, 0, 5000);
    }
}
