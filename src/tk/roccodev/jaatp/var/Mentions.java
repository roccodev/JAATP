package tk.roccodev.jaatp.var;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import tk.roccodev.jaatp.config.MessageConfig;

public class Mentions implements Listener{
	
	
	@EventHandler
	public void onChat(PlayerChatEvent e){
		
		if(!e.getMessage().contains("@")) return;
		
		ArrayList<String> words = new ArrayList<>(Arrays.asList(e.getMessage().split(" ")));
		ArrayList<String> mentions = new ArrayList<>();
		
		for(String word : words){
			if(word.contains("@")){
				
				mentions.add(word.split("@")[1]);
			}
		}
		
		for(String pname : mentions){
			String before = new String(pname);
			Player p = Bukkit.getPlayer(pname);
			if(p == null){
				e.getPlayer().sendMessage(MessageConfig.ERROR_PLAYER_MENTION_NOT_ONLINE);
				return;
			}
			pname = ChatColor.AQUA + pname;
			e.setMessage(e.getMessage().replaceAll("@" + before,ChatColor.AQUA + "@" + pname + ChatColor.RESET));
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1F, 1F);
		}
		
		
		
	}

}
