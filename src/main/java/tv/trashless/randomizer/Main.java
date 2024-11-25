package tv.trashless.randomizer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tv.trashless.randomizer.commands.BackpackCommand;
import tv.trashless.randomizer.commands.RandomizeCommand;
import tv.trashless.randomizer.commands.SettingsCommand;
import tv.trashless.randomizer.inventories.Items;
import tv.trashless.randomizer.listeners.*;
import tv.trashless.randomizer.inventories.SettingsGUI;
import tv.trashless.randomizer.inventories.SharedBackpackInventory;
import tv.trashless.randomizer.utils.Randomizer;

public final class Main extends JavaPlugin {
    static Main instance;

    @Override
    public void onLoad() {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Loading plugin...");

        //LOAD CONFIGS
        Randomizer.loadRandomizedItems();
        Randomizer.loadSettings();
        SharedBackpackInventory.loadContents();

        //CREATE ITEMS & INVENTORIES
        Items.create();
        SettingsGUI.create();

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Plugin loaded!");
    }

    @Override
    public void onEnable() {
        /*
            TODO:   - LootTableRandomizer
                    - RecipeRandomizer
                    - TradeRandomizer
         */

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Enabling plugin...");

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);

        pluginManager.registerEvents(new BlockDropItemListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new EntityDeathListener(), this);
        pluginManager.registerEvents(new PlayerFishListener(), this);

        pluginManager.registerEvents(new InventoryClickListener(), this);

        this.getCommand("randomize").setExecutor(new RandomizeCommand());
        this.getCommand("backpack").setExecutor(new BackpackCommand());
        this.getCommand("settings").setExecutor(new SettingsCommand());

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Plugin enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Disabling plugin...");

        //SAVE CONFIGS & INVENTORIES
        Randomizer.saveRandomizedItems();
        Randomizer.saveSettings();
        SharedBackpackInventory.saveContents();

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§7Plugin disabled!");
    }

    public static Main getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return "§8[§6Randomizer§8] ";
    }
}
