
package com.thorpscraft.imasonite.locale;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

import com.thorpscraft.imasonite.ThorpsCore;
import com.thorpscraft.imasonite.Tools;

public class L10n {
	private static final String BUNDLE_NAME = "Localization";
	private static Plugin thorpsPlugin;
	
	private static ResourceBundle RESOURCE_BUNDLE0;
	private static ResourceBundle RESOURCE_BUNDLE1;
	
	public static int CONTENT_VERSION;
	public static String ERROR_PERMISSION;
	public static String NULL_MESSAGE;
	public static String CMD_SAY_FORMAT_PLAYER;
	public static String CMD_SAY_FORMAT_SERVER;
	
	private L10n() {}
	
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE0.getString(key);
		} catch (Exception e) {
			try {
				return RESOURCE_BUNDLE1.getString(key);
			} catch (Exception ee) {
				//TODO: Check perms display to admin not all users, try for redundancy.
				//		and insert new on ver change. @iMasonite
				return Tools.formatString("&d Localization Resource Property:\n&e%1% &dNot Found!\n"
						+ "Please Update the Localization.properties config file.", key);
			}
		}
	}
	
	public static void init(Plugin instance) {
		thorpsPlugin = instance;
		reload();
	}
	
	public static void reload() {
		String locale = "";
		try {
			RESOURCE_BUNDLE1 = ResourceBundle.getBundle(BUNDLE_NAME, new UTF8Control());
			locale = RESOURCE_BUNDLE1.getLocale().toString();
			if (!(locale.equals(""))) {
				locale = "_".concat(locale);
			}
			
		} catch (MissingResourceException e) {
		}
		
		try {
			URL[] urls = { thorpsPlugin.getDataFolder().toURI().toURL() };
			
			RESOURCE_BUNDLE0 = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), new URLClassLoader(urls), new UTF8Control());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (MissingResourceException e) {
			
			ThorpsCore.logServer(Level.INFO, "Save New Resource Bundle: " + BUNDLE_NAME.concat(locale).replace('.', '/').concat(".properties"));
			
			thorpsPlugin.saveResource(BUNDLE_NAME.concat(locale).replace('.', '/').concat(".properties"), false);
			
			try {
				URL[] urls = { thorpsPlugin.getDataFolder().toURI().toURL() };
				RESOURCE_BUNDLE0 = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), new URLClassLoader(urls), new UTF8Control());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		for (Field field : L10n.class.getFields()) {
			if (field.getType().equals(String.class) && Modifier.isStatic(field.getModifiers())) {
				try {
					field.set(null, getString(field.getName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
