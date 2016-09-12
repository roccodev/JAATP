package tk.roccodev.jaatp.hook;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import tk.roccodev.jaatp.JaatpMain;
import tk.roccodev.jaatp.hook.hooks.PEXHook;

public class HookManager {

	private static List<PluginHook> hooks = new ArrayList<>();
	
	public static void init(){
		
		addHook(new PEXHook());
		
		
	}
	
	private static PluginHook getHook(Plugin plugin){
		for(PluginHook hook : hooks){
			if(hook.getHook() == plugin){
				return hook;
			}
		}
		return null;
	}
	
	public static PEXHook getPexHook(){
		
		return (PEXHook) getHook(Bukkit.getPluginManager().getPlugin("PermissionsEx"));
	}
	
	public static void addHook(PluginHook hook){
		
		if(hook.getHook() == null) {
			JaatpMain.instance.getLogger().warning("Could not load " + hook.getName() + " module!");
			return;
		}
		
		hooks.add(hook);
		
	}
	
	public static boolean isEnabled(Plugin plugin){
		
		return plugin != null;
		
	}
	
}
