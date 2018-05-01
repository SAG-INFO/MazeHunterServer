package de.sag.mazehunter.game;

import de.sag.mazehunter.Main;

/**
 *
 * @author sreis
 */
public class Game {

    public Game() {
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
    }
    
    public void start(){
        
    }
    
    public void update(float delta){
        
    }

    public void exit() {
    }
}
