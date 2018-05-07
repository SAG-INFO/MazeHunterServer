package de.sag.mazehunter.game;

import de.sag.mazehunter.Main;

/**
 *
 * @author sreis
 */
public class Game {
    
    public Player player[];

    public Game() {
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        Main.MAIN_SINGLETON.server.addListener(new InputListener());
        player = new Player[4];
    }
    
    public void start(){
        
    }
    
    public void update(float delta){
        
    }

    public void exit() {
    }
}
