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
import java.util.ArrayList;
import java.util.Optional;


/**
 *
 * @author sreis
 */
public class Game {

    public ArrayList<Player> players;
    
    public World world;

    public FACTORY abilityFACTORY;

    public Game() {
        players = new ArrayList<>();
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
        for (Player p : players) {
            p.update(delta);
        }
        if(world != null)
            world.update(delta);
    }
        

    public void exit() {
        world = null;
        players.clear();
    }
    
    public Player getPlayer(int connectionID){
        Optional<Player> player = players.stream().filter((Player p) -> {return p.connectionID == connectionID;}).findAny();
        if(!player.isPresent()) throw new RuntimeException("Player not found: "+connectionID);
        return player.get();
    }
}
