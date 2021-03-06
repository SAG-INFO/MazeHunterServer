package de.sag.mazehunter;

import de.sag.mazehunter.server.GameServer;
import de.sag.mazehunter.game.Game;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.lobby.Lobby;
import de.sag.mazehunter.server.networkData.Gameover;
import de.sag.mazehunter.server.networkData.SpawnPlayer;
import de.sag.mazehunter.server.networkData.lobby.PlayerLobby;
import de.sag.mazehunter.server.networkData.StartGameResponse;
import de.sag.mazehunter.server.networkData.configs.PushConfig;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Branch Test
 * @author sreis
 */
public class Main {

    public static Main MAIN_SINGLETON;
    
    /**maximum framerate. -1 for unlimited FPS.*/
    private static float FPS_CAP = 60;
    
    public final GameServer server;
    public Game game; 
    public Lobby lobby; 
    public PushConfig abilityConfig;
    
    public int state = STATE_LOBBY;
    public static final int STATE_LOBBY = 1;
    public static final int STATE_INGAME = 2;
    
    public Main() {
        MAIN_SINGLETON = this;
        
        server = new GameServer();
        
        lobby = new Lobby();
        game = new Game();
    }
    
    public void start(){
        server.startServer();
        
        long last_time = System.nanoTime();

        while (true) {
            long time = System.nanoTime();
            float delta_time = ((time - last_time) / 1000000000f);
            
            if(FPS_CAP != -1 && delta_time < 1f/FPS_CAP)
                continue;
            
            last_time = time;
            update(delta_time);
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

        game = new Game();
        game.start();
        
        
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                for (PlayerLobby player : lobby.players) {
                    game.spawnPlayer(player.id, player.name, player.slot==0);
                }
                lobby.players.clear();
            }
        }, 1000);
        
    }

    public void exitGame() {
        Gameover msg = new Gameover();
        server.sendToAllTCP(msg);
        game.exit();
        state = STATE_LOBBY;
    }
}
