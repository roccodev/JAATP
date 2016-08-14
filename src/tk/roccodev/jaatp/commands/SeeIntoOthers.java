package tk.roccodev.jaatp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import tk.roccodev.jaatp.config.MessageConfig;

public class SeeIntoOthers implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player){
			Player p = (Player) arg0;
			if(arg1.getName().equalsIgnoreCase("invsee")){
				
			
			
			
			if(arg3.length == 1){
				Player target = Bukkit.getPlayer(arg3[0]);
				if(target == null) { p.sendMessage(MessageConfig.ERROR_PLAYER_NOT_FOUND); return true; }
				
				showPlayerInventory(target, p);
				
			}
			else { p.sendMessage("Usage: " + arg1.getUsage());}
			}
			else if(arg1.getName().equalsIgnoreCase("enderchest")){
				if(arg3.length == 1){
					Player target = Bukkit.getPlayer(arg3[0]);
					if(target == null) { p.sendMessage(MessageConfig.ERROR_PLAYER_NOT_FOUND); return true; }
					
					showPlayerEnderChest(target, p);
					
				}
				else {showPlayerEnderChest(p, p); }
				
			}

		}
		return true;
	}
	
	
	private void showPlayerInventory(Player p, Player visitor){
		
		PlayerInventory pi = p.getInventory();
		
		visitor.openInventory(pi);
		
	}

	private void showPlayerEnderChest(Player p, Player visitor){
		
		Inventory inv = p.getEnderChest();
		visitor.openInventory(inv);
		
	}
}
