package tk.roccodev.jaatp.config.player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import tk.roccodev.jaatp.JaatpMain;

public class PlayerConfig {
	
	
	public static FileConfiguration newPlayerConfig(OfflinePlayer p){
		
		File f = new File(JaatpMain.instance.getDataFolder() + File.separator + "players" + File.separator + p.getUniqueId().toString() + ".yml");
		if(!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		config.addDefault("name", p.getName());
		config.addDefault("banned", 0);
		config.addDefault("muted", 0);
		config.addDefault("banReason", "");
		config.addDefault("banExpire", 0L);
		config.addDefault("muteReason", "");
		config.addDefault("muteExpire", 0L);
		config.addDefault("bannedBy", "");
		config.addDefault("mutedBy", "");
		config.addDefault("warns", new ArrayList<String>());
		config.options().copyDefaults(true);
		try {
			config.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return config;
		
	}
	
	public static FileConfiguration getPlayerConfig(OfflinePlayer p){
		
		File f = new File(JaatpMain.instance.getDataFolder() + File.separator + "players" + File.separator + p.getUniqueId().toString() + ".yml");
		
		return YamlConfiguration.loadConfiguration(f);
	}
	
	public static boolean isRegistered(OfflinePlayer p){
		
		return new File(JaatpMain.instance.getDataFolder() + File.separator + "players" + File.separator + p.getUniqueId().toString() + ".yml").exists();
		
	}
	
	public static void saveConfig(OfflinePlayer p, FileConfiguration config){
		
		try {
			config.save(new File(JaatpMain.instance.getDataFolder() + File.separator + "players" + File.separator + p.getUniqueId().toString() + ".yml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
