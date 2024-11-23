package tv.trashless.randomizer.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class SharedBackpack {
    private static final Inventory SHARED_BACKPACK = Bukkit.createInventory(null, 27, "Shared Backpack");
    private static final Config SHARED_BACKPACK_CONFIG = new Config("shared_backpack");

    public static void loadSharedBackpack() {
        for (int slot = 0; slot < SHARED_BACKPACK.getSize(); slot++) {
            String slotAsString = String.valueOf(slot);

            if (SHARED_BACKPACK_CONFIG.contains(slotAsString)) {
                Material material = Material.valueOf((String) SHARED_BACKPACK_CONFIG.get(slotAsString + ".material"));
                int count = (int) SHARED_BACKPACK_CONFIG.get(slotAsString + ".count");
                ItemStack item = new ItemStack(material, count);
                SHARED_BACKPACK.setItem(slot, item);
            }
        }
    }

    public static void saveBackpack() {
        for (int slot = 0; slot < SHARED_BACKPACK.getSize(); slot++) {
            String slotAsString = String.valueOf(slot);

            if (SHARED_BACKPACK.getItem(slot) != null) {
                try {
                    SHARED_BACKPACK_CONFIG.set(slotAsString + ".material", SHARED_BACKPACK.getItem(slot).getType().name());
                    SHARED_BACKPACK_CONFIG.set(slotAsString + ".count", SHARED_BACKPACK.getItem(slot).getAmount());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static Inventory getSharedBackpack() {
        return SHARED_BACKPACK;
    }
}
