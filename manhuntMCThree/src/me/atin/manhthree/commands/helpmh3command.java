package me.atin.manhthree.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.atin.manhthree.Sixth;

public class helpmh3command implements CommandExecutor{
	private Sixth plugin;
	public helpmh3command(Sixth plugin) {
		this.plugin = plugin;
		plugin.getCommand("helpmh3").setExecutor(this);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(ChatColor.AQUA + "The 3 speedrunner manhunt plugin is a plugin made for manhunt with three speedrunners instead of one. During the manhunt, players are given compasses, which point to one of the speedrunners when you right click the compass. ");
			p.sendMessage(ChatColor.AQUA + "Every time you right click, the compass points to the next speedrunner, meaning it cycles through all 3 of them. During the manhunt, hunters can't pick up, craft or take compasses from chests since they don't need more than one.");
			p.sendMessage(ChatColor.AQUA + " The speedrunners, on the other hand, can't pick up, craft or get compasses in any way. Commands:");
			p.sendMessage(ChatColor.AQUA + "/target3 [player1] [player2] [player3] This purpose of this command is to start the 3 speedrunner manhunt. You need to insert the name of the 3 players which will be the speedrunners.");
			p.sendMessage(ChatColor.AQUA + " When this command is run, hunters get a compass (if they don't have one already) and the speedrunners lose a compass (if they do have one for some reason).");
			p.sendMessage(ChatColor.AQUA + "/stoptarget3 The only thing this command does is it STOP the manhunt. It does not take any arguments.");
			p.sendMessage(ChatColor.YELLOW + "Made by AtinChing on GitHub and SpigotMC.");
		}
		else {
			sender.sendMessage(ChatColor.RED + "Only players can use this command!");
		}
		
		return false;
	}
	
}