package me.bobulo.bo3.listener;

import lombok.RequiredArgsConstructor;
import me.bobulo.bo3.BO3CreatorPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final BO3CreatorPlugin plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {

        // Remove player from cache
        plugin.getPlayerManager().removePlayer(event.getPlayer().getUniqueId());
    }

}
