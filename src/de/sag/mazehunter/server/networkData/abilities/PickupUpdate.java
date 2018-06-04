package de.sag.mazehunter.server.networkData.abilities;

import de.sag.mazehunter.utils.Vector2;
import java.util.ArrayList;

/**
 *
 * @author sreis
 */
public class PickupUpdate {

    public final ArrayList<PickupData> datas = new ArrayList<>();

    public static class PickupData {

        public Vector2 position;
        public String name;
    }
}
