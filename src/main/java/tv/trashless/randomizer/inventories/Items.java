package tv.trashless.randomizer.inventories;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tv.trashless.randomizer.utils.Randomizer;

import java.util.List;

public class Items {
    public static final ItemStack BLOCK_DROPS_ITEM = new ItemStack(Material.COBBLESTONE);
    public static final ItemStack MOB_DROPS_ITEM = new ItemStack(Material.ZOMBIE_HEAD);
    public static final ItemStack LOOT_TABLES_ITEM = new ItemStack(Material.CHEST);
    public static final ItemStack RECIPES_ITEM = new ItemStack(Material.CRAFTING_TABLE);
    public static final ItemStack TRADES_ITEM = new ItemStack(Material.EMERALD);
    public static final ItemStack FISHING_ITEM = new ItemStack(Material.FISHING_ROD);
    public static final ItemStack RANDOMIZER_TYPE_ITEM = new ItemStack(Material.ENDER_EYE);
    public static final ItemStack RANDOMIZE_ITEM = new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA);
    public static final ItemStack FILLER = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    public static final ItemStack ENABLED = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    public static final ItemStack DISABLED = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    public static final ItemStack COMING_SOON = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);

    public static final ItemMeta BLOCK_DROPS_META = BLOCK_DROPS_ITEM.getItemMeta();
    public static final ItemMeta MOB_DROPS_META = MOB_DROPS_ITEM.getItemMeta();
    public static final ItemMeta LOOT_TABLES_META = LOOT_TABLES_ITEM.getItemMeta();
    public static final ItemMeta RECIPES_META = RECIPES_ITEM.getItemMeta();
    public static final ItemMeta TRADES_META = TRADES_ITEM.getItemMeta();
    public static final ItemMeta FISHING_META = FISHING_ITEM.getItemMeta();
    public static final ItemMeta RANDOMIZER_TYPE_META = RANDOMIZER_TYPE_ITEM.getItemMeta();
    public static final ItemMeta RANDOMIZE_META = RANDOMIZE_ITEM.getItemMeta();
    public static final ItemMeta FILLER_META = FILLER.getItemMeta();
    public static final ItemMeta ENABLED_META = ENABLED.getItemMeta();
    public static final ItemMeta DISABLED_META = DISABLED.getItemMeta();
    public static final ItemMeta COMING_SOON_META = COMING_SOON.getItemMeta();

    public static void create() {
        BLOCK_DROPS_META.setDisplayName("§7Toggle §9§lBlock§3§lDrop§b§lRandomizer§7!");
        MOB_DROPS_META.setDisplayName("§7Toggle §9§lMob§3§lDrop§b§lRandomizer§7!");
        LOOT_TABLES_META.setDisplayName("§7Toggle §9§lLoot§3§lTable§b§lRandomizer§7!");
        RECIPES_META.setDisplayName("§7Toggle §9§lRecipe§b§lRandomizer§7!");
        TRADES_META.setDisplayName("§7Toggle §9§lTrade§b§lRandomizer§7!");
        FISHING_META.setDisplayName("§7Toggle §9§lFishing§b§lRandomizer§7!");
        RANDOMIZER_TYPE_META.setDisplayName("§7Toggle §9§lRandomizer§b§lType§7!");
        RANDOMIZE_META.setDisplayName("§9§lRandomize items§7!");
        FILLER_META.setDisplayName(" ");
        ENABLED_META.setDisplayName("§aEnabled");
        DISABLED_META.setDisplayName("§cDisabled");
        COMING_SOON_META.setDisplayName("§4Coming soon!");

        updateItemLore(RANDOMIZER_TYPE_ITEM);

        BLOCK_DROPS_ITEM.setItemMeta(BLOCK_DROPS_META);
        MOB_DROPS_ITEM.setItemMeta(MOB_DROPS_META);
        LOOT_TABLES_ITEM.setItemMeta(LOOT_TABLES_META);
        RECIPES_ITEM.setItemMeta(RECIPES_META);
        TRADES_ITEM.setItemMeta(TRADES_META);
        FISHING_ITEM.setItemMeta(FISHING_META);
        RANDOMIZER_TYPE_ITEM.setItemMeta(RANDOMIZER_TYPE_META);
        RANDOMIZE_ITEM.setItemMeta(RANDOMIZE_META);
        FILLER.setItemMeta(FILLER_META);
        ENABLED.setItemMeta(ENABLED_META);
        DISABLED.setItemMeta(DISABLED_META);
        COMING_SOON.setItemMeta(COMING_SOON_META);
    }

    public static void updateItemLore(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(List.of("§7§oCurrently active: §9" + Randomizer.getActiveType()));
        itemStack.setItemMeta(meta);
    }
}
