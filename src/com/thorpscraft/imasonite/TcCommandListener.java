
package com.thorpscraft.imasonite;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.thorpscraft.imasonite.locale.L10n;

public class TcCommandListener implements Listener {
	
	ThorpsCore plugin;
	
	public TcCommandListener(ThorpsCore instance) {
		this.plugin = instance;
	}
	
	// -----------------------------------------------
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		Player sender = event.getPlayer();
		
		String playerCommand = event.getMessage().split("\\s+")[0].toLowerCase();
		
		switch (playerCommand) {
			case "/say":
				String permissionsay = "thorpscore.say";
				String message = event.getMessage().replaceFirst("/say", "");
				
				if (!sender.hasPermission(permissionsay)) {
					Tools.formatMsg(sender, L10n.ERROR_PERMISSION, playerCommand, permissionsay);
					event.setCancelled(true);
					return;
				}
				// -------------
				if (message.trim().equalsIgnoreCase("")) {
					Tools.formatMsg(sender, L10n.NULL_MESSAGE, playerCommand);
					event.setCancelled(true);
					return;
				} else {
					Bukkit.broadcastMessage(Tools.formatString(L10n.CMD_SAY_FORMAT_PLAYER, sender.getName(), message));
					event.setCancelled(true);
				}
				// -------------
				break;
			default:
				return;
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onServerCommand(ServerCommandEvent event) {
		CommandSender sender = event.getSender();
		String serverCommand = event.getCommand().split("\\s+")[0].toLowerCase();
		
		switch (serverCommand) {
			case "say":
				// -------------
				String message = event.getCommand().replaceFirst("say", "");
				
				if (message.trim().equalsIgnoreCase("")) {
					Tools.formatMsg(sender, L10n.NULL_MESSAGE, serverCommand);
					event.setCommand("tcnull");
					return;
				} else {
					Bukkit.broadcastMessage(Tools.formatString(L10n.CMD_SAY_FORMAT_SERVER, message));
				}
				event.setCommand("tcnull");
				
				// -------------
				break;
			default:
				return;
		}
		return;
	}
}
