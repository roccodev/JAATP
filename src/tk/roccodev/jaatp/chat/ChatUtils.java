package tk.roccodev.jaatp.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ChatUtils {

	
	

	
	
	/*
	 * 
	 * TYPES:
	 * 
	 * 0 = Server-wide
	 * 1 = Worldwide
	 * 
	 * 
	 */
	public static void broadcastMessage(int type, String message, String perm, World world){
		
		if(perm == null){
			
			if(type == 0){
				
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
				}
				
			}
			else{
				
				for(Player p : world.getPlayers()){
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
				}
				
			}
			
		}
		else{
			
			if(type == 0){
				
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(p.hasPermission(perm)){
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					}
				}
				
			}
			else{
				for(Player p : world.getPlayers()){
					if(p.hasPermission(perm)){
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					}
				}
			}
			
		}
		
	}
	
	public static void broadcastServerMessage(String message){
		
		broadcastMessage(0, message, null, null);
	}
	
	public static void broadcastWorldMessage(String message, World world){
		broadcastMessage(1, message, null, world);
	}
	
	public static void broadcastServerMessage(String message, String perm){
		
		broadcastMessage(0, message, perm, null);
	}
	
	public static void broadcastWorldMessage(String message, World world, String perm){
		broadcastMessage(1, message, perm, world);
	}
	
}
