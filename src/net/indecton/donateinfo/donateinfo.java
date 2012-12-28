package net.indecton.donateinfo;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
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
	
	public void onEnable()
	{
		loadConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args)
	{
		if (args.length >= 1) //Has 1 or more arguments
		{
			if (args[0].equalsIgnoreCase("reload"))
			{
				if (sender.hasPermission("donateinfo.reload"))
				{
					reloadConfig();
					loadConfig();
					sender.sendMessage(ChatColor.GREEN + "Config Reloaded.");
				}
			}
		}
		else
		{
			for (short c=0; c < msg.size(); c++)
			{
				sender.sendMessage(msg.get(c).replace('$', '¤'));
			}
		}
		return true;
	}
}
