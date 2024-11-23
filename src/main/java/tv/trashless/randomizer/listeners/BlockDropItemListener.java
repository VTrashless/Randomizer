package tv.trashless.randomizer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import tv.trashless.randomizer.utils.Settings;

public class BlockDropItemListener implements Listener {

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent blockDropItemEvent) {
        if (Settings.load("randomize_block_drops")) blockDropItemEvent.setCancelled(true);
    }
}
