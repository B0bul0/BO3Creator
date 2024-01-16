package me.bobulo.bo3.command;

import me.bobulo.bo3.BO3CreatorPlugin;
import me.bobulo.bo3.util.Constant;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class WandCommand extends Command {

    private final BO3CreatorPlugin plugin;

    public WandCommand(BO3CreatorPlugin plugin) {
        super("bo3wand");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou must be a player to use this command!");
            return false;
        }

        if (!sender.hasPermission("bo3creator.create")) {
            sender.sendMessage("§cYou don't have permission to use this command!");
            return false;
        }

        Player player = (Player) sender;

        PlayerInventory inventory = player.getInventory();
        inventory.addItem(Constant.WAND_ITEM);

        sender.sendMessage(Constant.PREFIX + "§aYou got the §eBO3 Wand§a!");
        return false;
    }

}
