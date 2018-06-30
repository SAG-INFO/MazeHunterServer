package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.movement.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;

import de.sag.mazehunter.game.map.Map;

import de.sag.mazehunter.game.player.movement.MovementSpeedListener;

import de.sag.mazehunter.game.player.abilities.Attack.AttackListener;
import de.sag.mazehunter.game.player.abilities.Entity.EntityManager;
import de.sag.mazehunter.game.player.abilities.FACTORY;
import de.sag.mazehunter.game.player.abilities.Mobility.MobilityListener;
import de.sag.mazehunter.game.player.abilities.PickupManager;
import de.sag.mazehunter.game.player.abilities.SlideStuff.SlideListener;
import de.sag.mazehunter.game.player.abilities.Utility.UtilityListener;


/**
 *
 * @author sreis
 */
public class Game {

    public PickupManager pickupManager;
    public EntityManager entityManager;

    public Player player[];
    
    public Map world;

    public FACTORY abilityFACTORY;

    public Game() {
        player = new Player[4];
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        
        Main.MAIN_SINGLETON.server.addListener(new MovementListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementSpeedListener());
        

        world = new Map(25, 50);
        world.makeMap(true, false, false, true, true, true, false, true, true, false, false, true, true, true, true, true, false, true, true, true, true, false, true, false, false, true, true, false, true, true, false, true, false, true, true, true);

        Main.MAIN_SINGLETON.server.addListener(new MobilityListener());
        Main.MAIN_SINGLETON.server.addListener(new UtilityListener());
        Main.MAIN_SINGLETON.server.addListener(new AttackListener());
        Main.MAIN_SINGLETON.server.addListener(new SlideListener());
        
        abilityFACTORY = new FACTORY();
        pickupManager = new PickupManager();
        entityManager = new EntityManager();
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
        entityManager.entities.stream().forEach((entity) -> {entity.update(delta);});
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
