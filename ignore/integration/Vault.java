
package com.thorpscraft.imasonite.integration;

import org.bukkit.plugin.RegisteredServiceProvider;
import com.thorpscraft.imasonite.ThorpsCore;

import net.milkbowl.vault.permission.Permission;

public class Vault {
	static ThorpsCore plugin = ThorpsCore.getPlugin();
	
	// public static Economy econ = null;
	public static Permission	perms		= null;
	
	// public static Chat chat = null;
	
	public static boolean initialize() {
		
		if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		
		setupPermissions();
		return true;
		
	}
	
	// private boolean setupEconomy() {
	// RegisteredServiceProvider<Economy> rsp =
	// plugin.getServer().getServicesManager().getRegistration(Economy.class);
	// if (rsp == null) {
	// return false;
	// }
	// econ = rsp.getProvider();
	// return econ != null;
	// }
	
	// private boolean setupChat() {
	// RegisteredServiceProvider<Chat> rsp =
	// plugin.getServer().getServicesManager().getRegistration(Chat.class);
	// chat = rsp.getProvider();
	// return chat != null;
	// }
	
	private static boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}
	
}
