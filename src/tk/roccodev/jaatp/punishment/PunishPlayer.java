package tk.roccodev.jaatp.punishment;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import tk.roccodev.jaatp.config.MessageConfig;
import tk.roccodev.jaatp.config.player.PlayerConfig;

public class PunishPlayer {
	 
	
	public static void permBan(Player p, String reason){
		p.setBanned(true);
		FileConfiguration config = (PlayerConfig.getPlayerConfig(p) == null) ? PlayerConfig.newPlayerConfig(p) : PlayerConfig.getPlayerConfig(p);
		config.set("banned", 1);
		config.set("banReason", reason);
		config.set("banExpire", -1L);
		String message = "";
		boolean expiry = MessageConfig.YOU_HAVE_BEEN_BANNED.contains("<EXPIRE>");
		boolean b_reason = MessageConfig.YOU_HAVE_BEEN_BANNED.contains("<REASON>");
		
		if(expiry && b_reason){
			message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<EXPIRE>", "Permanent").replaceAll("<REASON>", reason);
		}
		else if(expiry){
			message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<EXPIRE>", "Permanent");
		}
		else if(b_reason){
			message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<REASON>", reason);
		}
		PlayerConfig.saveConfig(p, config);
		kickPlayer(p, message);
		
	}
	
	public static void permOffBan(String name, String reason){
		OfflinePlayer p = Bukkit.getOfflinePlayer(name);
		p.setBanned(true);
		FileConfiguration config = (PlayerConfig.getPlayerConfig(p) == null) ? PlayerConfig.newPlayerConfig(p) : PlayerConfig.getPlayerConfig(p);
		config.set("banned", 1);
		config.set("banReason", reason);
		config.set("banExpire", -1L);
		PlayerConfig.saveConfig(p, config);
		
		
	}
	
	public static void tempBan(Player p, String reason, long millis){
		p.setBanned(true);
		FileConfiguration config = (PlayerConfig.getPlayerConfig(p) == null) ? PlayerConfig.newPlayerConfig(p) : PlayerConfig.getPlayerConfig(p);
		config.set("banned", 1);
		config.set("banReason", reason);
		config.set("banExpire", millis);
		
	
		
		
		
		
		String message = "";
		boolean expiry = MessageConfig.YOU_HAVE_BEEN_BANNED.contains("<EXPIRE>");
		boolean b_reason = MessageConfig.YOU_HAVE_BEEN_BANNED.contains("<REASON>");
		
		if(expiry && b_reason){
			message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<EXPIRE>", new Date(millis).toString()).replaceAll("<REASON>", reason);
		}
		else if(expiry){
			message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<EXPIRE>", new Date(millis).toString());
		}
		else if(b_reason){
			message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<REASON>", reason);
		}
		kickPlayer(p, message);
		PlayerConfig.saveConfig(p, config);
	}
	
	public static void tempOffBan(String name, String reason, long millis){
		OfflinePlayer p = Bukkit.getOfflinePlayer(name);
		p.setBanned(true);
		FileConfiguration config = (PlayerConfig.getPlayerConfig(p) == null) ? PlayerConfig.newPlayerConfig(p) : PlayerConfig.getPlayerConfig(p);
		config.set("banned", 1);
		config.set("banReason", reason);
		config.set("banExpire", millis);
		PlayerConfig.saveConfig(p, config);
		
		
	}
	public static void kickPlayer(Player p, String reason){
		p.kickPlayer(reason);
	}
	
	public static void mutePlayer(Player p, String reason, long millis){
		
		FileConfiguration config = (PlayerConfig.getPlayerConfig(p) == null) ? PlayerConfig.newPlayerConfig(p) : PlayerConfig.getPlayerConfig(p);
		config.set("muted", 1);
		config.set("muteReason", reason);
		config.set("muteExpire", millis);
		PlayerConfig.saveConfig(p, config);
	}
	
	public static void unbanPlayer(OfflinePlayer p){
		p.setBanned(false);
		FileConfiguration config = (PlayerConfig.getPlayerConfig(p) == null) ? PlayerConfig.newPlayerConfig(p) : PlayerConfig.getPlayerConfig(p);
		config.set("banned", 0);
		config.set("banReason", "");
		config.set("banExpire", 0L);
	}
	

}
