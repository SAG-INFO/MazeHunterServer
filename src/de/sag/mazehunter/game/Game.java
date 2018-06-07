package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.MovementSpeedListener;
import de.sag.mazehunter.game.player.abilities.PickupManager;

/**
 *
 * @author sreis
 */
public class Game {
    
    public Player[] player;
    public Outputer outputer;

    public PickupManager pickupManager;
    
    public Game() {
        player = new Player[4];
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        outputer = new Outputer();
        createAbilityListeners();
        Main.MAIN_SINGLETON.server.addListener(new MovementListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementSpeedListener());
        
        pickupManager = new PickupManager();
    }
    
    public void createAbilityListeners() {
    }
    
    public void start(){
    }
    
    public void update(float delta){
        pickupManager.update();
        for (int i = 0; i < 4; i++) {
            if(player[i] == null)
                continue;
            player[i].update(delta);
        }
    }
    
    public void exit() {
    }
    
    public Player getPlayer(int connectionID){
        for (Player player : player) {
            if(player != null && player.connectionID == connectionID)
                return player;
        }
        
        throw new RuntimeException("Player with ConnectionID "+connectionID+" not found :/");
    }
}
