package tv.trashless.randomizer.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import tv.trashless.randomizer.utils.Randomizer;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent breakEvent) {
        if (Randomizer.getBooleanSetting("randomize_block_drops")) {

            if (Randomizer.getBooleanSetting("randomizer_type")) {

                Block block = breakEvent.getBlock();
                Material material = block.getBlockData().getMaterial();
                int stackSize = Math.max(block.getDrops().size(), 1);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Randomizer.getRandomizedItems().get(material), stackSize));

            } else {

                Block block = breakEvent.getBlock();
                int stackSize = Math.max(block.getDrops().size(), 1);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Randomizer.getRandomItem(), stackSize));

            }
        }
    }
}
