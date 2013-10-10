
package com.thorpscraft.imasonite;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thorpscraft.imasonite.configuration.Configuration;
import com.thorpscraft.imasonite.events.CommandListener;

public class ThorpsCore extends JavaPlugin {
	public static Logger			logger			= Logger.getLogger("Minecraft");
	
	public static ThorpsCore		instance;
	public static MessageSender		messageSender	= new MessageSender(instance);
	private final CommandListener	CommandListener	= new CommandListener(instance);
	public Configuration			configuration;
	
	// TODO: Move to config
	public static String			pluginPrefix	= "[ThorpsCore]: ";
	
	@Override
	public void onLoad() {
		instance = this;
		logServer(Level.INFO, "---------------------------");
		logServer(Level.INFO, " ThorpsCore ------ Loading ");
		logServer(Level.INFO, "---------------------------");
	}
	
	@Override
	public void onEnable() {
		regListener();
		logServer(Level.INFO, "ThorpsCore - Enabled");
	}
	
	@Override
	public void onDisable() {
		logServer(Level.INFO, "ThorpsCore - Disabled");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("returnme")) {
			return true;
		}
		return false;
	}
	
	public static void logServer(Level level, String logEntry) {
		logger.log(level, pluginPrefix + logEntry);
	}
	
	public void regListener() {
		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(instance.CommandListener, instance);
	}
}
