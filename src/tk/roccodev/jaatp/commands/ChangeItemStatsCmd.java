package tk.roccodev.jaatp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChangeItemStatsCmd implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		if(arg1.getName().equalsIgnoreCase("enchant")){
			if(arg3.length == 0){arg0.sendMessage("Usage: " + arg1.getUsage()); return true;}
			int level; Enchantment e; ItemStack is;
			if(arg3.length == 1){level = 1;} else { level = Integer.parseInt(arg3[1]);}
			e = Enchantment.getByName(arg3[0].toUpperCase());
			if(arg3[0].equalsIgnoreCase("sharpness")) e = Enchantment.DAMAGE_ALL;
			if(arg0 instanceof Player){
				Player p = (Player) arg0;
				 is = p.getItemInHand();
				 is.addEnchantment(e, level);
			}
		}
		else if(arg1.getName().equalsIgnoreCase("repair")){
			if(arg0 instanceof Player){
				
				
				Player p = (Player) arg0;
				ItemStack inHand = p.getItemInHand();
				if(inHand.getType().isBlock()) return true;
				if(arg3.length == 0) {
					int id = inHand.getTypeId();
					if((id >= 267 && id <= 286)||(id >= 290 && id <= 294) || (id >= 298 && id <= 317) || id == 346 || id == 359 ||
							id == 261 || (id >= 442 && id <= 443)){
						inHand.setDurability((short)0);
					}
				}
				if(arg3.length == 1){
					String whatType = arg3[0];
					if(whatType.equalsIgnoreCase("all")){
						for(ItemStack is : p.getInventory().getContents()){
							if(is == null) return true;
							int id = is.getTypeId();
							if((id >= 267 && id <= 286)||(id >= 290 && id <= 294) || (id >= 298 && id <= 317) || id == 346 || id == 359 ||
									id == 261 || (id >= 442 && id <= 443)){
								is.setDurability((short)0);
							}
						}
					}
				}
				
				
				
				
			}
		}
		
		
		return true;
	}

	
	
	
	
	
}
