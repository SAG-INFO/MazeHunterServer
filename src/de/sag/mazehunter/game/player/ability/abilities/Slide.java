/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import static de.sag.mazehunter.Main.MAIN_SINGLETON;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.map.Map;
import de.sag.mazehunter.game.player.ability.CooldownAbility;
import de.sag.mazehunter.server.networkData.abilities.responses.SlideResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author karl.huber
 */
public class Slide extends CooldownAbility{

    protected boolean canUse;
    private final Map map;
    
    private static boolean slideInProgress;
    
    public Slide(int playerId) {
        super(playerId, Config.SLIDE_COOLDOWN);
        canUse = true;
        map = Main.MAIN_SINGLETON.game.world.map;
    }

    public void use(Integer direction) {
        if(ready() && MAIN_SINGLETON.game.world.slideManager.canSlide()){
            startCooldown();
            moveStuff(direction);
        }
    }

    private void moveStuff(int direction){
        Vector2 playerPosition = Main.MAIN_SINGLETON.game.getPlayer(connectionID).mc.position;

        int row = 0;
        switch (direction) {
            case 1:
                row = map.translateCoordinateToBlock(playerPosition.x);
                MAIN_SINGLETON.game.world.slideManager.moveRowUp(row);
                break;
            case 2:
                row = map.translateCoordinateToBlock(playerPosition.y);
                MAIN_SINGLETON.game.world.slideManager.moveRowRight(row);
                break;
            case 3:
                row = map.translateCoordinateToBlock(playerPosition.x);
                MAIN_SINGLETON.game.world.slideManager.moveRowDown(row);
                break;
            case 4:
                row = map.translateCoordinateToBlock(playerPosition.y);
                MAIN_SINGLETON.game.world.slideManager.moveRowLeft(row);
                break;
            default:
                throw new RuntimeException("direction is bullshit");
        }

        Main.MAIN_SINGLETON.server.sendToAllTCP(new SlideResponse(direction, row));
    }
}
