package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.MovementSpeedListener;
import de.sag.mazehunter.game.player.abilities.Attack.AttackListener;
import de.sag.mazehunter.game.player.abilities.Attack.PickupCollector;
import de.sag.mazehunter.game.player.abilities.Mobility.MobilityListener;

/**
 *
 * @author sreis
 */
public class Game {
    
    public Player player[];
    public PickupCollector pickupCollector;
    public Game() {
        player = new Player[4];
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        createAbilityListeners();
        Main.MAIN_SINGLETON.server.addListener(new MovementListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementSpeedListener());
        
        pickupCollector = new PickupCollector();
    }
    
    public void createAbilityListeners() {
        Main.MAIN_SINGLETON.server.addListener(new MobilityListener());
        Main.MAIN_SINGLETON.server.addListener(new AttackListener());
    }
    
    public void start(){
    }
    
    public void update(float delta){
        for (int i = 0; i < 4; i++) {
            if(player[i] == null)
                continue;
            player[i].update(delta);
        }
    }
    
    

    public void exit() {
    }
}
