package tv.trashless.randomizer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tv.trashless.randomizer.commands.BackpackCommand;
import tv.trashless.randomizer.commands.RandomizeCommand;
import tv.trashless.randomizer.commands.SettingsCommand;
import tv.trashless.randomizer.listeners.*;
import tv.trashless.randomizer.utils.Settings;
import tv.trashless.randomizer.inventories.SettingsInventory;
import tv.trashless.randomizer.inventories.SharedBackpackInventory;
import tv.trashless.randomizer.utils.Randomizer;

import javax.annotation.Nonnull;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    static Main instance;
    private final Logger logger = Bukkit.getLogger();

    @Override
    public void onLoad() {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Loading plugin...");

        //LOAD CONFIGS & INVENTORIES
        Randomizer.loadRandomizedItems();
        Settings.loadAll();
        SharedBackpackInventory.loadContents();

        //CREATE INVENTORIES
        SettingsInventory.create();

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Plugin loaded!");
    }

    @Override
    public void onEnable() {
        /*
            TODO:
         */

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Enabling plugin...");

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);

        pluginManager.registerEvents(new BlockDropItemListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new EntityDeathListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);

        this.getCommand("randomize").setExecutor(new RandomizeCommand());
        this.getCommand("backpack").setExecutor(new BackpackCommand());
        this.getCommand("settings").setExecutor(new SettingsCommand());

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Plugin enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Disabling plugin...");

        //SAVE CONFIGS
        Settings.saveAll();
        Randomizer.saveRandomizedItems();

        SharedBackpackInventory.save();

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Plugin disabled!");
    }

    public static Main getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return "§8[§6Randomizer§8] ";
    }

    @Nonnull
    @Override
    public Logger getLogger() {
        return logger;
    }
}
