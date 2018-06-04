package de.sag.mazehunter.server;

import com.esotericsoftware.kryonet.Server;
import de.sag.mazehunter.server.networkData.ConnectResponse;
import de.sag.mazehunter.server.networkData.HealthUpdate;
import de.sag.mazehunter.server.networkData.LobbyUpdate;
import de.sag.mazehunter.server.networkData.MovementRequest;
import de.sag.mazehunter.server.networkData.MovementResponse;
import de.sag.mazehunter.server.networkData.MovementSpeedRequest;
import de.sag.mazehunter.server.networkData.PlayerLobby;
import de.sag.mazehunter.server.networkData.StartGameRequest;
import de.sag.mazehunter.server.networkData.StartGameResponse;
import de.sag.mazehunter.server.networkData.abilities.DashRequest;
import de.sag.mazehunter.server.networkData.abilities.DashResponse;
import de.sag.mazehunter.server.networkData.abilities.DisposePickup;
import de.sag.mazehunter.server.networkData.abilities.EquipAbility;
import de.sag.mazehunter.server.networkData.abilities.SpawnPickup;
import de.sag.mazehunter.server.networkData.abilities.StandardHealRequest;
import de.sag.mazehunter.server.networkData.abilities.StandardHealResponse;
import de.sag.mazehunter.server.networkData.configs.PushConfig;
import de.sag.mazehunter.utils.Vector2;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sreis
 */
public class GameServer extends Server{
    
    private final int TIMEOUT = 5000;
    private final int TCP_PORT = 54777;
    private final int UDP_PORT = 54779;

    public GameServer() {
        registerClasses();
    }
    
    public void startServer(){
        super.start();
        try {
            super.bind(TCP_PORT, UDP_PORT);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private void registerClasses(){
        //general Stuff
        getKryo().register(Vector2.class);
        getKryo().register(ArrayList.class);
        
        //Lobby Stuff
        getKryo().register(ConnectResponse.class);
        getKryo().register(PlayerLobby.class);
        getKryo().register(LobbyUpdate.class);
        getKryo().register(StartGameRequest.class);
        getKryo().register(StartGameResponse.class);
        
        //Movement Stuff
        getKryo().register(MovementRequest.class);
        getKryo().register(MovementResponse.class);
        getKryo().register(MovementSpeedRequest.class);
        
        //Config Stuff
        getKryo().register(PushConfig.class);
        
        //Ability Stuff
        getKryo().register(HealthUpdate.class);
        
        //Dash
        getKryo().register(DashRequest.class);
        getKryo().register(DashResponse.class);

        //StandardHeal
        getKryo().register(StandardHealRequest.class);
        getKryo().register(StandardHealResponse.class);
        
        //Blizzard
        
        //Pickups
        getKryo().register(SpawnPickup.class);
        getKryo().register(DisposePickup.class);
        getKryo().register(EquipAbility.class);
    }
}
