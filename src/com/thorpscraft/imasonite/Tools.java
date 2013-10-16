
package com.thorpscraft.imasonite;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Tools {
	
	public static String format(String text, String... replacement) {
		String output = text;
		for (int i = 0; i < replacement.length; i++) {
			output = output.replace("%" + (i + 1) + "%", replacement[i]);
		}
		return ChatColor.translateAlternateColorCodes('&', output);
	}
	
	public static void formatMsg(CommandSender player, String text, String... replacement) {
		player.sendMessage(format(text, replacement));
	}
	
	public static String formatStrip(String text, String... replacement) {
		return ChatColor.stripColor(format(text, replacement));
		
	}
	
	public static String formatString(String text, String... replacement) {
		return format(text, replacement);
	}
	
}
