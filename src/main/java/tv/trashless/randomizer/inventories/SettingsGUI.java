package tv.trashless.randomizer.inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import tv.trashless.randomizer.utils.Randomizer;

public class SettingsGUI {
    private static final Inventory SETTINGS_INVENTORY = Bukkit.createInventory(null, 54, "Settings");

    public static final int SLOT_BLOCK_DROPS = 11;
    public static final int SLOT_MOB_DROPS = 13;
    public static final int SLOT_LOOT_TABLES = 15;
    public static final int SLOT_RECIPES = 29;
    public static final int SLOT_TRADES = 31;
    public static final int SLOT_FISHING = 33;
    public static final int SLOT_RANDOMIZER_TYPE = 8;
    public static final int SLOT_RANDOMIZE = 53;

    private static final int SLOT_BLOCK_DROPS_INDICATOR = SLOT_BLOCK_DROPS + 9;
    private static final int SLOT_MOB_DROPS_INDICATOR = SLOT_MOB_DROPS + 9;
    private static final int SLOT_LOOT_TABLES_INDICATOR = SLOT_LOOT_TABLES + 9;
    private static final int SLOT_RECIPES_INDICATOR = SLOT_RECIPES + 9;
    private static final int SLOT_TRADES_INDICATOR = SLOT_TRADES + 9;
    private static final int SLOT_FISHING_INDICATOR = SLOT_FISHING + 9;

    public static void create() {
        for (int slot = 0; slot < SETTINGS_INVENTORY.getSize(); slot++) {
            SETTINGS_INVENTORY.setItem(slot, Items.FILLER);
        }

        SETTINGS_INVENTORY.setItem(SLOT_BLOCK_DROPS, Items.BLOCK_DROPS_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_MOB_DROPS, Items.MOB_DROPS_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_LOOT_TABLES, Items.LOOT_TABLES_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_RECIPES, Items.RECIPES_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_TRADES, Items.TRADES_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_FISHING, Items.FISHING_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_RANDOMIZER_TYPE, Items.RANDOMIZER_TYPE_ITEM);
        SETTINGS_INVENTORY.setItem(SLOT_RANDOMIZE, Items.RANDOMIZE_ITEM);

        update();
    }

    public static void update() {
        Items.updateItemLore(SETTINGS_INVENTORY.getItem(SLOT_RANDOMIZER_TYPE));
        updateIndicator("randomize_block_drops", SLOT_BLOCK_DROPS_INDICATOR);
        updateIndicator("randomize_mob_drops", SLOT_MOB_DROPS_INDICATOR);
        updateIndicator("randomize_loot_tables", SLOT_LOOT_TABLES_INDICATOR);
        updateIndicator("randomize_recipes", SLOT_RECIPES_INDICATOR);
        updateIndicator("randomize_trades", SLOT_TRADES_INDICATOR);
        updateIndicator("randomize_fishing", SLOT_FISHING_INDICATOR);
    }

    private static void updateIndicator(String setting, int indicatorSlot) {
        if (Randomizer.containsSetting(setting)) {

            if (Randomizer.getBooleanSetting(setting)) {
                SETTINGS_INVENTORY.setItem(indicatorSlot, Items.ENABLED);
            } else {
                SETTINGS_INVENTORY.setItem(indicatorSlot, Items.DISABLED);
            }

        } else {
            SETTINGS_INVENTORY.setItem(indicatorSlot, Items.COMING_SOON);
        }
    }

    public static Inventory getInventory() {
        update();
        return SETTINGS_INVENTORY;
    }
}
