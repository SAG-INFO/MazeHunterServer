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
import de.sag.mazehunter.server.networkData.abilities.requests.AttackRequest;
import de.sag.mazehunter.server.networkData.abilities.requests.MobilityRequest;
import de.sag.mazehunter.server.networkData.abilities.responses.DashResponse;
import de.sag.mazehunter.server.networkData.abilities.pickups.DisposePickup;
import de.sag.mazehunter.server.networkData.abilities.pickups.EquipAbility;
import de.sag.mazehunter.server.networkData.abilities.pickups.SpawnPickup;
import de.sag.mazehunter.server.networkData.abilities.entity.DisposeEntity;
import de.sag.mazehunter.server.networkData.abilities.requests.SlideRequest;
import de.sag.mazehunter.server.networkData.abilities.responses.FireballResponse;
import de.sag.mazehunter.server.networkData.abilities.responses.StandardHealResponse;
import de.sag.mazehunter.server.networkData.abilities.requests.UtilityRequest;
import de.sag.mazehunter.server.networkData.abilities.responses.FireballShootResponse;
import de.sag.mazehunter.server.networkData.abilities.responses.SlideResponse;
import de.sag.mazehunter.server.networkData.abilities.responses.StunArrowResponse;
import de.sag.mazehunter.server.networkData.abilities.responses.StunArrowShootResponse;
import de.sag.mazehunter.server.networkData.abilities.responses.TrapResponse;
import de.sag.mazehunter.server.networkData.abilities.responses.TrapShootResponse;
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
        
        //Ability Requests
        getKryo().register(AttackRequest.class);
        getKryo().register(UtilityRequest.class);
        getKryo().register(MobilityRequest.class);
        getKryo().register(SlideRequest.class);
        
        //AbilityResponses
        getKryo().register(DashResponse.class);
        getKryo().register(StandardHealResponse.class);
        getKryo().register(FireballResponse.class);
        getKryo().register(FireballShootResponse.class);
        getKryo().register(StunArrowResponse.class);
        getKryo().register(StunArrowShootResponse.class);
        getKryo().register(SlideResponse.class);
        getKryo().register(TrapResponse.class);
        getKryo().register(TrapShootResponse.class);
        
        //Entity Stuff
        getKryo().register(DisposeEntity.class);
        
        //Pickups
        getKryo().register(SpawnPickup.class);
        getKryo().register(DisposePickup.class);
        getKryo().register(EquipAbility.class);
        

    }
}
