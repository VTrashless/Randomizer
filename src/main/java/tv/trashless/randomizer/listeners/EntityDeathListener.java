package tv.trashless.randomizer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import tv.trashless.randomizer.utils.Randomizer;

import java.util.List;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onEntityDropItem(EntityDeathEvent entityDeathEvent) {
        if (Randomizer.getBooleanSetting("randomize_mob_drops")) {
            List<ItemStack> droppedItems = entityDeathEvent.getDrops();

            for (ItemStack droppedItem : droppedItems) {

                if (Randomizer.getBooleanSetting("randomizer_type")) {
                    droppedItem.setType(Randomizer.getRandomizedItems().get(droppedItem.getType()));
                } else {
                    droppedItem.setType(Randomizer.getRandomItem());
                }
            }
        }
    }
}
