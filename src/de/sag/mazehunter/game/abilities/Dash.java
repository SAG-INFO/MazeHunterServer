/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.abilities;

/**
 *
 * @author Karl Huber
 * 
 * A Bunch of Values to balance the dash ability.
 */
public class Dash extends Ability {
    
    public String type = "mobility";
    public String name = "dash";
    
    //range in tiles
    public static int RANGE = 5;
    
    //cooldown of the dash in seconds
    public static int COOLDOWN = 10;
}