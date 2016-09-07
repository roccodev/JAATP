package tk.roccodev.jaatp.config;

import org.bukkit.configuration.file.FileConfiguration;

import tk.roccodev.jaatp.JaatpMain;

public class MainConfig {

	public static FileConfiguration config = null;
	
	
	
	public static boolean ACCEPT_BETA_BUILDS;
	
	
	
	
	public static void initVar(){
		ACCEPT_BETA_BUILDS = config.getBoolean("accept-beta-builds");
	}
	
	public static void def(){
		
		config.addDefault("accept-beta-builds", false);
		config.options().copyDefaults(true);
		JaatpMain.instance.saveConfig();
		
	}
	
	
}
