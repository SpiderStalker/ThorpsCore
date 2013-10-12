
package com.thorpscraft.imasonite;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.thorpscraft.imasonite.locale.L10n;

public class ThorpsCore extends JavaPlugin {
	
	public static ThorpsCore thorpsCore;
	public FileConfiguration configFile;
	private TcCommandExecutor commandExecutor;
	
	public static Logger logger = Logger.getLogger("Minecraft");
	public static String pluginName = thorpsCore.getName();
	
	@Override
	public void onLoad() {
		thorpsCore = this;
		configFile = getConfig();
		configFile.options().copyDefaults(true);
		saveDefaultConfig();
		logServer(Level.INFO, "------------------------------------------------------");
		logServer(Level.INFO, "    ----- " + pluginName + " ----- Loading ----- ");
		
		L10n.init(thorpsCore);
		
		logServer(Level.INFO, "------------------------------------------------------");
		
		commandExecutor = new TcCommandExecutor(this);
		getCommand("Thorps").setExecutor(commandExecutor);
	}
	
	@Override
	public void onEnable() {
		logServer(Level.INFO, pluginName + " - Enabled");
	}
	
	@Override
	public void onDisable() {
		logServer(Level.INFO, pluginName + " - Disabled");
	}
	
	public static void logServer(Level level, String logEntry) {
		logger.log(level, "[" + pluginName + "]" + logEntry);
	}
	
	public static ThorpsCore getPlugin() {
		return thorpsCore;
	}
	
}
