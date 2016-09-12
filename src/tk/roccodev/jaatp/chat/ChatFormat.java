package tk.roccodev.jaatp.chat;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import tk.roccodev.jaatp.config.MainConfig;
import tk.roccodev.jaatp.hook.HookManager;

public class ChatFormat implements Listener {
	
	@EventHandler
	public void onChat(PlayerChatEvent evt){
		
		String message = evt.getMessage();
		String player = evt.getPlayer().getDisplayName();
		String pex_prefix = "";
		
		//PEX
		if(HookManager.isEnabled(Bukkit.getPluginManager().getPlugin("PermissionsEx"))){
			
			pex_prefix = HookManager.getPexHook().getPrefix(evt.getPlayer());
			
			
		}
		
		
		
		if(!MainConfig.USE_CUSTOM_FORMAT) return;
		evt.setFormat(MainConfig.CUSTOM_CHAT_FORMAT.replaceAll("\\{MESSAGE}", message).replaceAll("\\{PLAYER}", player).replaceAll("\\{PEX_PREFIX}", pex_prefix));
		
		
	}

}
