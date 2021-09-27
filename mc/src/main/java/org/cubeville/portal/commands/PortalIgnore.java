package org.cubeville.portal.commands;

import org.bukkit.Player;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterOnlinePlayer;

import org.cubeville.portal.PortalManager;

public class PortalIgnore extends Command
{
    public PortalIgnore() {
	super("ignore");
	addBaseParameter(new CommandParameterOnlinePlayer());
    }

    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

	Player player = (Player) baseParameters.get(0);
	PortalManager.getInstance().ignorePlayer(player.getUUID());

	return new CommandResponse("&aIgnoring player " + player.getName());
    }

}
