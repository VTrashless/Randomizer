package tv.trashless.randomizer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent quitEvent) {
        quitEvent.setQuitMessage("§c<< §f" + quitEvent.getPlayer().getDisplayName());
    }
}