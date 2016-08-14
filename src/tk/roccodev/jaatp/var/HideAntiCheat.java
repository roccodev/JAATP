package tk.roccodev.jaatp.var;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import tk.roccodev.jaatp.JaatpMain;

public class HideAntiCheat implements Listener{
	
	@EventHandler
	public void HAC(PlayerCommandPreprocessEvent e){
		
		Bukkit.getLogger().info(Bukkit.getPluginManager().getPlugin("AAC").getName());
		
		//Variables
		Player p = e.getPlayer();
		String cmd = e.getMessage();
		
		
	
		
		
		if(Bukkit.getPluginManager().getPlugin("NoCheatPlus") != null && Bukkit.getPluginManager().getPlugin("NoCheatPlus").isEnabled()){
			if((cmd.toUpperCase().contains("NCP") || cmd.toUpperCase().contains("NOCHEATPLUS")) && !(p.hasPermission("nocheat.admin"))){
				e.setCancelled(true);
				if(JaatpMain.isSpigot()){
					p.sendMessage(Bukkit.spigot().getConfig().getString("unknown-command"));
						
				}
				else{
					p.sendMessage("Unknown command. Type \"help\" for help.");
				}
				
			}
		}
		
		//Check for AAC
		if(Bukkit.getPluginManager().getPlugin("AAC") != null && Bukkit.getPluginManager().getPlugin("AAC").isEnabled()){
			if(cmd.toUpperCase().contains("AAC") && !(p.hasPermission("AAC.admin"))){	
				e.setCancelled(true);
				if(JaatpMain.isSpigot()){
					p.sendMessage(Bukkit.spigot().getConfig().getString("unknown-command"));
						
				}
				else{
					p.sendMessage("Unknown command. Type \"help\" for help.");
				}
			}
		}
		
		//Check for AntiCheat/AntiCheat+
		if((Bukkit.getPluginManager().getPlugin("AntiCheat") != null && Bukkit.getPluginManager().getPlugin("AntiCheat").isEnabled()) || (Bukkit.getPluginManager().getPlugin("AntiCheat+") != null && Bukkit.getPluginManager().getPlugin("AntiCheat+").isEnabled())){
			if(cmd.toUpperCase().contains("ANTICHEAT") && (!(p.hasPermission("anticheat.admin") || !(p.hasPermission("anticheat.mod"))))){
				e.setCancelled(true);
				if(JaatpMain.isSpigot()){
					p.sendMessage(Bukkit.spigot().getConfig().getString("unknown-command"));
						
				}
				else{
					p.sendMessage("Unknown command. Type \"help\" for help.");
				}
			}
		}
		
	}

}
