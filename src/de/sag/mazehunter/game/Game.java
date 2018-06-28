package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.movement.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.map.World;
import de.sag.mazehunter.game.player.LifeSystem;
import de.sag.mazehunter.game.player.movement.MovementSpeedListener;
import de.sag.mazehunter.game.player.abilities.DashListener;
import de.sag.mazehunter.game.player.abilities.StandardHealListener;

/**
 *
 * @author sreis
 */
public class Game {
    
    public Player player[];
    
    public World world;
    
    public LifeSystem lifeSystem;

    public Game() {
        player = new Player[4];
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        createAbilityListeners();
        Main.MAIN_SINGLETON.server.addListener(new MovementListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementSpeedListener());
        
        world = new World(25, 50);
        world.makeMap(true, false, false, true, true, true, false, true, true, false, false, true, true, true, true, true, false, true, true, true, true, false, true, false, false, true, true, false, true, true, false, true, false, true, true, true);
        lifeSystem = new LifeSystem();
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
        if (lifeSystem.checkDeath(player)) {
            //exit();
            System.exit(0);
        }
        
    }
    
    public void getPlayer(int netID){
        
    }
    

    public void exit() {
    }
}
