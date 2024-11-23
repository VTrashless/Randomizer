package tv.trashless.randomizer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tv.trashless.randomizer.utils.SettingsInventory;

public class SettingsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            player.openInventory(SettingsInventory.getInventory());
        }
        return true;
    }
}
