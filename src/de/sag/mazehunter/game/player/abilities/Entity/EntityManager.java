/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Entity;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.abilities.entity.DisposeEntity;
import java.util.ArrayList;

/**
 *
 * @author Karl Huber
 */
public class EntityManager {
    
    private int entityID;
    
    public ArrayList<AbilityEntity> entities;
    
    public void disposeEntity(AbilityEntity e) {
        entities.remove(e);
        Main.MAIN_SINGLETON.server.sendToAllTCP(new DisposeEntity(e.entityID));
    }

    public void disposeEntity(int entityID) {
        for (AbilityEntity e : entities) {
            if (e.entityID == entityID) {
                disposeEntity(e);
                return;
            }
        }
    }
    
    public int getNewEntityID() {
        entityID++;
        return entityID;
    }

    public EntityManager() {
        this.entities = new ArrayList<>();
    }
    
    public void update(float delta){
        entities.forEach((entity) -> {
            entity.update(delta);
        });
    }
}
