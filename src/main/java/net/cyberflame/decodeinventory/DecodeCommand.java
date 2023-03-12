package net.cyberflame.decodeinventory;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DecodeCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s,
	                         @NotNull String[] strings)
	{
		if (commandSender instanceof ConsoleCommandSender) {
			try
				{
					Player player = DecodeInventoryApp.getInstance().getServer().getPlayer(strings[0]);
					assert player != null;
					player.getInventory().addItem(DecodeInventoryApp.decodeInventory(strings[1]));
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
