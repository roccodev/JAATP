package tk.roccodev.jaatp.updater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import com.google.common.io.Files;

import tk.roccodev.jaatp.JaatpMain;
import tk.roccodev.jaatp.PluginUtils;

public class Updater {

	public static boolean checkForUpdate(){
		
		InputStream updater = null;
		
		try {
			updater = getUpdaterHoloHolo();
		} catch (IOException e) {
			JaatpMain.instance.getLogger().log(Level.WARNING, "Could not check for updates. :<");
			return false;
		}
		
		BufferedReader file = new BufferedReader(new InputStreamReader(updater));
		String holoholover = "";
		try {
			holoholover = file.readLine();
		} catch (IOException e) {
			
			JaatpMain.instance.getLogger().log(Level.WARNING, "Could not check for updates. :<");
			return false;
		}
		try {
			file.close();
		} catch (IOException e) {
			JaatpMain.instance.getLogger().log(Level.WARNING, "Could not check for updates. :<");
			return false;
		}
		
		
		String[] split = holoholover.split(":");
		
		double current = Double.parseDouble(JaatpMain.instance.getDescription().getVersion());
		double last = Double.parseDouble(split[1]);
		
		if(current < last){
			return true;
		}
		else{
			return false;
		}
		
	
		
	}
	
	private static InputStream getUpdaterHoloHolo() throws IOException{
		
		URL url = new URL("http://roccodeveloping.tk/holoholo/HoloHoloSfidaVer.txt");
		return url.openStream();
		
	}
	
	public static void updateForce() throws IOException{
		URL url = new URL("http://roccodeveloping.tk/holoholo/HoloHoloLastLink.txt");
		BufferedReader file = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String link = file.readLine();
		file.close();
		
		URL url2 = new URL(link);
		ReadableByteChannel channel = Channels.newChannel(url2.openStream());
		if(!new File(JaatpMain.instance.getDataFolder() + "/tmp/").exists()){
			new File(JaatpMain.instance.getDataFolder() + "/tmp/").mkdir();
		}
		FileOutputStream fos = new FileOutputStream(JaatpMain.instance.getDataFolder() + File.separator +"tmp" + File.separator + "lastversion.jar");
		fos.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
		
		Files.copy(new File(JaatpMain.instance.getDataFolder() + File.separator + "tmp" + File.separator + "lastversion.jar"), new File("plugins" + File.separator + "JAATP.jar"));
		
		
		
		
		
		
	}
	
	
}
