package tk.roccodev.jaatp.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.roccodev.jaatp.punishment.PunishPlayer;

public class PunishmentCommands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg1.getName().equalsIgnoreCase("ban")){
			
			if(arg3.length > 0){

				Player p = Bukkit.getPlayer(arg3[0]);
				
				if(arg3.length == 1){
				
					PunishPlayer.permBan(p, "The Ban Hammer has spoken!");
					
				}
				else if(arg3.length == 2){
				
					String reason = arg3[1];
					PunishPlayer.permBan(p, reason);
				
				}
				else{
					arg0.sendMessage("Usage: " + arg1.getUsage());
				}
				
			}
			else{
				arg0.sendMessage("Usage: " + arg1.getUsage());
			}
				
			
		}
		else if(arg1.getName().equalsIgnoreCase("unban")){
			if(arg3.length == 1){
				OfflinePlayer p = Bukkit.getOfflinePlayer(arg3[0]);
				PunishPlayer.unbanPlayer(p);
				
			}
		}
		else if(arg1.getName().equalsIgnoreCase("tempban")){
			if(arg3.length == 2){
				OfflinePlayer p = Bukkit.getOfflinePlayer(arg3[0]);
				String time = arg3[1];
				StringBuilder num = new StringBuilder();
				StringBuilder let = new StringBuilder();
				for(char c : time.toCharArray()){
					if(Character.isDigit(c)){
						num.append(c);
					}
					else{
						let.append(c);
					}
				}
				
				String nums = num.toString().trim();
				String letters = let.toString().trim();
				
				int numbers = Integer.parseInt(nums);
				int multiplier = 0;
				
				TimeUnit unit = null;
				
				if(letters.equalsIgnoreCase("d")){
					unit = TimeUnit.DAYS;
				}
				else if(letters.equalsIgnoreCase("h")){
					unit = TimeUnit.HOURS;
				}
				else if(letters.equalsIgnoreCase("y")){
					unit = TimeUnit.DAYS;
					multiplier = 364;
				}
				else if(letters.equalsIgnoreCase("m")){
					unit = TimeUnit.MINUTES;
				}
				else if(letters.equalsIgnoreCase("mo")){
					unit = TimeUnit.DAYS;
					multiplier = 29;
				}
				else if(letters.equalsIgnoreCase("w")){
					unit = TimeUnit.DAYS;
					multiplier = 6;
				}
				else{
					
				}
				
				long millis = unit.toMillis(numbers);
				long multi = 0L;
				if(multiplier == 0){ multi = 0L; } else {
				 multi = TimeUnit.DAYS.toMillis(multiplier); }
				long duration = millis + multi;
				
				if(p.getPlayer() != null){
					PunishPlayer.tempBan(p.getPlayer(), "The Ban Hammer has spoken!", System.currentTimeMillis() + duration);
				}
				else{
				
					PunishPlayer.tempOffBan(p.getName(), "The Ban Hammer has spoken!",System.currentTimeMillis() +  duration);
				}
				
				
				
				
				
			}
			else if(arg3.length >= 3){
				OfflinePlayer p = Bukkit.getOfflinePlayer(arg3[0]);
				String time = arg3[1].trim();
				StringBuilder num = new StringBuilder();
				StringBuilder let = new StringBuilder();
				for(char c : time.toCharArray()){
					if(Character.isDigit(c)){
						num.append(c);
					}
					else{
						let.append(c);
					}
				}
				
				String nums = num.toString().trim();
				String letters = let.toString().trim();
				
				
				int numbers = Integer.parseInt(nums);
				int multiplier = 0;
				
				TimeUnit unit = null;
				
				if(letters.equalsIgnoreCase("d")){
					unit = TimeUnit.DAYS;
				}
				else if(letters.equalsIgnoreCase("h")){
					unit = TimeUnit.HOURS;
				}
				else if(letters.equalsIgnoreCase("y")){
					unit = TimeUnit.DAYS;
					multiplier = 365;
				}
				else if(letters.equalsIgnoreCase("m")){
					unit = TimeUnit.MINUTES;
				}
				else if(letters.equalsIgnoreCase("mo")){
					unit = TimeUnit.DAYS;
					multiplier = 30;
				}
				else if(letters.equalsIgnoreCase("w")){
					unit = TimeUnit.DAYS;
					multiplier = 7;
				}
				else{
					
				}
				
				long millis = unit.toMillis(numbers);
				long multi = 0L;
				if(multiplier == 0){ multi = 0L; } else {
				 multi = TimeUnit.DAYS.toMillis(multiplier); }
				
				long duration = millis + multi;
				Bukkit.getLogger().info(nums + " " + let + " " + numbers + " " + letters + " " + millis + " " + new Date(System.currentTimeMillis() + duration) );
				StringBuilder sb = new StringBuilder();
				
				ArrayList<String> args = new ArrayList<>(Arrays.asList(arg3));
				args.remove(0); 
				Bukkit.getLogger().info(args.toString());
				args.remove(0);
				for(String s : args){
					sb.append(s).append(" ");
				}
				
				
				if(p.getPlayer() != null){
					PunishPlayer.tempBan(p.getPlayer(), sb.toString().trim(), System.currentTimeMillis() + duration);
				}
				else{
				
				PunishPlayer.tempOffBan(p.getName(), sb.toString().trim() , System.currentTimeMillis() + duration);
				}
			}
		}
		
		
		return true;
	}

}
