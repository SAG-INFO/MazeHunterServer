package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;

import de.sag.mazehunter.game.map.World;


import de.sag.mazehunter.game.player.movement.MovementSpeedListener;

import de.sag.mazehunter.game.player.abilities.Attack.AttackListener;
import de.sag.mazehunter.game.player.abilities.FACTORY;
import de.sag.mazehunter.game.player.abilities.Mobility.MobilityListener;
import de.sag.mazehunter.game.player.abilities.SlideStuff.SlideListener;
import de.sag.mazehunter.game.player.abilities.Utility.UtilityListener;


/**
 *
 * @author sreis
 */
public class Game {

    public Player player[];
    
    public World world;

    public FACTORY abilityFACTORY;

    public Game() {
        player = new Player[4];
    }

    public void start(){
        world = new World();
        
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementSpeedListener());
        Main.MAIN_SINGLETON.server.addListener(new MobilityListener());
        Main.MAIN_SINGLETON.server.addListener(new UtilityListener());
        Main.MAIN_SINGLETON.server.addListener(new AttackListener());
        Main.MAIN_SINGLETON.server.addListener(new SlideListener());
        
        abilityFACTORY = new FACTORY();
    }
    
    public void update(float delta){
        for (Player p : player) {
            if(p == null)
                continue;
            p.update(delta);
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
