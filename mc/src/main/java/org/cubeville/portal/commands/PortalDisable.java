package org.cubeville.portal.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

import org.cubeville.commons.commands.BaseCommand;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterInteger;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.commons.utils.ColorUtils;

import org.cubeville.portal.Portal;
import org.cubeville.portal.PortalManager;
import org.cubeville.portal.actions.Title;

public class PortalDisable extends BaseCommand
{
    public PortalDisable() {
	super("disable");
	addBaseParameter(new CommandParameterPortal());
	addFlag("cancel");
	addFlag("silent");
    }

    @Override
    public CommandResponse execute(CommandSender commandSender, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
	throws CommandExecutionException {

	Portal portal = (Portal) baseParameters.get(0);
	boolean cancel = flags.contains("cancel");

	portal.setDisabled(!cancel);

	if(!flags.contains("silent")) {
	    return new CommandResponse("&aPortal " + (cancel ? "enabled." : "disabled."));
	}
	else {
	    return new CommandResponse("");
	}
    }
}
