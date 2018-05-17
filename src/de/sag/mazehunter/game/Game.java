package de.sag.mazehunter.game;

import de.sag.mazehunter.Main;

/**
 *
 * @author sreis
 */
public class Game {
    
    public Player player[];
    public Outputer outputer;

    public Game() {
        player = new Player[4];
        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        outputer = new Outputer();
        Main.MAIN_SINGLETON.server.addListener(new InputListener());
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
