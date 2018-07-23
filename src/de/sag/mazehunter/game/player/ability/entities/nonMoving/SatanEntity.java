package de.sag.mazehunter.game.player.ability.entities.nonMoving;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.entities.AbilityEntity;
import de.sag.mazehunter.utils.Vector2;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author arein
 */
public class SatanEntity extends AbilityEntity{
    
    public SatanEntity(Vector2 position, int entityID, int connectionID) {
        super(position, entityID, connectionID, 2000);
        
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("pose");
                Main.MAIN_SINGLETON.game.world.entityManager.disposeEntity(SatanEntity.this);
            }
        }, 3000);
    }

    @Override
    protected void playerCollision(Player player) {
        player.mc.slow(0.4f, 2);
    }
}
