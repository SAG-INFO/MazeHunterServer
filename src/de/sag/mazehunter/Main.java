package de.sag.mazehunter;

import de.sag.mazehunter.server.GameServer;
import de.sag.mazehunter.game.Game;
import de.sag.mazehunter.lobby.Lobby;
import de.sag.mazehunter.server.networkData.StartGameResponse;

/**
 * Branch Test
 * @author sreis
 */
public class Main {

    public static Main MAIN_SINGLETON;
    
    public final GameServer server;
    public Game game; 
    public Lobby lobby; 
    
    public static final int STATE_LOBBY = 1;
    public static final int STATE_INGAME = 2;
    
    public int state = STATE_LOBBY;
    
    public Main() {
        MAIN_SINGLETON = this;
        
        server = new GameServer();
        
        lobby = new Lobby();
        game = new Game();
    }
    
    public void start(){
        server.startServer();
        
        while (true) {
            //TODO: deltaTime berechnen
            update(0);
        }
    }
    
    public void update(float delta){
        if(state == STATE_LOBBY)
            lobby.update(delta);
        if(state == STATE_INGAME)
            game.update(delta);
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void startGame() {
        this.state = STATE_INGAME;
        StartGameResponse startInfo = new StartGameResponse();
        this.server.sendToAllUDP(startInfo);
        game.start();
    }

    public void exitGame() {
        game.exit();
        state = STATE_LOBBY;
    }
}
