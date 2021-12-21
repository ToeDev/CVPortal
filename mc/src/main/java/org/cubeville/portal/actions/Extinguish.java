package org.cubeville.portal.actions;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("Extinguish")
public class Extinguish implements Action
{
    public Extinguish() {}

    public Extinguish(Map<String, Object> config) {
    }

    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<>();
        return ret;
    }

    @SuppressWarnings("deprecation")
    public void execute(Player player) {
        player.setFireTicks(0);
    }

    public String getLongInfo() {
        return " - &bExtinguish";
    }
    
    public String getShortInfo() {
        return "E";
    }

    public boolean isSingular() {
        return true;
    }    
    
}
