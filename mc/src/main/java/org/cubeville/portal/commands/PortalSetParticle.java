package org.cubeville.portal.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterDouble;
import org.cubeville.commons.commands.CommandParameterEnum;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.portal.actions.Playsound;

import org.cubeville.portal.Portal;
import org.cubeville.portal.PortalManager;

public class PortalSetParticle extends Command
{
    public PortalSetParticle() {
        super("set particle");
        addBaseParameter(new CommandParameterPortal());
        addBaseParameter(new CommandParameterEnum(Particle.class));
        addBaseParameter(new CommandParameterInteger());
    }

    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        Portal portal = (Portal) baseParameters.get(0);
        Particle particle = (Particle) baseParameters.get(1);
        portal.setParticle(particle, (Integer) baseParameters.get(2));
        PortalManager.getInstance().save();

        return new CommandResponse("&aParticle set.");
    }
}
