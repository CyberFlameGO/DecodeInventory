package net.cyberflame.decodeinventory;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static net.cyberflame.decodeinventory.DecodeInventoryApp.getInstance;

public class DecodeCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s,
	                         @NotNull String[] strings)
	{
		if (commandSender instanceof ConsoleCommandSender) {
			try
				{
					Player player = getInstance().getServer().getPlayer(strings[0]);
					assert player != null;
					getInstance().reloadConfig();
					player.getInventory().addItem(DecodeInventoryApp.decodeInventory(getInstance().getConfig().getString("b64-str")));
					player.updateInventory();
				}
			catch (IOException e)
				{
					throw new RuntimeException(e);
				}
		}
	else return false;
		return true;
	}
}
