package org.cubeville.portal.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterDouble;
import org.cubeville.commons.commands.CommandParameterVector;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.portal.Portal;
import org.cubeville.portal.PortalManager;
import org.cubeville.portal.actions.SpreadTeleport;

public class PortalSetSpreadTeleport extends Command
{
    public PortalSetSpreadTeleport() {
        super("set spreadteleport");
        addBaseParameter(new CommandParameterPortal());
        addParameter("area", true, new CommandParameterVector());
        addParameter("radius", true, new CommandParameterDouble());
        addFlag("borderonly");
        addFlag("centeryaw");
    }

    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        Portal portal = (Portal) baseParameters.get(0);

        Location location = player.getLocation();
        Double radius = (Double) parameters.get("radius");
        Vector area = (Vector) parameters.get("area");

        if(radius != null && area != null) throw new CommandExecutionException("Can only have one of area / radius parameters!");

        portal.addAction(new SpreadTeleport(player.getLocation(), area, radius, flags.contains("borderonly"), flags.contains("centeryaw")));
        PortalManager.getInstance().save();

        return new CommandResponse("&aSpread teleport set.");
    }

}
