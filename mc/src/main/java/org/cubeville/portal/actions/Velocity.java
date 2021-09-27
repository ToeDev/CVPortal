package org.cubeville.portal.actions;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;

@SerializableAs("Velocity")
public class Velocity implements Action
{
    boolean adjustToPlayerDirection;
    Vector velocity;

    public Velocity(Vector velocity) {
        this.velocity = velocity;
        adjustToPlayerDirection = false;
    }

    public Velocity(Vector velocity, boolean adjustToPlayerDirection) {
        this.velocity = velocity;
        this.adjustToPlayerDirection = adjustToPlayerDirection;
    }
    
    public Velocity(Map<String, Object> config) {
        velocity = (Vector) config.get("velocity");
        if(config.get("adjustToPlayerDirection") != null) {
            adjustToPlayerDirection = (Boolean) config.get("adjustToPlayerDirection");
        }
        else {
            adjustToPlayerDirection = false;
        }
    }

    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("velocity", velocity);
        ret.put("adjustToPlayerDirection", adjustToPlayerDirection);
        return ret;
    }

    public void execute(Player player) {
        if(!adjustToPlayerDirection) {
            player.setVelocity(velocity);
        }
        else {
            Vector pl = player.getLocation().getDirection();
            Vector vel = new Vector(pl.getX() * velocity.getX(), 0, pl.getZ() * velocity.getX());
            player.setVelocity(vel);
        }
    }

    public String getLongInfo() {
        return " - &bVelocity: " + velocity;
    }
    
    public String getShortInfo() {
        return "VEL";
    }

    public boolean isSingular() {
        return true;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public boolean isAdjustToPlayerDirection() {
        return adjustToPlayerDirection;
    }

    public void setAdjustToPlayerDirection(boolean adjustToPlayerDirection) {
        this.adjustToPlayerDirection = adjustToPlayerDirection;
    }
}
