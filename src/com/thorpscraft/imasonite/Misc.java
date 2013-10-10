
package com.thorpscraft.imasonite;

import org.bukkit.entity.Player;

/** Misc Methods and Objects
 * 
 * @author Michael Mason */
public class Misc {
	/** Test the Permission to use a command.
	 * 
	 * @param sender
	 * @param permission
	 * @return boolean */
	public static boolean hasPerms(Player sender, String permission) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOp()) {
				return true;
			} else
				if (sender.hasPermission(permission)) {
					return true;
				}
		}
		return false;
	}
}
