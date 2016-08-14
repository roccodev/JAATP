package tk.roccodev.jaatp;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Server.Spigot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import tk.roccodev.jaatp.commands.ChangeItemStatsCmd;
import tk.roccodev.jaatp.commands.ChangePlayerStatsCmd;
import tk.roccodev.jaatp.commands.PortableCommand;
import tk.roccodev.jaatp.commands.SeeIntoOthers;
import tk.roccodev.jaatp.config.MessageConfig;
import tk.roccodev.jaatp.var.HideAntiCheat;
import tk.roccodev.jaatp.var.Mentions;

public class JaatpMain extends JavaPlugin{
	
	

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onEnable() {
		
		//Register listeners
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Mentions(), this);
		pm.registerEvents(new PortableCommand(), this);
		pm.registerEvents(new HideAntiCheat(), this);
		
		//Load configs
		this.saveConfig();
		File msgConfig = new File(this.getDataFolder() + File.separator + "messages.yml");
		if(!msgConfig.exists())
			try {
				msgConfig.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		MessageConfig.config = YamlConfiguration.loadConfiguration(msgConfig);
		MessageConfig.def();
		MessageConfig.assignStrings();
		
		//Register Commands
		
		this.getCommand("workbench").setExecutor(new PortableCommand());
		this.getCommand("invsee").setExecutor(new SeeIntoOthers());
		this.getCommand("enderchest").setExecutor(new SeeIntoOthers());
		this.getCommand("heal").setExecutor(new ChangePlayerStatsCmd());
		this.getCommand("feed").setExecutor(new ChangePlayerStatsCmd());
		this.getCommand("level").setExecutor(new ChangePlayerStatsCmd());
		this.getCommand("enchant").setExecutor(new ChangeItemStatsCmd());
		this.getCommand("repair").setExecutor(new ChangeItemStatsCmd());
		SimplePluginManager spm=(SimplePluginManager)this.getServer().getPluginManager();
        Field f;
		try {
			f = SimplePluginManager.class.getDeclaredField("commandMap");
			f.setAccessible(true);
	        SimpleCommandMap scm=(SimpleCommandMap)f.get(spm);
	        f.setAccessible(false);
	        for(Command cmd : scm.getCommands()){
	        	cmd.setPermissionMessage(MessageConfig.PERMISSION_DENIED);
	        }
		
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	
	public static boolean isSpigot(){
		
		try{
			Spigot spigot = Bukkit.spigot();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("jaatp")){
			if(args.length == 0){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6===================================\n"
						+ "&c&lJAATP - &cJust Another Admin Tools Plugin\n&aAuthor:&7 RoccoDeveloping\n&aVersion: &7" + this.getDescription().getVersion() + "\n&6==================================="));
			}
		}
		
		return true;
	}
	
	
	
	
}
