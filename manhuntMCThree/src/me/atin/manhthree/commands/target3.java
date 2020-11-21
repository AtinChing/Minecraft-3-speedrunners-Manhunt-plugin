package me.atin.manhthree.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.atin.manhthree.Sixth;

public class target3 implements CommandExecutor{
	private Sixth plugin;
	public List<Player> playerList;
	public Player currentPlayer; // THe current player that will be checked for a compass.
	public int playerAmount;
	public ItemStack compass = new ItemStack(Material.COMPASS);
	public target3(Sixth plugin) {
	this.plugin = plugin;
	plugin.getCommand("target3").setExecutor(this);
	}
	@Override 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("target3.use")) {
				p.sendMessage(ChatColor.RED + "You need to have permission to use this command!");
				return true;
			}
			else if(!(args.length == 3)) {
				p.sendMessage(ChatColor.RED + "You need to enter 3 players only!");
				return true;
			}
			else if(args.length == 3) {
				plugin.vict1 = Bukkit.getPlayer(args[0]); // Setting the player values.
				plugin.vict2 = Bukkit.getPlayer(args[1]);
				plugin.vict3 = Bukkit.getPlayer(args[2]);
				
				
				plugin.turn = 1;
				plugin.manhuntIsOn = true;
				Bukkit.broadcastMessage(ChatColor.GREEN + "3 player Manhunt has been turned on. The position where compasses point to will now alternate between " + plugin.vict1.getName() + " or " + plugin.vict2.getName() + " or " + plugin.vict3.getName() + " whenever you right click holding the compass.");
				playerList = Bukkit.getServer().getWorld("world").getPlayers(); // List that includes all the players.
				playerAmount = playerList.size(); // The amount of players.
				int i = 0;
				while(i < playerAmount) {
					currentPlayer = (Player) playerList.get(i); 
					if(!currentPlayer.getInventory().contains(Material.COMPASS) && !(currentPlayer == plugin.vict1 || currentPlayer == plugin.vict2 || currentPlayer == plugin.vict3)) {
						currentPlayer.getInventory().addItem(compass); // Adds a compass to the inventory of every player that isn't a speedrunner (if they don't have a compass already.)
					}
					else if (currentPlayer.getInventory().contains(Material.COMPASS) && (currentPlayer == plugin.vict1 || currentPlayer == plugin.vict2 || currentPlayer == plugin.vict3)) {
						currentPlayer.getInventory().remove(Material.COMPASS); // Removes all the compasses from the speedrunners inventory (if they even have one).
					}
					i++;
				}
				return true;
			}
			else {
				p.sendMessage(ChatColor.RED + "You have not used this command properly.");
				return true;
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You need to be player to use this command!");
		}
		return false;
}
}
