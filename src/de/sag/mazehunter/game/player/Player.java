/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.ability.Ability;
import de.sag.mazehunter.game.player.ability.abilities.Dash;
import de.sag.mazehunter.game.player.ability.abilities.Fireball;
import de.sag.mazehunter.game.player.ability.abilities.Slide;
import de.sag.mazehunter.game.player.movement.MovementComponent;
import de.sag.mazehunter.server.networkData.HealthUpdate;

/**
 *
 * @author g.duennweber
 */
public class Player {

    public int connectionID;
    
    public final MovementComponent mc;
    
    public Ability ability = new Fireball(); 
    public Ability slide = new Slide(); 
    public Ability dash = new Dash(); 
    
    public Status status = new Status();

    public Player(int id) {
        connectionID = id;
        mc = new MovementComponent(this);
    }

    public void update(float delta) {
        mc.update(delta);
    }
}