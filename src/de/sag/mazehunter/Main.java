package de.sag.mazehunter;

/**
 * @author sreis
 */
public class Main {

    public final GameServer server;
    public Game game; 
    
    public static Main MAIN_SINGLETON;
    
    public Main() {
        MAIN_SINGLETON = this;
        
        server = new GameServer();
        game = new Game();
    }
    
    public void start(){
        server.startServer();
        
        while (true) {
            //TODO: deltaTime berechnen
            update(0);
        }
    }
    
    public void update(float delta){
        game.update(delta);
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
