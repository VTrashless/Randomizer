package tv.trashless.randomizer.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tv.trashless.randomizer.Main;
import tv.trashless.randomizer.inventories.SettingsGUI;

public class SettingsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player player) {

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {

                if (onlinePlayer.getOpenInventory().getTopInventory().equals(SettingsGUI.getInventory())) {
                    player.sendMessage(Main.getPrefix() + "§cCannot open settings as another player is modifying them!");
                    return true;
                } else {
                    player.openInventory(SettingsGUI.getInventory());
                }
            }
        }
        return true;
    }
}
