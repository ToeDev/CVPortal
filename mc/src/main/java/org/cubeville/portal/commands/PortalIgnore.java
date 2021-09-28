package org.cubeville.portal.commands;

import org.bukkit.entity.Player;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterOnlinePlayer;

import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.portal.PortalManager;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PortalIgnore extends Command
{
    public PortalIgnore() {
	super("ignore");
	addBaseParameter(new CommandParameterOnlinePlayer());
    }

    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

	Player p = (Player) baseParameters.get(0);
	PortalManager.getInstance().ignorePlayer(p.getUniqueId());

	return new CommandResponse("&aIgnoring player " + player.getName());
    }

}
