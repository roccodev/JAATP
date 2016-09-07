package tk.roccodev.jaatp;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server.Spigot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import tk.roccodev.jaatp.commands.ChangeItemStatsCmd;
import tk.roccodev.jaatp.commands.ChangePlayerStatsCmd;
import tk.roccodev.jaatp.commands.EggHatching;
import tk.roccodev.jaatp.commands.PortableCommand;
import tk.roccodev.jaatp.commands.PunishCmdsCompleter;
import tk.roccodev.jaatp.commands.PunishmentCommands;
import tk.roccodev.jaatp.commands.SeeIntoOthers;
import tk.roccodev.jaatp.config.MainConfig;
import tk.roccodev.jaatp.config.MessageConfig;
import tk.roccodev.jaatp.config.player.PlayerConfig;
import tk.roccodev.jaatp.punishment.PunishPlayer;
import tk.roccodev.jaatp.updater.Updater;
import tk.roccodev.jaatp.var.HideAntiCheat;
import tk.roccodev.jaatp.var.Mentions;

public class JaatpMain extends JavaPlugin implements Listener {
	
	public static JaatpMain instance;
	
	// Hatching eggs is good for health. -> Pokemon GO
	private static HashMap<Player, Integer> eggHatches = new HashMap<>();
	
	public JaatpMain(){
		instance = this;
	}
	

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onEnable() {
		
		//Check for update
		getLogger().info("Checking for updates...");
		if(Updater.checkForUpdate()){
			ConsoleCommandSender ccs = Bukkit.getConsoleSender();
			ccs.sendMessage(ChatColor.translateAlternateColorCodes('&', "Update found! Go to our Bukkit page or run &c/jaatp update&r to update!"));
		}
		else{
			getLogger().info("No update found. Good!");
		}
		
		
		//Register listeners
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Mentions(), this);
		pm.registerEvents(new PortableCommand(), this);
		pm.registerEvents(new HideAntiCheat(), this);
		pm.registerEvents(this, this);
		
		//Load folders
		
		File playersDir = new File(this.getDataFolder() + File.separator + "players");
		if(!playersDir.exists()) playersDir.mkdir();
		
		
		//Load configs
		MainConfig.config = this.getConfig();
		MainConfig.def();
		MainConfig.initVar();
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
		this.getCommand("ban").setExecutor(new PunishmentCommands());
		this.getCommand("unban").setExecutor(new PunishmentCommands());
		this.getCommand("unban").setTabCompleter(new PunishCmdsCompleter());
		this.getCommand("tempban").setExecutor(new PunishmentCommands());
		this.getCommand("warn").setExecutor(new PunishmentCommands());
		this.getCommand("warns").setExecutor(new PunishmentCommands());
		this.getCommand("delwarn").setExecutor(new PunishmentCommands());
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
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("oink")){
					if(sender instanceof Player){
					if(!eggHatches.containsKey((Player)sender)){
						eggHatches.put((Player) sender, 0);
						
					}
					
					if(EggHatching.hatchEgg((Player) sender, eggHatches.get((Player) sender))){
						
						
						
					}
					else{
						
						int bf = eggHatches.get((Player) sender);
						bf++;
						eggHatches.remove((Player) sender);
						eggHatches.put((Player) sender, bf);
					}
					
					}
				}
				else if(args[0].equalsIgnoreCase("update")){
					if(sender.hasPermission("jaatp.update")){
						
						try {
							Updater.updateForce();
							sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Updated succesfully! Now restart or reload the server/plugin to apply changes.");
						} catch (IOException e) {
							
							// TODO Auto-generated catch block
							sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Whoops! It seems something went wrong when updating! See console for details.");
							e.printStackTrace();
						}
						
					}
					else{
						sender.sendMessage("You don't have permission");
					}
				}
				
			}
		}
		
		return true;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if(!PlayerConfig.isRegistered(e.getPlayer())){
			PlayerConfig.newPlayerConfig(e.getPlayer());
		}
		
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		Player p = e.getPlayer();
		if(p.isBanned()){
			FileConfiguration config = PlayerConfig.getPlayerConfig(p);
			long millis = config.getLong("banExpire");
			String reason = config.getString("banReason");
		
			
			
			
			String message = "";
			boolean expiry = MessageConfig.YOU_HAVE_BEEN_BANNED.contains("<EXPIRE>");
			boolean b_reason = MessageConfig.YOU_HAVE_BEEN_BANNED.contains("<REASON>");
			
			if(millis <= System.currentTimeMillis()){
				PunishPlayer.unbanPlayer(p);
			}
			
			
			if(expiry && b_reason){
				if(millis != -1L){
					message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<EXPIRE>", new Date(millis).toString()).replaceAll("<REASON>", reason);
					}
					else{
						message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<EXPIRE>", "Permanent").replaceAll("<REASON>", reason);
					}
				
			}
			else if(expiry){
				if(millis != -1L){
				message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<EXPIRE>", new Date(millis).toString());
				}
				else{
					message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<EXPIRE>", "Permanent");
				}
			}
			else if(b_reason){
				message = MessageConfig.YOU_HAVE_BEEN_BANNED.replaceAll("<REASON>", reason);
			}
			
			e.setKickMessage(message);
			e.disallow(Result.KICK_BANNED, message);
		}
	}
	
	public static Set<OfflinePlayer> matchPlayer(String name){
		
		
		
		
		Set<OfflinePlayer> results = new HashSet<>();
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
			
			if(Bukkit.getOfflinePlayers().length == 0) return null;
			if(p.getName().toUpperCase().contains(name.toUpperCase())){
				results.add(p);
			}
		}
		
		if(results.isEmpty()) return null;
		return results;
		
	}
	
}
