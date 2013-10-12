
package com.thorpscraft.imasonite;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.thorpscraft.imasonite.locale.L10n;

public class TcCommandExecutor implements CommandExecutor, TabCompleter {
	private ThorpsCore thorpsPlugin;
	
	public TcCommandExecutor(ThorpsCore plugin) {
		this.thorpsPlugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean returnType = false;
		boolean consoleSender = !(sender instanceof Player);
		
		if (!consoleSender) {
			switch (cmd.getName().toLowerCase()) {
				case "thorpscore": case "thorps": case "th":
					String permission = "thorpscore.test";
					if (!sender.hasPermission(permission)) {
						Tools.formatMsg(sender, L10n.ERROR_PERMISSION, label, permission);
						return false;
					}
					Tools.formatMsg(sender, "&aHas Perms! :D");
					
					returnType = true;
					break;
				default:
					return false;
			}
		} else {
			switch (cmd.getName().toLowerCase()) {
				case "thorpscore": case "thorps": case "th":
					Tools.formatMsg(sender, "This command is on Console");
					returnType = true;
					break;
				default:
					return false;
			}
		}
		return returnType;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** @return the thorpsPlugin */
	public ThorpsCore getThorpsPlugin() {
		return thorpsPlugin;
	}
}
