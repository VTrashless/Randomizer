package tv.trashless.randomizer.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SettingsInventory {
    private static final Inventory SETTINGS_INVENTORY = Bukkit.createInventory(null, 54, "Settings");

    public static final int SLOT_BLOCK_DROPS = 11;
    public static final int SLOT_MOB_DROPS = 13;
    public static final int SLOT_LOOT_TABLES = 15;
    public static final int SLOT_RECIPES = 30;
    public static final int SLOT_TRADES = 32;
    public static final int SLOT_RANDOMIZE = 53;
    private static final int SLOT_BLOCK_DROPS_INDICATOR = SLOT_BLOCK_DROPS + 9;
    private static final int SLOT_MOB_DROPS_INDICATOR = SLOT_MOB_DROPS + 9;
    private static final int SLOT_LOOT_TABLES_INDICATOR = SLOT_LOOT_TABLES + 9;
    private static final int SLOT_RECIPES_INDICATOR = SLOT_RECIPES + 9;
    private static final int SLOT_TRADES_INDICATOR = SLOT_TRADES + 9;

    private static final ItemStack BLOCK_DROPS_ITEM = new ItemStack(Material.COBBLESTONE);
    private static final ItemStack MOB_DROPS_ITEM = new ItemStack(Material.ZOMBIE_HEAD);
    private static final ItemStack LOOT_TABLES_ITEM = new ItemStack(Material.CHEST);
    private static final ItemStack RECIPES_ITEM = new ItemStack(Material.CRAFTING_TABLE);
    private static final ItemStack TRADES_ITEM = new ItemStack(Material.EMERALD);
    private static final ItemStack RANDOMIZE_ITEM = new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA);
    private static final ItemStack FILLER = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private static final ItemStack ENABLED = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    private static final ItemStack DISABLED = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    private static final ItemStack COMING_SOON = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);

    private static final ItemMeta BLOCK_DROPS_META = BLOCK_DROPS_ITEM.getItemMeta();
    private static final ItemMeta MOB_DROPS_META = MOB_DROPS_ITEM.getItemMeta();
    private static final ItemMeta LOOT_TABLES_META = LOOT_TABLES_ITEM.getItemMeta();
    private static final ItemMeta RECIPES_META = RECIPES_ITEM.getItemMeta();
    private static final ItemMeta TRADES_META = TRADES_ITEM.getItemMeta();
    private static final ItemMeta RANDOMIZE_META = RANDOMIZE_ITEM.getItemMeta();
    private static final ItemMeta FILLER_META = FILLER.getItemMeta();
    private static final ItemMeta ENABLED_META = ENABLED.getItemMeta();
    private static final ItemMeta DISABLED_META = DISABLED.getItemMeta();
    private static final ItemMeta COMING_SOON_META = COMING_SOON.getItemMeta();

    public static void loadInventory() {
        BLOCK_DROPS_META.setDisplayName("§7Toggle §9§lBlock§3§lDrop§b§lRandomizer§7!");
        MOB_DROPS_META.setDisplayName("§7Toggle §9§lMob§3§lDrop§b§lRandomizer§7!");
        LOOT_TABLES_META.setDisplayName("§7Toggle §9§lLoot§3§lTable§b§lRandomizer§7!");
        RECIPES_META.setDisplayName("§7Toggle §9§lRecipe§b§lRandomizer§7!");
        TRADES_META.setDisplayName("§7Toggle §9§lTrade§b§lRandomizer§7!");
        RANDOMIZE_META.setDisplayName("§9§lRandomize items§7!");
        FILLER_META.setDisplayName(" ");
        ENABLED_META.setDisplayName("§aEnabled");
        DISABLED_META.setDisplayName("§cDisabled");
        COMING_SOON_META.setDisplayName("§4Coming soon!");

        BLOCK_DROPS_ITEM.setItemMeta(BLOCK_DROPS_META);
        MOB_DROPS_ITEM.setItemMeta(MOB_DROPS_META);
        LOOT_TABLES_ITEM.setItemMeta(LOOT_TABLES_META);
        RECIPES_ITEM.setItemMeta(RECIPES_META);
        TRADES_ITEM.setItemMeta(TRADES_META);
        RANDOMIZE_ITEM.setItemMeta(RANDOMIZE_META);
        FILLER.setItemMeta(FILLER_META);
        ENABLED.setItemMeta(ENABLED_META);
        DISABLED.setItemMeta(DISABLED_META);
        COMING_SOON.setItemMeta(COMING_SOON_META);

        for (int slot = 0; slot < SETTINGS_INVENTORY.getSize(); slot++) {
            SETTINGS_INVENTORY.setItem(slot, FILLER);
        }

        SETTINGS_INVENTORY.setItem(SLOT_BLOCK_DROPS, BLOCK_DROPS_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_MOB_DROPS, MOB_DROPS_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_LOOT_TABLES, LOOT_TABLES_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_RECIPES, RECIPES_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_TRADES, TRADES_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_RANDOMIZE, RANDOMIZE_ITEM);

        updateInventory();
    }

    public static void updateInventory() {
        putEnabledOrDisabled("randomize_block_drops", SLOT_BLOCK_DROPS_INDICATOR);
        putEnabledOrDisabled("randomize_mob_drops", SLOT_MOB_DROPS_INDICATOR);
        putEnabledOrDisabled("randomize_loot_tables", SLOT_LOOT_TABLES_INDICATOR);
        putEnabledOrDisabled("randomize_recipes", SLOT_RECIPES_INDICATOR);
        putEnabledOrDisabled("randomize_trades", SLOT_TRADES_INDICATOR);
    }

    private static void putEnabledOrDisabled(String path, int indicatorSlot) {
        if (Settings.getConfig().contains(path)) {
            if ((boolean) Settings.getConfig().get(path)) {
                SETTINGS_INVENTORY.setItem(indicatorSlot, ENABLED);
            } else {
                SETTINGS_INVENTORY.setItem(indicatorSlot, DISABLED);
            }
        } else {
            SETTINGS_INVENTORY.setItem(indicatorSlot, COMING_SOON);
        }
    }

    public static Inventory getInventory() {
        updateInventory();
        return SETTINGS_INVENTORY;
    }
}
