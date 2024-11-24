package tv.trashless.randomizer.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tv.trashless.randomizer.utils.Config;

import java.io.IOException;

public class SharedBackpackInventory {
    private static final Inventory SHARED_BACKPACK_INVENTORY = Bukkit.createInventory(null, 27, "Shared Backpack");
    private static final Config SHARED_BACKPACK_CONFIG = new Config("shared_backpack");

    public static void loadContents() {

        for (int slot = 0; slot < SHARED_BACKPACK_INVENTORY.getSize(); slot++) {
            String slotAsString = String.valueOf(slot);

            if (SHARED_BACKPACK_CONFIG.contains(slotAsString)) {
                Material material = Material.valueOf((String) SHARED_BACKPACK_CONFIG.get(slotAsString + ".material"));
                int count = (int) SHARED_BACKPACK_CONFIG.get(slotAsString + ".count");
                ItemStack item = new ItemStack(material, count);
                SHARED_BACKPACK_INVENTORY.setItem(slot, item);
            }
        }
    }

    public static void save() {

        for (int slot = 0; slot < SHARED_BACKPACK_INVENTORY.getSize(); slot++) {
            String slotAsString = String.valueOf(slot);

            if (SHARED_BACKPACK_INVENTORY.getItem(slot) != null) {
                try {
                    SHARED_BACKPACK_CONFIG.set(slotAsString + ".material", SHARED_BACKPACK_INVENTORY.getItem(slot).getType().name());
                    SHARED_BACKPACK_CONFIG.set(slotAsString + ".count", SHARED_BACKPACK_INVENTORY.getItem(slot).getAmount());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        try {
            SHARED_BACKPACK_CONFIG.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Inventory getInventory() {
        return SHARED_BACKPACK_INVENTORY;
    }
}