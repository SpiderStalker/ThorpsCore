package com.thorpscraft.imasonite;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;


public class ThorpsCraft extends JavaPlugin {

	//for convenience, a reference to the instance of this plugin.
	public static ThorpsCraft instance;
	//for logging to the console and log file.
	private static Logger logger = Logger.getLogger("Minecraft");


	public void onEnable() {
		LogThis(Level.INFO, "PluginCore - Enabled");

	}

	public void onDisable() {
		
	}
	
	public static void LogThis(Level level, String logEntry) {
		logger.log(level, "[ThorpsCraft]: " + logEntry);
	}
	
}
