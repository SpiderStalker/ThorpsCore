
package com.thorpscraft.imasonite;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.thorpscraft.imasonite.locale.L10n;

public class TcCommandExecutor implements CommandExecutor, TabCompleter {
	ThorpsCore plugin;
	
	public TcCommandExecutor(ThorpsCore plugin) {
		this.plugin = plugin;
	}
	
	// -----------------------------------------------
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean returnType = false;
		boolean consoleSender = !(sender instanceof Player);
		
		if (cmd.getName().equalsIgnoreCase("tcfix")) {
			return true;
		}
		
		if (!consoleSender) {
			switch (cmd.getName().toLowerCase()) {
				case "thorpscore":
				case "thorps":
				case "th":
					String permissiontest = "thorpscore.test";
					if (!sender.hasPermission(permissiontest)) {
						Tools.formatMsg(sender, L10n.ERROR_PERMISSION, label, permissiontest);
						return false;
					}
					// -------------
					Tools.formatMsg(sender, "&aHas Perms! :D");
					// -------------
					returnType = true;
					break;
				default:
					return false;
			}
		} else {
			switch (cmd.getName().toLowerCase()) {
				case "thorpscore":
				case "thorps":
				case "th":
					// -------------
					Tools.formatMsg(sender, "This command is on Console");
					// -------------
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
	
}
