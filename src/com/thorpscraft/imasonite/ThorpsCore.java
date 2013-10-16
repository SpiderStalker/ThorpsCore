
package com.thorpscraft.imasonite;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import com.thorpscraft.imasonite.locale.L10n;
import com.thorpscraft.imasonite.runnable.UpdateRunnable;

public class ThorpsCore extends JavaPlugin {
	
	private static ThorpsCore plugin;
	public static FileConfiguration config;
	private TcCommandExecutor commandExecutor;
	private TcCommandListener commandListener = new TcCommandListener(this);
	
	public static Logger logger = Logger.getLogger("Minecraft");
	public static String pluginName;
	private boolean isUpdate = false;
	
	@Override
	public void onLoad() {
		plugin = this;
		L10n.init(plugin);
		// -------------------------------------------
		pluginName = plugin.getName();
		config = getConfig();
		config.options().copyDefaults(true);
		saveDefaultConfig();
		// -------------------------------------------
		if (!config.isSet("UpdateCheck")) {
			config.set("UpdateCheck", true);
		}
		plugin.saveConfig();
		
	}
	
	@Override
	public void onEnable() {
		
		if (this.getConfig().getBoolean("UpdateCheck")) {
			Bukkit.getScheduler().runTaskLater(this, new UpdateRunnable(this), 10L);
		} else {
			logServer(Level.WARNING, "Update checking disabled!");
		}
		// -------------------------------------------
		
		commandExecutor = new TcCommandExecutor(this);
		getCommand("ThorpsCore").setExecutor(commandExecutor);
		registerListener();
		
		// -------------------------------------------
		if (config.getBoolean("Debug.Enabled", false)) logServer(Level.WARNING, pluginName + " - Debug Mode is Enabled");
		
		logServer(Level.INFO, pluginName + " - Enabled");
	}
	
	@Override
	public void onDisable() {
		
		logServer(Level.INFO, pluginName + " - Disabled");
	}
	
	/** @param level What level SEVERE WARNING, INFO ect
	 * @param logEntry The message to output to the server console. */
	public static void logServer(Level level, String logEntry) {
		logger.log(level, "[" + pluginName + "] " + logEntry);
	}
	
	/** Get the Instance of the plugin.
	 * 
	 * @return Return the instance of the plugin */
	public static ThorpsCore getPlugin() {
		return plugin;
	}
	
	/** Sets the update status for the plugin
	 * 
	 * @param status The status to set
	 * @return The newly set status */
	public boolean setUpdateStatus(boolean status) {
		this.isUpdate = status;
		return this.isUpdate;
	}
	
	/** Register Listeners */
	private void registerListener() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.commandListener, this);
	}
	
}
