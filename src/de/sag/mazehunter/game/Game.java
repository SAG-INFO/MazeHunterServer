package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.MovementSpeedListener;

import de.sag.mazehunter.game.player.abilities.Attack.AttackListener;
import de.sag.mazehunter.game.player.abilities.projectiles.Projectile;
import de.sag.mazehunter.game.player.abilities.projectiles.ProjectileManager;
import de.sag.mazehunter.game.player.abilities.FACTORY;
import de.sag.mazehunter.game.player.abilities.Mobility.MobilityListener;
import de.sag.mazehunter.game.player.abilities.PickupManager;
import de.sag.mazehunter.game.player.abilities.Utility.UtilityListener;

/**
 *
 * @author sreis
 */
public class Game {

    public PickupManager pickupManager;
    public ProjectileManager projectileManager;

    public Player player[];
    public FACTORY abilityFACTORY;

    public Game() {
        player = new Player[4];
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        
        Main.MAIN_SINGLETON.server.addListener(new MovementListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementSpeedListener());
        
        Main.MAIN_SINGLETON.server.addListener(new MobilityListener());
        Main.MAIN_SINGLETON.server.addListener(new UtilityListener());
        Main.MAIN_SINGLETON.server.addListener(new AttackListener());
        
        abilityFACTORY = new FACTORY();
        pickupManager = new PickupManager();
        projectileManager = new ProjectileManager();
    }

    public void start(){
    }
    
    public void update(float delta){
        for (Player p : player) {
            if(p == null)
                continue;
            p.update(delta);
        }
        pickupManager.update();
        projectileManager.updateAll(delta);
        
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
