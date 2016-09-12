package tk.roccodev.jaatp.chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import tk.roccodev.jaatp.config.MainConfig;

public class ChatFormat implements Listener {
	
	@EventHandler
	public void onChat(PlayerChatEvent evt){
		
		String message = evt.getMessage();
		String player = evt.getPlayer().getDisplayName();
		
		if(!MainConfig.USE_CUSTOM_FORMAT) return;
		evt.setFormat(MainConfig.CUSTOM_CHAT_FORMAT.replaceAll("{MESSAGE}", message).replaceAll("{PLAYER}", player));
		
		
	}

}
