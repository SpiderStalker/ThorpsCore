
package com.thorpscraft.imasonite;

import java.util.logging.Level;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thorpscraft.imasonite.abstraction.enums.TcChatColor;

public class MessageSender {
	ThorpsCore	plugin;
	
	MessageSender(ThorpsCore plugin) {
		this.plugin = plugin;
	}
	
	public void playerSend(Player player, String text) {
		player.sendMessage(TcChatColor.RED + ThorpsCore.pluginPrefix + text);
	}
	
	public void errorMessage(CommandSender sender, String text) {
		sender.sendMessage(TcChatColor.RED + ThorpsCore.pluginPrefix + text);
	}
	
	public void noPerms(Player player, String text) {
		if (text == null) {
			player.sendMessage(TcChatColor.RED + ThorpsCore.pluginPrefix + "You do not have permission for this command!");
		} else {
			if (text == "") {
				text = "You do not have permission for this command!";
			}
			player.sendMessage(TcChatColor.RED + ThorpsCore.pluginPrefix + text);
		}
	}
	
	public void exception(String exception) {
		ThorpsCore.logServer(Level.WARNING, ThorpsCore.pluginPrefix + "Exception: " + exception);
	}
}
