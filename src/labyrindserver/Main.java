package labyrindserver;

/**
 *
 * @author sreis
 */
public class Main {

    public static GameServer server;
    
    public Main() {
        server = new GameServer();
    }
    
    public void start(){
        server.startServer();
        
        while (true) {
            //TODO: deltaTime berechnen
            update(0);
        }
    }
    
    public void update(float delta){
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
