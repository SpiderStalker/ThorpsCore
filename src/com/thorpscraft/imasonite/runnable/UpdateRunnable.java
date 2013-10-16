
package com.thorpscraft.imasonite.runnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import com.thorpscraft.imasonite.ThorpsCore;
import com.thorpscraft.imasonite.Tools;

/** @author Michael Mason */
public class UpdateRunnable implements Runnable {
	
	private final ThorpsCore plugin;
	private final String VERSION_URL = "https://raw.github.com/iMasonite/ThorpsCore/master/VERSION";
	private final String UPDATE_URL = "https://github.com/iMasonite/ThorpsCore/releases";
	private Boolean isLatest = null;
	private String latest;
	private String version;
	
	public UpdateRunnable(ThorpsCore plugin) {
		super();
		this.plugin = plugin;
		this.version = plugin.getDescription().getVersion();
	}
	
	public void run() {
		if (version.endsWith("SNAPSHOT") || version.endsWith("DEV")) {
			plugin.getLogger().warning("Dev build detected, update checking disabled");
			isLatest = true;
			return;
		}
		try {
			URL call = new URL(VERSION_URL);
			InputStream stream = call.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			latest = reader.readLine();
			reader.close();
			
			if (latest.equalsIgnoreCase(version)) {
				ThorpsCore.logServer(Level.INFO, Tools.formatString("Latest Version in use: %1%", latest));
				isLatest = true;
			} else {
				ThorpsCore.logServer(Level.WARNING, Tools.formatString("New Version available: %1%", latest));
				ThorpsCore.logServer(Level.WARNING, Tools.formatString("Download: %1%", UPDATE_URL));
				isLatest = false;
			}
			plugin.setUpdateStatus(!isLatest);
		} catch (MalformedURLException ex) {
			ThorpsCore.logServer(Level.SEVERE, Tools.formatString("Error checking for update: %1%", ex.toString()));
		} catch (IOException ex) {
			ThorpsCore.logServer(Level.SEVERE, Tools.formatString("Error checking for update: %1%", ex.toString()));
		}
	}
}
