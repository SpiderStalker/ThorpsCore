
package com.thorpscraft.imasonite;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thorpscraft.imasonite.locale.L10n;

public class ThorpsCore extends JavaPlugin {
	
	public static ThorpsCore thorpsCore;
	public FileConfiguration configFile;
	private TcCommandExecutor commandExecutor;
	private TcCommandListener commandListener = new TcCommandListener(this);
	
	public static Logger logger = Logger.getLogger("Minecraft");
	private static String pluginName;
	
	@Override
	public void onLoad() {
		thorpsCore = this;
		pluginName = thorpsCore.getName();
		configFile = getConfig();
		configFile.options().copyDefaults(true);
		saveDefaultConfig();
		// -------------------------------------------
		
		L10n.init(thorpsCore);
		
	}
	
	@Override
	public void onEnable() {
		logServer(Level.INFO, pluginName + " - Enabled");
		
		commandExecutor = new TcCommandExecutor(this);
		getCommand("ThorpsCore").setExecutor(commandExecutor);
		registerListener();
		
	}
	
	@Override
	public void onDisable() {
		logServer(Level.INFO, pluginName + " - Disabled");
	}
	
	public void registerListener() {
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.commandListener, this);
	}
	
	public static void logServer(Level level, String logEntry) {
		logger.log(level, "[" + pluginName + "] " + logEntry);
	}
	
	public static ThorpsCore getPlugin() {
		return thorpsCore;
	}
	
}
