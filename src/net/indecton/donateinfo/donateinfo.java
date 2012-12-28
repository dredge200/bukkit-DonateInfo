package net.indecton.donateinfo;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class donateinfo extends JavaPlugin
{
	private List<String> msg;
	
	private void loadConfig()
	{
		if (new File("plugins/donateinfo/config.yml").exists() == false)
		{
			saveDefaultConfig();
		}
		msg = getConfig().getStringList("message");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args)
	{
		
		return true;
	}
}
