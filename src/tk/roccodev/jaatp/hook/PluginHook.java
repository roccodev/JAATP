package tk.roccodev.jaatp.hook;

import org.bukkit.plugin.Plugin;

public interface PluginHook {
	
	public void register();
	public Plugin getHook();
	
	public String getName();

}
