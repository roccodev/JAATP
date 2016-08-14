package tk.roccodev.jaatp.commands;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

public class PortableCommand implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(arg1.getName().equalsIgnoreCase("workbench")){
			
		if(arg0 instanceof Player){
			Player p = (Player) arg0;
			p.openWorkbench(null, true);
		}
			
		}
			
		
		return true;
	}
	
/*	
@EventHandler
public void onClick(InventoryClickEvent e){
	
	if(e.getCurrentItem() == null) return;
	if(e.getCurrentItem().getType() == null) return;
	
	if(e.getInventory().getName().equalsIgnoreCase("Portable Furnace") && 
			e.getCurrentItem().getType() == Material.COAL){
		e.setCancelled(true);
		
	}
	else if(e.getInventory().getHolder() == null && e.getCurrentItem().getType() == Material.INK_SACK){
		e.setCancelled(true);
	}
	
}
*/

@EventHandler
public void onEnd(InventoryCloseEvent e){
	if(e.getInventory().getName().contains("Portabled")){
		e.getInventory().clear();
	}
}
	
	
}
