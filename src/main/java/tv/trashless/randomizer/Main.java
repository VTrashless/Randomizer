package tv.trashless.randomizer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tv.trashless.randomizer.commands.BackpackCommand;
import tv.trashless.randomizer.commands.RandomizeCommand;
import tv.trashless.randomizer.listeners.*;
import tv.trashless.randomizer.utils.SharedBackpack;
import tv.trashless.randomizer.utils.Randomizer;

public final class Main extends JavaPlugin {
    static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return "§8[§6Randomizer§8] ";
    }

    @Override
    public void onLoad() {
        Bukkit.broadcastMessage(getPrefix() + "§7Loading plugin...");
    }

    @Override
    public void onEnable() {
        Randomizer.loadRandomizedItems();
        SharedBackpack.loadSharedBackpack();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);

        pluginManager.registerEvents(new BlockDropItemListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new EntityDeathListener(), this);

        this.getCommand("randomize").setExecutor(new RandomizeCommand());
        this.getCommand("backpack").setExecutor(new BackpackCommand());

        Bukkit.broadcastMessage(getPrefix() + "§7Plugin enabled!");
    }

    @Override
    public void onDisable() {
        SharedBackpack.saveBackpack();

        Bukkit.broadcastMessage(getPrefix() + "§7Disabling plugin...");
    }
}
