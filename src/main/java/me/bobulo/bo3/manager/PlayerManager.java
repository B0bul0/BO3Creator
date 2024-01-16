package me.bobulo.bo3.manager;

import me.bobulo.bo3.model.BO3Player;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private final Map<UUID, BO3Player> playerMap = new HashMap<>();

    public BO3Player getPlayer(@NotNull Player player) {
        return getPlayer(player.getUniqueId());
    }

    public BO3Player getPlayer(@NotNull UUID uuid) {
        return playerMap.get(uuid);
    }

    @NotNull
    public BO3Player getOrRegisterPlayer(@NotNull UUID playerUUID) {
        return playerMap.computeIfAbsent(playerUUID, uuid -> new BO3Player(playerUUID));
    }

    public void registerPlayer(UUID playerUUID) {
        BO3Player bo3Player = new BO3Player(playerUUID);

        playerMap.put(playerUUID, bo3Player);
    }

    public void removePlayer(UUID playerUUID) {
        playerMap.remove(playerUUID);
    }

    public void clear() {
        playerMap.clear();
    }

}
