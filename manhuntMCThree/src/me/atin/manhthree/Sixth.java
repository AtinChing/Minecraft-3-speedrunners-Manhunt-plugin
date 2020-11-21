package me.atin.manhthree;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.atin.manhthree.commands.helpmh3command;
import me.atin.manhthree.commands.stoptarget3;
import me.atin.manhthree.commands.target3;
import net.md_5.bungee.api.ChatColor;

public class Sixth extends JavaPlugin implements Listener{
	public Player vict1; // Victims
	public Player vict2;
	public Player vict3;
	public Player currenttarget; // The player currently being targeted.
	public Location loc;
	public boolean manhuntIsOn;
	public int turn = 0;
	@Override
	public void onEnable() {
	new target3(this);
	new stoptarget3(this);
	new helpmh3command(this);
	Bukkit.getPluginManager().registerEvents(this, this);
	manhuntIsOn = false;
	}
	@EventHandler
	public void OnInteract(PlayerInteractEvent event) {
		if(manhuntIsOn) {
			Action action = event.getAction();
			Player player = event.getPlayer();
			ItemStack item = event.getItem();
			if(item.getType() == Material.COMPASS && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && !(player == vict1 || player == vict2 || player == vict3)) {
				currenttarget = Switcher(turn); 
				loc = currenttarget.getLocation();
				if(currenttarget == vict1) {
					player.sendMessage(ChatColor.RED + "Your compass is now pointing to " + currenttarget.getName() + ".");
				}
				else if(currenttarget == vict2) { // If victim 2 is the current target
					player.sendMessage(ChatColor.GREEN + "Your compass is now pointing to " + currenttarget.getName() + ".");
				}
				else if(currenttarget == vict3) { // If victim 3 is the current target
					player.sendMessage(ChatColor.BLUE + "Your compass is now pointing to " + currenttarget.getName() + ".");
				}
				player.setCompassTarget(loc);
				if(!(turn == 3)) {
					turn++;
				}
				else {
					turn = 1;
				}
			}
		}
	}
	public Player Switcher(int no) {
		Player p;
		switch(no) {
		case 1: 
			p = vict1;
			break;
		case 2:
			p = vict2;
			break;
		case 3: 
			p = vict3;
			break;
		default:
			p = vict1;
		}
		return p;
	}
	@EventHandler
	public void onPickup(EntityPickupItemEvent event) {
		if(manhuntIsOn) {
		LivingEntity entity = event.getEntity();
		if(entity instanceof Player) {
			Player ply = (Player) entity;
			Item item = event.getItem();
			if((ply == vict1 || ply == vict2 || ply == vict3) && item.getItemStack().getType() == Material.COMPASS) {
				event.setCancelled(true); // Cancels event if a target tries to pick up a compass.
			}
			else if(item.getItemStack().getType() == Material.COMPASS && ply.getInventory().contains(Material.COMPASS)) {
				event.setCancelled(true); // Cancels event if the player already has a compass in their inventory and is trying to pick up a compass.
			}
			}
		}
		
	}
	@EventHandler
	public void onCraft(CraftItemEvent event) {
		if(manhuntIsOn) {
		ItemStack item = event.getInventory().getResult();
			if(item.getType() == Material.COMPASS) {
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(manhuntIsOn) {
			Player p = (Player) event.getWhoClicked(); // The player who clicked on the item
			ItemStack item = event.getCurrentItem(); // Item that the players clicked on
			InventoryType it = event.getInventory().getType(); // The inventory type of the block open.
			if(item.getType() == Material.COMPASS && (p == vict1 || p == vict2 || p == vict3)) {
				event.setCancelled(true); // Cancel this event if a target/speedrunner tries to click on a compass in a chest or their inventory.
			}
			else if(!(p == vict1 || p == vict2 || p == vict3) && !(it.equals(InventoryType.PLAYER))) {
				event.setCancelled(true); // Prevents the hunter from getting a compass from a chest or any other storage item (meaning they can only click and move the compass around in their own inventory.)
			}
		}
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if(manhuntIsOn) {
			Player p = (Player) event.getPlayer();
			Item item = event.getItemDrop();
			if(item.getItemStack().getType() == Material.COMPASS) {
				event.setCancelled(true); // Cancels the item drop if someone tries to drop a compass.
			}
		}
	}
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if(manhuntIsOn) {
			Player p = event.getPlayer();
			if(!(p == vict1 || p == vict2 || p == vict3)) {
				p.getInventory().addItem(new ItemStack(Material.COMPASS, 1)); // If the person who has respawned isn't a speedrunner then they will respawn with a compass in their inventory.
			}
		}
	}
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if(p.isOp()) {
			p.sendMessage(ChatColor.YELLOW + "If you want to play 3 speedrunner manhunt but don't know the commands then do /helpmanhunt3 or /helpmh3 for help.");
		}
	}
	
}
