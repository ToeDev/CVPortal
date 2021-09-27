package org.cubeville.portal.actions;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;

@SerializableAs("SpreadTeleport")
public class SpreadTeleport implements Action
{
    Location location;
    Vector area;
    Double radius;
    boolean borderOnly;
    boolean centerYaw;
    
    public SpreadTeleport(Location location, Vector area, Double radius, boolean borderOnly, boolean centerYaw) {
        this.location = location;
        this.area = area;
        this.radius = radius;
        this.borderOnly = borderOnly;
        this.centerYaw = centerYaw;
    }

    public SpreadTeleport(Map<String, Object> config) {
        location = (Location) config.get("location");
        area = (Vector) config.get("area");
        radius = (Double) config.get("radius");
        if(config.get("borderOnly") != null) {
            borderOnly = (Boolean) config.get("borderOnly");
        }
        else {
            borderOnly = true;
        }
        if(config.get("centerYaw") != null) {
            centerYaw = (Boolean) config.get("centerYaw");
        }
        else {
            centerYaw = true;
        }
    }

    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("location", location);
        ret.put("area", area);
        ret.put("radius", radius);
        ret.put("borderOnly", borderOnly);
        ret.put("centerYaw", centerYaw);
        return ret;
    }

    public void execute(Player player) {
        java.util.Random rnd = new java.util.Random();
        Location targetLocation = location.clone();
        Vector center = null;
        if(area != null) {
            center = new Vector(location.getX() + area.getX() / 2,
                                location.getY() + 1.5,
                                location.getZ() + area.getZ() / 2);
            double xrnd, zrnd;
            if(borderOnly) {
                double rmul = rnd.nextDouble();
                double omul = rnd.nextDouble() >= 0.5 ? 1.0 : 0.0;
                if(rnd.nextDouble() >= 0.5) {
                    xrnd = rmul;
                    zrnd = omul;
                }
                else {
                    xrnd = omul;
                    zrnd = rmul;
                }
            }
            else {
                xrnd = rnd.nextDouble();
                zrnd = rnd.nextDouble();
            }
            targetLocation.setX(targetLocation.getX() + xrnd * area.getX());
            targetLocation.setZ(targetLocation.getZ() + zrnd * area.getZ());
            targetLocation.setY(targetLocation.getY() + rnd.nextDouble() * area.getY());
        }
        else if(radius != null) {
            center = location.toVector();
            center.setY(center.getY() + 1.5);
            double angle = rnd.nextDouble() * 2 * Math.PI;
            double tr = radius;
            if(!borderOnly) tr *= rnd.nextDouble();
            targetLocation.setX(targetLocation.getX() + Math.cos(angle) * tr);
            targetLocation.setZ(targetLocation.getZ() + Math.sin(angle) * tr);
        }
        if(centerYaw) {
            targetLocation.setDirection(center.subtract(targetLocation.toVector()));
        }
        
        player.teleport(targetLocation);
    }

    public String getLongInfo() {
        String ret = " - &bSpread Teleport: " + Math.round(location.getX() * 100) / 100 + "/" + Math.round(location.getY() * 100) / 100 + "/" + Math.round(location.getZ() * 100) / 100;
        if(area != null) {
            ret += " - " + area;
        }
        else if(radius != null) {
            ret += " - " + radius;
        }
        if(borderOnly && centerYaw) {
            ret += " (border only, center yaw)";
        }
        else if(borderOnly) {
            ret += " (border only)";
        }
        else if(centerYaw) {
            ret += " (center yaw)";
        }
        return ret;
    }

    public String getShortInfo() {
        return "STP";
    }

    public boolean isSingular() {
        return true;
    }

}
