package org.cubeville.portal.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterEnumeratedString;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.portal.Portal;
import org.cubeville.portal.PortalManager;

public class PortalSetTrigger extends Command
{
    public PortalSetTrigger() {
        super("set trigger");
        setPermission("cvportal.settrigger");
        addBaseParameter(new CommandParameterPortal());
        addBaseParameter(new CommandParameterEnumeratedString("moveevent", "cyclic"));
    }

    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        Portal portal = (Portal) baseParameters.get(0);
        String trigger = (String) baseParameters.get(1);
        portal.setMoveEventTriggered(trigger.equals("moveevent"));
        PortalManager.getInstance().save();

        return new CommandResponse("&aTrigger set.");
    }
}
