
package com.thorpscraft.imasonite;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thorpscraft.imasonite.configuration.Configuration;
import com.thorpscraft.imasonite.events.CommandListener;
import com.thorpscraft.imasonite.events.PlayerListener;
import com.thorpscraft.imasonite.integration.Vault;

public class ThorpsCore extends JavaPlugin {
	public static Logger			logger			= Logger.getLogger("Minecraft");
	
	/* classes */
	public static ThorpsCore		instance;
	public static MessageSender		messageSender	= new MessageSender(instance);
	public static CommandListener	CommandListener	= new CommandListener(instance);
	public static PlayerListener	PlayerListener	= new PlayerListener(instance);
	
	public Configuration			configuration;
	
	// TODO: Move to config
	public static String			pluginPrefix	= "[ThorpsCore]: ";
	
	@Override
	public void onLoad() {
		instance = this;
		logServer(Level.INFO, "------------------------------------------------------");
		logServer(Level.INFO, " ThorpsCore ------ Loading ");
		logServer(Level.INFO, "------------------------------------------------------");
	}
	
	@Override
	public void onEnable() {
		if (!Vault.initialize()) {
			logServer(Level.SEVERE, "Disabled due to Vault not being found! Please install it.");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		instance.regEvents();
		logServer(Level.INFO, "ThorpsCore - Enabled");
	}
	
	@Override
	public void onDisable() {
		logServer(Level.INFO, "ThorpsCore - Disabled");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		boolean returnType = false;
		
		// Player player = null;
		// if (sender instanceof Player) {
		// player = (Player) sender;
		// }
		
		switch (cmd.getName().toLowerCase()) {
			case "thorps":
				
				returnType = true;
				break;
			case "returnme":
				// HACK: Just here for now, will kill bug band-aid later.
				returnType = true;
				break;
			default:
				returnType = false;
		}
		return returnType;
	}
	
	public static void logServer(Level level, String logEntry) {
		logger.log(level, pluginPrefix + logEntry);
	}
	
	public static ThorpsCore getPlugin() {
		return instance;
	}
	
	public void regEvents() {
		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(ThorpsCore.CommandListener, instance);
		pluginManager.registerEvents(ThorpsCore.PlayerListener, instance);
	}
}
