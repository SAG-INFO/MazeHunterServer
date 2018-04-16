package de.sag.mazehunter;

import com.esotericsoftware.kryonet.Server;
import de.sag.mazehunter.networkData.Connect;
import java.io.IOException;

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
        super.addListener(new ConnectSystem());
        try {
            super.bind(TCP_PORT, UDP_PORT);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private void registerClasses(){
        getKryo().register(Connect.class);
    }
}
