package me.bobulo.bo3.listener;

import lombok.RequiredArgsConstructor;
import me.bobulo.bo3.BO3CreatorPlugin;
import me.bobulo.bo3.model.BO3Location;
import me.bobulo.bo3.model.BO3Player;
import me.bobulo.bo3.util.Constant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class WandListener implements Listener {

    private final BO3CreatorPlugin plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();

        // Check if player has wand item in hand
        if (!Constant.WAND_ITEM.isSimilar(itemInHand)) {
            return;
        }

        // Check if player has permission
        if (!player.hasPermission("bo3creator.create")) {
            return;
        }

        event.setCancelled(true);

        Action action = event.getAction();

        BO3Player bo3Player = plugin.getPlayerManager().getOrRegisterPlayer(player.getUniqueId());

        // Set 1st position
        if (action == Action.LEFT_CLICK_BLOCK) {
            BO3Location bo3Location = BO3Location.fromBlock(event.getClickedBlock());
            bo3Player.setPos1(bo3Location);

            player.sendMessage(Constant.PREFIX + "§aSet pos §f1§a to §e" + bo3Location + "§a.");
            return;
        }

        // Set 2nd position
        if (action == Action.RIGHT_CLICK_BLOCK) {
            BO3Location bo3Location = BO3Location.fromBlock(event.getClickedBlock());
            bo3Player.setPos2(bo3Location);

            player.sendMessage(Constant.PREFIX + "§aSet pos §f2§a to §e" + bo3Location + "§a.");
        }
    }

}
