/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.DashResponse;
import de.sag.mazehunter.server.networkData.MovementResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author g.duennweber
 */
public class Outputer {
    public void sendMovementResponse(Vector2 position, Vector2 velocity, int id) {
        MovementResponse mr = new MovementResponse(position, velocity, id);
        Main.MAIN_SINGLETON.server.sendToAllUDP(mr);
    }
    
    public void sendDashResponse(Vector2 position, Vector2 velocity, int id) {
        DashResponse dr = new DashResponse(position, velocity, id);
        Main.MAIN_SINGLETON.server.sendToAllUDP(dr);
    }
}
