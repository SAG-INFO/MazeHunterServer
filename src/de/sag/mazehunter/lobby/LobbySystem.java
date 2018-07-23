package de.sag.mazehunter.lobby;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.ConnectResponse;
import de.sag.mazehunter.server.networkData.lobby.LobbyUpdate;
import de.sag.mazehunter.server.networkData.lobby.PlayerLobby;
import de.sag.mazehunter.server.networkData.StartGameRequest;
import de.sag.mazehunter.server.networkData.lobby.OccupySlotRequest;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author sreis
 */
public class LobbySystem extends Listener {

    @Override
    public void connected(Connection connection) {
        boolean canJoin = sendJoinResponse(connection.getID());

        if (canJoin) {
            joinPlayer(connection.getID());
        }
    }

    /**
     * @return if the player can sucessfully
     */
    private boolean sendJoinResponse(int connectionID) {
        ConnectResponse connect = new ConnectResponse();

        if (Main.MAIN_SINGLETON.state == Main.STATE_INGAME) {
            connect.connectionAccepted = false;
            connect.msg = "Game already in Progess :^{";
        } else {
            connect.connectionAccepted = true;
            connect.msg = "Welcome to the Lobby :^)";
        }

        Main.MAIN_SINGLETON.server.sendToUDP(connectionID, connect);
        return connect.connectionAccepted;
    }

    private void joinPlayer(int connectionID) {
        //update player list
        String name = "player_" + Main.MAIN_SINGLETON.lobby.players.size();
        Main.MAIN_SINGLETON.lobby.players.add(new PlayerLobby(connectionID, name));

        //send lobby update with short delay
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                sendLobbyUpdate();
            }
        }, 200);
    }

    @Override
    public void disconnected(Connection connection) {
        Main.MAIN_SINGLETON.lobby.players.removeIf(e -> (e.id == connection.getID()));
        sendLobbyUpdate();
    }

    private void sendLobbyUpdate() {
        LobbyUpdate lbb = new LobbyUpdate();
        lbb.nutten = new ArrayList<>(Main.MAIN_SINGLETON.lobby.players);
        Main.MAIN_SINGLETON.server.sendToAllUDP(lbb);
    }

    private void occupySlot(int id, int slotIndex) {
        ArrayList<PlayerLobby> players = Main.MAIN_SINGLETON.lobby.players;

        for (PlayerLobby player : players) {
            if (player.id == id) {
                if (player.slot == slotIndex) {     //leave Slot
                    player.slot = -1;
                }
                else if(!slotOccupied(slotIndex)){  //occupy Slot
                    player.slot = slotIndex;
                }
                
                sendLobbyUpdate();
                return;
            }
        }
    }

    private boolean slotOccupied(int slotIndex) {
        for (PlayerLobby p : Main.MAIN_SINGLETON.lobby.players) {
            if (p.slot == slotIndex) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof StartGameRequest) {
            Main.MAIN_SINGLETON.startGame();
        } else if (object instanceof OccupySlotRequest) {
            OccupySlotRequest req = (OccupySlotRequest) object;
            occupySlot(connection.getID(), req.index);
        }
    }
}
