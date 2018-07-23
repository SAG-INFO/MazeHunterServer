package de.sag.mazehunter.game;

import de.sag.mazehunter.game.player.movement.MovementListener;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.map.Block;
import de.sag.mazehunter.game.map.Map;
import de.sag.mazehunter.game.map.Tile;
import de.sag.mazehunter.game.player.Hunter;
import de.sag.mazehunter.game.player.Runner;
import de.sag.mazehunter.game.player.ability.AbilityListener;
import de.sag.mazehunter.game.player.LifeSystem;

import de.sag.mazehunter.game.player.movement.MovementSpeedListener;

import de.sag.mazehunter.server.networkData.SpawnPlayer;
import de.sag.mazehunter.utils.Vector2;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author sreis
 */
public class Game {

    public CopyOnWriteArrayList<Player> players;

    public World world;
    public LifeSystem lf;
    public Game() {
        lf = new LifeSystem();
        players = new CopyOnWriteArrayList<>();
    }

    public void spawnPlayer(int id, String name, boolean hunter) {
        Player player = hunter?new Hunter(name, id):new Runner(name, id);
        player.mc.position.set(spawnPosition());
        players.add(player);
        
        SpawnPlayer data = new SpawnPlayer();
        data.name = name;
        data.id = id;
        data.hunter = hunter;
        data.position.set(player.mc.position);
        Main.MAIN_SINGLETON.server.sendToAllTCP(data);
    }
    
    public Vector2 spawnPosition() {
        Tile spawntile = world.map.getRandomBlock().tilelist[1][1];
        Vector2 result = new Vector2();
        if(spawntile.open) {
            return result.set(spawntile.getPixelX(), spawntile.getPixelY()).add(Map.center/2, Map.center/2);
        }else {
            return spawnPosition();
        }
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
        if (lf.checkDeath(players)) {
            Main.MAIN_SINGLETON.exitGame();
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
