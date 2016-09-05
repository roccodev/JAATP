package tk.roccodev.jaatp.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import tk.roccodev.jaatp.JaatpMain;

public class PunishCmdsCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
	 if(arg1.getName().equalsIgnoreCase("unban")){
		 if(arg3[0].isEmpty()){
		 Set<OfflinePlayer> players = Bukkit.getServer().getBannedPlayers();
		 List<String> names = new ArrayList<String>();
		 
		 for(OfflinePlayer p : players){
			 if(p == null) return null;
			 
			 names.add(p.getName());
			 
		 }
		 
		 return names;
		 }
		 else{
			 
			 String toComplete = arg3[0];
		
			 List<String> results = new ArrayList<String>();
			
			 if(JaatpMain.matchPlayer(toComplete) == null) return null;
			 for(OfflinePlayer p : JaatpMain.matchPlayer(toComplete)){
				 
				 if(p == null) return null;
				 if(p.isBanned()) results.add(p.getName());
			 }
			 return results;
			 
		 }
		 
	 }
		return null;
	}

}
