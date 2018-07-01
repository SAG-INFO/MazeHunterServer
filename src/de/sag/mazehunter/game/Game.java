package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.movement.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.ability.AbilityListener;



import de.sag.mazehunter.game.player.movement.MovementSpeedListener;

import de.sag.mazehunter.game.player.ability.FACTORY;
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
        System.out.println("gthtr");
        
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementSpeedListener());
        Main.MAIN_SINGLETON.server.addListener(new AbilityListener());
        
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
