package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.configs.PushConfig;
import de.sag.mazehunter.game.player.MovementSpeedListener;
import de.sag.mazehunter.game.player.abilities.DashListener;
import de.sag.mazehunter.game.player.abilities.StandardHealListener;
import de.sag.mazehunter.server.networkData.HealthUpdate;

/**
 *
 * @author sreis
 */
public class Game {
    
    public Player player[];

    public Game() {
        player = new Player[4];
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        createAbilityListeners();
        Main.MAIN_SINGLETON.server.addListener(new MovementListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementSpeedListener());
    }
    
    public void createAbilityListeners() {
        Main.MAIN_SINGLETON.server.addListener(new DashListener());
        Main.MAIN_SINGLETON.server.addListener(new StandardHealListener());
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
