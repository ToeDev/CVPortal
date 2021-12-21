package org.cubeville.portal.actions;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;

@SerializableAs("ApplyPotionEffect")
public class ApplyPotionEffect implements Action
{
    PotionEffectType type;
    int duration;
    int amplifier;
    boolean particles;
    
    public ApplyPotionEffect(PotionEffectType type, int duration, int amplifier, boolean particles) {
        this.type = type;
        this.duration = duration;
        this.amplifier = amplifier;
	this.particles = particles;
    }

    public ApplyPotionEffect(Map<String, Object> config) {
        type = PotionEffectType.getByName((String) config.get("type"));
        duration = (int) config.get("duration");
        amplifier = (int) config.get("amplifier");
	if(config.get("particles") == null) {
	    particles = true;
	}
	else {
	    particles = (boolean) config.get("particles");
	}
    }

    public Map<String, Object> serialize() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("type", type.getName());
        ret.put("duration", duration);
        ret.put("amplifier", amplifier);
	ret.put("particles", particles);
        return ret;
    }

    public void execute(Player player) {
        player.addPotionEffect(new PotionEffect(type, duration * 20, amplifier - 1, false, particles));
    }

    public String getLongInfo() {
        return " - &bPotion Effect: &r" + type.getName() + " &r" + amplifier + " (" + duration + "s" + (particles == false ? ", no particles" : "") + ")";
    }
    
    public String getShortInfo() {
        return "PE";
    }
    
    public boolean isSingular() {
        return false;
    }
    
}
