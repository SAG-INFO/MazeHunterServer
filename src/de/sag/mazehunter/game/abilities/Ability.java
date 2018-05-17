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
 * available types
 * type = "utility" eg. heal/trap keybind is SPACE key
 * type = "skillshot" eg. damage projectile keybing is leftmouse
 * type = "mobility" eg. dash keybind is SHIFT
 */
public abstract class Ability {
    String name;
    String type;
}