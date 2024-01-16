package me.bobulo.bo3.command;

import me.bobulo.bo3.BO3CreatorPlugin;
import me.bobulo.bo3.model.BO3Location;
import me.bobulo.bo3.model.BO3Player;
import me.bobulo.bo3.util.Constant;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CenterCommand extends Command {

    private final BO3CreatorPlugin plugin;

    public CenterCommand(BO3CreatorPlugin plugin) {
        super("bo3center");
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

        BO3Player bo3Player = plugin.getPlayerManager().getOrRegisterPlayer(player.getUniqueId());
        BO3Location bo3Location = BO3Location.fromLocation(player.getLocation());

        bo3Player.setCenter(bo3Location);
        sender.sendMessage(Constant.PREFIX + "§aSet §fcenter§a to §e" + bo3Location + "§a.");

        return false;
    }

}
