package de.sag.mazehunter;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import static de.sag.mazehunter.Main.MAIN_SINGLETON;
import de.sag.mazehunter.networkData.ConnectResponse;

/**
 *
 * @author sreis
 */
public class ConnectSystem extends Listener{

    @Override
    public void connected(Connection connection) {
        ConnectResponse connect = new ConnectResponse();
        connect.connectionAccepted = true;
        MAIN_SINGLETON.server.sendToAllUDP(connect);
    }

    @Override
    public void disconnected(Connection connection) {
    }
}
