
package com.thorpscraft.imasonite;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TcCommandExecutor implements CommandExecutor, TabCompleter {
	private ThorpsCore thorpsPlugin;
	
	public TcCommandExecutor(ThorpsCore plugin) {
		this.thorpsPlugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean returnType = false;
		
		// Player player = null;
		// if (sender instanceof Player) {
		// player = (Player) sender;
		// }
		
		switch (cmd.getName().toLowerCase()) {
			case "thorps":
				
				returnType = true;
				break;
			case "returnme":
				// HACK: Just here for now, will kill bug band-aid later.
				returnType = true;
				break;
			default:
				returnType = false;
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
