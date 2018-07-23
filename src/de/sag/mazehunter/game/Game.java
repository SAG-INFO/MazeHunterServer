package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.movement.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Hunter;
import de.sag.mazehunter.game.player.Runner;
import de.sag.mazehunter.game.player.ability.AbilityListener;

import de.sag.mazehunter.game.player.movement.MovementSpeedListener;

import de.sag.mazehunter.server.networkData.SpawnPlayer;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author sreis
 */
public class Game {

    public CopyOnWriteArrayList<Player> players;

    public World world;

    public Game() {
        players = new CopyOnWriteArrayList<>();
    }

    public void spawnPlayer(int id, String name, boolean hunter) {
        Player player = hunter?new Hunter(name, id):new Runner(name, id);
        players.add(player);
        
        SpawnPlayer data = new SpawnPlayer();
        data.name = name;
        data.id = id;
        data.hunter = hunter;
        data.position.set(70, 70);
        Main.MAIN_SINGLETON.server.sendToAllTCP(data);
    }

    public void start() {
        world = new World();

        Main.MAIN_SINGLETON.server.addListener(new DisconnectListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementListener());
        Main.MAIN_SINGLETON.server.addListener(new MovementSpeedListener());
        Main.MAIN_SINGLETON.server.addListener(new AbilityListener());
    }

    public void update(float delta) {
        for (Player p : players) {
            p.update(delta);
        }
        if (world != null) {
            world.update(delta);
        }
    }

    public void exit() {
        world = null;
        players.clear();
    }

    public Player getPlayer(int connectionID) {
        Optional<Player> player = players.stream().filter((Player p) -> {
            return p.connectionID == connectionID;
        }).findAny();
        if (!player.isPresent()) {
            throw new RuntimeException("Player not found: " + connectionID);
        }
        return player.get();
    }
}
