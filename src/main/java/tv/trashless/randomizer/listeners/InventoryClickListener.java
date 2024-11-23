package tv.trashless.randomizer.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tv.trashless.randomizer.Main;
import tv.trashless.randomizer.utils.Randomizer;
import tv.trashless.randomizer.utils.Settings;
import tv.trashless.randomizer.utils.SettingsInventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent clickEvent) {
        if (clickEvent.getClickedInventory().equals(SettingsInventory.getInventory())) {
            clickEvent.setCancelled(true);

            switch (clickEvent.getSlot()) {

                case SettingsInventory.SLOT_BLOCK_DROPS -> {
                    Settings.save("randomize_block_drops", !Settings.load("randomize_block_drops"));
                }

                case SettingsInventory.SLOT_MOB_DROPS -> {
                    Settings.save("randomize_mob_drops", !Settings.load("randomize_mob_drops"));
                }

                case SettingsInventory.SLOT_RANDOMIZE -> {
                    Bukkit.broadcastMessage(Main.getPrefix() + "§7Randomizing items...");
                    Randomizer.createRandomizedItems();
                    Bukkit.broadcastMessage(Main.getPrefix() + "§7Finished randomizing items!");
                }
            }

            clickEvent.getWhoClicked().openInventory(SettingsInventory.getInventory());
        }
    }
}
