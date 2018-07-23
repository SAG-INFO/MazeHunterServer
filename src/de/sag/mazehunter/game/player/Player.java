/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import de.sag.mazehunter.game.player.ability.ActiveAbility;
import de.sag.mazehunter.game.player.ability.abilities.Dash;
import de.sag.mazehunter.game.player.ability.abilities.Fireball;
import de.sag.mazehunter.game.player.ability.abilities.NoAbility;
import de.sag.mazehunter.game.player.ability.abilities.Satan;
import de.sag.mazehunter.game.player.ability.abilities.Slide;
import de.sag.mazehunter.game.player.ability.abilities.SpeedBoost;
import de.sag.mazehunter.game.player.ability.abilities.StunArrow;
import de.sag.mazehunter.game.player.ability.abilities.Teleport;
import de.sag.mazehunter.game.player.movement.MovementComponent;

/**
 *
 * @author g.duennweber
 */
public class Player {

    public final String name;
    public int connectionID;
    
    public final MovementComponent mc;
    
    public ActiveAbility activeAbility; 
    public Slide slide; 
    public Dash dash;
    
    public Player(String name, int id) {
        this.name = name;
        this.connectionID = id;
        
        activeAbility = new NoAbility(id);
        slide = new Slide(id);
        dash = new Dash(id);
        
        mc = new MovementComponent(this);
    }

    public void update(float delta) {
        mc.update(delta);
        
        activeAbility.update(delta);
        slide.update(delta);
        dash.update(delta);
    }
}