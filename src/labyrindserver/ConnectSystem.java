package labyrindserver;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import labyrindserver.networkData.Connect;

/**
 *
 * @author sreis
 */
public class ConnectSystem extends Listener{

    @Override
    public void connected(Connection connection) {
        Connect connect = new Connect();
        Main.server.sendToAllUDP(connect);
    }

    @Override
    public void disconnected(Connection connection) {
    }
}
