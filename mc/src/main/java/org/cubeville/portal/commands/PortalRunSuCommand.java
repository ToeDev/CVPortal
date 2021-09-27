package org.cubeville.portal.commands;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.cubeville.commons.utils.ColorUtils;
import org.cubeville.commons.commands.BaseCommand;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.portal.Portal;

public class PortalRunSuCommand extends BaseCommand
{
    public PortalRunSuCommand() {
        super("runsucommand");
        setPermission("cvportal.runsucommand");
        addBaseParameter(new CommandParameterPortal());
        addBaseParameter(new CommandParameterString());
    }

    @SuppressWarnings("unchecked")
    public CommandResponse execute(CommandSender sender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        Portal portal = (Portal) baseParameters.get(0);
        String command = (String) baseParameters.get(1);

        Collection<Player> players = (Collection<Player>) Bukkit.getServer().getOnlinePlayers();
        portal.runSuCommand(players, command);

        return new CommandResponse("&aCommand executed.");
    }
}
