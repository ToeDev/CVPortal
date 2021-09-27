package org.cubeville.portal.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterString;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.portal.Portal;
import org.cubeville.portal.PortalManager;

public class PortalList extends Command
{
    public PortalList() {
        super("list");
        addFlag("-n");
        addFlag("-a");
        addOptionalBaseParameter(new CommandParameterString());
    }

    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {
        
        PortalManager portalManager = PortalManager.getInstance();

        List<Portal> portals = baseParameters.size() == 0
            ? portalManager.getPortals()
            : portalManager.getMatchingPortals((String) baseParameters.get(0));

        if(!flags.contains("-a")) {
            List<Portal> fportals = new ArrayList<>();
            UUID playerWorldId = player.getLocation().getWorld().getUID();
            for(Portal portal: portals) {
                if(portal.getWorld().equals(playerWorldId)) fportals.add(portal);
            }
            portals = fportals;
        }

        if(portals.size() == 0) return new CommandResponse("&cNo portals found.");

        if(flags.contains("-n")) {
            List<String> portalNames = new ArrayList<String>();
            for(Portal portal: portals) portalNames.add(portal.getName());
            String list = "&a";
            Collections.sort(portalNames);
            for(String portalName: portalNames) {
                if(list.length() > 2) list += ", ";
                list += portalName;
            }
            return new CommandResponse(list);
        }
        else {
            CommandResponse ret = new CommandResponse();
            for(Portal portal: portals) ret.addMessage(portal.getInfo());
            return ret;
        }
    }
}
