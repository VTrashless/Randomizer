package tv.trashless.randomizer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

public class BlockDropItemListener implements Listener {

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent blockDropItemEvent) {
        blockDropItemEvent.setCancelled(true);
    }
}
