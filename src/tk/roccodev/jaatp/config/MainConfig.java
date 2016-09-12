package tk.roccodev.jaatp.config;

import org.bukkit.configuration.file.FileConfiguration;

import tk.roccodev.jaatp.JaatpMain;

public class MainConfig {

	public static FileConfiguration config = null;
	
	
	
	public static boolean ACCEPT_BETA_BUILDS;
	public static boolean USE_CUSTOM_FORMAT;
	public static String CUSTOM_CHAT_FORMAT;
	
	
	
	
	public static void initVar(){
		ACCEPT_BETA_BUILDS = config.getBoolean("accept-beta-builds");
		USE_CUSTOM_FORMAT = config.getBoolean("use-custom-chat-format");
		CUSTOM_CHAT_FORMAT = config.getString("custom-chat-format");
	}
	
	public static void def(){
		
		config.addDefault("accept-beta-builds", false);
		config.addDefault("use-custom-chat-format", false);
		config.addDefault("custom-chat-format", "<{PLAYER}> {MESSAGE}");
		config.options().copyDefaults(true);
		JaatpMain.instance.saveConfig();
		
	}
	
	
}
