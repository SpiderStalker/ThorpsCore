
package com.thorpscraft.imasonite.events;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.thorpscraft.imasonite.ThorpsCore;
import com.thorpscraft.imasonite.enums.TcChatColor;
import com.thorpscraft.imasonite.integration.Vault;

public class CommandListener implements Listener {
	ThorpsCore plugin;
	
	public CommandListener(ThorpsCore instance) {
		this.plugin = instance;
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();

		if (event.getMessage().startsWith("/say")) {
			String permission = "thorpscraft.messaging.say";
			

			if (!Vault.perms.has(player, permission)) {
				ThorpsCore.messageSender.noPerms(player, null);
				event.setCancelled(true);
				return;
			}
			
			String message = event.getMessage().replaceFirst("/say", "");
			String console = "Console";
			if (message.trim().equalsIgnoreCase("")) {
				ThorpsCore.messageSender.playerSend(player, TcChatColor.RED + "You must enter a message!");
				return;
			}
			// TODO yeah this needs more work...
			String colored = TcChatColor.giveColor("&d [&6" + console + "&7: &c" + player.getName() + "&d]&7:&r" + message);
			Bukkit.broadcastMessage(colored);
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onServerCommand(ServerCommandEvent event) {
		if (event.getCommand().startsWith("say")) {
			
			String message = event.getCommand().replaceFirst("say", "");
			String console = "Console";
			if (message.trim().equalsIgnoreCase("")) {
				ThorpsCore.logServer(Level.WARNING, "You must enter a message!");
				return;
			}
			
			String colored = TcChatColor.giveColor("&d [&6" + console + "&d]&7:&d" + message);
			Bukkit.broadcastMessage(colored);
			event.setCommand("returnme");
		}
	}
}
