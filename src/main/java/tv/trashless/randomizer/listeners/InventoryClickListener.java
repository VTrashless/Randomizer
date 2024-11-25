package tv.trashless.randomizer.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tv.trashless.randomizer.Main;
import tv.trashless.randomizer.utils.Randomizer;
import tv.trashless.randomizer.inventories.SettingsGUI;

import java.util.Objects;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent clickEvent) {

        if (Objects.equals(clickEvent.getClickedInventory(), SettingsGUI.getInventory())) {
            clickEvent.setCancelled(true);

            switch (clickEvent.getSlot()) {

                case SettingsGUI.SLOT_BLOCK_DROPS -> {
                    Randomizer.toggleSetting("randomize_block_drops");
                }

                case SettingsGUI.SLOT_MOB_DROPS -> {
                    Randomizer.toggleSetting("randomize_mob_drops");
                }

                case SettingsGUI.SLOT_RANDOMIZER_TYPE -> {
                    Bukkit.broadcastMessage(Main.getPrefix() + "§7Toggled randomizer type! Now active: §9" + Randomizer.toggleActiveType());
                }

                case SettingsGUI.SLOT_RANDOMIZE -> {
                    Bukkit.broadcastMessage(Main.getPrefix() + "§7Randomizing items...");
                    Randomizer.createRandomizedItems();
                    Bukkit.broadcastMessage(Main.getPrefix() + "§7Finished randomizing items!");
                }
            }

            clickEvent.getWhoClicked().openInventory(SettingsGUI.getInventory());
        }
    }
}
