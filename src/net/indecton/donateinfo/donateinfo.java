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
	
	private String formatColors(String s)
	{
		String tmp = "";
		for (int i=0; i < s.length(); i++) //For each character in String s
		{
			if (s.charAt(i) == '$') //If character is $
			{
				if (i+1 < s.length()) //If is not last character
				{
					i++;
					if ((s.charAt(i) >= '0' && s.charAt(i) <= '9') ||
							(s.charAt(i) >= 'a' && s.charAt(i) <= 'f') ||
							(s.charAt(i) >= 'k' && s.charAt(i) <= 'o') ||
							(s.charAt(i) == 'r') || (s.charAt(i) == '_')) //Is valid symbol/color/format code
					{
						if (s.charAt(i) == '_') //Is symbol code
						{
							tmp += '$';
						}
						else //Is color/format code
						{
							tmp += ChatColor.COLOR_CHAR;
							tmp += s.charAt(i);
						}
					}
				}
			}
			else //If character is not $
			{
				tmp += s.charAt(i);
			}
		}
		return tmp;
	}
	
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
