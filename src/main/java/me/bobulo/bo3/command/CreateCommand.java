package me.bobulo.bo3.command;

import me.bobulo.bo3.BO3CreatorPlugin;
import me.bobulo.bo3.model.BO3Location;
import me.bobulo.bo3.model.BO3Player;
import me.bobulo.bo3.model.BO3Structure;
import me.bobulo.bo3.util.Constant;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CreateCommand extends Command {

    private final BO3CreatorPlugin plugin;

    public CreateCommand(BO3CreatorPlugin plugin) {
        super("bo3create");
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

        if (args.length == 0) {
            sender.sendMessage("§cUsage: /bo3create <name>");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage("§cName must not contain spaces!");
            return false;
        }

        Player player = (Player) sender;
        String name = args[0];

        BO3Player bo3Player = plugin.getPlayerManager().getPlayer(player.getUniqueId());

        if (bo3Player == null) {
            sender.sendMessage(Constant.PREFIX + "§cYou must set the positions first!");
            return false;
        }

        if (bo3Player.getPos1() == null) {
            sender.sendMessage(Constant.PREFIX + "§cYou must set 1st position first!");
            return false;
        }

        if (bo3Player.getPos2() == null) {
            sender.sendMessage(Constant.PREFIX + "§cYou must set 2nd position first!");
            return false;
        }

        BO3Structure bo3Structure = BO3Structure.builder()
                .name(name)
                .pos1(bo3Player.getPos1())
                .pos2(bo3Player.getPos2())
                .center(bo3Player.getCenter() == null ? BO3Location.fromLocation(player.getLocation()) : bo3Player.getCenter())
                .build();

        try {

            File bo3File = plugin.getBo3CreatorWriter().createBO3(player, bo3Structure);

            sender.sendMessage(Constant.PREFIX + "§aCreated file: §b" + bo3File.getName());

        } catch (IllegalArgumentException e) {
            sender.sendMessage(Constant.PREFIX + "§cAlready exists a BO3 with this name!");
        } catch (IOException e) {
            e.printStackTrace();
            sender.sendMessage(Constant.PREFIX + "§cAn error occurred while creating the BO3!");
        }

        return false;
    }

}
