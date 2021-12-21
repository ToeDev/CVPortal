package org.cubeville.portal.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
    
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterPotionEffectType;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.portal.Portal;
import org.cubeville.portal.PortalManager;
import org.cubeville.portal.actions.ApplyPotionEffect;

public class PortalAddPotionEffect extends Command
{
    public PortalAddPotionEffect() {
        super("add potioneffect");
        addBaseParameter(new CommandParameterPortal());
        addBaseParameter(new CommandParameterPotionEffectType());
        addBaseParameter(new CommandParameterInteger());
        addBaseParameter(new CommandParameterInteger());
	addFlag("noparticles");
    }

    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        Portal portal = (Portal) baseParameters.get(0);
        PotionEffectType type = (PotionEffectType) baseParameters.get(1);
        int amplifier = (Integer) baseParameters.get(2);
        int duration = (Integer) baseParameters.get(3);
	boolean particles = !flags.contains("noparticles");
        portal.addAction(new ApplyPotionEffect(type, duration, amplifier, particles));
        PortalManager.getInstance().save();

        return new CommandResponse("&aEffect added.");
    }
}
