package tk.roccodev.jaatp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import tk.roccodev.jaatp.chat.ChatUtils;
import tk.roccodev.jaatp.config.MessageConfig;

public class BroadcastCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg2.equalsIgnoreCase("bc") || arg2.equalsIgnoreCase("broadcast")){
			if(arg3.length >= 1){
				
				StringBuilder sb = new StringBuilder();
				for(String s : arg3){
					
					sb.append(s).append(" ");
					
				}
				
				
				ChatUtils.broadcastServerMessage(MessageConfig.BC_PREFIX + sb.toString().trim());
				
				
			}
			else{
				arg0.sendMessage("Usage: " + arg1.getUsage()); 
			}
		}
		return true;
	}

}
