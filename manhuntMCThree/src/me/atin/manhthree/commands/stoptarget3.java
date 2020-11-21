package me.atin.manhthree.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.atin.manhthree.Sixth;
import net.md_5.bungee.api.ChatColor;

public class stoptarget3 implements CommandExecutor{
	private Sixth plugin;
	public stoptarget3(Sixth plugin) {
	this.plugin = plugin;
	plugin.getCommand("stoptarget3").setExecutor(this);
	}
	@Override
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if(sender instanceof Player) {
		Player p = (Player) sender;
		if(!(args.length == 0)) {
		p.sendMessage(ChatColor.RED + "/stoptarget3 doesn't take any parameters!");
		return true;
		}
		else if(!p.hasPermission("stoptarget3.use")) { // If sender doesn't have permission.
			p.sendMessage("You need to permission to use this command!");
			return true;
		}
		else if(!plugin.manhuntIsOn) {
			p.sendMessage(ChatColor.RED + "3 player manhunt has already been turned off!");
			return true;
		}
		else if(plugin.manhuntIsOn && p.hasPermission("stoptarget3.use")){
			plugin.manhuntIsOn = false;
			Bukkit.broadcastMessage(ChatColor.GREEN + "3 player manhunt has successfully been turned off.");
			return true;
		}
		else {
			p.sendMessage(ChatColor.RED + "Invalid!");
		}
	}
	else {
		sender.sendMessage(ChatColor.RED + "Only players can use this command!");
	}
	return false;
}
}
