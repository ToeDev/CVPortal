package org.cubeville.portal.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterBoolean;
import org.cubeville.commons.commands.CommandResponse;

import org.cubeville.portal.Portal;
import org.cubeville.portal.PortalManager;
import org.cubeville.portal.actions.Action;
import org.cubeville.portal.actions.Teleport;

public class PortalSetBringHorses extends Command
{
    public PortalSetBringHorses() {
        super("set bring horses");
        addBaseParameter(new CommandParameterPortal());
        addBaseParameter(new CommandParameterBoolean());
    }

    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters)
        throws CommandExecutionException {

        Portal portal = (Portal) baseParameters.get(0);

        List<Action> teleport = portal.getActionsByType(Teleport.class);
        if(teleport.size() == 0) throw new CommandExecutionException("Portal has no teleport destination!");

        boolean bringHorses = (boolean) baseParameters.get(1);
        ((Teleport)teleport.get(0)).setBringHorses(bringHorses);
        PortalManager.getInstance().save();
        
        return new CommandResponse("Horse transport " + (bringHorses ? " enabled." : "disabled."));
    }
    
}
