package de.sag.mazehunter.server.networkData.lobby;

/**
 *
 * @author sreis
 */
public class PlayerLobby {

    public int id;
    public String name;
    public int slot = -1;
    //TODO Fänger|läufer zeug hier rein!
    
    public PlayerLobby() {
    }
    
    public PlayerLobby(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
}
