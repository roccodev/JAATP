package tk.roccodev.jaatp.hook.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import tk.roccodev.jaatp.hook.PluginHook;

public class PEXHook implements PluginHook, Listener {

	@Override
	public void register() {
		
		
		
	}

	@Override
	public Plugin getHook() {
		// TODO Auto-generated method stub
		return Bukkit.getPluginManager().getPlugin("PermissionsEx");
	}

	
	public PEXHook(){
		
		register();
	}
	
	@Override
	public String getName(){
		return "PermissionsEx";
	}
	
	public String getRank(Player player){
		
		PermissionUser user = PermissionsEx.getUser(player);
		
		if(user.getGroupNames() != null){
			return user.getGroupNames()[0];
		}
		return null;
		
	}
	
	public String getPrefix(Player player){
		PermissionUser user = PermissionsEx.getUser(player);

		
		return user.getPrefix();
	}
	
}
