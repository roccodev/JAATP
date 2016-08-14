package tk.roccodev.jaatp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.roccodev.jaatp.config.MessageConfig;

public class ChangePlayerStatsCmd implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

		if(arg1.getName().equalsIgnoreCase("heal")){
			if(arg3.length == 1){
				Player target = Bukkit.getPlayer(arg3[0]);
				if(target == null){arg0.sendMessage(MessageConfig.ERROR_PLAYER_NOT_FOUND); return true;}
				
				target.setHealth(20D);
				String msg = new String(MessageConfig.PLAYER_HEALED_YOU);
				target.sendMessage(msg.replaceAll("<PLAYER>", arg0.getName()));
				arg0.sendMessage(MessageConfig.YOU_HEALED.replaceAll("<PLAYER>", target.getName()));
			}
			else{
				if(arg0 instanceof Player){
					Player p = (Player) arg0;
					p.setHealth(20D);
					arg0.sendMessage(MessageConfig.YOU_HEALED.replaceAll("<PLAYER>", p.getName()));
				}
				else{
					arg0.sendMessage(MessageConfig.ERROR_CONSOLE_CANNOT_BE_HEALED);
				}
			}
		}
		else if(arg1.getName().equalsIgnoreCase("feed")){
			if(arg3.length == 1){
				Player target = Bukkit.getPlayer(arg3[0]);
				if(target == null){arg0.sendMessage(MessageConfig.ERROR_PLAYER_NOT_FOUND); return true;}
				
				target.setFoodLevel(20);
				String msg = new String(MessageConfig.PLAYER_HEALED_YOU);
				target.sendMessage(msg.replaceAll("<PLAYER>", arg0.getName()));
				arg0.sendMessage(MessageConfig.YOU_FED.replaceAll("<PLAYER>", target.getName()));
			}
			else{
				if(arg0 instanceof Player){
					Player p = (Player) arg0;
					p.setFoodLevel(20);
					arg0.sendMessage(MessageConfig.YOU_FED.replaceAll("<PLAYER>", p.getName()));
				}
				else{
					arg0.sendMessage(MessageConfig.ERROR_CONSOLE_CANNOT_BE_HEALED);
				}
			}
		}
		else if(arg1.getName().equalsIgnoreCase("level")){
			if(arg3.length == 2){
				Player target = Bukkit.getPlayer(arg3[1]);
				if(target == null){arg0.sendMessage(MessageConfig.ERROR_PLAYER_NOT_FOUND); return true;}
				if(arg3[0].equalsIgnoreCase("up")){
					
					target.giveExpLevels(1);
					arg0.sendMessage(MessageConfig.SUCCESFULLY_CHANGED_EXP.replaceAll("<PLAYER>", target.getName()));
					
				}else if(arg3[0].equalsIgnoreCase("down")){
					
					target.giveExpLevels(-1);
					arg0.sendMessage(MessageConfig.SUCCESFULLY_CHANGED_EXP.replaceAll("<PLAYER>", target.getName()));
				}
				else{
					arg0.sendMessage("Usage: " + arg1.getUsage());
				}
				
			}
			else{
				if(arg0 instanceof Player){
					Player p = (Player) arg0;
					if(arg3[0].equalsIgnoreCase("up")){
						
						p.giveExpLevels(1);
						arg0.sendMessage(MessageConfig.SUCCESFULLY_CHANGED_EXP.replaceAll("<PLAYER>", p.getName()));
						
					}else if(arg3[0].equalsIgnoreCase("down")){
						
						p.giveExpLevels(-1);
						arg0.sendMessage(MessageConfig.SUCCESFULLY_CHANGED_EXP.replaceAll("<PLAYER>", p.getName()));
					}
					else{
						arg0.sendMessage("Usage: " + arg1.getUsage());
					}
					
				}
				else{
					arg0.sendMessage(MessageConfig.ERROR_CONSOLE_CANNOT_BE_HEALED);
				}
			}
		}
		
		
		return true;
	}

}
