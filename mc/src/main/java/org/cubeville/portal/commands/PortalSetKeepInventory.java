package org.cubeville.portal.commands;

import org.bukkit.entity.Player;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandResponse;
import org.cubeville.portal.Portal;
import org.cubeville.portal.PortalManager;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PortalSetKeepInventory extends Command
{
    public PortalSetKeepInventory() {
        super("set keepinventory");
        addBaseParameter(new CommandParameterPortal());
        addBaseParameter(new CommandParameterBoolean());
    }

    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        Portal portal = (Portal) baseParameters.get(0);

        portal.setKeepInventory((boolean) baseParameters.get(1));
        PortalManager.getInstance().save();
        
        return new CommandResponse("&aValue set.");
    }
}

