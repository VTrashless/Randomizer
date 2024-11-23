package tv.trashless.randomizer.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import tv.trashless.randomizer.Main;
import tv.trashless.randomizer.utils.Randomizer;

public class RandomizeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Bukkit.broadcastMessage(Main.getPrefix() + "§7Randomizing items...");
        Randomizer.createRandomizedItems();
        Bukkit.broadcastMessage(Main.getPrefix() + "§7Finished randomizing items!");
        return true;
    }
}
