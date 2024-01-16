package me.bobulo.bo3.command;

import me.bobulo.bo3.BO3CreatorPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AliasCommand extends Command {

    private final BO3CreatorPlugin plugin;

    private final Map<String, String> aliases = new HashMap<>();

    public AliasCommand(BO3CreatorPlugin plugin) {
        super("bo3creator");
        this.plugin = plugin;

        aliases.put("1", "bo3pos1");
        aliases.put("2", "bo3pos2");

        aliases.put("center", "bo3center");

        aliases.put("wand", "bo3wand");

        aliases.put("create", "bo3create");
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

        if (args.length == 0 || !aliases.containsKey(args[0])) {
            sender.sendMessage("§cUsage: /bo3create <1/2/center/wand/create>");
            return false;
        }

        Player player = (Player) sender;
        String command = aliases.get(args[0]);

        String argsCommand = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        StringBuilder commandBuilder = new StringBuilder(command);

        if (argsCommand.length() > 0) {
            commandBuilder.append(" ");
            commandBuilder.append(argsCommand);
        }

        player.performCommand(commandBuilder.toString());
        return false;
    }

}
