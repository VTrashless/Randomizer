package tv.trashless.randomizer.listeners;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import tv.trashless.randomizer.utils.Randomizer;

public class PlayerFishListener implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent fishEvent) {
        if (Randomizer.getBooleanSetting("randomize_fishing")) {
            if (fishEvent.getCaught() != null) {
                Item item = (Item) fishEvent.getCaught();

                if (Randomizer.getBooleanSetting("randomizer_type")) {
                    item.setItemStack(new ItemStack(Randomizer.getRandomizedItems().get(item.getItemStack().getType())));
                } else {
                    item.setItemStack(new ItemStack(Randomizer.getRandomItem()));
                }
            }
        }
    }
}
