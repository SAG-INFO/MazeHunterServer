package de.sag.mazehunter.lobby;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.PlayerLobby;
import java.util.ArrayList;

/**
 *
 * @author sreis
 */
public class Lobby {

    public final ArrayList<PlayerLobby> players;
    
    public Lobby() {
        players = new ArrayList<>();
        
        LobbySystem c = new LobbySystem();
        Main.MAIN_SINGLETON.server.addListener(c);
    }
    
    public void update(float delta){
        
    }
}
