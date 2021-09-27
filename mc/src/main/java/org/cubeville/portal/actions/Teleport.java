package org.cubeville.portal.actions;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.configuration.serialization.SerializableAs;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;

import org.cubeville.portal.CVPortal;
    
@SerializableAs("Teleport")
public class Teleport implements Action
{
    Location location;
    boolean bringHorses;
    
    public Teleport(Location location) {
        this.location = location;
        this.bringHorses = false;
    }


    public Teleport(Map<String, Object> config) {
        this.location = (Location) config.get("location");
        if(config.get("bringHorses") != null) {
            this.bringHorses = (Boolean) config.get("bringHorses");
        }
        else {
            bringHorses = false;
        }
    }

    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("location", location);
        ret.put("bringHorses", bringHorses);
        return ret;
    }

    public void execute(Player player) {
        Entity horse = player.getVehicle();
        System.out.println(location);
        if(bringHorses == true && horse instanceof AbstractHorse) {
            horse.eject();
            horse.teleport(location);
            player.teleport(location);
            //horse.setPassenger(player);
            // Runnable runnable = new Runnable() {
            //         public void run() {
            //             horse.setPassenger(player);
            //         }
            //     };
            // Bukkit.getServer().getScheduler().runTaskLater(CVPortal.getInstance(), runnable, 5);
        }
        else {
            player.teleport(location);
        }
    }
    
    public String getLongInfo() {
        return " - &bTeleport: " + location;
    }

    public String getShortInfo() {
        return "TP";
    }

    public boolean isSingular() {
        return true;
    }

    public Location getLocation() {
        return location;
    }

    public void setBringHorses(boolean bringHorses) {
        this.bringHorses = bringHorses;
    }

    public boolean isBringHorses() {
        return bringHorses;
    }
}
